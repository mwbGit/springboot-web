package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.DateTimeUtils;
import com.mwb.web.utils.WebConstant;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@Table(name = "article_info")
public class ArticleInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;


    @Column(name = "author")
    private String author;

    @Column(name = "source")
    private String source;

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

    @Column(name = "pet_id")
    private int petId;

    public String getBodyDesc() {
        return body.replaceAll("<img[^>]*>", " ");
    }

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(getAddTime());
    }

    public String getDateTime() {
        return DateTimeUtils.formatYYYYMMDD(getAddTime());
    }

    public String getWaterImage() {
        return WebConstant.getWaterImage(image);
    }
}
