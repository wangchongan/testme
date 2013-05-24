package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.WorkSpaceDao;
import com.alibaba.testme.domain.dataobject.WorkSpaceDO;
import com.alibaba.testme.service.WorkSpaceService;

/**
 * WorkSpace Service Implement
 * 
 * @author xiaopenzi
 */
public class WorkSpaceServiceImpl implements WorkSpaceService {

    private WorkSpaceDao workSpaceDao;

    public void setWorkSpaceDao(WorkSpaceDao workSpaceDao) {
        this.workSpaceDao = workSpaceDao;
    }

    /**
     * @param workSpaceDO
     * @return
     */
    @Override
    public int addWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        if (workSpaceDO == null) {
            return 0;
        }
        return workSpaceDao.addWorkSpaceDO(workSpaceDO);
    }

    /**
     * @param workSpaceDO
     * @return
     */
    @Override
    public int updateWorkSpaceDO(WorkSpaceDO workSpaceDO) {
        if (workSpaceDO == null) {
            return 0;
        }
        return workSpaceDao.updateWorkSpaceDO(workSpaceDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteWorkSpaceDO(Integer id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return workSpaceDao.deleteWorkSpaceDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public WorkSpaceDO findById(Integer id) {
        if (id == null || id == 0L) {
            return null;
        }
        return workSpaceDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<WorkSpaceDO> findList(WorkSpaceDO workSpaceDO) {
        return workSpaceDao.findList(workSpaceDO);
    }

}
