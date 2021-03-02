package com.mwb.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.CommentMapper;
import com.mwb.web.model.CommentInfo;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.query.CommentQuery;
import com.mwb.web.service.CommentService;
import com.mwb.web.service.DynamicService;
import com.mwb.web.service.MessageService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private MessageService messageService;

    @Override
    public PageInfo<CommentInfo> search(CommentQuery query) {
        Example example = new Example(CommentInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        if (query.getDynamicId() != null) {
            criteria.andEqualTo("dynamicId", query.getDynamicId());
        }
        if (query.getDynamicIds() != null) {
            criteria.andIn("dynamicId", query.getDynamicIds());
        }
        if (query.getStatus() != null) {
            criteria.andEqualTo("status", query.getStatus());
        }
        if (query.getUserId() != null) {
            criteria.andEqualTo("userId", query.getUserId());
        }
        if (StringUtils.isNotBlank(query.getUserName())) {
            criteria.andEqualTo("userName", query.getUserName());
        }
        //排序
        example.orderBy(query.getOrder()).desc();
        return new PageInfo<>(selectByExample(example));
    }

    @Override
    public List<CommentInfo> searchById(List<Long> dynamicIds) {
        if (CollectionUtils.isEmpty(dynamicIds)) {
            return Collections.emptyList();
        }
        CommentQuery query = new CommentQuery();
        query.setDynamicIds(dynamicIds);
        query.setStatus(1);
        return search(query).getList();
    }

    @Override
    public CommentInfo saveOrUpdate(CommentInfo commentInfo) {
        commentInfo.setAddTime(new Date());
        commentInfo.setUpdateTime(new Date());
        if (commentInfo.getId() > 0) {
            updateNotNull(commentInfo);
        } else {//新建
            saveNotNull(commentInfo);
        }
        return commentInfo;
    }

    @Override
    public void audit(long id, int status, String reason) {
        CommentInfo commentInfo = selectByKey(id);
        if (commentInfo != null && commentInfo.getStatus() != status) {
            commentInfo.setStatus(status);
            updateNotNull(commentInfo);

            if (status == 1) {
                long userId = dynamicService.updateCommentNum(commentInfo.getDynamicId());
                if (userId > 0) {
                    MessageInfo messageInfo = new MessageInfo();
                    messageInfo.setUserId(userId);
                    messageInfo.setType(1);
                    messageInfo.setTitle(commentInfo.getUserName() + " 评论了您的动态");
                    messageInfo.setBody(commentInfo.getContent());
                    messageInfo.setAddTime(new Date());
                    messageService.sendMsg(messageInfo);
                }
            } else if (StringUtils.isNotBlank(reason)) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setUserId(commentInfo.getUserId());
                messageInfo.setType(0);
                messageInfo.setTitle("评论被驳回");
                messageInfo.setBody("原因：" + reason);
                messageInfo.setAddTime(new Date());
                messageService.sendMsg(messageInfo);
            }
        }
    }
}