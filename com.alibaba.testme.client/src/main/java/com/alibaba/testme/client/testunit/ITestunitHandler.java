/**
 * Project: com.alibaba.testme.client
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
package com.alibaba.testme.client.testunit;

import com.alibaba.testme.client.testunit.context.TestunitContext;
import com.alibaba.testme.client.testunit.dto.TestunitResult;

/**
 * TODO Comment of TestunitHandler
 * 
 * @author chongan.wangca
 */
public interface ITestunitHandler {

    /**
     * 测试单元调用入口
     * 
     * @param context
     * @return
     */
    public TestunitResult deal(TestunitContext context);

}
