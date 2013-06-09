package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitVO;

/**
 * Testunit Service Interface
 * 
 * @author xiaopenzi
 */
public interface TestunitService {

    /**
     * @param testunitDO
     * @return
     */
    public Long addTestunitDO(TestunitDO testunitDO);

    /**
     * @param DO
     * @return
     */
    public int updateTestunitDO(TestunitDO testunitDO);

    /**
     * @param id
     * @return
     */
    public int deleteTestunitDO(Long id);

    /**
     * @param id
     * @return
     */
    public TestunitDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<TestunitDO> findList(TestunitDO testunitDO);

    /**
     * 分页查询测试单元信息
     * 
     * @param index
     * @param sizePerPage
     * @param testunitQuery
     * @return
     */
    public Page<TestunitVO> queryPage(Integer index, Integer sizePerPage,
                                      TestunitQuery testUnitQuery);

}
