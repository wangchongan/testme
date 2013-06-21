package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TestunitParam Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamService {

    /**
     * @param testunitParamDO
     * @return
     */
    public Long addTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param testunitParamDOList
     * @return
     */
    public void batchSaveTestunitParamDO(List<TestunitParamDO> testunitParamDOList);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamDO(Long id);

    /**
     * 根据测试单元ID删除关联的参数信息
     * 
     * @param testunitId
     * @return
     */
    public int deleteByTestunitId(Long testunitId);

    /**
     * @param id
     * @return
     */
    public TestunitParamDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO);

}
