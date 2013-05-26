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
package com.alibaba.testme.core.testunit;

import com.alibaba.testme.client.testunit.context.TestunitContext;
import com.alibaba.testme.client.testunit.dto.TestunitResult;

/**
 * 测试单元调用
 * 
 * @author chongan.wangca
 */
public interface ITestunitClient {

    public TestunitResult invoke(TestunitContext testunitContext);

}
