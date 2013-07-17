/**
 * Project: com.alibaba.testme.remote.http
 * 
 * File Created at 2013-7-11
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
package com.alibaba.testme.remote.belfry;

import java.util.HashMap;
import java.util.Map;

/**
 * belfry请求参数构造器
 * 
 * @author lz
 */
public class BelfryParameterBuilderImpl implements BelfryParameterBuilder {

    @Override
    public Map<String, String> buildBelfryParameter(String jobId, String jobScheme,
                                                    String resultUrl, String runClass,
                                                    String runMethod) {

        // http://127.0.0.1:8090/container/direct?action_type=run&return_url_list=localhost&run_type=thread&job_id=243242&job_scheme=A
        //&run_method_args=a +b&run_class=com.alibaba.lp.event.ScheduleEventExecutorStarter&run_method=executeTask
        Map<String, String> params = new HashMap<String, String>();
        params.put(BelfryConstants.KEY_ACTION, "run");
        params.put(BelfryConstants.KEY_RESULT_URL_LIST, resultUrl);
        params.put(BelfryConstants.KEY_RUN_TYPE, "thread");

        params.put(BelfryConstants.KEY_JOB_ID, jobId);
        params.put(BelfryConstants.KEY_JOB_SCHEME, jobScheme);

        params.put(BelfryConstants.PARAM_MAP_KEY_RUN_CLASS, runClass);
        params.put(BelfryConstants.PARAM_MAP_KEY_RUN_METHOD, runMethod);

        params.put(BelfryConstants.PARAM_MAP_KEY_RUN_METHOD_ARGS, "a +b");
        return params;
    }
}
