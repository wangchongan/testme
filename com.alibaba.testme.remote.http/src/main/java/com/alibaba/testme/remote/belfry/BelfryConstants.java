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

/**
 * TODO Comment of BelfryParameterBuilder
 * 
 * @author lz
 */
public final class BelfryConstants {

    public static final String KEY_FEEDBACK_RESULT           = "result";

    public static final String KEY_JOB_CONTENT               = "content";

    public static final String KEY_JOB_ID                    = "job_id";
    public static final String KEY_JOB_SCHEME                = "job_scheme";
    public static final String KEY_JOB_BEGIN_TIME            = "job_begin_time";
    public static final String KEY_RESULT_URL_LIST           = "return_url_list";

    // ===================================

    public static final String KEY_RUN_TYPE                  = "run_type";

    // ===================================
    public static final String KEY_ACTION                    = "action_type";

    // VLAUE For KEY_ACTION
    public static final String VALUE_ACTION_RUN_JOB          = "run";
    public static final String VALUE_ACTION_KILL_JOB         = "terminate";
    public static final String VALUE_ACTION_DETAIL           = "detail";

    //============================================
    public static final String PARAM_MAP_KEY_RUN_CLASS       = "run_class";
    public static final String PARAM_MAP_KEY_RUN_METHOD      = "run_method";
    public static final String PARAM_MAP_KEY_RUN_METHOD_ARGS = "run_method_args";

    public static final String PARAM_MAP_KEY_RUN_CLASS_PATH  = "run_class_path";
    public static final String PARAM_MAP_KEY_RUN_LIB_PATH    = "run_lib_path";
    public static final String PARAM_MAP_KEY_RUN_JVM_ARGS    = "run_jvm_args";

    public static final String PARAM_MAP_KEY_RUN_SHELL       = "run_shell";
    public static final String PARAM_MAP_KEY_RUN_SHELL_ARGS  = "run_shell_args";

}
