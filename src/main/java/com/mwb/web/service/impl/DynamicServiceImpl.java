package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.DynamicInfo;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.model.query.DynamicQuery;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.PraiseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    public PageInfo<DynamicInfo> search(DynamicQuery query, long userId) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(DynamicInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (query.getId() != null) {
            criteria.andEqualTo("id", query.getId());
        }
        if (query.getStatus() > -1) {
            criteria.andEqualTo("status", query.getStatus());
        }
        if (query.getUserId() != null) {
            criteria.andEqualTo("userId", query.getUserId());
        }
        if (StringUtils.isNotBlank(query.getUserName())) {
            criteria.andEqualTo("userName", query.getUserName());
        }
        if (StringUtils.isNotBlank(query.getTitle())) {
            criteria.andEqualTo("title", "%" + query.getUserName() + "%");
        }
        example.orderBy(query.getOrder()).desc();

        //点赞评论
        List<DynamicInfo> dynamicInfos = selectByExample(example);
        if (CollectionUtils.isNotEmpty(dynamicInfos) && query.isShowComment()) {
            List<Long> ids = dynamicInfos.stream().map(DynamicInfo::getId).collect(Collectors.toList());
            List<CommentInfo> commentInfos = commentService.searchById(ids);
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
    public List<DynamicInfo> getNewList(PageQuery query) {
        return pageSearch(query, DynamicInfo.class);
    }

    @Override
    public DynamicInfo saveOrUpdate(DynamicInfo articleInfo) {
        articleInfo.setUpdateTime(new Date());
        if (articleInfo.getId() > 0) {
            updateNotNull(articleInfo);
        } else {//新建
            articleInfo.setAddTime(new Date());
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

    @Override
    public void updateCommentNum(long id) {
        DynamicInfo dynamicInfo = selectByKey(id);
        if (dynamicInfo != null) {
            dynamicInfo.setPraiseNum(dynamicInfo.getPraiseNum() + 1);
            saveOrUpdate(dynamicInfo);
        }
    }

    @Override
    public void audit(long id, int status, boolean sendMsg) {
        DynamicInfo dynamicInfo = selectByKey(id);
        if (dynamicInfo != null && status != dynamicInfo.getStatus()) {
            dynamicInfo.setStatus(status);
            updateNotNull(dynamicInfo);
            if (sendMsg) {
                //todo
            }
        }
    }
}