package com.mwb.web.model.common;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

@NoArgsConstructor
public class PageQuery implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;
    private static final int MAX_SIZE = 50;

    protected int page = 1;

    protected int pageSize = 20;

    protected String order = "id";

    protected boolean paged = true;

    public PageQuery(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageQuery(int pageSize, String order) {
        this.pageSize = pageSize;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return Math.min(MAX_SIZE, pageSize);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
