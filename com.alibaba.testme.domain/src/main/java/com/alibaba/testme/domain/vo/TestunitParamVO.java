/**
 * Project: com.alibaba.testme.domain
 * 
 * File Created at 2013-6-20
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

import com.alibaba.testme.common.enums.FormCtrlTypeEnum;

/**
 * 测试单元参数VO
 * 
 * @author xiaopenzi
 */
public class TestunitParamVO {

    /**
     * testunitParamId
     */
    private Long   testunitParamId;

    /**
     * 控件显示名称
     */
    private String labelName;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 控件表单类型
     */
    private String formCtrlType;

    /**
     * 测试单元ID
     */
    private Long   testunitId;

    /**
     * 缺省值
     */
    private String defaultValue;

    /**
     * 级别
     */
    private Long   rank;

    /**
     * 是否必填
     */
    private String isRequired;

    /**
     * 提示信息
     */
    private String help;
    /**
     * 控件配置项
     */
    private String testunitParamExt;
    /**
     * 页面中所在的行标号，每行数据的控件名称都是以指定字符串加上行标号
     */
    private int    rowMarkNumber;

    /**
     * @return the testunitParamId
     */
    public Long getTestunitParamId() {
        return testunitParamId;
    }

    /**
     * @param testunitParamId the testunitParamId to set
     */
    public void setTestunitParamId(Long testunitParamId) {
        this.testunitParamId = testunitParamId;
    }

    /**
     * @return the labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName the labelName to set
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * @return the paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName the paramName to set
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return the formCtrlType
     */
    public String getFormCtrlType() {
        return formCtrlType;
    }

    /**
     * @param formCtrlType the formCtrlType to set
     */
    public void setFormCtrlType(String formCtrlType) {
        this.formCtrlType = formCtrlType;
    }

    /**
     * @return the testunitId
     */
    public Long getTestunitId() {
        return testunitId;
    }

    /**
     * @param testunitId the testunitId to set
     */
    public void setTestunitId(Long testunitId) {
        this.testunitId = testunitId;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @return the rank
     */
    public Long getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(Long rank) {
        this.rank = rank;
    }

    /**
     * @return the isRequired
     */
    public String getIsRequired() {
        return isRequired;
    }

    /**
     * @param isRequired the isRequired to set
     */
    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * @return the help
     */
    public String getHelp() {
        return help;
    }

    /**
     * @param help the help to set
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * @return the testunitParamExt
     */
    public String getTestunitParamExt() {
        return testunitParamExt;
    }

    /**
     * @param testunitParamExt the testunitParamExt to set
     */
    public void setTestunitParamExt(String testunitParamExt) {
        this.testunitParamExt = testunitParamExt;
    }

    /**
     * @return the rowMarkNumber
     */
    public int getRowMarkNumber() {
        return rowMarkNumber;
    }

    /**
     * @param rowMarkNumber the rowMarkNumber to set
     */
    public void setRowMarkNumber(int rowMarkNumber) {
        this.rowMarkNumber = rowMarkNumber;
    }

    /**
     * 判断是否需要显示配置项（控件类型为下拉菜单时需显示）
     * 
     * @return
     */
    public String getNeedParamExt() {
        if (FormCtrlTypeEnum.SELECT_TYPE.getKey().equalsIgnoreCase(formCtrlType)) {
            return "";
        }

        return "none";
    }

}
