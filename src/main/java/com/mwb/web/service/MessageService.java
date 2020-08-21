package com.mwb.web.service;

import com.github.pagehelper.PageInfo;
import com.mwb.web.model.MessageInfo;
import com.mwb.web.model.query.MsgQuery;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface MessageService extends BaseService<MessageInfo> {

    int readAll(long userId);

    int unReadNum(long userId);

    boolean sendMsg(MessageInfo messageInfo);

    PageInfo<MessageInfo> search(MsgQuery query);

    boolean batchSend(MessageInfo messageInfo);

}