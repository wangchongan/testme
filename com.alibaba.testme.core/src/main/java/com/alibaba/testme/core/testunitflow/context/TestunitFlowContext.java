/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-22
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

import java.util.Map;

/**
 * TODO Comment of TestunitFlowContext
 * 
 * @author chongan.wangca
 */
public class TestunitFlowContext {

    private Integer             testunitFlowCaseId;

    private String              testunitFlowCaseStatus;

    private Integer             userId;

    private Integer             testunitFlowCaseDetailId;

    private Integer             testunitFlowDetailId;

    private String              testunitFlowCaseDetailStatus;

    private Map<String, String> inputParamsMap;

    private Map<String, String> outputParamsMap;

    private String              testunitClassQualifiedName;

    private String              testunitVersion;

    public Map<String, String> getInputParamsMap() {
        return inputParamsMap;
    }

    public void setInputParamsMap(Map<String, String> inputParamsMap) {
        this.inputParamsMap = inputParamsMap;
    }

    public Integer getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Integer testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    public String getTestunitFlowCaseStatus() {
        return testunitFlowCaseStatus;
    }

    public void setTestunitFlowCaseStatus(String testunitFlowCaseStatus) {
        this.testunitFlowCaseStatus = testunitFlowCaseStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTestunitFlowCaseDetailId() {
        return testunitFlowCaseDetailId;
    }

    public void setTestunitFlowCaseDetailId(Integer testunitFlowCaseDetailId) {
        this.testunitFlowCaseDetailId = testunitFlowCaseDetailId;
    }

    public Integer getTestunitFlowDetailId() {
        return testunitFlowDetailId;
    }

    public void setTestunitFlowDetailId(Integer testunitFlowDetailId) {
        this.testunitFlowDetailId = testunitFlowDetailId;
    }

    public String getTestunitFlowCaseDetailStatus() {
        return testunitFlowCaseDetailStatus;
    }

    public void setTestunitFlowCaseDetailStatus(String testunitFlowCaseDetailStatus) {
        this.testunitFlowCaseDetailStatus = testunitFlowCaseDetailStatus;
    }

    public Map<String, String> getOutputParamsMap() {
        return outputParamsMap;
    }

    public void setOutputParamsMap(Map<String, String> outputParamsMap) {
        this.outputParamsMap = outputParamsMap;
    }

    public String getTestunitClassQualifiedName() {
        return testunitClassQualifiedName;
    }

    public void setTestunitClassQualifiedName(String testunitClassQualifiedName) {
        this.testunitClassQualifiedName = testunitClassQualifiedName;
    }

    public String getTestunitVersion() {
        return testunitVersion;
    }

    public void setTestunitVersion(String testunitVersion) {
        this.testunitVersion = testunitVersion;
    }

}
