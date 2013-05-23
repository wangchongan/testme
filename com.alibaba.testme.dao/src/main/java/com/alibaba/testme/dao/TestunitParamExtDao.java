package com.alibaba.testme.dao;
import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;
/**
 * TestunitParamExt Dao Interface
 * @author xiaopenzi
 */
public interface TestunitParamExtDao {

	/**
	 * @param testunitParamExtDO
	 * @return
	 */
	public Long addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);
	
	/**
	 * @param testunitParamExtDO
	 * @return
	 */
	public Integer updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);
	
	/**
	 * @param id
	 * @return
	 */
	public Integer deleteTestunitParamExtDO(Long id);
	
	/**
	 * @param id
	 * @return
	 */
	public TestunitParamExtDO findById(Long id);
	
	/**
	 * @param testunitParamExtDO
	 * @return
	 */
	public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO);

}
