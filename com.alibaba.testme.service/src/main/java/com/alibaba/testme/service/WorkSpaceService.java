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
    public Long addWorkSpaceDO(WorkSpaceDO workSpaceDO);

    /**
     * @param DO
     * @return
     */
    public Integer updateWorkSpaceDO(WorkSpaceDO workSpaceDO);

    /**
     * @param id
     * @return
     */
    public Integer deleteWorkSpaceDO(Long id);

    /**
     * @param id
     * @return
     */
    public WorkSpaceDO findById(Long id);

    /**
     * @param id
     * @return
     */
    public List<WorkSpaceDO> findList(WorkSpaceDO workSpaceDO);

}
