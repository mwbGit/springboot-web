package com.mwb.web.model;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private int sex;

    @Column(name = "add_time", updatable = false)
    private Date addTime;

    @ColumnType(column = "update_time", jdbcType = JdbcType.DATETIMEOFFSET)
    private Date updateTime;

    @Transient
    private String otherThings; //非数据库表中字段
}
