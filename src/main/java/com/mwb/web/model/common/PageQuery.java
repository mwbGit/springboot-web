package com.mwb.web.model.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Data
public class PageQuery implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    protected int page = 1;

    protected int pageSize = 20;

    protected boolean paged = true;
}
