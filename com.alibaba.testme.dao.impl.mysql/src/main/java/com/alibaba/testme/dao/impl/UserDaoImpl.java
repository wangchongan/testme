package com.alibaba.testme.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.UserDao;
import com.alibaba.testme.domain.dataobject.UserDO;

public class UserDaoImpl extends SqlMapClientDaoSupport  implements UserDao{

    @Override
    public UserDO find(String userName, String password) {
        return null;
    }

}
