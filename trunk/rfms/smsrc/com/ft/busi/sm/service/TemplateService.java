package com.ft.busi.sm.service;

import com.ft.sm.dto.TemplateDTO;

/**
 * ģ���ȡ�ӿ�.
 * 
 * @version 1.0
 */
public interface TemplateService {
    /**
     * ��ȡָ���������֯���õ�ģ���б�
     * @param categoryCode
     * @param orgId
     * @return
     */
    public TemplateDTO[] getTemplateOfCategory(String categoryCode,Long orgId);
    
    /**
     * ����ģ������ȡģ�塣
     * @param templateCode    ģ����롣
     * @return
     */
    public TemplateDTO getTemplateByCode(String templateCode);
    
    /**
     * ����ģ���ʶ��ȡģ�塣
     * @param templateId      ģ���ʶ��
     * @return
     */
    public TemplateDTO getTemplateById(Long templateId);
}
