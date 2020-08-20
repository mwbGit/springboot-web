package com.mwb.web.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;
    private static final String DEFAULT_AVATAR = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2078562713,2600960194&fm=26&gp=0.jpg";

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "account")
    private String account;

    @Column(name = "name")
    private String name;

    @Column(name = "wechat")
    private String wechat;

    @Column(name = "head_img")
    private String headImg;

    /**
     * 0 女 1 男
     */
    @Column(name = "sex")
    private int sex;

    @Column(name = "password")
    private String password;

    /**
     * 0 待审核 1 有效 2冻结
     */
    @Column(name = "status")
    private int status;

    /**
     * 0 普通用户 1 管理员
     */
    @Column(name = "identity")
    private int identity;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private int unReadMsgNum;

    public boolean isFrozen() {
        return status == 2;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", identity=" + identity +
                '}';
    }

    public String getAvatar() {
        return StringUtils.isNotEmpty(headImg) ? headImg : DEFAULT_AVATAR;
    }
}