package com.alibaba.testme.core.bundle.manager;

import java.io.File;

import org.eclipse.gemini.blueprint.context.BundleContextAware;

import com.alibaba.testme.core.utils.tuple.Tuple3;

public interface TestMeBundleManager extends BundleContextAware {

    public Object getBundleService(String serviceName);

    public boolean isActive(String symbolicName);

    public boolean exist(String name, String version);

    public Tuple3<String, String, String> deploy(File stagedFile);

    public Tuple3<String, String, String> refresh(File stagedFile, String symbolicName);

    public Tuple3<String, String, String> install(File stagedFile);

}
