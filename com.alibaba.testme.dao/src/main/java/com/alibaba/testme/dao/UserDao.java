package com.alibaba.testme.dao;

import com.alibaba.testme.common.basedao.GenericDao;
import com.alibaba.testme.domain.dataobject.UserDO;
import com.alibaba.testme.domain.query.UserQuery;


public interface UserDao extends GenericDao<UserDO, UserQuery> {
	
}
