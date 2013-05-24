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
    public int addWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().insert("workSpace.add",
                workSpaceDO);
        if (result == null) {
            return 0;
        }
        return result;
    }

    /**
     * @param workSpaceDO
     * @return
     */
    @Override
    public int updateWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        Integer result = (Integer) this.getSqlMapClientTemplate().update("workSpace.update",
                workSpaceDO);
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
    public int deleteWorkSpaceDO(Integer id) {
        Integer result = (Integer) this.getSqlMapClientTemplate()
                .delete("workSpace.deleteById", id);
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
    public WorkSpaceDO findById(Integer id) {
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
