/**
 * Project: com.alibaba.testme.core
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
package com.alibaba.testme.core.testunitflow;

import java.util.Map;

import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;

/**
 * TODO Comment of ITestunitFlowDataManager
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowHelper {

    /**
     * 判断是否还存在可执行的测试实例节点
     * 
     * @param testunitFlowCaseId
     * @return true - 存在, false - 不存在
     */
    public boolean hasExecutableNode(Long testunitFlowCaseId);

    /**
     * 设置测试实例为暂停状态
     * 
     * @param testunitFlowContext
     */
    public void setTestunitFlowPaused(TestunitFlowContext testunitFlowContext);

    /**
     * 构建测试实例上下文信息
     * 
     * @param testunitFlowContext
     * @param inputParamsMap
     * @return
     */
    public TestunitFlowContext buildTestunitFlowContext(Integer testunitFlowContext,
                                                        Map<String, String> inputParamsMap);

}
