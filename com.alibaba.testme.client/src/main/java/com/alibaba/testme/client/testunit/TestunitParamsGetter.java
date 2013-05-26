/**
 * Project: com.alibaba.testme.client
 * 
 * File Created at 2013-5-26
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
package com.alibaba.testme.client.testunit;

import java.util.Map;

/**
 * TODO Comment of TestunitParamsGetter
 * 
 * @author chongan.wangca
 */
public interface TestunitParamsGetter {

    /**
     * 获取输入参数
     * 
     * @param testunitFlowCaseDetailId
     * @return
     */
    public Map<String, String> getInputParamsMap(Long testunitFlowCaseDetailId);

    /**
     * 获取历史节点的输入参数
     * 
     * @param testunitFlowCaseId
     * @param testunitFlowCaseDetailId 获取所有节点后，排除当前这个节点
     * @return
     */
    public Map<String, String> getHistoryParamsMap(Long testunitFlowCaseId,
                                                   Long testunitFlowCaseDetailId);

    /**
     * 获取环境变量参数
     * 
     * @param systemEnvId
     * @return
     */
    public Map<String, String> getSystemEnvParamsMap(Long systemEnvId);
}
