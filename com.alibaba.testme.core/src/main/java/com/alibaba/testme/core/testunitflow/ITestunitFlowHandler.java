/**
 * Project: com.alibaba.testme.core
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
package com.alibaba.testme.core.testunitflow;

import com.alibaba.testme.core.testunitflow.dto.TestRequestDTO;
import com.alibaba.testme.core.testunitflow.dto.TestunitFlowResult;

/**
 * TestunitFlow请求处理接口<br/>
 * TestunitFlowHandler只接受已生成实例的测试请求，所以testunitFlowCaseId必传<br/>
 * 对于首次执行，生成TestunitFlowCase数据的职责由接入层来判断并承担数据生成的功能。
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowHandler {

    /**
     * 请求处理总入口
     * 
     * @param testRequestDTO
     * @return
     */
    public TestunitFlowResult deal(TestRequestDTO testRequestDTO);

}
