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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/index/*")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping
    public String shouye(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "index";
    }

    @RequestMapping
    public String top(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "top";
    }

    @RequestMapping
    public String left(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "left";
    }

    @RequestMapping
    public String mainfra(Model model, HttpServletRequest request) {
        LOGGER.info("Test user listAll .....");
        model.addAttribute("request", request);
        return "mainfra";
    }

}
