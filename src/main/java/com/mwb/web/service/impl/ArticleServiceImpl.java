package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.ArticleMapper;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.query.ArticleQuery;
import com.mwb.web.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<ArticleInfo> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<ArticleInfo> search(ArticleQuery query) {
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(ArticleInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (query.getId() != null) {
            criteria.andEqualTo("id", query.getId());
        }
        if (query.getPetId() != null) {
            criteria.andEqualTo("petId", query.getPetId());
        }
        if (StringUtils.isNotBlank(query.getTitle())) {
            criteria.andEqualTo("title", "%" + query.getTitle() + "%");
        }

        //排序
        example.orderBy(query.getOrder()).desc();
        return new PageInfo<>(mapper.selectByExample(example));
    }

    @Override
    public List<ArticleInfo> hot(int pageSize) {
        return pageSearch(new PageQuery(pageSize, "praiseNum"), ArticleInfo.class);
    }

    @Override
    public ArticleInfo saveOrUpdate(ArticleInfo articleInfo) {
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            articleInfo.setAddTime(new Date());
            saveNotNull(articleInfo);
        }
        return articleInfo;
    }

}