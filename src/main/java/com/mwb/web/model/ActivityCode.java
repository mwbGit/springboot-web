package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/6
 */

@Data
@ToString
@Table(name = "activity_code")
public class ActivityCode extends BaseBean implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    @Column(name = "note")
    private String note;

    @Column(name = "code")
    private String code;

    @Column(name = "times")
    private int times;

    @Column(name = "use_times")
    private int useTimes;
}
