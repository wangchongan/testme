package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamDO;

/**
 * TestunitParam Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamDao {

    /**
     * @param testunitParamDO
     * @return
     */
    public Integer addTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param testunitParamDO
     * @return
     */
    public Integer updateTestunitParamDO(TestunitParamDO testunitParamDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitParamDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitParamDO findById(Long id);

    /**
     * @param testunitParamDO
     * @return
     */
    public List<TestunitParamDO> findList(TestunitParamDO testunitParamDO);

}
