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
package com.alibaba.testme.core.testunitflow.dto;

import java.util.Map;

/**
 * 测试请求DTO
 * 
 * @author chongan.wangca
 */
public class TestRequestDTO {

    //测试流程实例ID
    public Integer             testunitFlowCaseId;

    //输入参数
    public Map<String, String> inputParamsMap;

    public Integer getTestunitFlowCaseId() {
        return testunitFlowCaseId;
    }

    public void setTestunitFlowCaseId(Integer testunitFlowCaseId) {
        this.testunitFlowCaseId = testunitFlowCaseId;
    }

    public Map<String, String> getInputParamsMap() {
        return inputParamsMap;
    }

    public void setInputParamsMap(Map<String, String> inputParamsMap) {
        this.inputParamsMap = inputParamsMap;
    }

}
