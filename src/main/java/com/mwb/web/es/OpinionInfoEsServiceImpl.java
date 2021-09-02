package com.mwb.web.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.*;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2021/3/4 9:19 下午
 */
@Slf4j
@Service("opinionInfoEsService")
public class OpinionInfoEsServiceImpl implements OpinionInfoEsService, InitializingBean {

    @Resource(name = "elasticsearchOperations")
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public boolean save(ArticleEO opinionInfoEO) {
        IndexQuery indexQuery = getIndexQuery(opinionInfoEO);
        String documentId = elasticsearchRestTemplate.index(indexQuery);
        return StringUtils.isNotBlank(documentId);
    }

    @Override
    public boolean delete(long id) {
        String documentId = String.valueOf(id);
        return elasticsearchRestTemplate.delete(ArticleEO.class, documentId).equals(documentId);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        DeleteQuery deleteQuery = new DeleteQuery();
        String[] idStrs = new String[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idStrs[i] = String.valueOf(ids.get(i));
        }
        deleteQuery.setQuery(QueryBuilders.idsQuery().addIds(idStrs));
        elasticsearchRestTemplate.delete(deleteQuery, ArticleEO.class);
    }

    @Override
    public void batchSave(List<ArticleEO> articleEOS) {
        List<IndexQuery> indexQueries = articleEOS.stream().map(this::getIndexQuery).collect(Collectors.toList());
        elasticsearchRestTemplate.bulkIndex(indexQueries);
    }

