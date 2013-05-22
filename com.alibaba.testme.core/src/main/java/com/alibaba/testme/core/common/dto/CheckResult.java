/**
 * Project: com.alibaba.testme.core
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
package com.alibaba.testme.core.common.dto;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.testme.core.common.enums.CheckResultEnum;

/**
 * 处理结果
 * 
 * @author chongan.wangca
 */
public class CheckResult {

    public CheckResultEnum result        = CheckResultEnum.SUCCESS;

    public List<String>    errorMsgsList = new ArrayList<String>();

    public CheckResultEnum getResult() {
        return result;
    }

    public void setResult(CheckResultEnum result) {
        this.result = result;
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

    public void addErrorMsg(String errorMsg) {
        this.errorMsgsList.add(errorMsg);
    }

}
