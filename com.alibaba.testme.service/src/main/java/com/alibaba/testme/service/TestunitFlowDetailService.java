package com.alibaba.testme.service;
import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;
/**
 * TestunitFlowDetail Service Interface
 * @author xiaopenzi
 */
public interface TestunitFlowDetailService {

	/**
	 * @param testunitFlowDetailDO
	 * @return
	 */
	public Long addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO);
	
	/**
	 * @param DO
	 * @return
	 */
	public Integer updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO);
	
	/**
	 * @param id
	 * @return
	 */
	public Integer deleteTestunitFlowDetailDO(Long id);
	
	/**
	 * @param id 
	 * @return
	 */
	public TestunitFlowDetailDO findById(Long id);
	
	/**
	 * @param id
	 * @return
	 */
	public List<TestunitFlowDetailDO> findList(TestunitFlowDetailDO testunitFlowDetailDO);

}
