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
package com.alibaba.testme.core.common.interfaces;

import com.alibaba.testme.core.common.dto.CheckResult;

/**
 * TODO Comment of BaseChecker
 * 
 * @author chongan.wangca
 */
public interface BaseChecker<T> {

    public CheckResult check(T obj);

}
