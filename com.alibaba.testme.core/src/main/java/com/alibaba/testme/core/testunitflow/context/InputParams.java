/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-23
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
 * 输入参数
 * 
 * @author chongan.wangca
 */
public class InputParams {

    //来自上一测试单元输出的参数
    private Map<String, String> fromLastTestunitParamsMap;

    //来自用户输入的参数
    private Map<String, String> fromUserParamsMap;

    /**
     * 判断是否存在指定的参数
     * 
     * @param paramName
     * @return
     */
    public boolean hasParam(String paramName) {
        if (fromLastTestunitParamsMap != null && fromLastTestunitParamsMap.containsKey(paramName)) {
            return true;
        }
        if (fromUserParamsMap != null && fromUserParamsMap.containsKey(paramName)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否存在来自用户输入的参数
     * 
     * @return true - 存在, false - 不存在
     */
    public boolean hasFromUserParams() {
        if (fromUserParamsMap != null && fromUserParamsMap.size() > 0) {
            return true;
        }
        return false;
    }

    public Map<String, String> getFromLastTestunitParamsMap() {
        return fromLastTestunitParamsMap;
    }

    public void setFromLastTestunitParamsMap(Map<String, String> fromLastTestunitParamsMap) {
        this.fromLastTestunitParamsMap = fromLastTestunitParamsMap;
    }

    public Map<String, String> getFromUserParamsMap() {
        return fromUserParamsMap;
    }

    public void setFromUserParamsMap(Map<String, String> fromUserParamsMap) {
        this.fromUserParamsMap = fromUserParamsMap;
    }

}
