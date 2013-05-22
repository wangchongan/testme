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

import java.util.List;

import com.alibaba.testme.common.enums.TestunitDealStatusEnum;

/**
 * TODO Comment of TestunitFlowResultDTO
 * 
 * @author chongan.wangca
 */
public class TestunitFlowResult {

    //测试单元执行结果状态
    private TestunitDealStatusEnum status;

    private List<String>           errorMsgsList;

    public TestunitDealStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TestunitDealStatusEnum status) {
        this.status = status;
    }

    public List<String> getErrorMsgsList() {
        return errorMsgsList;
    }

    public void setErrorMsgsList(List<String> errorMsgsList) {
        this.errorMsgsList = errorMsgsList;
    }

    public void addAllErrorMsgs(List<String> errorMsgsList) {
        this.errorMsgsList.addAll(errorMsgsList);
    }

}
