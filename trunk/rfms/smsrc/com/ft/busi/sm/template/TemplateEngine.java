package com.ft.busi.sm.template;

import java.io.IOException;
import java.io.Writer;

import com.ft.sm.dto.OrganizationDTO;

/**
 * ģ�����档
 * 
 * @version 1.0
 */
public interface TemplateEngine {
    /**
     * ��ָ��ģ�����ʵ����������ʵ������������
     * 
     * @param context
     *                ģ������ִ�������ġ�
     * @param org
     *                ָ������֯������
     * @param categoryCode
     *                ģ�������롣
     * @param writer
     *                �������ģ��ʵ���������
     */
    public void execute(TemplateContext context, OrganizationDTO org,
            String categoryCode, Writer writer) throws IOException,
            TemplateException;
    
    /**
     * ��ָ��ģ�����ʵ����������ʵ������������
     * 
     * @param context
     *                ģ������ִ�������ġ�
     * @param templateCode
     *                ģ����롣
     * @param writer
     *                �������ģ��ʵ���������
     */
    public void execute(TemplateContext context,String templateCode, Writer writer) throws IOException,
            TemplateException;
}
