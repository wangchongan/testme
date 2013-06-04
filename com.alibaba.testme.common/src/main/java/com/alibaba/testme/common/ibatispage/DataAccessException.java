/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.testme.common.ibatispage;

/**
 * @author huangjun Aug 5, 2011 6:57:39 PM
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = 374646513581569310L;

    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
