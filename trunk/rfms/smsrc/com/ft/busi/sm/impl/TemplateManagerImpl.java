package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.RelTemplateOrgDao;
import com.ft.busi.sm.impl.dao.TemplateDao;
import com.ft.busi.sm.impl.dao.TemplateFileDao;
import com.ft.busi.sm.model.TemplateManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;
import com.ft.sm.entity.RelTemplateOrg;
import com.ft.sm.entity.Template;
import com.ft.sm.entity.TemplateFile;

/**
 * 模板管理实现类
 * 
 * @author <a href="mailto:zhouzhan@chances.com.cn">zhouzhan</a>
 * @version 1.0
 *                  
 * @spring.bean id="templateManagerImpl"
 */

public class TemplateManagerImpl implements TemplateManager, BusiBaseService {
    private TemplateDao templateDao;

    private RelTemplateOrgDao relTemplateOrgDao;

    private TemplateFileDao templateFileDao;

    private BusiAppService appService;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#deleteTemplate(com.huashu.boss.sm.dto.TemplateDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public void deleteTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#saveTempate(com.huashu.boss.sm.dto.TemplateDTO,
     *      java.lang.Long[], com.huashu.boss.busi.dto.AppRequest)
     */
    public Long saveTempate(TemplateDTO template, AppRequest appRequest)
            throws Exception {
        if (template == null || appRequest == null) {
            throw new java.lang.IllegalArgumentException();
        }

        // 保存模板信息
        Date createDate = new Date();
        Template templateEntity = template.getTemplate();
        templateEntity.setAppId(appRequest.getAppId());
        templateEntity.setCreateDate(createDate);
        templateEntity.setLastVersion(0);
        templateEntity.setLoginOrgId(appRequest.getLoginOrgId());
        templateEntity.setOperatorId(appRequest.getOperatorId());
        templateEntity.setOrgId(appRequest.getOrgId());
        templateEntity.setPublishVersion(0);
        this.templateDao.save(templateEntity);

        // 保存模板适用组织
        /*
         * for (int i = 0; i < orgIds.length; i++) { Long orgId = orgIds[i];
         * RelTemplateOrg relTemOrg = this.createRelTemplateOrg(templateEntity
         * .getTemplateId(), orgId.longValue(), appRequest);
         * this.relTemplateOrgDao.save(relTemOrg); }
         */

        return new Long(templateEntity.getTemplateId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#saveTemplate(com.huashu.boss.sm.dto.TemplateDTO,
     *      java.lang.Long[], com.huashu.boss.busi.dto.AppRequest,
     *      com.huashu.boss.sm.dto.TemplateFileDTO)
     */
    public Long saveTemplate(TemplateDTO template, AppRequest appRequest,
            TemplateFileDTO templateFile) throws Exception {
        if (templateFile == null) {
            throw new java.lang.IllegalArgumentException();
        }

        // 保存模板
        Long templateId = this.saveTempate(template, appRequest);

        // 保存模板文件信息
        TemplateFile fileEneity = templateFile.getTemplateFile();
        fileEneity.setCreateDate(new Date());
        fileEneity.setLoginOrgId(appRequest.getLoginOrgId());
        fileEneity.setOperatorId(appRequest.getOperatorId());
        fileEneity.setOrgId(appRequest.getOrgId());
        fileEneity.setTemplateId(templateId.longValue());

        long lastVersion = template.getTemplate().getLastVersion();
        lastVersion++;
        fileEneity.setFileVersion(lastVersion);

        this.templateFileDao.save(fileEneity);

        // 更新模板最新模板和发布模板信息。
        template.getTemplate().setLastVersion(lastVersion);
        template.getTemplate().setPublishVersion(lastVersion);
        this.templateDao.update(template.getTemplate());

        return templateId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#addTemplateBind(java.lang.Long,
     *      java.lang.Long, com.huashu.boss.busi.dto.AppRequest)
     */
    public void addTemplateBind(Long orgId, Long templateId,
            AppRequest appRequest) {
        RelTemplateOrg relTemOrg = new RelTemplateOrg();
        relTemOrg.setAppId(appRequest.getAppId());
        relTemOrg.setCreateDate(new Date());
        relTemOrg.setLoginOrgId(appRequest.getLoginOrgId());
        relTemOrg.setOperatorId(appRequest.getOperatorId());
        relTemOrg.setOrgId(appRequest.getOrgId());
        relTemOrg.setRelOrgId(orgId.longValue());
        relTemOrg.setTemplateId(templateId.longValue());
        this.relTemplateOrgDao.save(relTemOrg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#deleteTemplateBind(java.lang.Long,
     *      java.lang.Long, com.huashu.boss.busi.dto.AppRequest)
     */
    public void deleteTemplateBind(Long orgId, Long templateId,
            AppRequest appRequest) throws Exception {
        RelTemplateOrg relTemplateOrg = this.relTemplateOrgDao
                .findRelTemplateOrg(orgId, templateId);
        if (relTemplateOrg != null) {
            Object deleteObj = appService.deleteAndsettingHistoryObject(
                    appRequest, relTemplateOrg, this);
            this.relTemplateOrgDao.saveOrUpdate(deleteObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#updateTemplate(com.huashu.boss.sm.dto.TemplateDTO,
     *      java.lang.Long[], com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateTemplate(TemplateDTO template, AppRequest appRequest)
            throws Exception {

        // 更新模板信息
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                template.getTemplate(), this);
        this.templateDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#addTemplateFile(com.huashu.boss.sm.dto.TemplateDTO,
     *      com.huashu.boss.sm.dto.TemplateFileDTO,
     *      com.huashu.boss.busi.dto.AppRequest, boolean)
     */
    public void addTemplateFile(TemplateDTO template,
            TemplateFileDTO templateFile, AppRequest appRequest,
            boolean isPublish) throws Exception {
        if (templateFile == null) {
            throw new java.lang.IllegalArgumentException();
        }

        // 保存模板文件信息
        TemplateFile fileEneity = templateFile.getTemplateFile();
        fileEneity.setCreateDate(new Date());
        fileEneity.setLoginOrgId(appRequest.getLoginOrgId());
        fileEneity.setOperatorId(appRequest.getOperatorId());
        fileEneity.setOrgId(appRequest.getOrgId());
        fileEneity.setTemplateId(template.getTemplateId().longValue());

        long lastVersion = template.getTemplate().getLastVersion();
        lastVersion++;
        fileEneity.setFileVersion(lastVersion);

        this.templateFileDao.save(fileEneity);

        // 更新模板最新模板和发布模板信息。
        if (isPublish) {
            template.getTemplate().setPublishVersion(lastVersion);
        }

        template.getTemplate().setLastVersion(lastVersion);
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                template.getTemplate(), this);
        this.templateDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#publisTemplateFile(com.huashu.boss.sm.dto.TemplateDTO,
     *      com.huashu.boss.sm.dto.TemplateFileDTO,
     *      com.huashu.boss.busi.dto.AppRequest)
     */
    public void publisTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception {
        templateDto.getTemplate().setPublishVersion(
                templateFile.getTemplateFile().getFileVersion());
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                templateDto.getTemplate(), this);
        this.templateDao.saveOrUpdate(returnObj);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#deleteTemplateFile(com.huashu.boss.sm.dto.TemplateFileDTO)
     */
    public void deleteTemplateFile(TemplateDTO templateDto,
            TemplateFileDTO templateFile, AppRequest appRequest)
            throws Exception {
        long version = templateFile.getTemplateFile().getFileVersion();
        Template template = templateDto.getTemplate();

        this.templateFileDao.delete(templateFile.getTemplateFile());

        if (version == template.getLastVersion()) {
            Long lastVersion = this.templateFileDao.findLastVersion(templateDto
                    .getTemplateId());
            template.setLastVersion(lastVersion.longValue());
            Object returnObj = appService.saveAndsettingHistoryObject(
                    appRequest, templateDto.getTemplate(), this);
            this.templateDao.saveOrUpdate(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findTemplateFiles(java.lang.Long)
     */
    public List findTemplateFiles(Long templateId) throws Exception {
        return this.templateFileDao.findTemplateFileOfTemplate(templateId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findTemplateOfCategory(java.lang.String,
     *      java.lang.Long)
     */
    public List findBindTemplateOfOrg(String categoryCode, Long orgId) {
        return this.templateDao.findBindTemplateOfOrg(categoryCode, orgId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findTemplateOfCategory(java.lang.String)
     */
    public List findTemplateOfCategory(String categoryCode) {
        return this.templateDao.findTemplateByCategoryCode(categoryCode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findTemplateByCode(java.lang.String)
     */
    public TemplateDTO findTemplateByCode(String templateCode) {
        Template template = (Template) this.templateDao
                .getEntityByIdentityAttribute(Template.class,
                        Template.PROP_TEMPLATE_CODE, templateCode);
        if (template != null)
            return new TemplateDTO(template);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findPublishFileOfTemplate(com.huashu.boss.sm.dto.TemplateDTO)
     */
    public TemplateFileDTO findPublishFileOfTemplate(TemplateDTO template)
            throws Exception {
        TemplateFile templateFile = this.templateFileDao.findTemplateFile(
                template.getTemplateId(), template.getTemplate()
                        .getPublishVersion());
        if(templateFile != null)
            return new TemplateFileDTO(templateFile);
        else
            return null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findAllTemplates()
     */
    public List findAllTemplates() {
        return this.templateDao.findAllTemplates();
    }
    
    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.TemplateManager#findPublishFileOfTemplate(java.lang.String)
     */
    public TemplateFileDTO findPublishFileOfTemplate(String templateCode) {
        TemplateFile templateFile = this.templateFileDao.findPublishFile(templateCode);
        
        if(templateFile != null)
            return new TemplateFileDTO(templateFile);
        else
            return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) throws Exception {
        if (typeClass.equals(TemplateDTO.class)) {
            Template template = this.templateDao.getById(id);
            return new TemplateDTO(template);
        }
        if (typeClass.equals(TemplateFileDTO.class)) {
            TemplateFile templateFile = this.templateFileDao.getById(id);
            return new TemplateFileDTO(templateFile);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) throws Exception {
        if (typeClass.equals(Template.class)) {
            List result = this.templateDao.query(hql, params, page);
            return EntityDTOConverter.converTemplateDTO(result);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntityObject(Class typeClass, Serializable key) {
        if (typeClass.equals(Template.class)) {
            return this.templateDao.getById(key);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object entity) {
        this.templateDao.saveOrUpdate(entity);
    }

    /**
     * @spring.property ref = "TemplateDao"
     * @param templateDao
     *                the templateDao to set
     */
    public void setTemplateDao(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * @spring.property ref = "RelTemplateOrgDao"
     * @param relTemplateOrgDao
     *                the relTemplateOrgDao to set
     */
    public void setRelTemplateOrgDao(RelTemplateOrgDao relTemplateOrgDao) {
        this.relTemplateOrgDao = relTemplateOrgDao;
    }

    /**
     * @spring.property ref = "TemplateFileDao"
     * @param templateFileDao
     *                the templateFileDao to set
     */
    public void setTemplateFileDao(TemplateFileDao templateFileDao) {
        this.templateFileDao = templateFileDao;
    }

    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

}
