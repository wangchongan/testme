/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-23
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.core.testunitflow.context;

/**
 * 测试单元定义的参数
 * 
 * @author chongan.wangca
 */
public class TestunitDefParam {

    //参数名称
    private String paramName;

    //缺省值
    private String defaultValue;

    //是否必录
    private String isRequired;

    //测试单元ID
    private Long   testunitId;

    //控件名称
    private String labelName;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequired() {
        return "Y".equals(this.isRequired);
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public Long getTestunitId() {
        return testunitId;
    }

    public void setTestunitId(Long testunitId) {
        this.testunitId = testunitId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

}
