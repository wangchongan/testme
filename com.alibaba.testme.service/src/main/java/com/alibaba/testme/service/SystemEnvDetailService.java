package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;

/**
 * SystemEnvDetail Service Interface
 * 
 * @author xiaopenzi
 */
public interface SystemEnvDetailService {

    /**
     * @param systemEnvDetailDO
     * @return
     */
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * 批量保存
     * 
     * @param systemEnvDetailDOList
     */
    public void batchSaveEnvDetail(List<SystemEnvDetailDO> systemEnvDetailDOList);

    /**
     * @param DO
     * @return
     */
    public int updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * @param id
     * @return
     */
    public int deleteSystemEnvDetailDO(Long id);

    /**
     * @param id
     * @return
     */
    public SystemEnvDetailDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * 分页查询配置信息
     * 
     * @param index
     * @param sizePerPage
     * @param systemConfigQuery
     * @return
     */
    public Page<SystemConfigVO> queryPage(Integer index, Integer sizePerPage,
                                          SystemConfigQuery systemConfigQuery);

    /**
     * @param idList
     * @return
     */
    public int delSystemEnvDetailDOByIds(List<Long> idList);

    /**
     * 根据系统环境ID和配置类型删除参数详情记录，配置类型可为空
     */
    public int deleteByEnvId(Long systemEnvId, String configType);

    /**
     * 修改参数值
     * 
     * @param systemEnvDetailId
     * @param propValue
     * @param modifier
     * @return
     */
    public int updatePropValue(Long systemEnvDetailId, String propValue, String modifier);

    /**
     * 根据动态条件获取配置信息
     * 
     * @param systemConfigQuery
     * @return
     */
    public List<SystemConfigVO> findByConditions(SystemConfigQuery systemConfigQuery);

}
