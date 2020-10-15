package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Data
@Table(name = "user")
public class User extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private int sex;

    @Transient
    private String otherThings; //非数据库表中字段
}
