/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-6-4
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
package com.alibaba.testme.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.testme.common.constants.CommonConstants;
import com.alibaba.testme.domain.dataobject.UserDO;

/**
 * TODO Comment of SessionUtils
 * 
 * @author lz
 */
public class SessionUtils {

    /**
     * 从 session 中获取当前登录用户
     * 
     * @param session 当前session中的用户
     * @return
     */
    public static UserDO getLoginUser(HttpServletRequest request) {
        UserDO user = null;
        if (request != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                user = (UserDO) session.getAttribute(CommonConstants.SESSION_USER);
            }
        }
        return user;
    }
}
