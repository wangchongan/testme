package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;

/**
 * TestunitFlowCase Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowCaseService {

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    public Long addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO);

    /**
     * @param DO
     * @return
     */
    public Integer updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitFlowCaseDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowCaseDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitFlowCaseDO> findList(TestunitFlowCaseDO testunitFlowCaseDO);

}
