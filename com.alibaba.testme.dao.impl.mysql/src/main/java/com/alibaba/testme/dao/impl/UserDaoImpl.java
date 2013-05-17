package com.alibaba.testme.dao.impl;

import com.alibaba.testme.dao.UserDao;
import com.alibaba.testme.dao.impl.base.GenericDaoImpl;
import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.domain.query.UserQuery;

public class UserDaoImpl extends GenericDaoImpl<UserDO, UserQuery> implements UserDao{

	@Override
	public String getNameSpace() {
		return "user";
	}

}
