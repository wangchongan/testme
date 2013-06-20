package com.alibaba.testme.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.UserDao;
import com.alibaba.testme.domain.dataobject.UserDO;

public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

    @Override
    public UserDO find(String userName, String password) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userName", userName);
        paramMap.put("password", password);
        return (UserDO) this.getSqlMapClientTemplate().queryForObject("user.findByNameAndPassword",
                paramMap);
    }

}
