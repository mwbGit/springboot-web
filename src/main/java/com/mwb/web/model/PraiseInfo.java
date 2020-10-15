package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import com.mwb.web.utils.DateTimeUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 * @create 2020/8/7
 */
@Data
@NoArgsConstructor
@Table(name = "praise_info")
public class PraiseInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "object_id")
    private long objectId;

    @Column(name = "user_id")
    private long userId;

    /**
     * 0 动态 1 文章
     */
    @Column(name = "type")
    private int type;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(getAddTime());
    }

    public PraiseInfo(long objectId, long userId, int type) {
        this.objectId = objectId;
        this.userId = userId;
        this.type = type;
    }
}
