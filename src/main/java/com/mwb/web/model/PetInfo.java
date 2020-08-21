package com.mwb.web.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@Table(name = "pet_info")
public class PetInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "introduce")
    private String introduce;

    /**
     * 状态 0-猫 1狗
     */
    @Column(name = "type")
    private int type;

    @Column(name = "level")
    private int level;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "trait")
    private String trait;

    @Column(name = "advantage")
    private String advantage;

    @Column(name = "defect")
    private String defect;

    @Column(name = "score")
    private int score;

    @Column(name = "price")
    private String price;

    @Column(name = "deleted")
    private int deleted;

    public List<String> getTraits() {
        if (StringUtils.isBlank(trait)) {
            return Collections.emptyList();
        }
        return Arrays.asList(trait.split(","));
    }

    public List<String> getAdvantages() {
        if (StringUtils.isBlank(advantage)) {
            return Collections.emptyList();
        }
        return Arrays.asList(advantage.split(","));
    }

    public List<String> getDefects() {
        if (StringUtils.isBlank(defect)) {
            return Collections.emptyList();
        }
        return Arrays.asList(defect.split(","));
    }


}
