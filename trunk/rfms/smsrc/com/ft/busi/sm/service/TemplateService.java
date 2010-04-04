package com.ft.busi.sm.service;

import com.ft.sm.dto.TemplateDTO;

/**
 * 模板获取接口.
 * 
 * @version 1.0
 */
public interface TemplateService {
    /**
     * 获取指定类别中组织适用的模板列表。
     * @param categoryCode
     * @param orgId
     * @return
     */
    public TemplateDTO[] getTemplateOfCategory(String categoryCode,Long orgId);
    
    /**
     * 根据模板编码获取模板。
     * @param templateCode    模板编码。
     * @return
     */
    public TemplateDTO getTemplateByCode(String templateCode);
    
    /**
     * 根据模板标识获取模板。
     * @param templateId      模板标识。
     * @return
     */
    public TemplateDTO getTemplateById(Long templateId);
}
