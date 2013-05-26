/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-20
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
package com.alibaba.testme.core.testunitflowcase.dto;

import java.util.Map;

/**
 * 测试请求数据封装对象
 * 
 * @author chongan.wangca
 */
public class TestRequestDTO {

    //测试单元流程实例ID，首次执行时为空
    private Long                testunitFlowCaseId;

    //测试单元流程ID，首次执行时不为空
    private Long                testunitFlowId;

    private Map<String, String> userInputParamsMap;

    public Long getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Long testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    public Map<String, String> getUserInputParamsMap() {
        return userInputParamsMap;
    }

    public void setUserInputParamsMap(Map<String, String> userInputParamsMap) {
        this.userInputParamsMap = userInputParamsMap;
    }

    public Long getTestunitFlowId() {
        return testunitFlowId;
    }

    public void setTestunitFlowId(Long testunitFlowId) {
        this.testunitFlowId = testunitFlowId;
    }

}
