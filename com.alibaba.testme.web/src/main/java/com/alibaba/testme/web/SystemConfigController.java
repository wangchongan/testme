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

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/systemconfig/*")
public class SystemConfigController {

    @RequestMapping
    public String addSystemConfig(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "systemconfig/addSystemConfig";
    }

    @RequestMapping
    public String systemConfigList(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "systemconfig/systemConfigList";
    }

    @RequestMapping
    public String configList(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "systemconfig/configList";
    }

}
