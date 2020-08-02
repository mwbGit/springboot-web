package com.mwb.web.model.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    private long total;

    private int pages;

    private List<T> list;

    public PageResult(PageInfo pageInfo) {
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
        this.list = pageInfo.getList();
    }

    public PageResult() {

    }
}
