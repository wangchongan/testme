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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.testme.common.enums.ConfigTypeEnum;
import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;
import com.alibaba.testme.service.SystemEnvDetailService;
import com.alibaba.testme.service.SystemEnvService;
import com.alibaba.testme.service.SystemRequirePropService;
import com.alibaba.testme.service.SystemService;
import com.alibaba.testme.web.common.SessionUtils;

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
     * 上传文件大小限制
     */
    private static final Long        MAX_SIZE   = Long.valueOf(5 * 1024 * 1024);
    /**
     * 文件后缀
     */
    private static final String      PROPERTIES = "properties";

    private static final Logger      logger     = LoggerFactory
                                                        .getLogger(SystemConfigController.class);

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
        systemDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
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
    @RequestMapping
    public String updateSystemConfig(Model model, HttpServletRequest request,
                                     @RequestParam("systemId") Long systemId,
                                     @RequestParam("name") String name,
                                     @RequestParam("remark") String remark) {
        String resultMsg = null;
        if (systemId == null || systemId <= 0L || StringUtils.isBlank(name)
                || StringUtils.isBlank(remark)) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        SystemDO systemDO = new SystemDO();
        systemDO.setId(systemId);
        systemDO.setName(name);
        systemDO.setRemark(remark);
        systemDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        SystemDO entity = systemService.findByName(systemDO.getName());
        if (entity != null && !entity.getId().equals(systemDO.getId())) {
            resultMsg = "温馨提醒：该系统名称已经存在,name:" + systemDO.getName();
            model.addAttribute("resultMsg", resultMsg);
            return systemConfigList(model, request);
        }
        int result = systemService.updateSystemDO(systemDO);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息更新失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！更新系统成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return systemConfigList(model, request);
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
        //获取系统列表
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        //获取配置类型
        List<ConfigTypeEnum> configTypeList = ConfigTypeEnum.getList();
        model.addAttribute("systemDOList", systemDOList);
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
                                      @ModelAttribute("systemConfigQuery") SystemConfigQuery systemConfigQuery) {
        String resultMsg = null;
        if (systemConfigQuery == null) {
            systemConfigQuery = new SystemConfigQuery();
        }
        systemConfigQuery.setUserId(SessionUtils.getLoginUser(request).getId());
        Page<SystemConfigVO> resultPage = systemEnvDetailService.queryPage(
                systemConfigQuery.getPageIndex(), systemConfigQuery.getSizePerPage(),
                systemConfigQuery);
        if (resultPage == null) {
            resultMsg = "温馨提醒：异常原因，查询失败！";
        }
        model.addAttribute("systemConfigVOPage", resultPage);
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
     * 根据ID删除配置详情信息
     * 
     * @param model
     * @param request
     * @param id
     * @return
     */
    @RequestMapping
    public String deleteSystemEnvDetailBySystemEnv(Model model, HttpServletRequest request,
                                                   @RequestParam("id") Long id,
                                                   @RequestParam("systemEnvId") Long systemEnvId) {
        String resultMsg = null;
        if (id == null || id <= 0L || systemEnvId == null || systemEnvId <= 0L) {
            resultMsg = "温馨提醒：参数不能为空！";
        }
        int result = systemEnvDetailService.deleteSystemEnvDetailDO(id);
        if (result <= 0L) {
            resultMsg = "温馨提醒：配置信息详情删除失败,id:" + id;
        } else {
            resultMsg = "温馨提醒：恭喜你！配置信息详情删除成功！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return editSystemEnv(model, request, systemEnvId);
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
        systemRequirePropDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemRequirePropDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
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
        systemRequirePropDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
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
        entity.setUserId(SessionUtils.getLoginUser(request).getId());
        List<SystemEnvDO> systemEnvDOList = systemEnvService.findList(entity);
        if (systemEnvDOList != null && systemEnvDOList.size() > 0) {
            validteConfigNameMsg = "亲！该配置名称已经存在哦！";
            model.addAttribute("validteConfigNameMsg", validteConfigNameMsg);
        }
        model.addAttribute("configName", configName);
        return configureSystemRequiredProp(model, request);
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
    public String isExistsConfigNameBySystemEnv(Model model, HttpServletRequest request,
                                                @RequestParam("configName") String configName,
                                                @RequestParam("systemEnvId") Long systemEnvId) {
        String validteConfigNameMsg = null;
        SystemEnvDO entity = new SystemEnvDO();
        entity.setConfigName(configName);
        entity.setUserId(SessionUtils.getLoginUser(request).getId());
        List<SystemEnvDO> systemEnvDOList = systemEnvService.findList(entity);
        if (systemEnvDOList != null && systemEnvDOList.size() > 0) {
            validteConfigNameMsg = "亲！该配置名称已经存在哦！";
            model.addAttribute("validteConfigNameMsg", validteConfigNameMsg);
        }
        model.addAttribute("configName", configName);
        return editSystemEnv(model, request, systemEnvId);
    }

    /**
     * 编辑参数值
     * 
     * @param model
     * @param request
     * @param systemEnvDetailId
     * @param propValue
     * @return
     */
    @RequestMapping
    public String editPropValue(Model model, HttpServletRequest request,
                                @RequestParam("systemEnvDetailId") Long systemEnvDetailId,
                                @RequestParam("propValue") String propValue) {
        String resultMsg = null;
        if (StringUtils.isBlank(propValue) || systemEnvDetailId == null || systemEnvDetailId <= 0L) {
            resultMsg = "温馨提醒：参数值和参数详情ID不能为空！";
            model.addAttribute("resultMsg", resultMsg);
            return configList(model, request);
        }
        String modifier = SessionUtils.getLoginUser(request).getUserName();
        int result = systemEnvDetailService.updatePropValue(systemEnvDetailId, propValue, modifier);
        if (result == 0) {
            resultMsg = "温馨提醒：参数值更新失败！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return configList(model, request);

    }

    /**
     * 编辑参数值
     * 
     * @param model
     * @param request
     * @param systemEnvDetailId
     * @param propValue
     * @return
     */
    @RequestMapping
    public String editPropValueBySystemEnv(Model model,
                                           HttpServletRequest request,
                                           @RequestParam("systemEnvDetailId") Long systemEnvDetailId,
                                           @RequestParam("propValue") String propValue,
                                           @RequestParam("systemEnvId") Long systemEnvId) {
        String resultMsg = null;
        if (StringUtils.isBlank(propValue) || systemEnvDetailId == null || systemEnvDetailId <= 0L
                || systemEnvId == null || systemEnvId <= 0L) {
            resultMsg = "温馨提醒：参数值和参数详情ID不能为空！";
            model.addAttribute("resultMsg", resultMsg);
            return editSystemEnv(model, request, systemEnvId);
        }
        String modifier = SessionUtils.getLoginUser(request).getUserName();
        int result = systemEnvDetailService.updatePropValue(systemEnvDetailId, propValue, modifier);
        if (result == 0) {
            resultMsg = "温馨提醒：参数值更新失败！";
        }
        model.addAttribute("resultMsg", resultMsg);
        return editSystemEnv(model, request, systemEnvId);

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
        Long result = saveSystemEnvDO(configName, systemId, request);
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

            Long saveResult = saveSystemEnvDetailDO(propCode, propvalue, propName, result, request);
            if (saveResult == null || saveResult <= 0L) {
                systemEnvDetailService.deleteByEnvId(result, null);
                systemEnvService.deleteSystemEnvDO(result);
                return goConfigureSystemRequiredProp("温馨提醒：参数信息保存失败，请重试！", model, request);
            }
        }
        return goConfigureSystemRequiredProp("温馨提醒：恭喜你！参数信息保存成功！", model, request);
    }

    /**
     * 进入参数配置信息页面systemEnv
     * 
     * @return
     */
    @RequestMapping
    public String editSystemEnv(Model model, HttpServletRequest request,
                                @RequestParam("systemEnvId") Long systemEnvId) {
        if (systemEnvId == null || systemEnvId <= 0L) {
            model.addAttribute("resultMsg", "系统环境ID不能为空！");
            return configList(model, request);
        }
        SystemConfigQuery systemConfigQuery = new SystemConfigQuery();
        systemConfigQuery.setSystemEnvId(systemEnvId);
        List<SystemConfigVO> systemConfigVOList = systemEnvDetailService
                .findByConditions(systemConfigQuery);
        if (systemConfigVOList != null && systemConfigVOList.size() > 0) {
            model.addAttribute("configName", systemConfigVOList.get(0).getConfigName());
            model.addAttribute("systemEnvId", systemConfigVOList.get(0).getSystemEnvId());
            model.addAttribute("systemId", systemConfigVOList.get(0).getSystemId());
            model.addAttribute("systemName", systemConfigVOList.get(0).getSystemName());
        }
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("systemDOList", systemDOList);
        model.addAttribute("systemConfigVOList", systemConfigVOList);

        return "systemconfig/editSystemEnv";
    }

    /**
     * 更新配置名称和所属系统
     * 
     * @param model
     * @param request
     * @param systemEnvId
     * @return
     */
    @RequestMapping
    public String updateSystemEnv(Model model, HttpServletRequest request,
                                  @RequestParam("systemEnvId") Long systemEnvId,
                                  @RequestParam("configName") String configName,
                                  @RequestParam("systemId") Long systemId) {
        if (StringUtils.isBlank(configName) || systemEnvId == null || systemEnvId <= 0L
                || systemId == null || systemId <= 0L) {
            model.addAttribute("resultMsg", "配置名称和所属系统不能为空！");
            return editSystemEnv(model, request, systemEnvId);
        }
        SystemEnvDO entity = new SystemEnvDO();
        entity.setConfigName(configName);
        entity.setUserId(10L);
        List<SystemEnvDO> systemEnvDOList = systemEnvService.findList(entity);
        if (systemEnvDOList != null && systemEnvDOList.size() > 0) {
            model.addAttribute("resultMsg", "亲！该配置名称已经存在哦！");
        }
        SystemEnvDO systemEnvDO = new SystemEnvDO();
        systemEnvDO.setConfigName(configName);
        systemEnvDO.setId(systemEnvId);
        systemEnvDO.setSystemId(systemId);
        systemEnvDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        int result = systemEnvService.updateSystemEnvDO(systemEnvDO);
        if (result == 0) {
            model.addAttribute("resultMsg", "配置名称和所属系统更新失败！");
        }

        return editSystemEnv(model, request, systemEnvId);
    }

    /**
     * 往指定配置名称中添加一个参数
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveSystemEnvDetail(Model model, HttpServletRequest request,
                                      @RequestParam("systemEnvId") Long systemEnvId,
                                      @RequestParam("systemId") Long systemId,
                                      @RequestParam("propKey") String propKey,
                                      @RequestParam("propValue") String propValue,
                                      @RequestParam("remark") String remark) {
        String resultMsg = null;
        if (systemEnvId == null || systemEnvId <= 0L || systemId == null || systemId <= 0L
                || StringUtils.isBlank(propKey) || StringUtils.isBlank(propValue)
                || StringUtils.isBlank(remark)) {
            resultMsg = "温馨提醒：参数不能为空！";
            model.addAttribute("resultMsg", resultMsg);
            return editSystemEnv(model, request, systemEnvId);
        }
        SystemEnvDetailDO systemEnvDetailDO = new SystemEnvDetailDO();
        systemEnvDetailDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setPropKey(propKey);
        systemEnvDetailDO.setPropValue(propValue);
        systemEnvDetailDO.setRemark(remark);
        systemEnvDetailDO.setSystemEnvId(systemEnvId);
        SystemRequirePropDO query = new SystemRequirePropDO();
        query.setCreator(SessionUtils.getLoginUser(request).getUserName());
        query.setPropCode(propKey);
        query.setSystemId(systemId);
        List<SystemRequirePropDO> systemRequirePropDOList = systemRequirePropService
                .findList(query);
        if (systemRequirePropDOList == null || systemRequirePropDOList.size() == 0) {//不在用户定义的必要参数内
            systemEnvDetailDO.setConfigType(ConfigTypeEnum.USER_CUSTOM.getKey());
        } else {
            systemEnvDetailDO.setConfigType(ConfigTypeEnum.SYSTEM_REQUIRED.getKey());
        }
        //查询该参数是否已经存在
        SystemConfigQuery systemConfigQuery = new SystemConfigQuery();
        systemConfigQuery.setSystemEnvId(systemEnvId);
        systemConfigQuery.setPropKey(propKey);
        List<SystemConfigVO> systemConfigVOList = systemEnvDetailService
                .findByConditions(systemConfigQuery);
        if (systemConfigVOList != null && systemConfigVOList.size() > 0) {
            resultMsg = "温馨提醒：该参数已经存在,参数名：" + propKey;
            model.addAttribute("resultMsg", resultMsg);
            return editSystemEnv(model, request, systemEnvId);
        }
        Long result = systemEnvDetailService.addSystemEnvDetailDO(systemEnvDetailDO);
        if (result == null || result <= 0L) {
            resultMsg = "温馨提醒：添加参数保存失败，请重试！";
        } else {
            resultMsg = "温馨提醒：恭喜你!添加参数成功！";
        }
        model.addAttribute("resultMsg", resultMsg);

        return editSystemEnv(model, request, systemEnvId);
    }

    /**
     * 进入参数导入页面
     * 
     * @return
     */
    @RequestMapping
    public String importConfig(Model model, HttpServletRequest request) {
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("systemDOList", systemDOList);
        return "systemconfig/importConfig";
    }

    /**
     * 导入配置页面：校验配置名称
     */
    @RequestMapping
    public String validateConfigName(Model model, HttpServletRequest request,
                                     @RequestParam("configName") String configName,
                                     @RequestParam("systemId") Long systemId) {
        model.addAttribute("configName", configName);
        model.addAttribute("systemId", systemId);
        if (StringUtils.isBlank(configName) || systemId == null || systemId <= 0L) {
            model.addAttribute("validateResult", "配置名称和所属系统不能为空");
            return importConfig(model, request);
        }
        //如果该配置名称已经存在，且属于该用户创建的，则无需重新创建，否则需要重新创建
        SystemEnvDO systemEnvDO = new SystemEnvDO();
        systemEnvDO.setConfigName(configName);
        List<SystemEnvDO> systemEnvList = systemEnvService.findList(systemEnvDO);
        if (systemEnvList == null || systemEnvList.size() == 0) {
            return importConfig(model, request);
        } else {
            for (SystemEnvDO envDO : systemEnvList) {
                if (!envDO.getUserId().equals(SessionUtils.getLoginUser(request).getId())) {
                    model.addAttribute("validateResult", "配置名称重复！");
                    return importConfig(model, request);
                } else if (!envDO.getSystemId().equals(systemId)) {
                    model.addAttribute("validateResult", "您已经创建了同样的配置名称，但所属系统不相同，所属系统ID为："
                            + systemId);
                    return importConfig(model, request);
                }
            }
        }
        return importConfig(model, request);
    }

    /**
     * 导入参数配置
     * 
     * @param model
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST)
    public String importConfigProcess(Model model, HttpServletRequest request,
                                      @RequestParam("configName") String configName,
                                      @RequestParam("systemId") Long systemId) {
        model.addAttribute("configName", configName);
        model.addAttribute("systemId", systemId);
        if (StringUtils.isBlank(configName) || systemId == null || systemId <= 0L) {
            return goImportConfig("配置名称和所属系统不能为空", model, request);
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("configFile");
        String resultMsg = null;
        InputStream inputStream = null;
        //基本校验
        resultMsg = simpleCheck(multipartFile);
        if (StringUtils.isNotBlank(resultMsg)) {
            return goImportConfig(resultMsg, model, request);
        }
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            logger.error("import config error", e);
            return goImportConfig(e.getMessage(), model, request);
        }
        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            logger.error("load property file error", e);
            return goImportConfig("加载配置文件失败" + e.getMessage(), model, request);
        }
        if (property == null || property.entrySet() == null || property.entrySet().size() == 0) {
            return goImportConfig("没有参数配置记录", model, request);
        }

        //保存参数记录
        return deleteAndSaveEnvDetails(configName, systemId, property, model, request);
    }

    private String goImportConfig(String resultMsg, Model model, HttpServletRequest request) {
        model.addAttribute("resultMsg", resultMsg);
        return importConfig(model, request);
    }

    /*
     * 保存系统环境配置，先删除该配置名称对应的所有用户自定义类型的参数信息，再批量插入配置信息
     */
    @Transactional
    private String deleteAndSaveEnvDetails(String configName, Long systemId, Properties property,
                                           Model model, HttpServletRequest request) {
        SystemEnvDO systemEnvDO = new SystemEnvDO();
        systemEnvDO.setConfigName(configName);
        List<SystemEnvDO> systemEnvList = systemEnvService.findList(systemEnvDO);
        Long systemEnvId = null;
        if (systemEnvList == null || systemEnvList.size() == 0) {
            Long result = saveSystemEnvDO(configName, systemId, request);
            if (result == null || result <= 0L) {
                return goImportConfig("参数配置保存失败，请重试！", model, request);
            }
            systemEnvId = result;
        } else {
            systemEnvId = systemEnvList.get(0).getId();
        }
        List<SystemEnvDetailDO> systemEnvDetailList = new ArrayList<SystemEnvDetailDO>();
        for (Iterator<Entry<Object, Object>> it = property.entrySet().iterator(); it.hasNext();) {
            Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
            systemEnvDetailList.add(buildEnvDetails(systemEnvId, entry, request));
        }
        try {
            systemEnvDetailService.deleteByEnvId(systemEnvId, ConfigTypeEnum.USER_CUSTOM.getKey());
            systemEnvDetailService.batchSaveEnvDetail(systemEnvDetailList);
        } catch (Exception e) {
            logger.error("deleteAndSaveEnvDetails error", e);
            return goImportConfig(e.getMessage(), model, request);
        }

        return goImportConfig("恭喜你！导入成功！", model, request);
    }

    /*
     * 组装参数详情信息
     */
    private SystemEnvDetailDO buildEnvDetails(Long systemEnvId, Entry<Object, Object> entry,
                                              HttpServletRequest request) {
        SystemEnvDetailDO systemEnvDetailDO = new SystemEnvDetailDO();
        systemEnvDetailDO.setConfigType(ConfigTypeEnum.USER_CUSTOM.getKey());
        systemEnvDetailDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setPropKey(String.valueOf(entry.getKey()));
        systemEnvDetailDO.setPropValue(String.valueOf(entry.getValue()));
        systemEnvDetailDO.setRemark(String.valueOf(entry.getKey()));
        systemEnvDetailDO.setSystemEnvId(systemEnvId);

        return systemEnvDetailDO;
    }

    private String simpleCheck(MultipartFile multipartFile) {
        // 判断上传文件是否为空
        if (multipartFile == null || multipartFile.getName() == null) {
            return "上传的文件为空!";
        }
        if (multipartFile.getSize() == 0) {
            return "上传的文件大小为空!";
        }
        // 判断上传文件的大小是否超出限制
        if (multipartFile.getSize() > MAX_SIZE) {
            return "上传的文件大小超过最大值：" + MAX_SIZE / (1024 * 1024) + "M!";
        }
        // 判断上传文件是否为允许处理的类型
        String extesion = getExtension(multipartFile.getOriginalFilename());
        if (!PROPERTIES.equalsIgnoreCase(extesion)) {
            return "文件格式错误，请上传.properties文件";
        }
        return null;
    }

    private String goConfigureSystemRequiredProp(String resultMsg, Model model,
                                                 HttpServletRequest request) {
        model.addAttribute("resultMsg", resultMsg);
        return configureSystemRequiredProp(model, request);
    }

    private Long saveSystemEnvDO(String configName, Long systemId, HttpServletRequest request) {
        SystemEnvDO systemEnvDO = new SystemEnvDO();
        systemEnvDO.setConfigName(configName);
        systemEnvDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDO.setIsDefault("Y");
        systemEnvDO.setSystemId(systemId);
        systemEnvDO.setUserId(0L);

        return systemEnvService.addSystemEnvDO(systemEnvDO);
    }

    private Long saveSystemEnvDetailDO(String propCode, String propvalue, String propName,
                                       Long systemEnvId, HttpServletRequest request) {
        SystemEnvDetailDO systemEnvDetailDO = new SystemEnvDetailDO();
        systemEnvDetailDO.setCreator(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setModifier(SessionUtils.getLoginUser(request).getUserName());
        systemEnvDetailDO.setPropKey(propCode);
        systemEnvDetailDO.setPropValue(propvalue);
        systemEnvDetailDO.setRemark(propName);
        systemEnvDetailDO.setSystemEnvId(systemEnvId);
        systemEnvDetailDO.setConfigType(ConfigTypeEnum.SYSTEM_REQUIRED.getKey());

        return systemEnvDetailService.addSystemEnvDetailDO(systemEnvDetailDO);
    }

    /**
     * 获取扩展名
     * 
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}
