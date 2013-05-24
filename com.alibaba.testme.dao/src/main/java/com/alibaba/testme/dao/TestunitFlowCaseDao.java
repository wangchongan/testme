package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;

/**
 * TestunitFlowCase Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowCaseDao {

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    public int addTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO);

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    public int updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowCaseDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowCaseDO findById(Integer id);

    /**
     * @param testunitFlowCaseDO
     * @return
     */
    public List<TestunitFlowCaseDO> findList(TestunitFlowCaseDO testunitFlowCaseDO);

}
