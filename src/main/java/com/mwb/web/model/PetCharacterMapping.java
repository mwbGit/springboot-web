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
@Table(name = "pet_character_mapping")
public class PetCharacterMapping implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "pet_id")
    private long petId;

    @Column(name = "character_id")
    private long characterId;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;
}
