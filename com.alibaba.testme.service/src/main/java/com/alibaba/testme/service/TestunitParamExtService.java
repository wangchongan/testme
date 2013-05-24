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
    public Integer addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param DO
     * @return
     */
    public Integer updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitParamExtDO(Long id);

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
