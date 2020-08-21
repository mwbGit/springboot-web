package com.mwb.web.model;

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
@Table(name = "banner_info")
public class BannerInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    /**
     * 类型 0无 1文章 2品种 3动态
     */
    @Column(name = "type")
    private int type;

    /**
     * 0 下线 1 上线
     */
    @Column(name = "status")
    private int status;

    @Column(name = "level")
    private int level;

    @Column(name = "object_id")
    private int objectId;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    public String getTypeDesc(){
        switch (type) {
            case 1:
                return "文章";
            case 2:
                return "品种";
            case 3:
                return "动态";
            default:
                return "图片";
        }
    }
}
