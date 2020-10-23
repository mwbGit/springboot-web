package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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

    private static final String WATERMARK = "?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,size_20,text_54yr5ZKq5LmL5a62,color_FFFFFF,shadow_50,t_100,g_se,x_10,y_10";


    public String getImage() {
        if (StringUtils.isNoneBlank(image)) {
            return image + WATERMARK;
        }
        return image;
    }
}
