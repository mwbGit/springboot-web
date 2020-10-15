package com.mwb.web.model;

import com.mwb.web.model.common.BaseBean;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/18
 */
@Data
@Table(name = "feedback_info")
public class FeedbackInfo extends BaseBean {

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private long userId;

}
