package com.ft.sm.adapter;

import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;

import com.ft.busi.sm.model.ConfigParamManager;
import com.ft.busi.sm.service.ConfigurationService;
import com.ft.common.exception.CommonRuntimeException;

/**
 * �������÷���ӿ�ʵ��
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
     * ��ȡϵͳ�����е����ò�����
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
     * ��ȡָ���ڵ��µ��������ò�����
     * 
     * @param nodeCode
     *                �ڵ���롣
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
