package com.ft.busi.sm.template;

import java.io.IOException;
import java.io.Reader;

import com.ft.sm.dto.OrganizationDTO;

/**
 * ģ���ļ��ֿ��ࡣ
 * 
 * @version 1.0
 */
public interface TemplateRepository {
    /**
     *  ��ȡָ����֯��ָ������а󶨵�ģ���ֽ�����
     * 
     * @param org
     *                ��֯������
     * @param categoryCode
     *                ģ�������롣
     * @return
     * @throws IOException
     */
    public Reader getTemplateReader(OrganizationDTO org, String categoryCode)
            throws IOException, TemplateException;
    
    /**
     * ��ȡָ��ģ����ֽ�����
     * @param templateCode
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public Reader getTemplateReader(String templateCode) throws IOException, TemplateException;
}
