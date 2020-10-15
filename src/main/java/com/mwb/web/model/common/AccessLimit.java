package com.mwb.web.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessLimit {

    private String ip;

    private String uri;

    private List<Long> times;

}