    @Override
    public long countByEsQuery(ArticleEsQuery articleEsQuery) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(getQueryBuilder(articleEsQuery));
        return elasticsearchRestTemplate.count(nativeSearchQueryBuilder.build(), ArticleEO.class);
    }

    @Override
    public Map<String, Long> normalGroupByEsQuery(ArticleEsQuery articleEsQuery) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(articleEsQuery.getGroupKey()).field(articleEsQuery.getGroupKey()).size(Integer.MAX_VALUE));
        nativeSearchQueryBuilder.withQuery(getQueryBuilder(articleEsQuery));
        nativeSearchQueryBuilder.withIndices(OpinionEsConstants.OPINION_INDEX_NAME);
        nativeSearchQueryBuilder.withTypes(OpinionEsConstants.OPINION_INDEX_TYPE);
        Aggregations aggregations = elasticsearchRestTemplate.query(nativeSearchQueryBuilder.build(), SearchResponse::getAggregations);
        ParsedStringTerms terms = aggregations.get(articleEsQuery.getGroupKey());
        if (CollectionUtils.isEmpty(terms.getBuckets())) {
            return Collections.emptyMap();
        }
        return terms.getBuckets().stream().collect(Collectors.toMap(Terms.Bucket::getKeyAsString, Terms.Bucket::getDocCount));
    }

    @Override
    public Map<String, Map<String, Long>> highGroupByEsQuery(ArticleEsQuery articleEsQuery) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        TermsAggregationBuilder parent = AggregationBuilders.terms(articleEsQuery.getParentGroupKey()).field(articleEsQuery.getParentGroupKey()).size(Integer.MAX_VALUE);
        TermsAggregationBuilder child = AggregationBuilders.terms(articleEsQuery.getChildGroupKey()).field(articleEsQuery.getChildGroupKey()).size(Integer.MAX_VALUE);
        parent.subAggregation(child);
        nativeSearchQueryBuilder.addAggregation(parent);
        nativeSearchQueryBuilder.withQuery(getQueryBuilder(articleEsQuery));
        nativeSearchQueryBuilder.withIndices(OpinionEsConstants.OPINION_INDEX_NAME);
        nativeSearchQueryBuilder.withTypes(OpinionEsConstants.OPINION_INDEX_TYPE);
        Aggregations aggregations = elasticsearchRestTemplate.query(nativeSearchQueryBuilder.build(), SearchResponse::getAggregations);
        ParsedLongTerms parentTerms = aggregations.get(articleEsQuery.getParentGroupKey());
        if (CollectionUtils.isEmpty(parentTerms.getBuckets())) {
            return Collections.emptyMap();
        }
        return parentTerms.getBuckets().stream().collect(Collectors.toMap(Terms.Bucket::getKeyAsString, parentBucket -> {
            ParsedTerms childTerms = parentBucket.getAggregations().get(articleEsQuery.getChildGroupKey());
            if (CollectionUtils.isEmpty(childTerms.getBuckets())) {
                return Collections.emptyMap();
            }
            return childTerms.getBuckets().stream().collect(Collectors.toMap(Terms.Bucket::getKeyAsString, Terms.Bucket::getDocCount));
        }));
    }

    @Override
    public Page<ArticleEO> searchNormalPage(ArticleEsQuery articleEsQuery) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(getQueryBuilder(articleEsQuery));
        nativeSearchQueryBuilder.withPageable(PageRequest.of((articleEsQuery.getPage() - 1), articleEsQuery.getPageSize()))
                .withSort(SortBuilders.fieldSort(articleEsQuery.getSortKey()).order(articleEsQuery.isSortDesc() ? SortOrder.DESC : SortOrder.ASC));
        Page<ArticleEO> page = elasticsearchRestTemplate.queryForPage(nativeSearchQueryBuilder.build(), ArticleEO.class);
        return page;
    }

    @Override
    public List<ArticleEO> getAll(ArticleEsQuery articleEsQuery) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(getQueryBuilder(articleEsQuery));
        nativeSearchQueryBuilder.withPageable(PageRequest.of((articleEsQuery.getPage() - 1), articleEsQuery.getPageSize()))
                .withSort(SortBuilders.fieldSort(articleEsQuery.getSortKey()).order(articleEsQuery.isSortDesc() ? SortOrder.DESC : SortOrder.ASC));
        ScrolledPage<ArticleEO> scroll = elasticsearchRestTemplate.startScroll(5000, nativeSearchQueryBuilder.build(), ArticleEO.class);
        System.out.println("查询总命中数：" + scroll.getTotalElements());
        List<ArticleEO> result = new ArrayList<>();
        while (scroll.hasContent()) {
            result.addAll(scroll.getContent());
            //取下一页，scrollId在es服务器上可能会发生变化，需要用最新的。发起continueScroll请求会重新刷新快照保留时间
            scroll = elasticsearchRestTemplate.continueScroll(scroll.getScrollId(), 5000, ArticleEO.class);
        }
        //及时释放es服务器资源
        elasticsearchRestTemplate.clearScroll(scroll.getScrollId());
        return result;
    }

    @Override
    public List<String> getTitleSearchSuggest(String keyWord) {
        List<String> suggests = new ArrayList<>(8);
        try {
            SuggestionBuilder titleSuggestBuilder = SuggestBuilders.completionSuggestion("title").prefix(keyWord).skipDuplicates(true).size(1);
            SuggestionBuilder companySuggestBuilder = SuggestBuilders.completionSuggestion("company").prefix(keyWord).skipDuplicates(true).size(1);
            SuggestBuilder suggestBuilder = new SuggestBuilder();
            String titleSuggest = "titleSuggest";
            String companyNameSuggest = "companyNameSuggest";
            suggestBuilder.addSuggestion(titleSuggest, titleSuggestBuilder)
                    .addSuggestion(companyNameSuggest, companySuggestBuilder);
            SearchResponse searchResponse = elasticsearchRestTemplate.suggest(suggestBuilder, ArticleEO.class);
            List<? extends Suggest.Suggestion.Entry.Option> options = searchResponse.getSuggest().getSuggestion(companyNameSuggest).getEntries().get(0).getOptions();
            if (CollectionUtils.isNotEmpty(options)) {
                options.forEach(workContent -> {
                    suggests.add(workContent.getText().string());
                });
            }

        } catch (Exception e) {
        }
        return suggests;
    }

    @Override
    public List<String> getTitleSuggest(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Collections.EMPTY_LIST;
        }
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withIndices(OpinionEsConstants.OPINION_INDEX_NAME);
        builder.withTypes(OpinionEsConstants.OPINION_INDEX_TYPE);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.filter(QueryBuilders.termQuery("userId", userId));
