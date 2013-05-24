package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;

/**
 * TestunitFlowCaseDetail Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowCaseDetailDao {

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public int addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public int updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowCaseDetailDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowCaseDetailDO findById(Integer id);

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public List<TestunitFlowCaseDetailDO> findList(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

}
