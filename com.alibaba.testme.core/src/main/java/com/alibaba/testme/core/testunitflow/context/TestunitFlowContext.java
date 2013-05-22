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

    private TestunitFlowCaseExtend testunitFlowCaseExtend;

    private TestunitExtend         testunitExtend;

    private TestunitFlowExtend     testunitFlowExtend;

    // ‰»Î≤Œ ˝
    private Map<String, String>    inputParamsMap;

    public Map<String, String> getInputParamsMap() {
        return inputParamsMap;
    }

    public void setInputParamsMap(Map<String, String> inputParamsMap) {
        this.inputParamsMap = inputParamsMap;
    }

    public TestunitFlowCaseExtend getTestunitFlowCaseExtend() {
        return testunitFlowCaseExtend;
    }

    public void setTestunitFlowCaseExtend(TestunitFlowCaseExtend testunitFlowCaseExtend) {
        this.testunitFlowCaseExtend = testunitFlowCaseExtend;
    }

    public TestunitExtend getTestunitExtend() {
        return testunitExtend;
    }

    public void setTestunitExtend(TestunitExtend testunitExtend) {
        this.testunitExtend = testunitExtend;
    }

    public TestunitFlowExtend getTestunitFlowExtend() {
        return testunitFlowExtend;
    }

    public void setTestunitFlowExtend(TestunitFlowExtend testunitFlowExtend) {
        this.testunitFlowExtend = testunitFlowExtend;
    }

}
