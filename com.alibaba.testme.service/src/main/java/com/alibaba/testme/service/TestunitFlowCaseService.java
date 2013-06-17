package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.TestunitFlowCaseDO;
import com.alibaba.testme.domain.query.TestunitFlowCaseQuery;
import com.alibaba.testme.domain.vo.TestCaseVO;
import com.alibaba.testme.domain.vo.TestunitFlowCaseVO;

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
    public int updateTestunitFlowCaseDO(TestunitFlowCaseDO testunitFlowCaseDO);

    /**
     * @param id
     */
    public void deleteTestunitFlowCaseDO(Long id);

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

    public Page<TestunitFlowCaseVO> queryPage(TestunitFlowCaseQuery testunitFlowCaseQuery);

    public TestCaseVO queryTestunitFlowCaseDetail(Long id);

}
