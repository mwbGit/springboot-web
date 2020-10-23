package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:
 * 宠物图片
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@ToString
@Table(name = "pet_picture")
public class PetPicture extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    private static final String WATERMARK = "?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,size_20,text_54yr5ZKq5LmL5a62,color_FFFFFF,shadow_50,t_100,g_se,x_10,y_10";

    @Column(name = "pet_id")
    private long petId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "level")
    private int level;


    public String getImage() {
        if (StringUtils.isNoneBlank(image)) {
            return image + WATERMARK;
        }
        return image;
    }
}
