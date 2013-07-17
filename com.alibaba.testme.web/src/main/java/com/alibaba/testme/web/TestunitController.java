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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.common.enums.FormCtrlTypeEnum;
import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.core.bundle.manager.TestMeBundleManager;
import com.alibaba.testme.core.utils.tuple.Tuple3;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.dataobject.TestunitParamDO;
import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;
import com.alibaba.testme.domain.dataobject.WorkSpaceDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitParamVO;
import com.alibaba.testme.domain.vo.TestunitVO;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.service.TestunitParamExtService;
import com.alibaba.testme.service.TestunitParamService;
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
    private WorkSpaceService        workSpaceService;
    @Resource
    private SystemService           systemService;
    @Resource
    private TestunitService         testunitService;
    @Resource
    private TestunitParamService    testunitParamService;
    @Resource
    private TestunitParamExtService testunitParamExtService;

    @Resource
    private TestMeBundleManager     testMeBundleManager;
    private static final Logger     logger         = LoggerFactory
                                                           .getLogger(TestunitController.class);
    private static final String     IS_ADD_PROCESS = "isAddProcess";

    //页面初始化
    private void init(Model model) {
        //获取工作空间列表
        List<WorkSpaceDO> workSpaceDOList = workSpaceService.findList(new WorkSpaceDO());

        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());

        model.addAttribute("workSpaceDOList", workSpaceDOList);
        model.addAttribute("systemDOList", systemDOList);
    }

    //页面初始化
    private void init(Model model, Long systemId) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());

        //获取工作空间列表
        Long id = null;
        if (systemId != null && systemId > 0L) {
            id = systemId;
        } else if (systemDOList != null && systemDOList.size() > 0) {
            id = systemDOList.get(0).getId();
        }

        //获取表单控件类型
        List<FormCtrlTypeEnum> formCtrlTypeEnumList = FormCtrlTypeEnum.getList();

        //获取工作空间列表
        WorkSpaceDO query = new WorkSpaceDO();
        query.setSystemId(id);
        List<WorkSpaceDO> workSpaceDOList = workSpaceService.findList(query);

        model.addAttribute("formCtrlTypeEnumList", formCtrlTypeEnumList);
        model.addAttribute("workSpaceDOList", workSpaceDOList);
        model.addAttribute("systemDOList", systemDOList);
    }

    /**
     * 进入测试单元页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String testunitList(Model model, HttpServletRequest request) {
        //初始化页面
        init(model);

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
        //分页查询
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

    /**
     * 进入测试单元详情页面
     * 
     * @param model
     * @param request
     * @param testunitId
     * @return
     */
    @RequestMapping
    public String testunitDetail(Model model, HttpServletRequest request,
                                 @RequestParam("testunitId") Long testunitId) {
        //前置校验
        if (testunitId == null || testunitId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元Id为空！");
            return testunitList(model, request);
        }

        //获取测试单元及包含的参数配置项信息
        TestunitVO testunitVO = getTestunitInformation(testunitId);
        model.addAttribute("testunitVO", testunitVO);

        return "testunitmanage/testunitDetail";
    }

    /**
     * 进入编辑测试单元页面
     * 
     * @param model
     * @param request
     * @param testunitId
     * @return
     */
    @RequestMapping
    public String editTestunit(Model model, HttpServletRequest request,
                               @RequestParam("testunitId") Long testunitId) {
        if (testunitId == null || testunitId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元Id为空！");
            return testunitList(model, request);
        }

        //页面初始化
        init(model, null);
        model.addAttribute(IS_ADD_PROCESS, false);

        //获取测试单元及包含的参数配置项信息
        TestunitVO testunitVO = getTestunitInformation(testunitId);
        model.addAttribute("testunitVO", testunitVO);

        return "testunitmanage/addEditTestunit";

    }

    /**
     * 删除bundlefile文件
     * 
     * @param model
     * @param request
     * @param testunitId
     * @param bundleFileName
     * @return
     */
    @RequestMapping
    public String deleteBundleFile(Model model, HttpServletRequest request,
                                   @RequestParam("testunitId") Long testunitId) {
        //前置校验
        if (testunitId == null || testunitId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元Id为空！");
            return editTestunit(model, request, testunitId);
        }

        //解部署并删除bundle文件
        String resultMsg = deleteAndUndeployBundleFile(testunitId, request);
        if (StringUtils.isNotBlank(resultMsg)) {
            model.addAttribute("resultMsg", resultMsg);
        }

        return editTestunit(model, request, testunitId);
    }

    /**
     * 删除测试单元信息
     * 
     * @return
     */
    @RequestMapping
    public String deleteTestunit(Model model, HttpServletRequest request,
                                 @RequestParam("testunitId") Long testunitId) {
        if (testunitId == null || testunitId <= 0L) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元Id为空！");
            return testunitList(model, request);
        }

        //解部署并删除bundle文件
        String resultMsg = deleteAndUndeployBundleFile(testunitId, request);
        if (StringUtils.isNotBlank(resultMsg)) {
            model.addAttribute("resultMsg", resultMsg);
            return testunitList(model, request);
        }

        //删除测试单元信息
        int result = testunitService.deleteTestunitDO(testunitId);
        if (result <= 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元信息删除失败！");
            return testunitList(model, request);
        }

        //先删除参数配置项信息,再删除参数信息
        testunitParamExtService.deleteByTestunitId(testunitId);
        result = testunitParamService.deleteByTestunitId(testunitId);
        if (result <= 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元信息删除失败！");
        }
        model.addAttribute("resultMsg", "温馨提醒：测试单元信息删除成功！");

        return testunitList(model, request);
    }

    /**
     * 进入新增测试单元页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String addEditTestunit(Model model, HttpServletRequest request) {
        //页面初始化
        init(model, null);
        model.addAttribute(IS_ADD_PROCESS, true);

        return "testunitmanage/addEditTestunit";
    }

    /**
     * 根据系统ID获取工作空间
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String getWorkSpaceListBySystemId(Model model, HttpServletRequest request,
                                             @RequestParam("systemId") Long systemId) {
        if (systemId != null && systemId > 0L) {
            //页面初始化
            init(model, systemId);
        }
        model.addAttribute("systemId", systemId);

        return "testunitmanage/addEditTestunit";
    }

    /**
     * 更新测试单元信息
     * 
     * @param model
     * @param request
     * @param testunitVO
     * @param configTableRownumberList
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String updateTestunit(Model model,
                                 HttpServletRequest request,
                                 @ModelAttribute("testunitVO") TestunitVO testunitVO,
                                 @RequestParam("configTableRownumberList") String configTableRownumberList) {
        //前置校验
        model.addAttribute("testunitVO", testunitVO);
        model.addAttribute("configTableRownumberList", configTableRownumberList);
        if (testunitVO == null) {
            model.addAttribute("resultMsg", "温馨提醒:参数获取失败！请联系技术解决！");
            return editTestunit(model, request, 0L);
        }

        //解析参数，将json串转化为List对象
        List<TestunitParamVO> testunitParamVOList = null;
        try {
            testunitParamVOList = JSON.parseArray(testunitVO.getTestunitParamVOStr(),
                    TestunitParamVO.class);
        } catch (Exception e) {
            model.addAttribute("resultMsg", "温馨提醒：解析测试单元参数失败！" + e.getMessage());
            return editTestunit(model, request, testunitVO.getTestunitId());
        }
        if (testunitParamVOList == null || testunitParamVOList.size() == 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元参数为空！");
            return editTestunit(model, request, testunitVO.getTestunitId());
        }
        testunitVO.setTestunitParamVOList(testunitParamVOList);

        //参数校验
        String resultMsg = valideParams(testunitVO);
        if (resultMsg != null) {
            model.addAttribute("resultMsg", resultMsg);
            return editTestunit(model, request, testunitVO.getTestunitId());
        }

        //判断测试单元对应的bundle信息是否存在，如果不存在，说明删除了老的bundle文件，此时需要保存并部署新的bundle文件
        TestunitDO testunitDO = testunitService.findById(testunitVO.getTestunitId());
        if (testunitDO == null || StringUtils.isBlank(testunitDO.getBundleFileName())
                || StringUtils.isBlank(testunitDO.getBundleVersion())
                || StringUtils.isBlank(testunitDO.getSymbolicName())) {
            //保存bundle文件
            resultMsg = saveAndDeployBundleFile(request, testunitVO);
            if (resultMsg != null) {
                model.addAttribute("resultMsg", resultMsg);
                return addEditTestunit(model, request);
            }
        }

        //更新测试单元信息
        resultMsg = buildAndSaveUpdateTestunit(testunitParamVOList, testunitVO, request, false);
        if (resultMsg != null) {
            model.addAttribute("resultMsg", resultMsg);
            return editTestunit(model, request, testunitVO.getTestunitId());
        }

        model.addAttribute("resultMsg", "温馨提醒：恭喜你！测试单元信息更新成功！");
        return addEditTestunit(model, request);
    }

    /**
     * 保存测试单元信息
     * 
     * @param model
     * @param request
     * @param systemId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveTestunit(Model model,
                               HttpServletRequest request,
                               @ModelAttribute("testunitVO") TestunitVO testunitVO,
                               @RequestParam("configTableRownumberList") String configTableRownumberList) {
        //前置校验
        model.addAttribute("testunitVO", testunitVO);
        model.addAttribute("configTableRownumberList", configTableRownumberList);
        if (testunitVO == null) {
            model.addAttribute("resultMsg", "温馨提醒:参数获取失败！请联系技术解决！");
            return addEditTestunit(model, request);
        }
        //解析JSON串
        List<TestunitParamVO> testunitParamVOList = null;
        try {
            testunitParamVOList = JSON.parseArray(testunitVO.getTestunitParamVOStr(),
                    TestunitParamVO.class);
        } catch (Exception e) {
            model.addAttribute("resultMsg", "温馨提醒：解析测试单元参数失败！" + e.getMessage());
            return addEditTestunit(model, request);
        }
        if (testunitParamVOList == null || testunitParamVOList.size() == 0) {
            model.addAttribute("resultMsg", "温馨提醒：测试单元参数为空！");
            return addEditTestunit(model, request);
        }
        testunitVO.setTestunitParamVOList(testunitParamVOList);
        String resultMsg = valideParams(testunitVO);
        if (resultMsg != null) {
            model.addAttribute("resultMsg", resultMsg);
            return addEditTestunit(model, request);
        }

        //唯一性校验，如果数据库中已经存在该测试单元，则忽略
        TestunitDO query = new TestunitDO();
        query.setName(testunitVO.getTestunitName());
        List<TestunitDO> testunitDOList = testunitService.findList(query);
        if (testunitDOList != null && testunitDOList.size() > 0) {
            model.addAttribute("resultMsg",
                    "温馨提醒:该测试单元名称已经存在！测试单元名称：" + testunitVO.getTestunitName());
            return addEditTestunit(model, request);
        }

        //保存bundle文件
        resultMsg = saveAndDeployBundleFile(request, testunitVO);
        if (resultMsg != null) {
            model.addAttribute("resultMsg", resultMsg);
            return addEditTestunit(model, request);
        }

        //保存测试单元信息
        resultMsg = buildAndSaveUpdateTestunit(testunitParamVOList, testunitVO, request, true);
        if (resultMsg != null) {
            model.addAttribute("resultMsg", resultMsg);
            return addEditTestunit(model, request);
        }

        model.addAttribute("resultMsg", "温馨提醒：恭喜你！测试单元信息保存成功！");
        return addEditTestunit(model, request);
    }

    //解部署bundle并删除bundle文件
    private String deleteAndUndeployBundleFile(Long testunitId, HttpServletRequest request) {
        //取得bundle文件的相关信息
        TestunitDO testunitDO = testunitService.findById(testunitId);
        if (testunitDO == null || StringUtils.isBlank(testunitDO.getSymbolicName())
                || StringUtils.isBlank(testunitDO.getBundleFileName())
                || StringUtils.isBlank(testunitDO.getBundleVersion())) {
            return "温馨提醒：部署的bundle文件信息为空！";
        }

        try {
            //解部署
            testMeBundleManager.undeploy(testunitDO.getSymbolicName(),
                    testunitDO.getBundleVersion());

            //删除bundlefile文件
            File file1 = new File(CommonConstants.BUNDLE_FILE_PATH + testunitDO.getBundleFileName());
            if (file1.exists()) {
                file1.delete();
            }

            //清空测试单元bundle文件信息
            testunitService.setBundleInformationNull(testunitId, SessionUtils.getLoginUser(request)
                    .getUserName());
        } catch (Exception e) {
            logger.error("delete bundle File error", e);
            return "温馨提醒：删除bundle文件失败！" + e.getMessage();
        }

        return null;
    }

    //保存并部署bundle file文件
    private String saveAndDeployBundleFile(HttpServletRequest request, TestunitVO testunitVO) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("bundleFile");
        if (multipartFile == null) {
            return "温馨提醒:bundle文件未上传！";
        }
        try {
            InputStream ins = multipartFile.getInputStream();
            File file1 = new File(CommonConstants.BUNDLE_FILE_PATH);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            String filePath = CommonConstants.BUNDLE_FILE_PATH
                    + multipartFile.getOriginalFilename();
            FileOutputStream out = new FileOutputStream(filePath);
            FileCopyUtils.copy(ins, out);

            File bundleFile = new File(filePath);

            //  校验bundle文件格式
            checkBundleFile(bundleFile);

            // 部署bundle文件到osgi容器
            Tuple3<String, String, String> tuple = this.testMeBundleManager.deploy(bundleFile);
            if (tuple == null) {
                return "温馨提醒：bundle文件部署失败！";
            }
            testunitVO.setSymbolicName(tuple._1());
            testunitVO.setBundleVersion(tuple._2());
            testunitVO.setBundleFileName(multipartFile.getOriginalFilename());

        } catch (Exception e) {
            logger.error("save bundle file error", e);
            return e.getMessage();
        }

        return null;
    }

    // 校验bundle文件格式
    private void checkBundleFile(File bundleFile) throws IOException {
        JarFile jar = new JarFile(bundleFile);
        Attributes attrs = jar.getManifest().getMainAttributes();
        String bundleVersion = attrs.getValue("Bundle-Version");
        String symbolicName = attrs.getValue("Bundle-SymbolicName");

        if (StringUtils.isBlank(symbolicName) || StringUtils.isBlank(bundleVersion)) {
            throw new RuntimeException("bundle 文件不合法");
        }
    }

    //前置校验
    private String valideParams(TestunitVO testunitVO) {
        if (StringUtils.isBlank(testunitVO.getTestunitName())) {
            return "温馨提醒:测试单元名称为空！";
        }
        if (StringUtils.isBlank(testunitVO.getTestunitCode())) {
            return "温馨提醒:测试单元编码为空！";
        }
        if (testunitVO.getSystemId() == null || testunitVO.getSystemId() <= 0L) {
            return "温馨提醒:测试单元所属系统为空！";
        }
        if (StringUtils.isBlank(testunitVO.getTestunitTag())) {
            return "温馨提醒:测试单元标签为空！";
        }
        if (StringUtils.isBlank(testunitVO.getCustomWorSpaceName())
                && testunitVO.getWorkSpaceId() == null || testunitVO.getWorkSpaceId() <= 0L) {
            return "温馨提醒:测试单元所属工作空间为空！";
        }
        if (StringUtils.isBlank(testunitVO.getClassQualifiedName())) {
            return "温馨提醒:测试单元包名及类名为空！";
        }
        if (StringUtils.isBlank(testunitVO.getTestunitParamVOStr())) {
            return "温馨提醒:测试单元包含的参数为空！";
        }

        return null;
    }

    //组装并保存测试单元信息
    private String buildAndSaveUpdateTestunit(List<TestunitParamVO> testunitParamVOList,
                                              TestunitVO testunitVO, HttpServletRequest request,
                                              boolean isAddProcess) {
        //组装并保存工作空间
        Long workSpaceId = testunitVO.getWorkSpaceId();
        if (StringUtils.isNotBlank(testunitVO.getCustomWorSpaceName())
                && (testunitVO.getWorkSpaceId() == null || testunitVO.getWorkSpaceId() <= 0L)) {
            workSpaceId = assembleWorkSpaceDO(testunitVO, request);
            if (workSpaceId == null || workSpaceId <= 0L) {
                return "温馨提醒：工作空间保存失败！";
            }
        }

        //组装并保存测试单元基本信息
        TestunitDO testunitDO = assembleTestunitDO(testunitVO, request, workSpaceId);
        Long testunitId = testunitVO.getTestunitId();
        if (isAddProcess) {
            testunitId = testunitService.addTestunitDO(testunitDO);
            if (testunitId == null || testunitId <= 0L) {
                return "温馨提醒：测试单元信息保存失败！";
            }
        } else {
            int result = testunitService.updateTestunitDO(testunitDO);
            if (result == 0) {
                return "温馨提醒：测试单元信息更新失败！";
            }
        }
        if (!isAddProcess) {
            testunitParamExtService.deleteByTestunitId(testunitId);
            testunitParamService.deleteByTestunitId(testunitId);
        }

        //组装并保存测试单元参数信息
        String resultMsg = assembleTestunitParamDO(testunitParamVOList, request, testunitId);
        if (resultMsg != null) {
            return resultMsg;
        }

        return null;
    }

    //组装并保存工作空间
    private Long assembleWorkSpaceDO(TestunitVO testunitVO, HttpServletRequest request) {
        WorkSpaceDO workSpaceDO = new WorkSpaceDO();
        workSpaceDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        workSpaceDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        workSpaceDO.setName(testunitVO.getCustomWorSpaceName());
        workSpaceDO.setSystemId(testunitVO.getSystemId());
        workSpaceDO.setUserId(SessionUtils.getLoginUser(request).getId());

        return workSpaceService.addWorkSpaceDO(workSpaceDO);
    }

    //组装测试单元基本信息
    private TestunitDO assembleTestunitDO(TestunitVO testunitVO, HttpServletRequest request,
                                          Long workSpaceId) {
        TestunitDO testunitDO = new TestunitDO();
        testunitDO.setClassQualifiedName(testunitVO.getClassQualifiedName());
        testunitDO.setCode(testunitVO.getTestunitCode());
        testunitDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        testunitDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        testunitDO.setName(testunitVO.getTestunitName());
        testunitDO.setRemark(testunitVO.getRemark());
        testunitDO.setTag(testunitVO.getTestunitTag());
        testunitDO.setWorkSpaceId(workSpaceId);
        testunitDO.setUserId(SessionUtils.getLoginUser(request).getId());
        testunitDO.setId(testunitVO.getTestunitId());
        testunitDO.setBundleFileName(testunitVO.getBundleFileName());
        testunitDO.setBundleVersion(testunitVO.getBundleVersion());
        testunitDO.setSymbolicName(testunitVO.getSymbolicName());

        return testunitDO;
    }

    //组装并保存测试单元参数信息
    private String assembleTestunitParamDO(List<TestunitParamVO> testunitParamVOList,
                                           HttpServletRequest request, Long testunitId) {
        for (TestunitParamVO testunitParamVO : testunitParamVOList) {
            //组装测试单元参数
            TestunitParamDO testunitParamDO = assembleTestunitParamDO(testunitParamVO, request,
                    testunitId);
            Long testunitParamId = testunitParamService.addTestunitParamDO(testunitParamDO);
            if (testunitParamId == null || testunitParamId <= 0L) {
                return "温馨提醒：测试单元参数信息保存失败！";
            }
            if (FormCtrlTypeEnum.SELECT_TYPE.getKey().equalsIgnoreCase(
                    testunitParamVO.getFormCtrlType())) {
                //组装并保存测试单元包含的参数配置项信息
                String resultMsg = assembleTestunitParamExtDO(request,
                        testunitParamVO.getTestunitParamExt(), testunitParamId);
                if (resultMsg != null) {
                    return resultMsg;
                }
            }
        }
        return null;
    }

    //组装测试单元参数DO
    private TestunitParamDO assembleTestunitParamDO(TestunitParamVO testunitParamVO,
                                                    HttpServletRequest request, Long testunitId) {
        TestunitParamDO testunitParamDO = new TestunitParamDO();
        testunitParamDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        testunitParamDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        testunitParamDO.setDefaultValue(testunitParamVO.getDefaultValue());
        testunitParamDO.setFormCtrlType(testunitParamVO.getFormCtrlType());
        testunitParamDO.setHelp(testunitParamVO.getHelp());
        testunitParamDO.setIsRequired(testunitParamVO.getIsRequired());
        testunitParamDO.setLabelName(testunitParamVO.getLabelName());
        testunitParamDO.setParamName(testunitParamVO.getParamName());
        testunitParamDO.setRank(testunitParamVO.getRank());
        testunitParamDO.setTestunitId(testunitId);

        return testunitParamDO;
    }

    //组装并保存测试单元包含的参数配置项信息
    private String assembleTestunitParamExtDO(HttpServletRequest request, String testunitParamExt,
                                              Long testunitParamId) {
        List<TestunitParamExtDO> testunitParamExtDOList = new ArrayList<TestunitParamExtDO>();
        String[] keyvalues = testunitParamExt.split(";");
        if (keyvalues == null || keyvalues.length == 0) {
            return "温馨提醒：测试单元参数配置项保存失败！";
        }
        for (int i = 0; i < keyvalues.length; i++) {
            String keyAndValue[] = keyvalues[i].split("=");
            if (keyAndValue == null || keyAndValue.length != 2) {
                continue;
            }
            TestunitParamExtDO testunitParamExtDO = new TestunitParamExtDO();
            testunitParamExtDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
            testunitParamExtDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
            testunitParamExtDO.setTestunitParamId(testunitParamId);
            testunitParamExtDO.setValueName(keyAndValue[0]);
            testunitParamExtDO.setValue(keyAndValue[1]);
            testunitParamExtDOList.add(testunitParamExtDO);
        }
        testunitParamExtService.batchSaveTestunitParamExtDO(testunitParamExtDOList);
        return null;
    }

    //获取测试单元对应的参数及配置项信息
    private TestunitVO getTestunitInformation(Long testunitId) {
        TestunitVO testunitVO = testunitService.findTestunitVOById(testunitId);
        TestunitParamDO query = new TestunitParamDO();
        query.setTestunitId(testunitId);
        List<TestunitParamDO> paramDOList = testunitParamService.findList(query);
        if (paramDOList == null || paramDOList.size() == 0) {
            return testunitVO;
        }
        List<TestunitParamVO> testunitParamVOList = new ArrayList<TestunitParamVO>();
        StringBuffer configTableRownumberList = new StringBuffer();
        for (int k = 0; k < paramDOList.size(); k++) {
            TestunitParamDO paramDO = paramDOList.get(k);
            TestunitParamVO testunitParamVO = new TestunitParamVO();
            testunitParamVO.setDefaultValue(paramDO.getDefaultValue());
            testunitParamVO.setFormCtrlType(paramDO.getFormCtrlType());
            testunitParamVO.setHelp(paramDO.getHelp());
            testunitParamVO.setTestunitParamId(paramDO.getId());
            testunitParamVO.setIsRequired(paramDO.getIsRequired());
            testunitParamVO.setLabelName(paramDO.getLabelName());
            testunitParamVO.setParamName(paramDO.getParamName());
            testunitParamVO.setRank(paramDO.getRank());
            testunitParamVO.setTestunitId(testunitId);
            int rowMarkNumber = Integer.parseInt(Long.toString(paramDO.getId() + 1000L));//行号置为1000以后的数字，防止跟新增的行号重复
            testunitParamVO.setRowMarkNumber(rowMarkNumber);
            configTableRownumberList.append(rowMarkNumber).append("#");
            if (FormCtrlTypeEnum.SELECT_TYPE.getKey().equalsIgnoreCase(paramDO.getFormCtrlType())) {
                TestunitParamExtDO extDO = new TestunitParamExtDO();
                extDO.setTestunitParamId(paramDO.getId());
                List<TestunitParamExtDO> paramExtList = testunitParamExtService.findList(extDO);
                if (paramExtList != null && paramExtList.size() > 0) {
                    StringBuffer testunitParamExt = new StringBuffer();
                    for (int i = 0; i < paramExtList.size(); i++) {
                        TestunitParamExtDO testunitParamExtDO = paramExtList.get(i);
                        testunitParamExt.append(testunitParamExtDO.getValueName()).append("=")
                                .append(testunitParamExtDO.getValue());
                        if (i != paramExtList.size() - 1) {
                            testunitParamExt.append(";");
                        }
                    }
                    testunitParamVO.setTestunitParamExt(testunitParamExt.toString());
                }
            }
            testunitParamVOList.add(testunitParamVO);
        }
        testunitVO.setConfigTableRownumberList(configTableRownumberList.toString());
        testunitVO.setTestunitParamVOList(testunitParamVOList);

        return testunitVO;
    }
}
