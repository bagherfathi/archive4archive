package com.ft.busi.sm.service;

import org.apache.commons.configuration.Configuration;

/**
 * ��ȡ���ò����ӿڡ�
 * 
 * @version 1.0
 * 
 */
public interface ConfigurationService {
    /**
     * ��ȡϵͳ�����е����ò�����
     * 
     * @return
     */
    public Configuration getConfigurations();

    /**
     * ��ȡָ���ڵ��µ��������ò�����
     * 
     * @param nodeCode
     *                �ڵ���롣
     * @return
     */
    public Configuration getConfigurations(String nodeCode);
}
