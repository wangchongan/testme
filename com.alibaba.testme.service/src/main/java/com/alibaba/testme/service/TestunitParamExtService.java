package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;

/**
 * TestunitParamExt Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamExtService {

    /**
     * @param testunitParamExtDO
     * @return
     */
    public Long addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param testunitParamExtDOList
     * @return
     */
    public void batchSaveTestunitParamExtDO(List<TestunitParamExtDO> testunitParamExtDOList);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamExtDO(Long id);

    /**
     * 根据测试单元ID删除参数配置项信息
     * 
     * @param testunitId
     * @return
     */
    public int deleteByTestunitId(Long testunitId);

    /**
     * @param id
     * @return
     */
    public TestunitParamExtDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO);

}
