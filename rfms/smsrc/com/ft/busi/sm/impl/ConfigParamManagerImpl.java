package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.ft.busi.sm.impl.dao.ConfigParamDao;
import com.ft.busi.sm.model.ConfigParamManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.sm.entity.ConfigParam;

/**
 * 系统配置参数管理实现类.
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="configParamManager" parent="transactionProxyFactoryBean"
 *                  target="configParamManagerImpl"
 * 
 * @spring.bean id="configParamManagerImpl"
 */
public class ConfigParamManagerImpl implements ConfigParamManager {

    private ConfigParamDao configParamDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#findAllParams()
     */
    public List findAllParams(long type) {

        return this.configParamDao.findAllParams(type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#saveParam(com.huashu.boss.sm.entity.ConfigParam)
     */
    public Long saveParam(ConfigParamDTO paramDTO, ConfigParamDTO parentDTO) {
        paramDTO.setParnetId(parentDTO.getConfigId().longValue());
        this.configParamDao.save(paramDTO.getConfigParam());
        paramDTO.setPath(parentDTO.getPath()
                + paramDTO.getConfigId().toString()
                + ConfigParamDTO.PATH_SEPARATOR);
        this.configParamDao.update(paramDTO.getConfigParam());
        return paramDTO.getConfigId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#updateParam(com.huashu.boss.sm.entity.ConfigParam)
     */
    public Long updateParam(ConfigParamDTO paramDTO) {
        this.configParamDao.update(paramDTO.getConfigParam());
        return paramDTO.getConfigId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#findParamsByParentId(java.lang.Long)
     */
    public List findParamsByParentId(Long parentId) {
        return this.configParamDao.findParamsByParentId(parentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#findRootParamNode()
     */
    public ConfigParamDTO findRootParamNode() {
        return new ConfigParamDTO(this.configParamDao.findRootParamNode());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#getAllParams()
     */
    public Properties getAllParams() {
        Properties properties = new Properties();
        List params = EntityDTOConverter
                .converConfigParam2DTO(this.configParamDao
                        .findAllParams(ConfigParamDTO.PARAM_TYPE_VALUE));
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            ConfigParamDTO element = (ConfigParamDTO) iter.next();
            properties.setProperty(findKey(element.getPath(), params), element
                    .getConfigValue());
        }
        return properties;
    }

    /**
     * 查找指定参数的key值,从根节点开始。
     */
    private String findKey(String path, List nodes) {
        String[] ids = path.split(ConfigParamDTO.PATH_SEPARATOR);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            for (Iterator iter = nodes.iterator(); iter.hasNext();) {
                ConfigParamDTO element = (ConfigParamDTO) iter.next();
                if (element.getConfigId().toString().equals(ids[i])) {
                    buffer.append(element.getConfigCode());
                    break;
                }
            }
            if (i != (ids.length - 1)) {
                buffer.append(".");
            }
        }
        return buffer.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.ConfigParamManager#getParamsOfNode(java.lang.String)
     */
    public Properties getParamsOfNode(String nodeCode) {
        Properties properties = new Properties();
        ConfigParamDTO node = new ConfigParamDTO(this.configParamDao
                .findConfigParamByCode(nodeCode));

        if (node == null)
            return properties;

        List params = this.configParamDao
                .findAllParams(ConfigParamDTO.PARAM_TYPE_VALUE);

        // 如果指定代码配置参数为参数值类型
        if (node.getType() == ConfigParamDTO.PARAM_TYPE_VALUE) {
            properties.setProperty(findKey(node.getPath(), params), node
                    .getConfigValue());
            return properties;
        }

        // 将该节点下所有配置参数存放到properties中
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            ConfigParamDTO element = (ConfigParamDTO) iter.next();
            if (element.getType() == ConfigParamDTO.PARAM_TYPE_VALUE
                    && element.getPath().startsWith(node.getPath())) {
                properties.setProperty(findKey(element.getPath(), params),
                        element.getConfigValue());
            }
        }
        return properties;
    }

    /**
     * @spring.property ref="ConfigParamDao"
     * @param configParamDao
     *                The configParamDao to set.
     */
    public void setConfigParamDao(ConfigParamDao configParamDao) {
        this.configParamDao = configParamDao;
    }

    public Object getEntity(Class typeClass, Serializable id) {
        ConfigParam configParam = this.configParamDao.loadById(id);
        return new ConfigParamDTO(configParam);
    }

    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        // TODO Auto-generated method stub
        return null;
    }

    public List loadByIds(Class typeClass, Serializable[] ids) {
        // TODO Auto-generated method stub
        return null;
    }
}
