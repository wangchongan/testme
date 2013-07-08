/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-7-2
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
package com.alibaba.testme.core.bundle.exception;

/**
 * Comment of BundleManagerException
 * 
 * @author lz
 */
public class BundleManagerException extends RuntimeException {
    private static final long serialVersionUID = -6484139291798527968L;

    public BundleManagerException() {
        super();
    }

    public BundleManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BundleManagerException(String message) {
        super(message);
    }

    public BundleManagerException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
