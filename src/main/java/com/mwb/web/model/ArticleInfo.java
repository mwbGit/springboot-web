package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.DateTimeUtils;
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

    public static void main(String[] args) {
        String str  = "9、金毛犬不挑食，有时候会随便捡起地上的东西吃，这不是<img src=\"https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg\" alt=\"7eb5c34d18064d3c4a0640aac680798982dfa5cfde049fbcc8fa446880d75552\">一个好习惯，需要从小就对它进行调教和训练，否则很难制止它的行为。\n";
        System.out.println(str);
        str =str.replaceAll("<img[^>]*>", " ");
        System.out.println(str);
    }
}
