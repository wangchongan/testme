package com.alibaba.testme.dao;
import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowDO;
/**
 * TestunitFlow Dao Interface
 * @author xiaopenzi
 */
public interface TestunitFlowDao {

	/**
	 * @param testunitFlowDO
	 * @return
	 */
	public Long addTestunitFlowDO(TestunitFlowDO testunitFlowDO);
	
	/**
	 * @param testunitFlowDO
	 * @return
	 */
	public Integer updateTestunitFlowDO(TestunitFlowDO testunitFlowDO);
	
	/**
	 * @param id
	 * @return
	 */
	public Integer deleteTestunitFlowDO(Long id);
	
	/**
	 * @param id
	 * @return
	 */
	public TestunitFlowDO findById(Long id);
	
	/**
	 * @param testunitFlowDO
	 * @return
	 */
	public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO);

}
