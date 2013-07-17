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
import org.eclipse.gemini.blueprint.util.OsgiServiceReferenceUtils;
import org.eclipse.virgo.kernel.deployer.core.ApplicationDeployer;
import org.eclipse.virgo.kernel.deployer.core.DeploymentException;
import org.eclipse.virgo.kernel.deployer.core.DeploymentIdentity;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(TestMeBundleManagerImpl.class);
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
    public Object getService(String serviceName) {
        ServiceReference<?> serviceReference = this.bundleContext.getServiceReference(serviceName);
        Object result = this.getService_(serviceReference);
        return result;
    }

    @Override
    public Object getService(String bundleName, String version, String serviceName) {
        ServiceReference<?>[] serviceReferences = OsgiServiceReferenceUtils.getServiceReferences(
                this.bundleContext, new String[] { serviceName });
        if (null != serviceReferences) {
            Bundle bundle = getBundle(bundleName, version);
            for (ServiceReference<?> serviceReference : serviceReferences) {
                if (serviceReference.getBundle().getBundleId() == bundle.getBundleId()) {
                    return getService_(serviceReference);
                }
            }
        }
        return null;
    }

    private Object getService_(ServiceReference<?> serviceReference) {
        if (null != serviceReference) {
            Bundle bundle = serviceReference.getBundle();
            if (!OsgiBundleUtils.isBundleActive(bundle)) {
                try {
                    if (bundle.getState() == Bundle.RESOLVED) {
                        bundle.start(Bundle.ACTIVE);
                    }
                } catch (BundleException e) {
                    throw new BundleManagerException("The bundle is " + bundle.getState()
                            + " in osgi container.");
                }
            }
            Object service = this.bundleContext.getService(serviceReference);
            if (null != service) {
                return service;
            }
        }
        return null;
    }

    @Override
    public ITestunitHandler getService(Class<? extends ITestunitHandler> clazz) {
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
    public boolean isExist(String symbolicName, String version) {
        try {
            if (null != getBundle(symbolicName, version)) {
                return true;
            }
        } catch (BundleManagerException e) {
            logger.error("invoke isExist error: ", e);
        }
        return false;
    }

    private Bundle getBundle(String symbolicName, String version) {
        Set<Bundle> temp = new HashSet<Bundle>();
        if (symbolicName != null && version != null) {
            Bundle[] allBundles = this.bundleContext.getBundles();
            for (Bundle quasiBundle : allBundles) {
                if (quasiBundle.getSymbolicName().equals(symbolicName)) {
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
        throw new BundleManagerException("Cannot find any bundle with name[" + symbolicName
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
