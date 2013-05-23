package com.alibaba.testme.service;
import java.util.List;

import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
/**
 * SystemEnvDetail Service Interface
 * @author xiaopenzi
 */
public interface SystemEnvDetailService {

	/**
	 * @param systemEnvDetailDO
	 * @return
	 */
	public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);
	
	/**
	 * @param DO
	 * @return
	 */
	public Integer updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);
	
	/**
	 * @param id
	 * @return
	 */
	public Integer deleteSystemEnvDetailDO(Long id);
	
	/**
	 * @param id 
	 * @return
	 */
	public SystemEnvDetailDO findById(Long id);
	
	/**
	 * @param id
	 * @return
	 */
	public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO);

}
