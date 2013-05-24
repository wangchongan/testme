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

/**
 * 测试实例上下文
 * 
 * @author chongan.wangca
 */
public class TestunitFlowContext {

    //测试实例ID
    private Integer                  testunitFlowCaseId;

    //测试实例状态
    private String                   testunitFlowCaseStatus;

    //用户ID
    private Integer                  userId;

    //测试实例节点ID
    private Integer                  testunitFlowCaseDetailId;

    //测试流程节点ID
    private Integer                  testunitFlowDetailId;

    //测试实例节点状态
    private String                   testunitFlowCaseDetailStatus;

    //输入参数
    private InputParams              inputParams;

    //输出参数
    private OutputParams             outputParams;

    //测试单元定义管理器
    private TestunitDefParamsManager testunitDefParamsManager;

    //测试单元类路径
    private String                   testunitClassQualifiedName;

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

    public InputParams getInputParams() {
        return inputParams;
    }

    public void setInputParams(InputParams inputParams) {
        this.inputParams = inputParams;
    }

    public OutputParams getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(OutputParams outputParams) {
        this.outputParams = outputParams;
    }

    public String getTestunitClassQualifiedName() {
        return testunitClassQualifiedName;
    }

    public void setTestunitClassQualifiedName(String testunitClassQualifiedName) {
        this.testunitClassQualifiedName = testunitClassQualifiedName;
    }

    public TestunitDefParamsManager getTestunitDefParamsManager() {
        return testunitDefParamsManager;
    }

    public void setTestunitDefParamsManager(TestunitDefParamsManager testunitDefParamsManager) {
        this.testunitDefParamsManager = testunitDefParamsManager;
    }

}
