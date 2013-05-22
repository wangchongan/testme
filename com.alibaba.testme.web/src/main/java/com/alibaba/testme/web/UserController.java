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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.domain.query.UserQuery;
import com.alibaba.testme.service.UserService;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {
    public UserController() {
        super();
        System.out.println("hello init ");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private UserService         userService;

    @RequestMapping(method=RequestMethod.GET)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Test user listAll .....");

        List<UserDO> userList = null;
        try {
            
            userList = userService.findList(null);
            
        } catch (Exception ex) {
            LOGGER.error("listAll", ex);
        }
        model.addAttribute("userName", "张三");
        model.addAttribute("userList", userList);
        model.addAttribute("request", request);
        return "user/userList";
    }
    
    
    @RequestMapping(value="/user/init")
    public String init() {
        LOGGER.info("Test user init .....");
        try {
            UserDO entity = new UserDO();
            entity.setCreator("SYS");
            entity.setGmtCreate(new Date());
            entity.setModifier("SYS");
            entity.setGmtModified(new Date());
            entity.setUserName("李四");
            entity.setPassword("007");
            userService.add(entity );
            
        } catch (Exception ex) {
            LOGGER.error("listAll", ex);
        }
        return "user/userList";
    }
    
    
    @RequestMapping(method=RequestMethod.POST,value="/user/save")
    public String save(@RequestParam(value="password") String password) {
        LOGGER.info("Test user init .....");
        try {
            UserDO entity = new UserDO();
            entity.setCreator("SYS");
            entity.setGmtCreate(new Date());
            entity.setModifier("SYS");
            entity.setGmtModified(new Date());
            entity.setUserName("aaaaaa");
            entity.setPassword(password);
            userService.add(entity );
            
        } catch (Exception ex) {
            LOGGER.error("listAll", ex);
        }
        return "user/userList";
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/userList")
    public List<UserDO> userList(Model model) {
        UserQuery userQuery = new UserQuery();
        userQuery.setUserName("李四");
        List<UserDO> userList = userService.findList(userQuery);
//        JSON.toJSONString(userList);
       return  userList;
    }
    
}
