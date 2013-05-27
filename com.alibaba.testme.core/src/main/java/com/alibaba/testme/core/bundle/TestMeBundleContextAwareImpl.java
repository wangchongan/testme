/**
 * Project: com.alibaba.testme.core
 * 
 * File Created at 2013-5-24
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
package com.alibaba.testme.core.bundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * TODO Comment of TestMeBundleContextAware
 * @author lz
 *
 */
public class TestMeBundleContextAwareImpl implements TestMeBundleContextAware {
    
    private BundleContext bundleContext;

    @Override
    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }
    
    @Override
    public Object getBundleService(String clazz) {
        ServiceReference<?> serviceReference = this.bundleContext.getServiceReference(clazz);
        Object result = this.bundleContext.getService(serviceReference);
        return result;
    }
    

}
