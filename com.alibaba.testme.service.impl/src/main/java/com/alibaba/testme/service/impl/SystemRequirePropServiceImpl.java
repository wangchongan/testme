package com.alibaba.testme.service.impl;
import java.util.List;

import com.alibaba.testme.dao.SystemRequirePropDao;
import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;
import com.alibaba.testme.service.SystemRequirePropService;
/**
 * SystemRequireProp Service Implement
 * @author xiaopenzi
 */
public class SystemRequirePropServiceImpl implements SystemRequirePropService{

	private SystemRequirePropDao systemRequirePropDao;
	
	public void setSystemRequirePropDao(SystemRequirePropDao systemRequirePropDao){ 
		this.systemRequirePropDao = systemRequirePropDao; 
	}


	/**
	 * @param systemRequirePropDO
	 * @return
	 */
	@Override
	public Long addSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO){
		if(systemRequirePropDO == null){
		  return 0L;
		}
		return systemRequirePropDao.addSystemRequirePropDO(systemRequirePropDO);
	}
	
	/**
	 * @param systemRequirePropDO
	 * @return
	 */
	@Override
	public Integer updateSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO){
		if(systemRequirePropDO == null){
		  return 0;
		}
		return systemRequirePropDao.updateSystemRequirePropDO(systemRequirePropDO);
	
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteSystemRequirePropDO(Long id){
		if(id == null || id == 0L){
		  return 0;
		}
		return systemRequirePropDao.deleteSystemRequirePropDO(id);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public SystemRequirePropDO findById(Long id){
		if(id == null || id == 0L){
		  return null;
		}
		return systemRequirePropDao.findById(id);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Override
	public List<SystemRequirePropDO> findList(SystemRequirePropDO systemRequirePropDO){
		return systemRequirePropDao.findList(systemRequirePropDO);
	}

}
