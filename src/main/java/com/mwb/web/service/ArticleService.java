package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.ArticleInfo;
import com.mwb.web.model.query.ArticleQuery;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface ArticleService extends BaseService<ArticleInfo> {

    PageInfo<ArticleInfo> search(ArticleQuery userQuery);

    List<ArticleInfo> hot(int pageSize);

    ArticleInfo saveOrUpdate(ArticleInfo articleInfo);


}