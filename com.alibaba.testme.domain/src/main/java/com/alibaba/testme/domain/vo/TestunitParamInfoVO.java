/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-19
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
package com.alibaba.testme.domain.vo;

import java.util.List;

import com.alibaba.testme.common.enums.FormCtrlTypeEnum;

/**
 * 任务建立测试单元参数
 * 
 * @author lz
 */
public class TestunitParamInfoVO {
    private Long                         testunitParamId;
    private String                       labelName;
    private String                       paramName;
    private FormCtrlTypeEnum             formCtrlType;
    private String                       defaultValue;
    private String                       isRequired;
    private String                       help;

    private List<TestunitParamExtInfoVO> testunitParamExtInfoVOs;

    public Long getTestunitParamId() {
        return testunitParamId;
    }

    public void setTestunitParamId(Long testunitParamId) {
        this.testunitParamId = testunitParamId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public FormCtrlTypeEnum getFormCtrlType() {
        return formCtrlType;
    }

    public void setFormCtrlType(String formCtrlType) {
        this.formCtrlType = FormCtrlTypeEnum.parse(formCtrlType);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public List<TestunitParamExtInfoVO> getTestunitParamExtInfoVOs() {
        return testunitParamExtInfoVOs;
    }

    public void setTestunitParamExtInfoVOs(List<TestunitParamExtInfoVO> testunitParamExtInfoVOs) {
        this.testunitParamExtInfoVOs = testunitParamExtInfoVOs;
    }

}
