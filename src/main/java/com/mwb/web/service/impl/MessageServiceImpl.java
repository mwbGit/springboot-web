package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mwb.web.mapper.MessageMapper;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.query.MsgQuery;
import com.mwb.web.service.MessageService;
import com.mwb.web.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<MessageInfo> implements MessageService {

    @Autowired
    private UserInfoService userService;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int readAll(long userId) {
        userService.updateCache(userId);
        return messageMapper.updateStatus(userId);
    }

    @Override
    public int unReadNum(long userId) {
        return messageMapper.countStatus(userId);
    }

    @Override
    public boolean sendMsg(MessageInfo messageInfo) {
        if (messageInfo == null) {
            return false;
        }
        saveNotNull(messageInfo);
        userService.updateCache(messageInfo.getUserId());
        return false;
    }

    @Override
    public PageInfo<MessageInfo> search(MsgQuery query) {
        //分页
        PageHelper.startPage(query.getPage(), query.getPageSize());
        Example example = new Example(MessageInfo.class);
        //条件查询
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", query.getUserId());
        if (query.getType() >= 0) {
            criteria.andEqualTo("type", query.getType());
        }
        if (query.getStatus() != null) {
            criteria.andEqualTo("status", query.getStatus());
        }
        //排序
        example.orderBy("id").desc();
        List<MessageInfo> users = selectByExample(example);
        return new PageInfo<>(users);
    }

    @Override
    public boolean batchSend(MessageInfo messageInfo) {
        if (messageInfo.getUserId() > 0) {
            sendMsg(messageInfo);
            return true;
        }
        List<Long> userIds = userService.getValidIds();
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (Long userId : userIds) {
                messageInfo.setId(0);
                messageInfo.setUserId(userId);
                sendMsg(messageInfo);
            }
        }
        return true;
    }
}