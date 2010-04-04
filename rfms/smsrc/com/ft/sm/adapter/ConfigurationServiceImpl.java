package com.ft.sm.adapter;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;

import com.ft.busi.sm.model.ConfigParamManager;
import com.ft.busi.sm.service.ConfigurationService;
import com.ft.common.exception.CommonRuntimeException;

/**
 * 参数配置服务接口实现
 * 
 * @spring.bean id="configurationService"
 * 
 */
public class ConfigurationServiceImpl implements ConfigurationService {
    private ConfigParamManager configManager;

    /**
     * @spring.property ref="configParamManager"
     * @param configManager
     *                the configManager to set
     */
    public void setConfigManager(ConfigParamManager configManager) {
        this.configManager = configManager;
    }

    /**
     * 获取系统中所有的配置参数。
     * 
     * @return
     */
    public Configuration getConfigurations() {
        try {
            Properties results = this.configManager.getAllParams();
            return ConfigurationConverter.getConfiguration(results);
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

    /**
     * 获取指定节点下的所有配置参数。
     * 
     * @param nodeCode
     *                节点编码。
     * @return
     */
    public Configuration getConfigurations(String nodeCode) {
        try {
            Properties results = this.configManager.getParamsOfNode(nodeCode);
            return ConfigurationConverter.getConfiguration(results);
        } catch (Exception ex) {
            throw new CommonRuntimeException(ex);
        }
    }

}
