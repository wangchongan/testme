package com.alibaba.testme.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.alibaba.testme.dao.WorkSpaceDao;
import com.alibaba.testme.domain.dataobject.WorkSpaceDO;

/**
 * WorkSpace Dao Implement
 * 
 * @author xiaopenzi
 */
public class WorkSpaceDaoImpl extends SqlMapClientDaoSupport implements WorkSpaceDao {

    /**
     * @param workSpaceDO
     * @return
     */
    @Override
    public Integer addWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        return (Integer) this.getSqlMapClientTemplate().insert("workSpace.add", workSpaceDO);
    }

    /**
     * @param workSpaceDO
     * @return
     */
    @Override
    public Integer updateWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        return (Integer) this.getSqlMapClientTemplate().update("workSpace.update", workSpaceDO);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Integer deleteWorkSpaceDO(Long id) {
        return (Integer) this.getSqlMapClientTemplate().delete("workSpace.deleteById", id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public WorkSpaceDO findById(Long id) {
        return (WorkSpaceDO) this.getSqlMapClientTemplate()
                .queryForObject("workSpace.findById", id);
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<WorkSpaceDO> findList(WorkSpaceDO workSpaceDO) {
        return (List<WorkSpaceDO>) this.getSqlMapClientTemplate().queryForList(
                "workSpace.findList", workSpaceDO);
    }

}
