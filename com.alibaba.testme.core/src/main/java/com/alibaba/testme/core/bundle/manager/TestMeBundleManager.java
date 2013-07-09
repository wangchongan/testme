package com.alibaba.testme.core.bundle.manager;

import java.io.File;

import org.eclipse.gemini.blueprint.context.BundleContextAware;

import com.alibaba.testme.client.testunit.ITestunitHandler;
import com.alibaba.testme.core.utils.tuple.Tuple3;

public interface TestMeBundleManager extends BundleContextAware {

    Object getBundleService(String serviceName);

    boolean isActive(String symbolicName);

    boolean exist(String name, String version);

    Tuple3<String, String, String> deploy(File stagedFile);

    Tuple3<String, String, String> refresh(File stagedFile, String symbolicName);

    Tuple3<String, String, String> install(File stagedFile);

    ITestunitHandler getBundleService(Class<? extends ITestunitHandler> clazz);

}
