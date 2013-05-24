package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowDO;

/**
 * TestunitFlow Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowDao {

    /**
     * @param testunitFlowDO
     * @return
     */
    public int addTestunitFlowDO(TestunitFlowDO testunitFlowDO);

    /**
     * @param testunitFlowDO
     * @return
     */
    public int updateTestunitFlowDO(TestunitFlowDO testunitFlowDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowDO findById(Integer id);

    /**
     * @param testunitFlowDO
     * @return
     */
    public List<TestunitFlowDO> findList(TestunitFlowDO testunitFlowDO);

}
