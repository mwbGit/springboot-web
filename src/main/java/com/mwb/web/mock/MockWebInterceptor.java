package com.mwb.web.mock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
@Component
public class MockWebInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!MockConfigContext.isMock() || !(handler instanceof HandlerMethod)) {
            return true;
        }
        PrintWriter pw = null;
        try {
            String key = StringUtils.isNotBlank(request.getQueryString()) ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
            String content = MockConfigContext.get(key);
            if (content == null) {
                return true;
            }

            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            pw = response.getWriter();
            pw.write(content);
            return false;
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }

}
