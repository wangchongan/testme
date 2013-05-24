package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowDO;

/**
 * TestunitFlow Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowService {

    /**
     * @param testunitFlowDO
     * @return
     */
    public Integer addTestunitFlowDO(TestunitFlowDO testunitFlowDO);

    /**
     * @param DO
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
     * @param id
     * @return
     */
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO);

}
