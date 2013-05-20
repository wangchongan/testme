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
package com.alibaba.testme.core.testunitflow.impl;

import com.alibaba.testme.core.testunitflow.TestunitFlowHandler;
import com.alibaba.testme.core.testunitflow.dto.impl.DefaultTestRequestDTO;
import com.alibaba.testme.core.testunitflow.dto.impl.DefaultTestunitFlowResult;

/**
 * 单向流程TestunitFlow处理总入口
 * 
 * @author chongan.wangca
 */
public class DefaultTestunitFlowHandler implements
        TestunitFlowHandler<DefaultTestRequestDTO, DefaultTestunitFlowResult> {

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.core.testunitflow.TestunitFlowHandler#deal(com.alibaba
     * .testme.core.testunitflow.dto.TestRequestDTO)
     */
    @Override
    public DefaultTestunitFlowResult deal(DefaultTestRequestDTO testRequestDTO) {
        return null;
    }

}
