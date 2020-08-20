package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.PetInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.PraiseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("dynamicService")
public class DynamicServiceImpl extends BaseServiceImpl<DynamicInfo> implements DynamicService {

    @Autowired
    private PraiseService praiseService;

    @Autowired
    private CommentService commentService;

    @Override
    public PageInfo<DynamicInfo> search(PageQuery query, long userId) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(DynamicInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        example.orderBy("id").desc();
        List<DynamicInfo> dynamicInfos = selectByExample(example);
        if (CollectionUtils.isNotEmpty(dynamicInfos)) {
            List<Long> ids = dynamicInfos.stream().map(DynamicInfo::getId).collect(Collectors.toList());
            List<CommentInfo> commentInfos = commentService.search(ids);
            List<Long> praiseIds = praiseService.search(ids, userId, 0);
            if (CollectionUtils.isNotEmpty(commentInfos)) {
                Map<Long, List<CommentInfo>> commentMap = new HashMap<>(commentInfos.size());
                for (CommentInfo commentInfo : commentInfos) {
                    if (commentMap.containsKey(commentInfo.getDynamicId())) {
                        commentMap.get(commentInfo.getDynamicId()).add(commentInfo);
                    } else {
                        List<CommentInfo> temp = new ArrayList<>(1);
                        temp.add(commentInfo);
                        commentMap.put(commentInfo.getDynamicId(), temp);
                    }
                }
                for (DynamicInfo dynamicInfo : dynamicInfos) {
                    if (commentMap.containsKey(dynamicInfo.getId())) {
                        dynamicInfo.setComments(commentMap.get(dynamicInfo.getId()));
                    }
                    dynamicInfo.setPraised(praiseIds.contains(dynamicInfo.getId()));
                }
            }
        }


        return new PageInfo<>(dynamicInfos);
    }

    @Override
    public List<DynamicInfo> getNewList(int pageSize) {
        PageHelper.startPage(1, pageSize);
        Example example = new Example(PetInfo.class);
        //排序
        example.orderBy("add_time").desc();
        List<DynamicInfo> dynamicInfos = selectByExample(example);
        return dynamicInfos;
    }

    @Override
    public DynamicInfo saveOrUpdate(DynamicInfo articleInfo) {
        articleInfo.setAddTime(new Date());
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            saveNotNull(articleInfo);
        }
        return articleInfo;
    }

    @Override
    public void updatePraiseNum(long id, boolean addUp) {
        DynamicInfo dynamicInfo = selectByKey(id);
        if (dynamicInfo != null) {
            dynamicInfo.setPraiseNum(Math.max(0, addUp ? dynamicInfo.getPraiseNum() + 1 : dynamicInfo.getPraiseNum() - 1));
            saveOrUpdate(dynamicInfo);
        }
    }
}