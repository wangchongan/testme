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
package com.alibaba.testme.core.testunitflow.dto;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.testme.core.testunitflow.context.TestunitDefParam;

/**
 * 输入参数是否满足测试单元执行的必需参数的校验结果
 * 
 * @author chongan.wangca
 */
public class ParamsMatchCheckResult {

    private List<TestunitDefParam> absentParamsList;

    public boolean isMatch() {
        if (absentParamsList == null || absentParamsList.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 添加缺少的参数
     * 
     * @param testunitDefParam
     */
    public void addAbsentParam(TestunitDefParam testunitDefParam) {
        if (absentParamsList == null) {
            absentParamsList = new ArrayList<TestunitDefParam>();
        }
        absentParamsList.add(testunitDefParam);
    }

    public List<TestunitDefParam> getAbsentParamsList() {
        return absentParamsList;
    }

    public void setAbsentParamsList(List<TestunitDefParam> absentParamsList) {
        this.absentParamsList = absentParamsList;
    }

    /**
     * 获取缺少的参数中文名
     * 
     * @return
     */
    public String getAbsentParamsLabelNames() {

        if (absentParamsList == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (TestunitDefParam testunitDefParam : absentParamsList) {
            sb.append("[");
            sb.append(testunitDefParam.getLabelName());
            sb.append("]");
        }
        return sb.toString();

    }
}
