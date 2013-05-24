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
    public int addTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitParamDO(Integer id);

    /**
     * @param id
     * @return
     */
    public TestunitParamDO findById(Integer id);

    /**
     * @param id
     * @return
     */
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO);

}
