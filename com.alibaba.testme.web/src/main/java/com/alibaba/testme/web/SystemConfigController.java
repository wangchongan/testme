/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-5-21
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
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;
import com.alibaba.testme.domain.enums.ConfigTypeEnum;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;
import com.alibaba.testme.service.SystemEnvDetailService;
import com.alibaba.testme.service.SystemEnvService;
import com.alibaba.testme.service.SystemRequirePropService;
import com.alibaba.testme.service.SystemService;

/**
 * SystemConfigController 配置管理模块控制器
 * 
 * @author xiaopenzi
 */
@Controller
@RequestMapping(value = "/systemconfig/*")
public class SystemConfigController {
    @Resource
    private SystemService            systemService;
    @Resource
    private SystemEnvDetailService   systemEnvDetailService;
    @Resource
    private SystemRequirePropService systemRequirePropService;
    @Resource
    private SystemEnvService         systemEnvService;
    /**
     * 每页显示条数
     */
    private static final Integer     SIZE_PER_PAGE = 1;

    @RequestMapping
    public String addSystemConfig(Model model, HttpServletRequest request) {
        return "systemconfig/addSystemConfig";
    }

    @RequestMapping
    public String systemConfigList(Model model, HttpServletRequest request) {
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("systemDOList", systemDOList);
        return "systemconfig/systemConfigList";
    }

    /**
     * 保存系统信息
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveSystemConfig(Model model, HttpServletRequest request,
                                   @ModelAttribute("systemDO") SystemDO systemDO) {
        String resultMsg = null;
        if (systemDO == null || StringUtils.isBlank(systemDO.getName())) {
            resultMsg = "温馨提醒：参数不能为空！";
            model.addAttribute("resultMsg", resultMsg);
            return "systemconfig/addSystemConfig";
        }
        systemDO.setCreator("sys");
        systemDO.setModifier("sys");
        SystemDO entity = systemService.findByName(systemDO.getName());
        if (entity != null) {
            resultMsg = "温馨提醒：该系统名称已经存在,name:" + systemDO.getName();
            model.addAttribute("resultMsg", resultMsg);
            return "systemconfig/addSystemConfig";
        }
        Long result = systemService.addSystemDO(systemDO);
        if (result == null || result <= 0L) {
            resultMsg = "温馨提醒：系统信息保存失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！新增系统成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return "systemconfig/addSystemConfig";
    }

    /**
     * 进入系统配置编辑页面
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping
    public String editSystemConfig(Model model, HttpServletRequest request,
                                   @RequestParam("id") Long id) {
        String resultMsg = null;
        if (id == null) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        SystemDO systemDO = systemService.findById(id);
        if (systemDO == null) {
            resultMsg = "温馨提醒：不存在该系统信息";
        }
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("systemDO", systemDO);

        return "systemconfig/editSystemConfig";
    }

    /**
     * 更新系统配置
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String updateSystemConfig(Model model, HttpServletRequest request,
                                     @ModelAttribute("systemDO") SystemDO systemDO) {
        String resultMsg = null;
        if (systemDO == null || StringUtils.isBlank(systemDO.getName())) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        systemDO.setCreator("sys");
        systemDO.setModifier("sys");
        SystemDO entity = systemService.findByName(systemDO.getName());
        if (entity != null && !entity.getId().equals(systemDO.getId())) {
            resultMsg = "温馨提醒：该系统名称已经存在,name:" + systemDO.getName();
            model.addAttribute("resultMsg", resultMsg);
            return "systemconfig/editSystemConfig";
        }
        int result = systemService.updateSystemDO(systemDO);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息更新失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！更新系统成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("systemDO", systemDO);
        return "systemconfig/editSystemConfig";
    }

    @RequestMapping
    public String deleteSystemConfig(Model model, HttpServletRequest request,
                                     @RequestParam("id") Long id) {
        String resultMsg = null;
        if (id == null) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        int result = systemService.deleteSystemDO(id);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息删除失败,id:" + id;
        } else {
            resultMsg = "温馨提醒：恭喜你！系统信息删除成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return systemConfigList(model, request);
    }

    /**
     * 批量删除系统信息
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping
    public String batchDelSystemConfig(Model model, HttpServletRequest request,
                                       @RequestParam("ids") String ids) {
        String resultMsg = null;
        if (StringUtils.isBlank(ids)) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        String[] idArray = ids.split(",");
        if (idArray == null || idArray.length == 0) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        List<Long> idList = new ArrayList<Long>();
        for (int i = 0; i < idArray.length; i++) {
            idList.add(Long.parseLong(idArray[i]));
        }

        int result = systemService.delSystemDOByIds(idList);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息批量删除失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！系统信息批量删除成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return systemConfigList(model, request);
    }

    /**
     * 进入配置详情列表页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String configList(Model model, HttpServletRequest request) {
        Page<SystemConfigVO> resultPage = systemEnvDetailService.queryPage(1, SIZE_PER_PAGE,
                new SystemConfigQuery());
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        //获取配置类型
        List<ConfigTypeEnum> configTypeList = ConfigTypeEnum.getList();
        model.addAttribute("systemDOList", systemDOList);
        model.addAttribute("systemConfigVOPage", resultPage);
        model.addAttribute("configTypeList", configTypeList);
        return "systemconfig/configList";
    }

    /**
     * 分页查询配置详情
     * 
     * @param model
     * @param request
     * @param systemDO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String systemEnvDetailList(Model model,
                                      HttpServletRequest request,
                                      @RequestParam("pageIndex") int index,
                                      @RequestParam("sizePerPage") int sizePerPage,
                                      @ModelAttribute("systemConfigQuery") SystemConfigQuery systemConfigQuery) {
        String resultMsg = null;
        if (systemConfigQuery == null) {
            systemConfigQuery = new SystemConfigQuery();
        }
        if (index == 0) {
            index = 1;
        }
        if (sizePerPage == 0) {
            sizePerPage = SIZE_PER_PAGE;
        }

        Page<SystemConfigVO> resultPage = systemEnvDetailService.queryPage(index, sizePerPage,
                systemConfigQuery);
        if (resultPage == null) {
            resultMsg = "温馨提醒：异常原因，查询失败！";
        }
        model.addAttribute("systemConfigVOPage", resultPage);
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("systemConfigQuery", systemConfigQuery);
        return configList(model, request);
    }

    /**
     * 根据ID删除配置详情信息
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping
    public String deleteSystemEnvDetail(Model model, HttpServletRequest request,
                                        @RequestParam("id") Long id) {
        String resultMsg = null;
        if (id == null) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        int result = systemEnvDetailService.deleteSystemEnvDetailDO(id);
        if (result <= 0L) {
            resultMsg = "温馨提醒：配置信息详情删除失败,id:" + id;
        } else {
            resultMsg = "温馨提醒：恭喜你！配置信息详情删除成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return configList(model, request);
    }

    /**
     * 批量删除配置详情信息
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping
    public String batchDelEnvDetail(Model model, HttpServletRequest request,
                                    @RequestParam("ids") String ids) {
        String resultMsg = null;
        if (StringUtils.isBlank(ids)) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        String[] idArray = ids.split(",");
        if (idArray == null || idArray.length == 0) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        List<Long> idList = new ArrayList<Long>();
        for (int i = 0; i < idArray.length; i++) {
            idList.add(Long.parseLong(idArray[i]));
        }

        int result = systemEnvDetailService.delSystemEnvDetailDOByIds(idList);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息批量删除失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！系统信息批量删除成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return configList(model, request);
    }

    /**
     * 进入定义系统必要参数页面
     * 
     * @return
     */
    @RequestMapping
    public String addSystemRequiredProp(Model model, HttpServletRequest request) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("systemDOList", systemDOList);

