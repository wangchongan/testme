/**
 * Project: com.alibaba.testme.core
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
package com.alibaba.testme.core.testunitflowcase;

import java.util.Map;

/**
 * TODO Comment of ITestunitFlowCaseGenerator
 * 
 * @author chongan.wangca
 */
public interface ITestunitFlowCaseGenerator {

    public Long generate(Long testunitFlowId, Map<String, String> userInputParamsMap);

}
