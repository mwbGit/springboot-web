package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@Table(name = "pet_character")
public class PetCharacter extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private int type;

    @Transient
    private boolean checked;
}
