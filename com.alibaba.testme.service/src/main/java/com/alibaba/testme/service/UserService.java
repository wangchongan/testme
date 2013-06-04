package com.alibaba.testme.service;

import com.alibaba.testme.domain.dataobject.UserDO;

public interface UserService{

    UserDO authenticate(String userName, String password);

}
