package com.alibaba.testme.service;

import java.util.List;

import com.alibaba.testme.domain.dataobject.WorkSpaceDO;

/**
 * WorkSpace Service Interface
 * 
 * @author xiaopenzi
 */
public interface WorkSpaceService {

    /**
     * @param workSpaceDO
     * @return
     */
    public int addWorkSpaceDO(WorkSpaceDO workSpaceDO);

    /**
     * @param DO
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
     * @param id
     * @return
     */
    public List<WorkSpaceDO> findList(WorkSpaceDO workSpaceDO);

}
