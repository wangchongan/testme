package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.TestunitDO;

/**
 * Testunit Dao Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitDao {

    /**
     * @param testunitDO
     * @return
     */
    public Integer addTestunitDO(TestunitDO testunitDO);

    /**
     * @param testunitDO
     * @return
     */
    public Integer updateTestunitDO(TestunitDO testunitDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteTestunitDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitDO findById(Long id);

    /**
     * @param testunitDO
     * @return
     */
    public List<TestunitDO> findList(TestunitDO testunitDO);

}
