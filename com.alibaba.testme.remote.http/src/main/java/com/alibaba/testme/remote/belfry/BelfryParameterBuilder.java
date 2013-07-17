package com.alibaba.testme.remote.belfry;

import java.util.Map;

public interface BelfryParameterBuilder {

    public abstract Map<String, String> buildBelfryParameter(String jobId, String jobScheme,
                                                             String resultUrl, String runClass,
                                                             String runMethod);

}