        return "systemconfig/addSystemRequiredProp";
    }

    /**
     * 保存用户定义的系统必要参数
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveSystemRequiredProp(Model model,
                                         HttpServletRequest request,
                                         @ModelAttribute("systemRequirePropDO") SystemRequirePropDO systemRequirePropDO) {
        String resultMsg = null;
        if (systemRequirePropDO == null || StringUtils.isBlank(systemRequirePropDO.getPropCode())
                || StringUtils.isBlank(systemRequirePropDO.getPropName())
                || systemRequirePropDO.getSystemId() == null) {
            resultMsg = "温馨提醒：参数名称和所属系统不能为空！";
            model.addAttribute("resultMsg", resultMsg);
            return addSystemRequiredProp(model, request);
        }
        systemRequirePropDO.setCreator("sys");
        systemRequirePropDO.setModifier("sys");
        SystemRequirePropDO entity = systemRequirePropService.findByPropCodeAndSystemId(
                systemRequirePropDO.getSystemId(), systemRequirePropDO.getPropCode());
        if (entity != null) {
            resultMsg = "温馨提醒：该系统下参数编码为：" + systemRequirePropDO.getPropCode() + "已经存在！";
            model.addAttribute("resultMsg", resultMsg);
            return addSystemRequiredProp(model, request);
        }
        Long result = systemRequirePropService.addSystemRequirePropDO(systemRequirePropDO);
        if (result != null && result > 0) {
            resultMsg = "温馨提醒：恭喜你！必要参数信息保存成功！";
        } else {
            resultMsg = "温馨提醒：必要参数信息保存失败！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return addSystemRequiredProp(model, request);
    }

    /**
     * 进入配置必要参数页面
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public String configureSystemRequiredProp(Model model, HttpServletRequest request) {
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        SystemRequirePropDO systemRequirePropDO = new SystemRequirePropDO();
        systemRequirePropDO.setCreator("sys");
        List<SystemRequirePropDO> entityList = systemRequirePropService
                .findList(systemRequirePropDO);
        model.addAttribute("systemDOList", systemDOList);
        model.addAttribute("systemRequirePropDOList", entityList);

        return "systemconfig/configureSystemRequiredProp";
    }

    /**
     * 校验配置名称的唯一性
     * 
     * @param model
     * @param request
     * @param configName
     * @param systemId
     * @return
     */
    @RequestMapping
    public String isExistsConfigName(Model model, HttpServletRequest request,
                                     @RequestParam("configName") String configName) {
        String validteConfigNameMsg = null;
        SystemEnvDO entity = new SystemEnvDO();
        entity.setConfigName(configName);
        entity.setUserId(10L);
        List<SystemEnvDO> systemEnvDOList = systemEnvService.findList(entity);
        if (systemEnvDOList != null && systemEnvDOList.size() > 0) {
            validteConfigNameMsg = "亲！该配置名称已经存在哦！";
            model.addAttribute("validteConfigNameMsg", validteConfigNameMsg);
        }
        model.addAttribute("configName", configName);
        return configureSystemRequiredProp(model, request);
    }

    /**
     * 保存用户设置的系统必要参数值
     * 
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String saveSystemEnv(Model model, HttpServletRequest request,
                                @RequestParam("configName") String configName,
                                @RequestParam("systemId") Long systemId) {
        model.addAttribute("configName", configName);
        model.addAttribute("systemId", systemId);
        if (StringUtils.isBlank(configName) || systemId == null || systemId <= 0L) {
            return goConfigureSystemRequiredProp("温馨提醒：配置名称和所属系统不能为空！", model, request);
        }

        String[] systemRequirePropIds = request.getParameterValues("systemRequirePropId");
        if (systemRequirePropIds == null || systemRequirePropIds.length == 0) {
            return goConfigureSystemRequiredProp("温馨提醒：参数信息为空，无需保存！", model, request);
        }
        //保存系统环境参数信息
        Long result = saveSystemEnvDO(configName, systemId);
        if (result == null || result <= 0L) {
            return goConfigureSystemRequiredProp("温馨提醒：必要参数信息保存失败！", model, request);

        }
        //保存系统参数详情信息
        for (String id : systemRequirePropIds) {
            String propvalue = request.getParameter("propvalue_" + id);
            String nullable = request.getParameter("nullable_" + id);
            String propName = request.getParameter("propName_" + id);
            String propCode = request.getParameter("propCode_" + id);
            if (StringUtils.isBlank(nullable) || StringUtils.isBlank(propCode)
                    || StringUtils.isBlank(propName)) {
                return goConfigureSystemRequiredProp("温馨提醒：必要参数信息获取失败！请联系技术解决", model, request);
            }
            if ("N".equalsIgnoreCase(nullable) && StringUtils.isBlank(propvalue)) {
                return goConfigureSystemRequiredProp("温馨提醒：不可为空的参数必须有值", model, request);
            }

            Long saveResult = saveSystemEnvDetailDO(propCode, propvalue, propName, result);
            if (saveResult == null || saveResult <= 0L) {
                systemEnvDetailService.deleteByEnvId(result);
                systemEnvService.deleteSystemEnvDO(result);
                return goConfigureSystemRequiredProp("温馨提醒：参数信息保存失败，请重试！", model, request);
            }
        }
        return goConfigureSystemRequiredProp("温馨提醒：恭喜你！参数信息保存成功！", model, request);
    }

    private String goConfigureSystemRequiredProp(String resultMsg, Model model,
                                                 HttpServletRequest request) {
        model.addAttribute("resultMsg", resultMsg);
        return configureSystemRequiredProp(model, request);
    }

    private Long saveSystemEnvDO(String configName, Long systemId) {
        SystemEnvDO systemEnvDO = new SystemEnvDO();
        systemEnvDO.setConfigName(configName);
        systemEnvDO.setCreator("sys");
        systemEnvDO.setModifier("sys");
        systemEnvDO.setIsDefault("Y");
        systemEnvDO.setSystemId(systemId);
        systemEnvDO.setUserId(0L);

        return systemEnvService.addSystemEnvDO(systemEnvDO);
    }

    private Long saveSystemEnvDetailDO(String propCode, String propvalue, String propName,
                                       Long systemEnvId) {
        SystemEnvDetailDO systemEnvDetailDO = new SystemEnvDetailDO();
        systemEnvDetailDO.setCreator("sys");
        systemEnvDetailDO.setModifier("sys");
        systemEnvDetailDO.setPropKey(propCode);
        systemEnvDetailDO.setPropValue(propvalue);
        systemEnvDetailDO.setRemark(propName);
        systemEnvDetailDO.setSystemEnvId(systemEnvId);
        systemEnvDetailDO.setConfigType(ConfigTypeEnum.SYSTEM_REQUIRED.getKey());

        return systemEnvDetailService.addSystemEnvDetailDO(systemEnvDetailDO);
    }
}
