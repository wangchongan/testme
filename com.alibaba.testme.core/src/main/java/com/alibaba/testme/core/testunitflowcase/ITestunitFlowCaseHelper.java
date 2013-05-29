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
package com.alibaba.testme.core.testunitflowcase;

import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;

/**
 * TestunitFlow辅助类
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowCaseHelper {

    /**
     * 判断是否还存在可执行的测试实例节点
     * 
     * @param testunitFlowCaseId
     * @return true - 存在, false - 不存在
     */
    public boolean hasExecutableNode(Long testunitFlowDetailId);

    /**
     * 设置测试实例为暂停状态
     * 
     * @param testunitFlowContext
     */
    public void setPaused(TestunitFlowCaseContext testunitFlowCaseContext);

    /**
     * 设置为执行中
     * 
     * @param testunitFlowCaseContext
     */
    public void setRunning(TestunitFlowCaseContext testunitFlowCaseContext);

}
