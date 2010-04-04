package com.ft.sm.template.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.busi.sm.model.TemplateManager;
import com.ft.busi.sm.template.TemplateException;
import com.ft.busi.sm.template.TemplateRepository;
import com.ft.common.cache.AbstractRepository;
import com.ft.common.event.SMEvent;
import com.ft.common.event.SMEventListener;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.common.EventConstants;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;

/**
 * 模板文件仓库实现类。
 * 
 * @spring.bean id="templateRepository" init-method="initialize"
 */
public class TemplateRepositoryImpl extends AbstractRepository implements
        TemplateRepository, SMEventListener {
    private static final Log logger = LogFactory
            .getLog(TemplateRepositoryImpl.class);

    private TemplateManager templateManager;

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.template.TemplateRepository#getRuleReader(long,
     *      java.lang.String)
     */
    public Reader getTemplateReader(OrganizationDTO org, String categoryCode)
            throws IOException, TemplateException {
        if (org == null || categoryCode == null) {
            throw new java.lang.IllegalArgumentException();
        }

        try {
            List result = this.templateManager.findBindTemplateOfOrg(
                    categoryCode, org.getOrgId());
            if (result.size() <= 0) {
                throw new TemplateException(
                        "Not found bind of organizaiton,org id="
                                + org.getOrgId() + ",category code="
                                + categoryCode);
            }

            // 获取查询结果中的第一个模板
            TemplateDTO template = (TemplateDTO) result.get(0);
            return this.getTemplateReader(template.getTemplate().getTemplateCode());
        } catch (Exception ex) {
            throw new TemplateException(ex);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.busi.sm.template.TemplateRepository#getTemplateReader(java.lang.String)
     */
    public Reader getTemplateReader(String templateCode) throws IOException,
            TemplateException {
        if (templateCode == null) {
            throw new java.lang.IllegalArgumentException();
        }
        
        TemplateFileDTO templateFile = (TemplateFileDTO)this.cache.get(templateCode);

        if(templateFile == null){
            try{
                templateFile = this.templateManager.findPublishFileOfTemplate(templateCode);
            }catch(Exception ex){
                throw new TemplateException("Not foung publis file of template,template code " + templateCode ,ex);
            }
            
            if(templateFile == null){
                throw new TemplateException("Not foung publis file of template,template code " + templateCode);
            }else{
                this.cache.put(templateCode,templateFile);
            }
        }
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                templateFile.getTemplateFile().getFileContent());
        InputStreamReader reader = new InputStreamReader(inputStream);

        return reader;
    }

    public boolean isSupport(SMEvent event) {
        if (EventConstants.EVENT_TYPE_TEMPLATE.equals(event.getType()))
            return true;

        return false;
    }

    public void onEvent(SMEvent event) {
        logger.info("Receive message:" + event.toString());
        String key = event.getKey();
        if (EventConstants.EVENT_UPDATE_TEMPLATE.equals(event.getAction())) {
            addOrUpdate(key);
        }
    }

   
    /**
     * 系统启动时装载模板数据到缓存中。
     */
    public void initialize() {
        this.cache.clear();
        //启动线程进行数据装载
        Thread loadTemplate = new Thread(){
            public void run(){
                List templates;
               
                try {
                    templates = templateManager.findAllTemplates();
                } catch (Exception e) {
                    logger.error("Load template data error.",e);
                    return;
                }
                
                for (Iterator iter = templates.iterator(); iter.hasNext();) {
                    TemplateDTO element = (TemplateDTO) iter.next();
                    addOrUpdate(element.getTemplate().getTemplateCode());
                }
            }
        };
        
        loadTemplate.start();
    }
    
    /**
     * 更新规则文件缓存。
     * 
     * @param ruleCode
     */
    private void addOrUpdate(String templateCode) {
        TemplateFileDTO fileDto;
        try {
            fileDto = this.templateManager.findPublishFileOfTemplate(templateCode);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        
        this.cache.put(templateCode, fileDto);
    }

    /**
     * @spring.property ref="templateManager"
     * @param templateManager
     *                the templateManager to set
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }
}
