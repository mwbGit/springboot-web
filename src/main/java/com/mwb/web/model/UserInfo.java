package com.mwb.web.model;

import com.mwb.web.model.common.UserRobot;
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

    @Column(name = "introduce")
    private String introduce;

    /**
     * 0 女 1 男
     */
    @Column(name = "sex")
    private int sex;

    @Column(name = "password")
    private String password;

    /**
     * 0 待审核 1 有效 2 驳回 3 冻结
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

    public boolean unPass() {
        return status == 3;
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

    public String getSexDesc() {
        return sex == 0 ? "女" : "男";
    }

    public String getStatusDesc() {
        switch (status) {
            case 0:
                return "待审核";
            case 1:
                return "审核通过";
            case 2:
                return "审核驳回";
            case 3:
                return "冻结";
            default:
                return "";
        }
    }

    public boolean isAdmin() {
        return identity == 1;
    }

    public String getAvatar() {
        return StringUtils.isNotEmpty(headImg) ? headImg : UserRobot.DEFAULT_AVATAR;
    }
}