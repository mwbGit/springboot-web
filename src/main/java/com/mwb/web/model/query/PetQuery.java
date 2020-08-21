package com.mwb.web.model.query;

import com.mwb.web.model.common.PageQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Data
public class PetQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    private String name;

    private Integer level;

    private Integer score;

    private int characterId;

    private String order = "id";
}
