package com.alibaba.testme.core.bundle.manager;

import java.io.File;

import org.eclipse.gemini.blueprint.context.BundleContextAware;

import com.alibaba.testme.client.testunit.ITestunitHandler;
import com.alibaba.testme.core.utils.tuple.Tuple3;

public interface TestMeBundleManager extends BundleContextAware {

    Object getService(String bundleName, String version, String serviceName);

    Object getService(String serviceName);

    boolean isActive(String symbolicName);

    boolean isExist(String symbolicName, String version);

    Tuple3<String, String, String> deploy(File stagedFile);

    Tuple3<String, String, String> refresh(File stagedFile, String symbolicName);

    Tuple3<String, String, String> install(File stagedFile);

    ITestunitHandler getService(Class<ITestunitHandler> clazz);

    void undeploy(String symbolicName, String version);

    Object getService(String serviceName, String filter);

    ITestunitHandler getService(Class<ITestunitHandler> clazz, String filter);

}
