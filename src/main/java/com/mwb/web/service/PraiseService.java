package com.mwb.web.service;

import com.mwb.web.model.PraiseInfo;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public interface PraiseService extends BaseService<PraiseInfo> {

    List<Long> search(List<Long> objectIds, long userId, int type);

    boolean savePraiseInfo(PraiseInfo articleInfo);

}