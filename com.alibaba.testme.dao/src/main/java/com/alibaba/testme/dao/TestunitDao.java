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

    /**
     * 根据测试单元ID获取测试单元详情信息
     * 
     * @param testunitId
     * @return
     */
    public TestunitVO findTestunitVOById(Long testunitId);

    /**
     * 重置bundle信息
     * 
     * @param testunitId
     * @return
     */
    public int setBundleInformationNull(Long testunitId, String modifier);

    /**
     * 更新bundle信息
     * 
     * @param testunitId 测试单元ID
     * @param symbolicName MF文件名
     * @param bundleVersion bundle版本
     * @param bundleFileName bundle文件名
     * @param classQualifiedName 类名
     * @param modifier 修改者
     * @return
     */
    public int updateBundleInformation(Long testunitId, String symbolicName, String bundleVersion,
                                       String bundleFileName, String classQualifiedName,
                                       String modifier);

}
