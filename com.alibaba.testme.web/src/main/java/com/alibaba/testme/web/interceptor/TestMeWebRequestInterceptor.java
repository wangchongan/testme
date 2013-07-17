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
package com.alibaba.testme.web.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.web.common.PageUtil;

/**
 * TODO Comment of ContentPathInterceptor
 * 
 * @author lz
 */
public class TestMeWebRequestInterceptor extends HandlerInterceptorAdapter {

    private static final String USER_LOGIN = "/user/login";

    @Resource
    private PageUtil            pageUtil;
    private List<String>        whiteList;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        boolean handlerOk = super.preHandle(request, response, handler);
        if (handlerOk) {
            String url = request.getRequestURL().toString();
            if (whiteList != null && !whiteList.isEmpty()) {
                for (String whiteUrl : whiteList) {
                    if (url.endsWith(whiteUrl))
                        return true;
                }
            }

            HttpSession session = request.getSession();
            UserDO user = (UserDO) session.getAttribute(CommonConstants.SESSION_USER);
            if (user != null) {
                return true;
            }
            response.sendRedirect(request.getContextPath() + USER_LOGIN);
        }
        return false;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("request", request);
        if (!StringUtils.equals(modelAndView.getViewName(), "redirect:/")) {
            modelAndView.addObject("contextPath", request.getContextPath());
        }
        modelAndView.addObject("pageUtil", pageUtil);
    }
}
