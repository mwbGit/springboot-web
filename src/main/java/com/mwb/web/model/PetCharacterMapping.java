package com.mwb.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mwb.web.model.common.BaseBean;
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
@Table(name = "pet_character_mapping")
public class PetCharacterMapping extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "pet_id")
    private long petId;

    @Column(name = "character_id")
    private long characterId;

}
