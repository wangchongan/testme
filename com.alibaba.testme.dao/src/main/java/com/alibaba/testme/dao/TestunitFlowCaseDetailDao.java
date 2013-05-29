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
    public Long addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public int updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitFlowCaseDetailDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowCaseDetailDO findById(Long id);

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public List<TestunitFlowCaseDetailDO> findList(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * 获取最后一条测试实例节点记录
     * 
     * @param testunitFlowCaseId
     * @return
     */
    public TestunitFlowCaseDetailDO findLast(Long testunitFlowCaseId);
}
