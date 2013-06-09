/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-6-9
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

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestunitFlowVO;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitFlowService;
import com.alibaba.testme.service.TestunitService;
import com.alibaba.testme.web.common.SessionUtils;

/**
 * 测试单元流程控制器
 * 
 * @author xiaopenzi
 */
@Controller
@RequestMapping(value = "/testflowmanage/*")
public class TestunitFlowController {
    @Resource
    private SystemService       systemService;
    @Resource
    private TestunitFlowService testunitFlowService;
    @Resource
    private TestunitService     testunitService;

    /**
     * 进入测试单元流程页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String testunitFlowList(Model model, HttpServletRequest request) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("systemDOList", systemDOList);

        return "testflowmanage/testunitFlowList";
    }

    /**
     * 测试流程分页查询
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String testunitFlowListByPage(Model model,
                                         HttpServletRequest request,
                                         @ModelAttribute("testunitFlowQuery") TestunitFlowQuery testunitFlowQuery) {
        String resultMsg = null;
        if (testunitFlowQuery == null) {
            testunitFlowQuery = new TestunitFlowQuery();
        }
        Page<TestunitFlowVO> resultPage = testunitFlowService.queryPage(
                testunitFlowQuery.getPageIndex(), testunitFlowQuery.getSizePerPage(),
                testunitFlowQuery);
        if (resultPage == null) {
            resultMsg = "温馨提醒：异常原因，查询失败！";
        }
        model.addAttribute("userId", SessionUtils.getLoginUser(request).getId());
        model.addAttribute("testunitFlowVOPage", resultPage);
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("testunitFlowQuery", testunitFlowQuery);

        return testunitFlowList(model, request);
    }

    /**
     * 进入新增测试单元流程页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String addTestunitFlow(Model model, HttpServletRequest request) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        //获取测试单元列表
        List<TestunitDO> testunitDOList = testunitService.findList(new TestunitDO());
        model.addAttribute("testunitDOList", testunitDOList);
        model.addAttribute("systemDOList", systemDOList);

        return "testflowmanage/addTestunitFlow";
    }

}
