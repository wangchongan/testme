package com.alibaba.testme.dao;

import com.alibaba.testme.domain.dataobject.UserDO;



public interface UserDao {

    UserDO find(String userName, String password);
	
}
