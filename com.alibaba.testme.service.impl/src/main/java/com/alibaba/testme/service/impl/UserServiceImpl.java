package com.alibaba.testme.service.impl;

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
        String encryptPwd = "";
//        UserDO result = this.userDao.find(userName, encryptPwd);
        
        UserDO result = new UserDO();
        result.setUserName(userName);
        return result;
    }

}
