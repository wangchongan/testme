package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;

/**
 * SystemEnvDetail Dao Interface
 * 
 * @author xiaopenzi
 */
public interface SystemEnvDetailDao {

    /**
     * @param systemEnvDetailDO
     * @return
     */
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO);

    /**
     * @param systemEnvDetailDO
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
     * @param systemEnvDetailDO
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

}
