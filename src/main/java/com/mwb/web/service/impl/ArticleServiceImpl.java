package com.mwb.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.ArticleMapper;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageInfo<ArticleInfo> search(PageQuery query) {
        return new PageInfo<>(pageSearch(query, ArticleInfo.class));
    }

    @Override
    public List<ArticleInfo> hot(int pageSize) {
        return pageSearch(new PageQuery(pageSize, "praiseNum"), ArticleInfo.class);
    }

    @Override
    public ArticleInfo saveOrUpdate(ArticleInfo articleInfo) {
        articleInfo.setAddTime(new Date());
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            saveNotNull(articleInfo);
        }
        return articleInfo;
    }

}