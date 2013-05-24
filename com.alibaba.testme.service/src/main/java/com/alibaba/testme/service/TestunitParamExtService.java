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
    public int addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamExtDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitParamExtDO findById(Integer id);

    /**
     * @param id
     * @return
     */
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO);

}
