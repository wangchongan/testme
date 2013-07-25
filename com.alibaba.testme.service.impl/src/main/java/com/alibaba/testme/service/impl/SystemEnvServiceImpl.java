package com.alibaba.testme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.testme.dao.SystemEnvDao;
import com.alibaba.testme.dao.SystemEnvDetailDao;
import com.alibaba.testme.domain.dataobject.SystemEnvDO;
import com.alibaba.testme.domain.dataobject.SystemEnvDetailDO;
import com.alibaba.testme.service.SystemEnvService;

/**
 * SystemEnv Service Implement
 * 
 * @author xiaopenzi
 */
public class SystemEnvServiceImpl implements SystemEnvService {

    private SystemEnvDao       systemEnvDao;
    private SystemEnvDetailDao systemEnvDetailDao;

    public void setSystemEnvDao(SystemEnvDao systemEnvDao) {
        this.systemEnvDao = systemEnvDao;
    }

    /**
     * @param systemEnvDetailDao the systemEnvDetailDao to set
     */
    public void setSystemEnvDetailDao(SystemEnvDetailDao systemEnvDetailDao) {
        this.systemEnvDetailDao = systemEnvDetailDao;
    }

    /**
     * @param systemEnvDO
     * @return
     */
    @Override
    public Long addSystemEnvDO(SystemEnvDO systemEnvDO) {
        if (systemEnvDO == null) {
            return null;
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

    /*
     * (non-Javadoc)
     * @see
     * com.alibaba.testme.service.SystemEnvService#buildSystemEnvParamsMap(java
     * .lang.Long)
     */
    @Override
    public Map<String, String> buildSystemEnvParamsMap(Long systemEnvId) {
        Map<String, String> resultMap = new HashMap<String, String>();
        if (systemEnvId == null || systemEnvId <= 0L) {
            return resultMap;
        }
        SystemEnvDetailDO systemEnvDetailDO = new SystemEnvDetailDO();
        systemEnvDetailDO.setSystemEnvId(systemEnvId);
        List<SystemEnvDetailDO> deatilList = systemEnvDetailDao.findList(systemEnvDetailDO);
        if (deatilList != null && deatilList.size() > 0) {
            for (SystemEnvDetailDO detailDO : deatilList) {
                resultMap.put(detailDO.getPropKey(), detailDO.getPropValue());
            }
        }

        return resultMap;
    }
}
