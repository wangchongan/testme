/**
 * Project: lplsc.common.shared
 * 
 * File Created at 2012-11-26上午11:19:01
 * $Id$
 * 
 * Copyright 1999-2012 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

import java.util.List;

/**
 * 支持分页取数
 * 
 * @author zhengxing 2012-11-26上午11:19:01
 */
public interface PageItemCursor<T> {

    List<T> nextPage();

    boolean hasNext();

    void setPageSize(int pageSize);

    int getCount();
}
