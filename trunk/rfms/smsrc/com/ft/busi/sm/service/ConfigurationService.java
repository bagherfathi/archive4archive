package com.ft.busi.sm.service;

import org.apache.commons.configuration.Configuration;

/**
 * 获取配置参数接口。
 * 
 * @version 1.0
 * 
 */
public interface ConfigurationService {
    /**
     * 获取系统中所有的配置参数。
     * 
     * @return
     */
    public Configuration getConfigurations();

    /**
     * 获取指定节点下的所有配置参数。
     * 
     * @param nodeCode
     *                节点编码。
     * @return
     */
    public Configuration getConfigurations(String nodeCode);
}
