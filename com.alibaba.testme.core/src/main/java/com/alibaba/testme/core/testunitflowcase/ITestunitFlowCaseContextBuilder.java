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
package com.alibaba.testme.core.testunitflowcase;

import java.util.Map;

import com.alibaba.testme.core.testunitflowcase.context.TestunitFlowCaseContext;

/**
 * TODO Comment of TestunitFlowContextBuilder
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowCaseContextBuilder {

    public TestunitFlowCaseContext build(Long testunitFlowCaseId, Map<String, String> inputParamsMap);

}
