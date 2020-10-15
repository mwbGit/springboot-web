package com.mwb.web.model;

import com.alibaba.fastjson.JSON;
import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/7
 */
@Data
@ToString
@Table(name = "dynamic_info")
public class DynamicInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "head_img")
    private String headImg;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "images")
    private String images;

    @Column(name = "praise_num")
    private int praiseNum;

    /**
     * 0 未审核 1审核通过 2驳回
     */
    @Column(name = "status")
    private int status;

    @Transient
    private List<CommentInfo> comments;

    @Transient
    private boolean praised;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(getAddTime());
    }

    public int getCommentNum() {
        return comments != null ? comments.size() : 0;
    }

    public List<String> getImageUrls() {
        if (StringUtils.isNotBlank(images)) {
            return JSON.parseArray(images, String.class);
        }
        return Collections.emptyList();
    }

    public String getOneImageUrl() {
        List<String> urls = getImageUrls();
        return urls.size() > 0 ? urls.get(0) : "";
    }

    public void setImages(String[] strings) {
        if (strings == null) {
            strings = new String[]{};
        }
        this.images = JSON.toJSONString(strings);
    }

    public void setImages(String images) {
        this.images = images;
    }
}
