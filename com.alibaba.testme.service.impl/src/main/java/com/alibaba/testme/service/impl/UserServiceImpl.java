package com.alibaba.testme.service.impl;

import org.apache.commons.lang.StringUtils;

import com.alibaba.testme.dao.UserDao;
import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDO authenticate(String userName, String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return null;
        }

        return userDao.find(userName, password);
    }

}
