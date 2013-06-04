/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

import java.util.Map;

/**
 * @author huangjun Aug 5, 2011 5:39:46 PM
 */
public class PageDialect {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void translatePageArguments(Integer index, Integer sizePerPage, Map parameters) {
        if (index == null || sizePerPage == null) {
            throw new IllegalArgumentException("index or sizePerPage is null.");
        }

        int intIndex = index;
        if (intIndex < 0) {
            intIndex = 0;
        }

        if (sizePerPage.intValue() < 0) {
            throw new IllegalArgumentException("sizePerPage must be a positive number.");
        }
        int startRow = intIndex * sizePerPage;
        int endRow = startRow + sizePerPage;

        parameters.put(Page.START_ROW, startRow);
        parameters.put(Page.END_ROW, endRow);
    }

}
