package com.alibaba.testme.core.bundle;

import org.eclipse.gemini.blueprint.context.BundleContextAware;

public interface TestMeBundleContextAware extends BundleContextAware{
    public Object getBundleService(String clazz);
}
