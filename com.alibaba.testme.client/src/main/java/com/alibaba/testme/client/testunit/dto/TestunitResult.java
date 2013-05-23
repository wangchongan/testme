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
package com.alibaba.testme.client.testunit.dto;

import java.util.List;
import java.util.Map;

import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;

/**
 * 测试单元执行结果
 * 
 * @author chongan.wangca
 */
public class TestunitResult {

    private TestunitResultStatus status;

    private List<String>         errorMsgsList;

    private Map<String, String>  outputParamsMap;

    public TestunitResultStatus getStatus() {
        return status;
    }

    public void setStatus(TestunitResultStatus status) {
        this.status = status;
    }

    public List<String> getErrorMsgsList() {
        return errorMsgsList;
    }

    public void setErrorMsgsList(List<String> errorMsgsList) {
        this.errorMsgsList = errorMsgsList;
    }

    public Map<String, String> getOutputParamsMap() {
        return outputParamsMap;
    }

    public void setOutputParamsMap(Map<String, String> outputParamsMap) {
        this.outputParamsMap = outputParamsMap;
    }

}
