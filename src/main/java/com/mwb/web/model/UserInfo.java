package com.mwb.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mwb.web.model.common.BaseBean;
import com.mwb.web.model.common.UserRobot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class UserInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int identity;

    @Transient
    private int unReadMsgNum;

    public UserInfo(long id, String name, String headImg) {
        this.id = id;
        this.name = name;
        this.headImg = headImg;
    }

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
        return StringUtils.isNotEmpty(headImg) ? headImg : UserRobot.DEFAULT.getHeadImg();
    }
}