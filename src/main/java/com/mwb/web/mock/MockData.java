package com.mwb.web.mock;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/11/12
 */
public class MockData implements Serializable {
    private static final long serialVersionUID = -812286384321466835L;

    private Integer id;

    private String application;

    private String classMethodName;

    private String param;

    private String result;

    private Integer status;


    public String getOnlyKey() {
        return StringUtils.defaultString(application) + StringUtils.defaultString(classMethodName) + "?" + StringUtils.defaultString(param);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getClassMethodName() {
        return classMethodName;
    }

    public void setClassMethodName(String classMethodName) {
        this.classMethodName = classMethodName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MockData mockData = (MockData) o;
        return Objects.equals(application, mockData.application) &&
                Objects.equals(classMethodName, mockData.classMethodName) &&
                Objects.equals(param, mockData.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(application, classMethodName, param);
    }
}
