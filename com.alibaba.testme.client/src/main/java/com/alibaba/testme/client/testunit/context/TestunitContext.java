/**
 * Project: com.alibaba.testme.client
 * 
 * File Created at 2013-5-21
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
package com.alibaba.testme.client.testunit.context;

import java.util.Map;

/**
 * TODO Comment of TestunitContext
 * 
 * @author chongan.wangca
 */
public class TestunitContext {

    //类路径
    private String              classQualifiedName;

    //测试流程实例ID
    private Long                testunitFlowCaseId;

    //测试流程实例节点ID
    private Long                testunitFlowCaseDetailId;

    //系统环境变量ID
    private Long                systemEnvId;

    //系统环境变量参数
    private Map<String, String> systemEnvParamsMap;

    //当前节点输入参数
    private Map<String, String> inputParamsMap;

    //历史节点输入参数
    private Map<String, String> historyParamsMap;

    public String getClassQualifiedName() {
        return classQualifiedName;
    }

    public void setClassQualifiedName(String classQualifiedName) {
        this.classQualifiedName = classQualifiedName;
    }

    public Long getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Long testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    public Long getTestunitFlowCaseDetailId() {
        return testunitFlowCaseDetailId;
    }

    public void setTestunitFlowCaseDetailId(Long testunitFlowCaseDetailId) {
        this.testunitFlowCaseDetailId = testunitFlowCaseDetailId;
    }

    public Long getSystemEnvId() {
        return systemEnvId;
    }

    public void setSystemEnvId(Long systemEnvId) {
        this.systemEnvId = systemEnvId;
    }

    public Map<String, String> getSystemEnvParamsMap() {
        return systemEnvParamsMap;
    }

    public void setSystemEnvParamsMap(Map<String, String> systemEnvParamsMap) {
        this.systemEnvParamsMap = systemEnvParamsMap;
    }

    public Map<String, String> getInputParamsMap() {
        return inputParamsMap;
    }

    public void setInputParamsMap(Map<String, String> inputParamsMap) {
        this.inputParamsMap = inputParamsMap;
    }

    public Map<String, String> getHistoryParamsMap() {
        return historyParamsMap;
    }

    public void setHistoryParamsMap(Map<String, String> historyParamsMap) {
        this.historyParamsMap = historyParamsMap;
    }

}
