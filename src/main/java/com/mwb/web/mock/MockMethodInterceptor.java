package com.mwb.web.mock;


import com.alibaba.fastjson.JSONObject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
public class MockMethodInterceptor implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (MockConfigContext.isMock()) {
            try {
                Class<?> targetClass = methodInvocation.getThis() != null ? AopUtils.getTargetClass(methodInvocation.getThis()) : null;
                if (targetClass != null) {
                    Method specificMethod = ClassUtils.getMostSpecificMethod(methodInvocation.getMethod(), targetClass);
                    if (!specificMethod.getDeclaringClass().equals(Object.class)) {
                        Method method = BridgeMethodResolver.findBridgedMethod(specificMethod);
                        Class<?> returnType = method.getReturnType();
                        if (!MockConfigContext.notReturnType(returnType)) {
                            Mock mock = method.getAnnotation(Mock.class);
                            if (mock != null) {
                                StringBuilder key = new StringBuilder(targetClass.getName() + "." + method.getName() + "(");
                                Parameter[] parameters = method.getParameters();
                                JSONObject object = new JSONObject();
                                String paramKey = "";
                                for (int i = 0; i < parameters.length; i++) {
                                    Parameter parameter = parameters[i];
                                    key.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName());
                                    if (i < parameters.length - 1) {
                                        key.append(", ");
                                    }
                                    if (i == 0 && parameters.length == 1 && MockMethodUtils.containClass(parameter.getType())) {
                                        paramKey = methodInvocation.getArguments()[i].toString();
                                    }
                                    object.put(parameter.getName(), methodInvocation.getArguments()[i]);
                                }

                                key.append(")?");
                                String result = MockConfigContext.get(key + object.toJSONString());
                                result = result == null ? MockConfigContext.get(key + paramKey) : result;
                                if (result != null) {
                                    if (MockMethodUtils.containClass(method.getReturnType())) {
                                        Object ob = MockMethodUtils.getObject(method.getReturnType(), result);
                                        if (ob != null) {
                                            return ob;
                                        }
                                    }
                                    return JSONObject.parseObject(result, method.getGenericReturnType());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("MockMethodInterceptor.invoke err,", e);
            }
        }
        return methodInvocation.proceed();
    }

}
