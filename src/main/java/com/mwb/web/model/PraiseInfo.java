package com.mwb.web.model;

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
public class PraiseInfo implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "object_id")
    private long objectId;

    @Column(name = "user_id")
    private long userId;

    /**
     * 0 动态 1 文章
     */
    @Column(name = "type")
    private int type;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    private Date updateTime;

    public String getTimeDesc() {
        return DateTimeUtils.fromTodayDesc(addTime);
    }

    public PraiseInfo(long objectId, long userId, int type) {
        this.objectId = objectId;
        this.userId = userId;
        this.type = type;
    }
}
