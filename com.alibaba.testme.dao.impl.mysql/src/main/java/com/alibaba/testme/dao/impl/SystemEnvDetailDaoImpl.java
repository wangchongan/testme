package com.alibaba.testme.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.testme.common.ibatispage.Page;
import com.alibaba.testme.common.ibatispage.PageSqlMapClientDaoSupport;
import com.alibaba.testme.dao.SystemEnvDetailDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.domain.query.SystemConfigQuery;
import com.alibaba.testme.domain.vo.SystemConfigVO;

/**
 * SystemEnvDetail Dao Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvDetailDaoImpl extends PageSqlMapClientDaoSupport<SystemConfigVO> implements
        SystemEnvDetailDao {

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public Long addSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        return (Long) this.getSqlMapClientTemplate().insert("systemEnvDetail.add",
                systemEnvDetailDO);

    }

    /**
     * @param systemEnvDetailDO
     * @return
     */
    @Override
    public int updateSystemEnvDetailDO(SystemEnvDetailDO systemEnvDetailDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("systemEnvDetail.update",
                systemEnvDetailDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteSystemEnvDetailDO(Long id) {
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.deleteById", id);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemEnvDetailDO findById(Long id) {
        return (SystemEnvDetailDO) this.getSqlMapClientTemplate().queryForObject(
                "systemEnvDetail.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemEnvDetailDO> findList(SystemEnvDetailDO systemEnvDetailDO) {
        return (List<SystemEnvDetailDO>) this.getSqlMapClientTemplate().queryForList(
                "systemEnvDetail.findList", systemEnvDetailDO);
    }

    @Override
    public Page<SystemConfigVO> queryPage(Integer index, Integer sizePerPage,
                                          SystemConfigQuery systemConfigQuery) {
        return (Page<SystemConfigVO>) this.page(index, sizePerPage, systemConfigQuery,
                "systemEnvDetail.getCount", "systemEnvDetail.pageList");
    }

    @Override
    public int delSystemEnvDetailDOByIds(List<Long> idList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idList", idList);
        Integer result = (Integer) this.getSqlMapClientTemplate().delete(
                "systemEnvDetail.delSystemEnvDetailDOByIds", paramMap);
        if (result == null) {
            return 0;
        }

        return result;
    }

}
