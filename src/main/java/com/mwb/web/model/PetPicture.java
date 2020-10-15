package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.AESUtil;
import lombok.Data;
import lombok.ToString;

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


    @Column(name = "pet_id")
    private long petId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "level")
    private int level;

}
