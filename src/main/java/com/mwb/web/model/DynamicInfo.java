package com.mwb.web.model;

import com.alibaba.fastjson.JSON;
import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
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
@Table(name = "dynamic_info")
public class DynamicInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

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

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private List<CommentInfo> comments;

    @Transient
    private boolean praised;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(addTime);
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

    public void setImages(List<String> strings) {
        if (CollectionUtils.isEmpty(strings)) {
            strings = Collections.emptyList();
        }
        this.images = JSON.toJSONString(strings);
    }

    public void setImages(String images) {
        this.images = images;
    }
}
