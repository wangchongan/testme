/**
 * Project: com.alibaba.testme.web
 * 
 * File Created at 2013-5-31
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
package com.alibaba.testme.web.sso;

import java.io.IOException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.buc.sso.client.handler.impl.SimpleSSOCallback;
import com.alibaba.buc.sso.client.util.BucSSOClientUtil;
import com.alibaba.platform.buc.sso.common.constants.BucSSOConstants;
import com.alibaba.platform.buc.sso.common.dto.User;
import com.alibaba.platform.buc.sso.common.tool.CookieUtil;
import com.alibaba.platform.buc.sso.common.tool.SSOEncodeUtil;

/**
 * TODO Comment of TestMeSSOCallback
 * @author lz
 *
 */
public class TestMeSSOCallback extends SimpleSSOCallback {
    public final static String         USER_COOKIE = "USER_COOKIE";
    private static final Logger        log         = LoggerFactory
                                                           .getLogger(TestMeSSOCallback.class);

    //    public static final String         SESSION_KEY_USERNAME = "username";

    private static ThreadLocal<String> loginName   = new ThreadLocal<String>();

    @Override
    public void beforeLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }

    @Override
    public boolean isRequestIgnored(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        return false;
    }

    @Override
    public boolean checkUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Cookie cookie = CookieUtil.getCookie(getCookieName(), request);
        String userJsonStr = null;
        if (cookie == null)
            return false;
        try {
            userJsonStr = SSOEncodeUtil.decodeCookieText(cookie.getValue());
        } catch (InvalidKeyException e) {
            log.error(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            log.error(e.getMessage(), e);
        } catch (BadPaddingException e) {
            log.error(e.getMessage(), e);
            cookie.setPath(BucSSOConstants.SLASH);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        if (StringUtils.isNotEmpty(userJsonStr)) {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(userJsonStr, User.class);
            loginName.set(user.getEmailPrefix());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addUser(User user, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (user != null) {
            try {
                ObjectMapper om = new ObjectMapper();
                String value = SSOEncodeUtil.encodeCookieText(om.writeValueAsString(user));

                CookieUtil.addCookie(getCookieName(), value, BucSSOConstants.TOKEN_MAX_AGE,
                        BucSSOConstants.SLASH, BucSSOClientUtil.getSsoCookieDomain(), response);
                loginName.set(user.getEmailPrefix());
            } catch (InvalidKeyException e) {
                log.error(e.getMessage(), e);
            } catch (IllegalBlockSizeException e) {
                log.error(e.getMessage(), e);
            } catch (BadPaddingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void removeUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CookieUtil.removeCookie(getCookieName(), BucSSOConstants.SLASH,
                BucSSOClientUtil.getSsoCookieDomain(), response);
        loginName.remove();
    }

    public static String getLoginUser(HttpServletRequest request) {
        return getLoginUser();
    }

    private static String getLoginUser() {
        return loginName.get();
    }

    @Override
    public String getCookieName() {
        return USER_COOKIE;
    }

    public static void removeUser() {
        loginName.remove();
    }
}
