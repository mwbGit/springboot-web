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
public class MsgQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    private long userId;

    private int type;

    private Integer status;


}
