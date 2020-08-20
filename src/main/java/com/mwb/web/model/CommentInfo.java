package com.mwb.web.model;

import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
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
@Data
@Table(name = "comment_info")
public class CommentInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "dynamic_id")
    private long dynamicId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "head_img")
    private String headImg;

    @Column(name = "content")
    private String content;

    @Column(name = "reply_name")
    private String replyName;

    @Column(name = "praise_num")
    private int praiseNum;

    /**
     * 0 未审核 1审核通过 2驳回
     */
    @Column(name = "status")
    private int status;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(addTime);
    }
}
