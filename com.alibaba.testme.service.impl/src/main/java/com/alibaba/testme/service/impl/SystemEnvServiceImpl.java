package com.alibaba.testme.service.impl;

import java.util.List;

import com.alibaba.testme.dao.SystemEnvDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.service.SystemEnvService;

/**
 * SystemEnv Service Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvServiceImpl implements SystemEnvService {

    private SystemEnvDao systemEnvDao;

    public void setSystemEnvDao(SystemEnvDao systemEnvDao) {
        this.systemEnvDao = systemEnvDao;
    }

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public int addSystemEnvDO(SystemEnvDO systemEnvDO) {
        if (systemEnvDO == null) {
            return 0;
        }
        return systemEnvDao.addSystemEnvDO(systemEnvDO);
    }

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public int updateSystemEnvDO(SystemEnvDO systemEnvDO) {
        if (systemEnvDO == null) {
            return 0;
        }
        return systemEnvDao.updateSystemEnvDO(systemEnvDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteSystemEnvDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return systemEnvDao.deleteSystemEnvDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemEnvDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return systemEnvDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<SystemEnvDO> findList(SystemEnvDO systemEnvDO) {
        return systemEnvDao.findList(systemEnvDO);
    }

}
