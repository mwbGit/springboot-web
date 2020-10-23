package com.mwb.web.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mwb.web.utils.AESUtil;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/10/15
 */
@Data
public class BaseBean {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    protected long id;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @Column(name = "update_time")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updateTime;

    public String getIdStr () {
        return AESUtil.encryptId(id);
    }

}
