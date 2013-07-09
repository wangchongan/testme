/**
 * Project: com.alibaba.testme.common
 * 
 * File Created at 2013-5-24
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
package com.alibaba.testme.common.basedto;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Comment of BaseResult
 * 
 * @author chongan.wangca
 */
public class BaseResult {

    private List<String> errorMsgsList;

    private List<String> msgsList;

    public BaseResult() {
        super();
        this.errorMsgsList = new ArrayList<String>();
        this.msgsList = new ArrayList<String>();
    }

    public void addAllErrorMsgs(List<String> errorMsgsList) {
        if (errorMsgsList != null) {
            this.errorMsgsList.addAll(errorMsgsList);
        }
    }

    public void addAllMsgs(List<String> msgsList) {
        if (msgsList != null) {
            this.msgsList.addAll(msgsList);
        }
    }

    /**
     * 添加错误日志
     * 
     * @param errorMsg
     */
    public void addErrorMsg(String errorMsg) {
        if (errorMsg == null) {
            return;
        }

        if (this.errorMsgsList == null) {
            this.errorMsgsList = new ArrayList<String>();
        }
        this.errorMsgsList.add(errorMsg);
    }

    /**
     * 添加正常日志
     * 
     * @param msg
     */
    public void addMsg(String msg) {
        if (msg == null) {
            return;
        }

        if (this.msgsList == null) {
            this.msgsList = new ArrayList<String>();
        }
        this.msgsList.add(msg);
    }

    public List<String> getErrorMsgsList() {
        return errorMsgsList;
    }

    public void setErrorMsgsList(List<String> errorMsgsList) {
        this.errorMsgsList = errorMsgsList;
    }

    public List<String> getMsgsList() {
        return msgsList;
    }

    public void setMsgsList(List<String> msgsList) {
        this.msgsList = msgsList;
    }

}
