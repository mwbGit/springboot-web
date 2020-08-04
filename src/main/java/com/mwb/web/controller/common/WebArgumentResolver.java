package com.mwb.web.controller.common;

import com.mwb.web.model.LoginUser;
import com.mwb.web.utils.WebConstant;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */
@Component
public class WebArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Class<?> clazz = parameter.getParameterType();
        if (clazz.equals(LoginUser.class)) {
            return webRequest.getAttribute(WebConstant.USER_BASE, RequestAttributes.SCOPE_REQUEST);
        } else {
            return null;
        }
    }

}
