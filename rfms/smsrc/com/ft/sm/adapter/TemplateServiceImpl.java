package com.ft.sm.adapter;

import java.util.List;

import com.ft.busi.sm.model.TemplateManager;
import com.ft.busi.sm.service.TemplateService;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.TemplateDTO;

/**
 * 模板接口实现类.
 * 
 * @spring.bean id="templateService"
 * 
 */
public class TemplateServiceImpl implements TemplateService{
    private TemplateManager templateManager;
    
    /*
     * (non-Javadoc)
     * @see com.ft.busi.sm.service.TemplateService#getTemplateOfCategory(java.lang.String, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public TemplateDTO[] getTemplateOfCategory(String categoryCode, Long orgId) {
        try{
            List result = this.templateManager.findBindTemplateOfOrg(categoryCode, orgId);
            return (TemplateDTO[])result.toArray(new TemplateDTO[0]);
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
    }
    

    /* (non-Javadoc)
     * @see com.ft.busi.sm.service.TemplateService#getTemplateByCode(java.lang.String)
     */
    public TemplateDTO getTemplateByCode(String templateCode) {
        try{
            return this.templateManager.findTemplateByCode(templateCode);
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
    }



    /* (non-Javadoc)
     * @see com.ft.busi.sm.service.TemplateService#getTemplateById(java.lang.Long)
     */
    public TemplateDTO getTemplateById(Long templateId) {
        try{
            return (TemplateDTO)this.templateManager.getEntity(TemplateDTO.class, templateId);
        }catch(Exception ex){
            throw new CommonRuntimeException(ex);
        }
    }

    /**
     * @spring.property ref="templateManager"
     * 
     * @param templateManager the templateManager to set
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }
}
