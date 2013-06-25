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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
import com.alibaba.testme.domain.query.TestunitFlowQuery;
import com.alibaba.testme.domain.vo.TestunitFlowVO;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitFlowDetailService;
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
    private SystemService             systemService;
    @Resource
    private TestunitFlowService       testunitFlowService;
    @Resource
    private TestunitService           testunitService;
    @Resource
    private TestunitFlowDetailService testunitFlowDetailService;

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
        testunitFlowInit(model, request);
        return "testflowmanage/addTestunitFlow";
    }

    /**
     * 保存测试单元流程
     * 
     * @param model
     * @param request
     * @param testunitFlowDO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveTestunitFlow(Model model, HttpServletRequest request,
                                   @ModelAttribute("testunitFlowDO") TestunitFlowDO testunitFlowDO,
                                   @RequestParam("testunitIdStr") String testunitIdStr) {
        model.addAttribute("testunitFlowDO", testunitFlowDO);
        //前置校验
        String validateResult = validateParam(testunitFlowDO, testunitIdStr);
        if (StringUtils.isNotBlank(validateResult)) {
            model.addAttribute("resultMsg", validateResult);
            return addTestunitFlow(model, request);
        }
        String testunitIds[] = testunitIdStr.split("#");
        if (testunitIds == null || testunitIds.length == 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试流程包含的测试单元不能为空！");
            return addTestunitFlow(model, request);
        }
        //保存测试单元流程主体信息
        Long result = buildAndSaveTestunitFlowDO(request, testunitFlowDO);
        if (result <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元流程保存失败！");
            return addTestunitFlow(model, request);
        }
        //保存测试单元流程详情信息
        model.addAttribute("resultMsg", "温馨提醒：测试单元流程保存成功！");
        buildAndSaveTestunitFlowDetailDO(request, testunitIds, result);
        //设置测试单元列表
        setTestunitList(model, testunitIds);

        return addTestunitFlow(model, request);
    }

    /**
     * 进入编辑测试单元流程页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String goEditTestunitFlow(Model model, HttpServletRequest request,
                                     @RequestParam("testunitFlowId") Long testunitFlowId) {
        //初始化页面
        testunitFlowInit(model, request);
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            model.addAttribute("resultMsg", "测试单元流程ID为空！");
            return "testflowmanage/editTestunitFlow";
        }
        //根据ID获取测试单元流程主体信息
        TestunitFlowDO testunitFlowDO = testunitFlowService.findById(testunitFlowId);
        if (testunitFlowDO == null) {
            model.addAttribute("resultMsg", "测试单元流程信息为空！");
            return "testflowmanage/editTestunitFlow";
        }
        model.addAttribute("testunitFlowDO", testunitFlowDO);
        //根据测试单元流程ID获取所属测试单元信息
        List<TestunitDO> testunitDOSelectedList = testunitService
                .findByTestunitFlowId(testunitFlowId);
        if (testunitDOSelectedList == null || testunitDOSelectedList.size() == 0) {
            model.addAttribute("resultMsg", "测试单元流程所属测试单元信息为空！");
            return "testflowmanage/editTestunitFlow";
        }
        model.addAttribute("testunitDOSelectedList", testunitDOSelectedList);

        return "testflowmanage/editTestunitFlow";
    }

    /**
     * 更新测试单元流程信息
     */
    @RequestMapping(method = RequestMethod.POST)
    public String updateTestunitFlow(Model model,
                                     HttpServletRequest request,
                                     @ModelAttribute("testunitFlowDO") TestunitFlowDO testunitFlowDO,
                                     @RequestParam("testunitIdStr") String testunitIdStr) {
        //前置校验
        if (testunitFlowDO == null || testunitFlowDO.getId() == null
                || testunitFlowDO.getId() <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元流程ID为空！");
            return goEditTestunitFlow(model, request, 0L);
        }
        String validateResult = validateParam(testunitFlowDO, testunitIdStr);
        if (StringUtils.isNotBlank(validateResult)) {
            model.addAttribute("resultMsg", validateResult);
            return goEditTestunitFlow(model, request, testunitFlowDO.getId());
        }
        String testunitIds[] = testunitIdStr.split("#");
        if (testunitIds == null || testunitIds.length == 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试流程包含的测试单元不能为空！");
            return goEditTestunitFlow(model, request, testunitFlowDO.getId());
        }
        //更新测试单元流程主体信息
        testunitFlowDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        int result = testunitFlowService.updateTestunitFlowDO(testunitFlowDO);
        if (result == 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试流程更新失败！");
            return goEditTestunitFlow(model, request, testunitFlowDO.getId());
        }
        //更新测试单元流程详情信息
        testunitFlowDetailService.deleteByTestunitFlowId(testunitFlowDO.getId());
        buildAndSaveTestunitFlowDetailDO(request, testunitIds, testunitFlowDO.getId());

        model.addAttribute("resultMsg", "温馨提醒：测试流程更新成功！");
        return goEditTestunitFlow(model, request, testunitFlowDO.getId());
    }

    /**
     * 删除测试单元流程信息
     */
    @RequestMapping
    public String deleteTestunitFlow(Model model, HttpServletRequest request,
                                     @RequestParam("testunitFlowId") Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元流程ID为空！");
            return testunitFlowList(model, request);
        }
        testunitFlowService.deleteTestunitFlowDO(testunitFlowId);
        testunitFlowDetailService.deleteByTestunitFlowId(testunitFlowId);

        model.addAttribute("resultMsg", "温馨提醒：测试单元流程删除成功！");
        return testunitFlowList(model, request);
    }

    /**
     * 查看测试单元流程信息
     */
    @RequestMapping
    public String testunitFlowDetail(Model model, HttpServletRequest request,
                                     @RequestParam("testunitFlowId") Long testunitFlowId) {
        if (testunitFlowId == null || testunitFlowId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元流程ID为空！");
            return testunitFlowList(model, request);
        }
        TestunitFlowVO testunitFlowVO = testunitFlowService.queryById(testunitFlowId);
        List<TestunitDO> testunitDOList = testunitService.findByTestunitFlowId(testunitFlowId);
        if (testunitFlowVO != null && testunitDOList != null && testunitDOList.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < testunitDOList.size(); i++) {
                sb.append(testunitDOList.get(i).getName());
                if (i != testunitDOList.size() - 1) {
                    sb.append("--->");
                }
            }
            testunitFlowVO.setTestunitStr(sb.toString());
        }
        model.addAttribute("testunitFlowVO", testunitFlowVO);
        return "testflowmanage/testunitFlowDetail";
    }

    //测试单元流程页面初始化
    private void testunitFlowInit(Model model, HttpServletRequest request) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        //获取测试单元列表
        List<TestunitDO> testunitDOList = testunitService.findList(new TestunitDO());
        model.addAttribute("testunitDOList", testunitDOList);
        model.addAttribute("systemDOList", systemDOList);
    }

    //前置校验
    private String validateParam(TestunitFlowDO testunitFlowDO, String testunitIdStr) {
        if (testunitFlowDO == null || StringUtils.isBlank(testunitFlowDO.getCode())
                || StringUtils.isBlank(testunitFlowDO.getName())) {
            return "温馨提醒：测试单元流程编码和名称不能为空！";
        }
        if (StringUtils.isBlank(testunitFlowDO.getEnvConfigRequired())) {
            return "温馨提醒：是否需要参数配置必选！";
        }
        if (StringUtils.isBlank(testunitFlowDO.getTag())) {
            return "温馨提醒：测试单元流程标签不能为空！";
        }
        if (testunitFlowDO.getSystemId() == null || testunitFlowDO.getSystemId() <= 0L) {
            return "温馨提醒：测试单元流程所属系统不能为空！";
        }
        if (StringUtils.isBlank(testunitIdStr)) {
            return "温馨提醒：测试流程包含的测试单元不能为空！";
        }

        return null;
    }

    //构建并保存测试单元流程主体信息
    private Long buildAndSaveTestunitFlowDO(HttpServletRequest request,
                                            TestunitFlowDO testunitFlowDO) {
        testunitFlowDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        testunitFlowDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        testunitFlowDO.setIsActive("Y");
        testunitFlowDO.setTimes(0L);
        //TODO
        testunitFlowDO.setUserId(SessionUtils.getLoginUser(request).getId() == null ? 1L
                : SessionUtils.getLoginUser(request).getId());
        return testunitFlowService.addTestunitFlowDO(testunitFlowDO);
    }

    //构建并保存测试单元流程详情信息
    private void buildAndSaveTestunitFlowDetailDO(HttpServletRequest request, String[] testunitIds,
                                                  Long testunitFlowId) {
        List<TestunitFlowDetailDO> testunitFlowDetailList = new ArrayList<TestunitFlowDetailDO>();
        for (int i = 0; i < testunitIds.length; i++) {
            TestunitFlowDetailDO testunitFlowDetailDO = new TestunitFlowDetailDO();
            testunitFlowDetailDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
            testunitFlowDetailDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
            testunitFlowDetailDO.setTestunitFlowId(testunitFlowId);
            testunitFlowDetailDO.setTestunitId(Long.parseLong(testunitIds[i]));
            if (testunitIds.length > 1) {
                if (i == 0) {
                    testunitFlowDetailDO.setPreTestunitId(0L);
                    testunitFlowDetailDO.setNextTestunitId(Long.parseLong(testunitIds[i + 1]));
                } else if (i == testunitIds.length - 1) {
                    testunitFlowDetailDO.setPreTestunitId(Long.parseLong(testunitIds[i - 1]));
                    testunitFlowDetailDO.setNextTestunitId(0L);
                } else {
                    testunitFlowDetailDO.setPreTestunitId(Long.parseLong(testunitIds[i - 1]));
                    testunitFlowDetailDO.setNextTestunitId(Long.parseLong(testunitIds[i + 1]));
                }
            } else {
                testunitFlowDetailDO.setPreTestunitId(0L);
                testunitFlowDetailDO.setNextTestunitId(0L);
            }

            testunitFlowDetailList.add(testunitFlowDetailDO);
        }
        testunitFlowDetailService.batchSaveTestunitFlowDetail(testunitFlowDetailList);
    }

    //设置测试单元列表
    private void setTestunitList(Model model, String[] testunitIds) {
        LinkedHashMap<String, Object> testunitMap = new LinkedHashMap<String, Object>();
        List<Long> testunitIdList = new ArrayList<Long>();
        for (int i = 0; i < testunitIds.length; i++) {
            Long testunitId = Long.parseLong(testunitIds[i]);
            testunitIdList.add(testunitId);
        }
        List<TestunitDO> testunitDOList = testunitService.findByIdList(testunitIdList);
        Map<Long, String> map = new HashMap<Long, String>();
        if (testunitDOList != null && testunitDOList.size() > 0) {
            for (int j = 0; j < testunitDOList.size(); j++) {
                map.put(testunitDOList.get(j).getId(), testunitDOList.get(j).getName());
            }
        }
        for (int k = 0; k < testunitIds.length; k++) {
            testunitMap.put(testunitIds[k], map.get(Long.parseLong(testunitIds[k])));
        }
        model.addAttribute("testunitMap", testunitMap);
    }
}
