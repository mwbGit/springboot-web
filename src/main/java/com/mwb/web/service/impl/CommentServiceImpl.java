package com.mwb.web.service.impl;

import com.mwb.web.mapper.CommentMapper;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.service.CommentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<CommentInfo> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentInfo> search(List<Long> dynamicIds) {
        if (CollectionUtils.isEmpty(dynamicIds)) {
            return Collections.emptyList();
        }
        Example example = new Example(CommentInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("dynamicId", dynamicIds);
        criteria.andEqualTo("status", 1);
        //排序
        example.orderBy("id").desc();
        return selectByExample(example);
    }

    @Override
    public CommentInfo saveOrUpdate(CommentInfo articleInfo) {
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