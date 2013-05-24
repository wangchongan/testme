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
package com.alibaba.testme.core.testunitflow;

import com.alibaba.testme.client.testunit.dto.TestunitResult;
import com.alibaba.testme.core.testunitflow.context.TestunitFlowContext;

/**
 * 后续测试单元路由
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowAfterProcessor {

    public void doProcess(TestunitFlowContext testunitFlowContext, TestunitResult testunitResult);
}
