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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.testme.domain.dataobject.SystemDO;
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
    private SystemService systemService;

    @RequestMapping
    public String addSystemConfig(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "systemconfig/addSystemConfig";
    }

    @RequestMapping
    public String systemConfigList(Model model, HttpServletRequest request) {
        List<SystemDO> systemDOList = systemService.findList(new SystemDO());
        model.addAttribute("request", request);
        model.addAttribute("systemDOList", systemDOList);
        return "systemconfig/systemConfigList";
    }

    @RequestMapping
    public String configList(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "systemconfig/configList";
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
        }
        systemDO.setCreator("sys");
        systemDO.setModifier("sys");
        Long result = systemService.addSystemDO(systemDO);
        if (result == null || result <= 0L) {
            resultMsg = "温馨提醒：系统信息保存失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！新增系统成功！";
        }
        model.addAttribute("request", request);
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
        model.addAttribute("request", request);
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
        int result = systemService.updateSystemDO(systemDO);
        if (result <= 0L) {
            resultMsg = "温馨提醒：系统信息更新失败";
        } else {
            resultMsg = "温馨提醒：恭喜你！更新系统成功！";
        }
        model.addAttribute("request", request);
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
        model.addAttribute("request", request);
        model.addAttribute("resultMsg", resultMsg);
        return "systemconfig/systemConfigList";
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
        model.addAttribute("request", request);
        model.addAttribute("resultMsg", resultMsg);
        return "systemconfig/systemConfigList";
    }
}
