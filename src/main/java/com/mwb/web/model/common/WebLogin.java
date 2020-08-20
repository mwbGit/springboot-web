package com.mwb.web.model.common;


import java.lang.annotation.*;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/2/25 16:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface WebLogin {

    /**
     * 是否必须登录
     *
     * @return
     */
    Option option() default Option.OPTIONAL;

    /**
     * 是否有效用户
     *
     * @return
     */
    boolean valid() default false;

    /**
     * 访问限制 时间单位
     *
     * @return
     */
    int accessSeconds() default 1;

    /**
     * 访问限制 时间单位下可访问次数
     * 3次/1秒
     *
     * @return
     */
    int accessMax() default 3;

    enum Option {
        /**
         *
         */
        MUST,
        OPTIONAL,
        ADMIN;

        Option() {
        }
    }
}
