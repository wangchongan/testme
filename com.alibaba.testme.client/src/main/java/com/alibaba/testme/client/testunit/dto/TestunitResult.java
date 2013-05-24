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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.testme.client.testunit.enums.TestunitResultStatus;

/**
 * 测试单元执行结果
 * 
 * @author chongan.wangca
 */
public class TestunitResult {

    //执行结果状态
    private TestunitResultStatus status;

    //异常日志信息
    private List<String>         errorMsgsList;

    //正常日志信息
    private List<String>         msgsList;

    //输出参数
    private Map<String, String>  outputParamsMap;

    /**
     * 添加异常信息
     * 
     * @param errorMsg
     */
    public void addErrorMsg(String errorMsg) {
        if (errorMsgsList == null) {
            errorMsgsList = new ArrayList<String>();
        }
        errorMsgsList.add(errorMsg);
    }

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

    public List<String> getMsgsList() {
        return msgsList;
    }

    public void setMsgsList(List<String> msgsList) {
        this.msgsList = msgsList;
    }

}
