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
package com.alibaba.testme.core.bundle.manager.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.gemini.blueprint.util.OsgiBundleUtils;
import org.eclipse.virgo.kernel.deployer.core.ApplicationDeployer;
import org.eclipse.virgo.kernel.deployer.core.DeploymentException;
import org.eclipse.virgo.kernel.deployer.core.DeploymentIdentity;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.alibaba.testme.client.testunit.ITestunitHandler;
import com.alibaba.testme.core.bundle.exception.BundleManagerException;
import com.alibaba.testme.core.bundle.manager.TestMeBundleManager;
import com.alibaba.testme.core.utils.tuple.Tuple;
import com.alibaba.testme.core.utils.tuple.Tuple3;

/**
 * bundle管理类
 * 
 * @author lz
 */
public class TestMeBundleManagerImpl implements TestMeBundleManager {

    private BundleContext       bundleContext;

    private ApplicationDeployer applicationDeployer;

    public void setApplicationDeployer(ApplicationDeployer applicationDeployer) {
        this.applicationDeployer = applicationDeployer;
    }

    @Override
    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    @Override
    public Object getBundleService(String serviceName) {
        ServiceReference<?> serviceReference = this.bundleContext.getServiceReference(serviceName);
        Object result = this.bundleContext.getService(serviceReference);
        return result;
    }

    @Override
    public ITestunitHandler getBundleService(Class<? extends ITestunitHandler> clazz) {
        ServiceReference<? extends ITestunitHandler> serviceReference = this.bundleContext
                .getServiceReference(clazz);
        ITestunitHandler result = this.bundleContext.getService(serviceReference);
        return result;
    }

    @Override
    public boolean isActive(String symbolicName) {
        Bundle bundle = OsgiBundleUtils.findBundleBySymbolicName(bundleContext, symbolicName);
        if (bundle == null) {
            return false;
        }
        return OsgiBundleUtils.isBundleActive(bundle);
    }

    @Override
    public boolean exist(String name, String version) {
        try {
            if (null != getBundle(name, version)) {
                return true;
            }
        } catch (BundleManagerException e) {
        }
        return false;
    }

    private Bundle getBundle(String name, String version) {
        Set<Bundle> temp = new HashSet<Bundle>();
        if (name != null && version != null) {
            Bundle[] allBundles = this.bundleContext.getBundles();
            for (Bundle quasiBundle : allBundles) {
                if (quasiBundle.getSymbolicName().equals(name)) {
                    temp.add(quasiBundle);
                    if (quasiBundle.getVersion().toString().equals(version)) {
                        return quasiBundle;
                    }
                }
            }
            if (temp.size() == 1) {
                return temp.iterator().next();
            }
        }
        throw new BundleManagerException("Cannot find any bundle with name[" + name
                + "] and version[" + version + "].");
    }

    @Override
    public Tuple3<String, String, String> deploy(File stagedFile) {
        try {
            DeploymentIdentity identity = this.applicationDeployer.deploy(stagedFile.toURI());
            return Tuple.tuple3(identity.getSymbolicName(), identity.getVersion(),
                    identity.getType());
        } catch (DeploymentException e) {
            throw new BundleManagerException(e);
        }
    }

    @Override
    public Tuple3<String, String, String> refresh(File stagedFile, String symbolicName) {
        try {
            DeploymentIdentity identity = this.applicationDeployer.refresh(stagedFile.toURI(),
                    symbolicName);
            return Tuple.tuple3(identity.getSymbolicName(), identity.getVersion(),
                    identity.getType());
        } catch (DeploymentException e) {
            throw new BundleManagerException(e);
        }
    }

    @Override
    public Tuple3<String, String, String> install(File stagedFile) {
        try {
            DeploymentIdentity identity = this.applicationDeployer.install(stagedFile.toURI());
            return Tuple.tuple3(identity.getSymbolicName(), identity.getVersion(),
                    identity.getType());
        } catch (DeploymentException e) {
            throw new BundleManagerException(e);
        }
    }

}
