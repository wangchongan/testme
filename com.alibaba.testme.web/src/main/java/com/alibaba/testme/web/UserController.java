/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-6-3
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.service.UserService;

/**
 * TODO Comment of LoginController
 * 
 * @author lz
 */
@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model, HttpServletRequest request) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request,
                        @RequestParam(required = true) String userName,
                        @RequestParam(required = true) String password) {
        HttpSession session = request.getSession();

        UserDO user = userService.authenticate(userName, password);
        if (user != null) {
            session.setAttribute(CommonConstants.SESSION_USER, user);
            return "index";
        } else {
            model.addAttribute("errMsg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {

        return "index";
    }

}
