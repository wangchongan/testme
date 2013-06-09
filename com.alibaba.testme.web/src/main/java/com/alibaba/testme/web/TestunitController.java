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
import com.alibaba.testme.domain.dataobject.WorkSpaceDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitVO;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitService;
import com.alibaba.testme.service.WorkSpaceService;
import com.alibaba.testme.web.common.SessionUtils;

/**
 * 测试单元控制器
 * 
 * @author xiaopenzi
 */
@Controller
@RequestMapping(value = "/testunitmanage/*")
public class TestunitController {
    @Resource
    private WorkSpaceService workSpaceService;
    @Resource
    private SystemService    systemService;
    @Resource
    private TestunitService  testunitService;

    /**
     * 进入测试单元页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String testunitList(Model model, HttpServletRequest request) {
        //获取工作空间列表
        List<WorkSpaceDO> workSpaceDOList = workSpaceService.findList(new WorkSpaceDO());
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("workSpaceDOList", workSpaceDOList);
        model.addAttribute("systemDOList", systemDOList);

        return "testunitmanage/testunitList";
    }

    /**
     * 测试单元分页查询
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String testunitListByPage(Model model, HttpServletRequest request,
                                     @ModelAttribute("testunitQuery") TestunitQuery testunitQuery) {
        String resultMsg = null;
        if (testunitQuery == null) {
            testunitQuery = new TestunitQuery();
        }
        Page<TestunitVO> resultPage = testunitService.queryPage(testunitQuery.getPageIndex(),
                testunitQuery.getSizePerPage(), testunitQuery);
        if (resultPage == null) {
            resultMsg = "温馨提醒：异常原因，查询失败！";
        }
        model.addAttribute("userId", SessionUtils.getLoginUser(request).getId());
        model.addAttribute("testunitVOPage", resultPage);
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("testunitQuery", testunitQuery);

        return testunitList(model, request);
    }

}
