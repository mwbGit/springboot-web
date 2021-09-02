package com.mwb.web.es;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/3/29 8:46 下午
 */
public interface OpinionInfoEsService {

    /**
     * 保存
     *
     * @param opinionInfoEO
     * @return
     */
    boolean save(ArticleEO opinionInfoEO);

    /**
     * 批量保存
     *
     * @param articleEOS
     */
    void batchSave(List<ArticleEO> articleEOS);

    /**
     * 批量删除
     *
     * @param ids
     */
    void batchDelete(List<Long> ids);

    /**
     * 删除
     *
     * @param processId
     * @return
     */
    boolean delete(long processId);

    long countByEsQuery(ArticleEsQuery articleEsQuery);

    /**
     * 普通分页查询
     *
     * @param articleEsQuery
     * @return
     */
    Page<ArticleEO> searchNormalPage(ArticleEsQuery articleEsQuery);

    /**
     * 返回全量
     * 游标方式
     *
     * @param articleEsQuery
     * @return
     */
    List<ArticleEO> getAll(ArticleEsQuery articleEsQuery);

    /**
     * 聚合分组
     *
     * @param articleEsQuery
     * @return
     */
    Map<String, Long> normalGroupByEsQuery(ArticleEsQuery articleEsQuery);

    /**
     * 二级分组
     *
     * @param articleEsQuery
     * @return
     */
    Map<String, Map<String, Long>> highGroupByEsQuery(ArticleEsQuery articleEsQuery);

    /**
     * 组合Suggest
     *
     * @param keyWord
     * @return
     */
    List<String> getTitleSearchSuggest(String keyWord);

    /**
     * 简单Suggest
     *
     * @param keyword
     * @return
     */
    List<String> getTitleSuggest(String keyword);
}
