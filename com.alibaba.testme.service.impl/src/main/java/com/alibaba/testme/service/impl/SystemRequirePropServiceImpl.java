package com.alibaba.testme.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.testme.dao.SystemRequirePropDao;
import com.alibaba.testme.domain.dataobject.SystemRequirePropDO;
import com.alibaba.testme.service.SystemRequirePropService;

/**
 * SystemRequireProp Service Implement
 * 
 * @author xiaopenzi
 */
public class SystemRequirePropServiceImpl implements SystemRequirePropService {

    private SystemRequirePropDao systemRequirePropDao;

    public void setSystemRequirePropDao(SystemRequirePropDao systemRequirePropDao) {
        this.systemRequirePropDao = systemRequirePropDao;
    }

    /**
     * @param systemRequirePropDO
     * @return
     */
    @Override
    public Long addSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO) {
        if (systemRequirePropDO == null) {
            return null;
        }
        return systemRequirePropDao.addSystemRequirePropDO(systemRequirePropDO);
    }

    /**
     * @param systemRequirePropDO
     * @return
     */
    @Override
    public int updateSystemRequirePropDO(SystemRequirePropDO systemRequirePropDO) {
        if (systemRequirePropDO == null) {
            return 0;
        }
        return systemRequirePropDao.updateSystemRequirePropDO(systemRequirePropDO);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteSystemRequirePropDO(Long id) {
        if (id == null || id == 0L) {
            return 0;
        }
        return systemRequirePropDao.deleteSystemRequirePropDO(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SystemRequirePropDO findById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return systemRequirePropDao.findById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<SystemRequirePropDO> findList(SystemRequirePropDO systemRequirePropDO) {
        return systemRequirePropDao.findList(systemRequirePropDO);
    }

    @Override
    public SystemRequirePropDO findByPropCodeAndSystemId(Long systemId, String propCode) {
        if (systemId == null || systemId == 0L || StringUtils.isBlank(propCode)) {
            return null;
        }
        return systemRequirePropDao.findByPropCodeAndSystemId(systemId, propCode);
    }

}
