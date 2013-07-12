/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-7-12
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
package com.alibaba.testme.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.client.common.FeedbackResult;
import com.alibaba.testme.client.common.RemoteApiCallbackDataCache;

/**
 * TODO Comment of RemoteApiCallback
 * 
 * @author lz
 */
@Controller
@RequestMapping(value = "/callback/*")
public class RemoteApiCallback {

    public static final String         RESPOND_OK   = "OK";
    public static final String         RESPOND_FAIL = "FAIL";

    @Resource
    private RemoteApiCallbackDataCache remoteApiCallbackDataCache;

    @ResponseBody
    public String belfryInvoke(Model model, @RequestParam("result") String callBackResult) {
        FeedbackResult parseObject = JSON.parseObject(callBackResult, FeedbackResult.class);
        String jobId = parseObject.getJobRunId();
        remoteApiCallbackDataCache.put(jobId, parseObject);
        return RESPOND_OK;
    }
}
