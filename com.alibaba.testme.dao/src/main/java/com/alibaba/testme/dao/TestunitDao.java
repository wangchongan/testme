package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.TestunitDO;
import com.alibaba.testme.domain.query.TestunitQuery;
import com.alibaba.testme.domain.vo.TestunitVO;

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
    public Long addTestunitDO(TestunitDO testunitDO);

    /**
     * @param testunitDO
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
     * @param testunitDO
     * @return
     */
    public List<TestunitDO> findList(TestunitDO testunitDO);

    /**
     * 分页查询测试单元信息
     * 
     * @param index
     * @param sizePerPage
     * @param testUnitQuery
     * @return
     */
    public Page<TestunitVO> queryPage(Integer index, Integer sizePerPage,
                                      TestunitQuery testunitQuery);

    /**
     * 根据ID获取测试单元信息
     * 
     * @param idList
     * @return
     */
    public List<TestunitDO> findByIdList(List<Long> idList);

    /**
     * 根据测试单元流程ID获取包含的测试单元信息
     * 
     * @param testunitFlowId
     * @return
     */
    public List<TestunitDO> findByTestunitFlowId(Long testunitFlowId);

}
