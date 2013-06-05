package com.alibaba.testme.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.testme.dao.SystemDao;
import com.alibaba.testme.domain.dataobject.SystemDO;
import com.alibaba.testme.service.SystemService;

/**
 * System Service Implement
 * 
 * @author xiaopenzi
 */
public class SystemServiceImpl implements SystemService {

    private SystemDao systemDao;

    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    /**
     * @param systemDO
     * @return
     */
    @Override
    public Long addSystemDO(SystemDO systemDO) {
        if (systemDO == null) {
            return null;
        }
        return systemDao.addSystemDO(systemDO);
    }

    /**
     * @param systemDO
     * @return
     */
    @Override
    public int updateSystemDO(SystemDO systemDO) {
        if (systemDO == null) {
            return 0;
        }
        return systemDao.updateSystemDO(systemDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteSystemDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return systemDao.deleteSystemDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return systemDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<SystemDO> findList(SystemDO systemDO) {
        return systemDao.findList(systemDO);
    }

    /**
     * @param idList
     * @return
     */
    public int delSystemDOByIds(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return 0;
        }
        return systemDao.delSystemDOByIds(idList);
    }

    @Override
    public SystemDO findByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return systemDao.findByName(name);
    }
}
