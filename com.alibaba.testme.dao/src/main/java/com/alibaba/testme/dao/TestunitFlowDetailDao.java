package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowDetailDO;

/**
 * TestunitFlowDetail Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowDetailDao {

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    public Long addTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO);

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    public int updateTestunitFlowDetailDO(TestunitFlowDetailDO testunitFlowDetailDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowDetailDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowDetailDO findById(Long id);

    /**
     * @param testunitFlowDetailDO
     * @return
     */
    public List<TestunitFlowDetailDO> findList(TestunitFlowDetailDO testunitFlowDetailDO);

}
