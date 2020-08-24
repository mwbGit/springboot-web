package com.mwb.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/18
 */
@Data
@NoArgsConstructor
@Table(name = "ip_list")
public class IpList {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "ip")
    private String ip;

    /**
     * 0 黑名单 1白名单
     */
    @Column(name = "type")
    private int type;

    @Column(name = "reason")
    private String reason;

    @Column(name = "add_time", updatable = false)
    private Date addTime;


    public IpList(String ip) {
        this.ip = ip;
        this.addTime = new Date();
    }
}