//        boolQueryBuilder.filter(QueryBuilders.termsQuery("poolStatus", new int[]{0, 1}));
        boolQueryBuilder.filter(QueryBuilders.wildcardQuery("title", String.format("*%s*", keyword)));
        builder.withQuery(boolQueryBuilder);
        List<ArticleEO> articleEOS = elasticsearchRestTemplate.queryForList(builder.build(), ArticleEO.class);
        List<String> titles = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(articleEOS)) {
            titles = articleEOS.stream().map(ArticleEO::getTitle).collect(Collectors.toList());
        }
        return titles;
    }

    private IndexQuery getIndexQuery(ArticleEO opinionInfoEO) {
        return new IndexQueryBuilder()
                .withObject(opinionInfoEO)
                .build();
    }

    private QueryBuilder getQueryBuilder(ArticleEsQuery articleEsQuery) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (CollectionUtils.isNotEmpty(articleEsQuery.getStatus())) {
            queryBuilder.filter(QueryBuilders.termsQuery("status", articleEsQuery.getStatus()));
        }
        if (articleEsQuery.getAddBeginTime() != null) {
            queryBuilder.filter(QueryBuilders.rangeQuery("addTime").gte(articleEsQuery.getAddBeginTime()));
        }
        if (articleEsQuery.getAddEndTime() != null) {
            queryBuilder.filter(QueryBuilders.rangeQuery("addTime").lte(articleEsQuery.getAddEndTime()));
        }
        if (CollectionUtils.isNotEmpty(articleEsQuery.getLabelIds())) {
            queryBuilder.filter(QueryBuilders.termsQuery("labelIds", articleEsQuery.getLabelIds()));
        }
        if (articleEsQuery.getChannelId() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("channelId", articleEsQuery.getChannelId()));
        }
        if (StringUtils.isNotBlank(articleEsQuery.getTitle())) {
            String wildCardWord = String.format("*%s*", articleEsQuery.getTitle());
            queryBuilder.filter(QueryBuilders.wildcardQuery("title", wildCardWord));
        }
        if (StringUtils.isNotBlank(articleEsQuery.getCompany())) {
            String wildCardWord = String.format("*%s*", articleEsQuery.getCompany());
            queryBuilder.filter(QueryBuilders.wildcardQuery("company", wildCardWord));
        }
        if (articleEsQuery.isUidAndPhoneMatchOne()) {
            BoolQueryBuilder uidAndPhoneBuilder = QueryBuilders.boolQuery();
            if (StringUtils.isNotBlank(articleEsQuery.getPhone())) {
                uidAndPhoneBuilder.should(QueryBuilders.termQuery("phone", articleEsQuery.getPhone()));
            }
            if (articleEsQuery.getUid() > 0) {
                uidAndPhoneBuilder.should(QueryBuilders.termQuery("uid", articleEsQuery.getUid()));
            }
            queryBuilder.filter(uidAndPhoneBuilder);
        } else {
            if (StringUtils.isNotBlank(articleEsQuery.getPhone())) {
                queryBuilder.filter(QueryBuilders.termQuery("phone", articleEsQuery.getPhone()));
            }
            if (articleEsQuery.getUid() > 0) {
                queryBuilder.filter(QueryBuilders.termQuery("uid", articleEsQuery.getUid()));
            }
        }
        if (articleEsQuery.getLevel() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("level", articleEsQuery.getLevel()));
        }
        if (articleEsQuery.getParentTypeId() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("parentTypeId", articleEsQuery.getParentTypeId()));
        }
        if (articleEsQuery.getTypeId() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("typeId", articleEsQuery.getTypeId()));
        }
        if (articleEsQuery.getEffect() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("effect", articleEsQuery.getEffect()));
        }
        if (articleEsQuery.getEmotion() > 0) {
            queryBuilder.filter(QueryBuilders.termQuery("emotion", articleEsQuery.getEmotion()));
        }
        if (StringUtils.isNotBlank(articleEsQuery.getUserName())) {
            queryBuilder.filter(QueryBuilders.termQuery("updateUserName", articleEsQuery.getUserName()));
        }
        return queryBuilder;
    }

    @Override
    public void afterPropertiesSet() {
        elasticsearchRestTemplate.createIndex(ArticleEO.class);
        elasticsearchRestTemplate.putMapping(ArticleEO.class);
    }


}
