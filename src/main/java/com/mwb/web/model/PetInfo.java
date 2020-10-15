package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@Table(name = "pet_info")
public class PetInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

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

    @Column(name = "characters")
    private String characters;

    @Column(name = "nation")
    private String nation;

    @Column(name = "easy_of_disease")
    private String easyOfDisease;

    @Column(name = "life")
    private String life;

    @Column(name = "feature")
    private String feature;

    @Column(name = "care_knowledge")
    private String careKnowledge;

    @Column(name = "feed_points")
    private String feedPoints;

    @Column(name = "character_feature")
    private String characterFeature;

    public List<String> getTraits() {
        if (StringUtils.isBlank(trait)) {
            return Collections.emptyList();
        }
        return Arrays.asList(trait.split(","));
    }

    public List<String> getEasyOfDiseases() {
        if (StringUtils.isBlank(easyOfDisease)) {
            return Collections.emptyList();
        }
        return Arrays.asList(easyOfDisease.split(","));
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
