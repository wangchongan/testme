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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.testme.common.enums.TestunitFlowCaseStatusEnum;
import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.core.testunitflowcase.ITestunitFlowCaseHandler;
import com.alibaba.testme.core.testunitflowcase.dto.TestRequestDTO;
import com.alibaba.testme.core.testunitflowcase.dto.TestunitFlowCaseResult;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.query.TaskCreateParamQuery;
import com.alibaba.testme.domain.query.TestunitFlowCaseQuery;
import com.alibaba.testme.domain.vo.TestCaseVO;
import com.alibaba.testme.domain.vo.TestFlowParamVO;
import com.alibaba.testme.domain.vo.TestunitFlowCaseVO;
import com.alibaba.testme.service.SystemEnvService;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitFlowCaseService;
import com.alibaba.testme.service.TestunitFlowService;
import com.alibaba.testme.web.common.SessionUtils;

/**
 * 测试任务管理
 * 
 * @author lz
 */
@Controller
@RequestMapping(value = "/taskmanage/*")
public class TaskController {

    private static final Logger      logger = LoggerFactory.getLogger(TaskController.class);

    @Resource
    private SystemService            systemService;

    @Resource
    private SystemEnvService         systemEnvService;

    @Resource
    private TestunitFlowService      testunitFlowService;

    @Resource
    private TestunitFlowCaseService  testunitFlowCaseService;
    @Resource
    private ITestunitFlowCaseHandler iTestunitFlowCaseHandler;

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

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        TestCaseVO testCaseVO = this.testunitFlowCaseService.queryTestunitFlowCaseDetail(id);
        model.addAttribute("testCaseVO", testCaseVO);
        return "/taskmanage/taskDetail";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(Model model,
                         HttpServletRequest request,
                         @ModelAttribute("testunitFlowCaseQuery") TestunitFlowCaseQuery testunitFlowCaseQuery,
                         @PathVariable("id") Long id) {
        String resultMsg = "删除成功";
        try {
            //this.testunitFlowCaseService.deleteTestunitFlowCaseDO(id);
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("删除测试任务出错, TestunitFlowCaseID: " + id);
                resultMsg = "删除失败";
            }
        }
        model.addAttribute("resultMsg", resultMsg);
        return this.list(model, request, testunitFlowCaseQuery);
    }

    @RequestMapping
    public String taskCreate(Model model, HttpServletRequest request) {

        return "/taskmanage/taskCreate";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.POST)
    public String submitTask(Model model, HttpServletRequest request) {

        Map<String, String> userInputParamsMap = request.getParameterMap();
        TestRequestDTO testRequestDTO = new TestRequestDTO();

        Long systemEnvId = 0L;
        Long testunitFlowId = 0L;
        Long testunitFlowCaseId = 0L;
        Long userId = SessionUtils.getLoginUser(request).getId();

        testRequestDTO.setSystemEnvId(systemEnvId);
        testRequestDTO.setTestunitFlowCaseId(testunitFlowCaseId);
        testRequestDTO.setTestunitFlowId(testunitFlowId);
        testRequestDTO.setUserId(userId);

        testRequestDTO.setUserInputParamsMap(userInputParamsMap);
        TestunitFlowCaseResult testunitFlowCaseResult = null;

        // invoke TestunitFlowCaseHandler
        try {
            //            testunitFlowCaseResult = iTestunitFlowCaseHandler.deal(testRequestDTO);
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("处理任务错误: ", e);
            }
        }

        model.addAttribute("testunitFlowCaseResult", testunitFlowCaseResult);
        return "/taskmanage/dealResult";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getStatus(Model model,
                            HttpServletRequest request,
                            @ModelAttribute("testunitFlowCaseQuery") TestunitFlowCaseQuery testunitFlowCaseQuery,
                            @PathVariable("id") Long id) {
        String resultMsg = "删除成功";
        try {
            this.testunitFlowCaseService.deleteTestunitFlowCaseDO(id);
        } catch (Exception e) {
            if (logger.isWarnEnabled()) {
                logger.warn("删除测试任务出错, TestunitFlowCaseID: " + id);
                resultMsg = "删除失败";
            }
        }
        model.addAttribute("resultMsg", resultMsg);
        return this.list(model, request, testunitFlowCaseQuery);
    }

    /**
     * 分页获取任务创建列表(当任务创建页面发生改变时,触发分页查询)
     * 
     * @param model
     * @param request
     * @param pageNo
     * @param cellsPerPage
     * @param type
     * @param keyword
     * @return
     */
    @RequestMapping
    public String changePage(Model model, HttpServletRequest request,
                             @RequestParam("page_no") int pageNo,
                             @RequestParam("cells_per_page") int cellsPerPage,
                             @RequestParam("type") String type,
                             @RequestParam("keyword") String keyword) {

        TaskCreateParamQuery taskCreateParamQuery = new TaskCreateParamQuery();
        taskCreateParamQuery.setCellsPerPage(cellsPerPage);
        taskCreateParamQuery.setTag(keyword);

        List<TestFlowParamVO> testFlowParamList = this.testunitFlowService
                .getTaskCreateParam(taskCreateParamQuery);

        model.addAttribute("testFlowParamList", testFlowParamList);
        return "/taskmanage/changePage";
    }
}
