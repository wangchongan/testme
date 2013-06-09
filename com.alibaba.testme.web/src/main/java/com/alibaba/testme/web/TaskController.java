/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-6-7
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
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TestunitFlowCaseQuery;
import com.alibaba.testme.domain.vo.TestunitFlowCaseVO;
import com.alibaba.testme.service.SystemEnvService;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowService;

/**
 * 测试任务管理
 * 
 * @author lz
 */
@Controller
@RequestMapping(value = "/taskmanage/*")
public class TaskController {

    @Resource
    private SystemService           systemService;

    @Resource
    private SystemEnvService        systemEnvService;

    @Resource
    private TestunitFlowService     testunitFlowService;

    @Resource
    private TestunitFlowCaseService testunitFlowCaseService;

    @ModelAttribute("pageName")
    public String getQueryPageName() {
        String result = "aaaaaaaa";
        return result;
    }

    public void initParam(Model model, HttpServletRequest request) {
        model.addAttribute("statusList", TestunitFlowCaseStatusEnum.values());
        model.addAttribute("systemList", this.systemService.findList(new SystemDO()));
        model.addAttribute("systemEnvList", this.systemEnvService.findList(new SystemEnvDO()));
        model.addAttribute("testunitFlowList",
                this.testunitFlowService.findList(new TestunitFlowDO()));
    }

    @RequestMapping
    public String init(Model model,
                       HttpServletRequest request,
                       @ModelAttribute("testunitFlowCaseQuery") TestunitFlowCaseQuery testunitFlowCaseQuery) {

        this.initParam(model, request);
        return "/taskmanage/taskList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String list(Model model,
                       HttpServletRequest request,
                       @ModelAttribute("testunitFlowCaseQuery") TestunitFlowCaseQuery testunitFlowCaseQuery) {
        Page<TestunitFlowCaseVO> queryPage = testunitFlowCaseService
                .queryPage(testunitFlowCaseQuery);
        model.addAttribute("queryPage", queryPage);
        this.initParam(model, request);
        return "/taskmanage/taskList";
    }
}
