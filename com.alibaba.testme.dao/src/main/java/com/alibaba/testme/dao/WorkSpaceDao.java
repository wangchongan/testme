package com.alibaba.testme.dao;

import java.util.List;

import com.alibaba.testme.domain.dataobject.WorkSpaceDO;

/**
 * WorkSpace Dao Interface
 * 
 * @author xiaopenzi
 */
public interface WorkSpaceDao {

    /**
     * @param workSpaceDO
     * @return
     */
    public int addWorkSpaceDO(WorkSpaceDO workSpaceDO);

    /**
     * @param workSpaceDO
     * @return
     */
    public int updateWorkSpaceDO(WorkSpaceDO workSpaceDO);

    /**
     * @param id
     * @return
     */
    public int deleteWorkSpaceDO(Integer id);

    /**
     * @param id
     * @return
     */
    public WorkSpaceDO findById(Integer id);

    /**
     * @param workSpaceDO
     * @return
     */
    public List<WorkSpaceDO> findList(WorkSpaceDO workSpaceDO);

}
