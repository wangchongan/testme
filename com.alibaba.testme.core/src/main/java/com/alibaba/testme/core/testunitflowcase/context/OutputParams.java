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
package com.alibaba.testme.core.testunitflowcase.context;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 输出参数
 * 
 * @author chongan.wangca
 */
public class OutputParams {

    //输出参数
    public Map<String, String> outputParamsMap;

    public String getOutputParamsJSON() {
        if (outputParamsMap == null) {
            return null;
        }
        return JSON.toJSONString(outputParamsMap);
    }

    public Map<String, String> getOutputParamsMap() {
        return outputParamsMap;
    }

    public void setOutputParamsMap(Map<String, String> outputParamsMap) {
        this.outputParamsMap = outputParamsMap;
    }

}
