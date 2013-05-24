package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitParamExtDO;

/**
 * TestunitParamExt Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitParamExtDao {

    /**
     * @param testunitParamExtDO
     * @return
     */
    public int addTestunitParamExtDO(TestunitParamExtDO testunitParamExtDO);

    /**
     * @param testunitParamExtDO
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
     * @param testunitParamExtDO
     * @return
     */
    public List<TestunitParamExtDO> findList(TestunitParamExtDO testunitParamExtDO);

}
