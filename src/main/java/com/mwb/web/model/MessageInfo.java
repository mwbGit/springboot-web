package com.mwb.web.model;

import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/7
 */
@ToString
@Data
@Table(name = "message_info")
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "object_id")
    private long objectId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    /**
     * 类型 0 系统 1 动态
     */
    @Column(name = "type")
    private int type;

    /**
     * 状态 0:未读 1:已读
     */
    @Column(name = "status")
    private int status;

    /**
     * 状态 0:未删除 1:已删除
     */
    @Column(name = "deleted")
    private int deleted;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    public String getTypeDesc() {
        switch (type) {
            case 0:
                return "系统通知";
            case 1:
                return "动态消息";
            default:
                return "其他消息";
        }
    }


    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(addTime);
    }
}
