package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDetailDO;

/**
 * TestunitFlowCaseDetail Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitFlowCaseDetailService {

    /**
     * @param testunitFlowCaseDetailDO
     * @return
     */
    public Long addTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param DO
     * @return
     */
    public Integer updateTestunitFlowCaseDetailDO(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitFlowCaseDetailDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitFlowCaseDetailDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitFlowCaseDetailDO> findList(TestunitFlowCaseDetailDO testunitFlowCaseDetailDO);

}
