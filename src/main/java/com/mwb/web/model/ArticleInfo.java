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
 * @create 2020/8/6
 */

@Data
@Table(name = "article_info")
public class ArticleInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "image")
    private String image;

    @Column(name = "view_num")
    private int viewNum;

    @Column(name = "praise_num")
    private int praiseNum;

    @Column(name = "type")
    private int type;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(addTime);
    }

}
