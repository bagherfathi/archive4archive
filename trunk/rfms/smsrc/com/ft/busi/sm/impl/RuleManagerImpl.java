package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.RuleCategoryDao;
import com.ft.busi.sm.impl.dao.RuleFileDao;
import com.ft.busi.sm.impl.dao.RuleInfoDao;
import com.ft.busi.sm.model.RuleManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;
import com.ft.sm.entity.RuleCategory;
import com.ft.sm.entity.RuleFile;
import com.ft.sm.entity.RuleInfo;

/**
 * 规则管理类
 * 
 * @version 1.0
 *                  
 * @spring.bean id="ruleManagerImpl"
 * 
 */
public class RuleManagerImpl implements RuleManager,BusiBaseService {

    private RuleInfoDao ruleInfoDao;

    private RuleFileDao ruleFileDao;

    private RuleCategoryDao ruleCategoryDao;
    
    private BusiAppService appService;

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#saveCategory(com.huashu.boss.sm.entity.RuleCategory)
     */
    public void saveCategory(RuleCategoryDTO categoryDTO) {
        if (null == categoryDTO) {
            throw new IllegalArgumentException();
        }
        RuleCategory category = categoryDTO.getCategory();
        this.ruleCategoryDao.save(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#updateCategory(com.huashu.boss.sm.entity.RuleCategory)
     */
    public void updateCategory(RuleCategoryDTO categoryDTO) {
        if (null == categoryDTO) {
            throw new IllegalArgumentException();
        }
        RuleCategory category = categoryDTO.getCategory();
        this.ruleCategoryDao.update(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#deleteCategory(com.huashu.boss.sm.entity.RuleCategory)
     */
    public void deleteCategory(RuleCategoryDTO categoryDTO) {
        if (null == categoryDTO) {
            throw new IllegalArgumentException();
        }
        RuleCategory category = categoryDTO.getCategory();
        this.ruleCategoryDao.delete(category);
    }
    
    

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RuleManager#saveRule(com.huashu.boss.sm.dto.RuleInfoDTO, java.lang.Long, com.huashu.boss.sm.dto.OperatorDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,AppRequest appRequest) {
        if(rule == null || appRequest == null) {
            throw new java.lang.IllegalArgumentException();
        }
        
        // 保存模板信息
        Date createDate = new Date();
        RuleInfo ruleInfo = rule.getRuleInfo();
        ruleInfo.setAppId(appRequest.getAppId());
        ruleInfo.setCategoryId(categoryId.longValue());
        ruleInfo.setCreateDate(createDate);
        ruleInfo.setLoginOrgId(appRequest.getLoginOrgId());
        ruleInfo.setOperatorId(appRequest.getOperatorId());
        ruleInfo.setOrgId(appRequest.getOrgId());
        ruleInfo.setPublishVersion(0);
        
        this.ruleInfoDao.save(ruleInfo);
        return new Long(ruleInfo.getRuleId());
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RuleManager#saveRule(com.huashu.boss.sm.dto.RuleInfoDTO, java.lang.Long, com.huashu.boss.sm.dto.OperatorDTO, com.huashu.boss.sm.dto.RuleFileDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public Long saveRule(RuleInfoDTO rule, Long categoryId,
            OperatorDTO currentUser, RuleFileDTO ruleFile, AppRequest appRequest) {
        if(ruleFile == null || currentUser == null){
            throw new java.lang.IllegalArgumentException();
        }
        
        //保存模板信息
        Long ruleId = this.saveRule(rule, categoryId, appRequest);
        
        //保存模板文件
        ruleFile.setRuleId(ruleId);
        ruleFile.setOperatorId(currentUser.getOperatorId());
        ruleFile.setPublisher(currentUser.getContact().getName());
        ruleFile.setUploadTime(new Date());
        ruleFile.setVersion(1);
        
        this.ruleFileDao.save(ruleFile.getRuleFile());
        
        //更新规则发布版本
        rule.setPublishVersion(1);
        this.ruleInfoDao.update(rule.getRuleInfo());
        
        return ruleId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findCategoryById(java.lang.Long)
     */
    public RuleCategoryDTO findCategoryById(Long categoryId) {
        if (null == categoryId) {
            throw new IllegalArgumentException();
        }
        RuleCategory category = this.ruleCategoryDao.loadById(categoryId);
        return new RuleCategoryDTO(category);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findRuleById(java.lang.Long)
     */
    public RuleInfoDTO findRuleById(Long ruleId) {
        if (null == ruleId) {
            throw new IllegalArgumentException();
        }
        
        
        RuleInfo ruleInfo = this.ruleInfoDao.getById(ruleId);  
        return new RuleInfoDTO(ruleInfo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#updateRule(com.huashu.boss.sm.entity.Rule)
     */
    public void updateRule(RuleInfoDTO ruleInfoDTO,AppRequest appRequest) throws Exception{
        if (null == ruleInfoDTO) {
            throw new IllegalArgumentException();
        }
        
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                ruleInfoDTO.getRuleInfo(), this);
        this.ruleInfoDao.saveOrUpdate(returnObj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#deleteRule(com.huashu.boss.sm.entity.Rule)
     */
    public void deleteRule(RuleInfoDTO ruleInfoDTO) {
        if (null == ruleInfoDTO) {
            throw new IllegalArgumentException();
        }
        // Long ruleId = rule.getRuleId();
        this.ruleInfoDao.delete(ruleInfoDTO.getTarget());
        this.ruleFileDao.deleteRuleFileByRuleId(ruleInfoDTO.getRuleId());

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findRuleFileByFileId(java.lang.Long)
     */
    public RuleFileDTO findRuleFileByFileId(Long fileId) {
        if (null == fileId) {
            throw new IllegalArgumentException();
        }
        
        RuleFileDTO ruleFile = ruleFileDao.getById(fileId);
        if (ruleFile == null)
            return null;
        
        RuleInfo rule = this.ruleInfoDao.getById(ruleFile.getRuleId());
        ruleFile.setRule(rule);
        return ruleFile;
    }
    

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RuleManager#addRuleFile(com.huashu.boss.sm.dto.RuleInfoDTO, com.huashu.boss.sm.dto.RuleFileDTO, com.huashu.boss.busi.dto.AppRequest, boolean)
     */
    public void addRuleFile(RuleInfoDTO rule, RuleFileDTO ruleFile,
            AppRequest appRequest, boolean isPublish) throws Exception {
        if (ruleFile == null) {
            throw new java.lang.IllegalArgumentException();
        }

        // 保存规则文件信息
        RuleFile fileEneity = ruleFile.getRuleFile();
        fileEneity.setUploadTime(new Date());
        fileEneity.setOperatorId(appRequest.getOperatorId());
        fileEneity.setRuleId(rule.getRuleId().longValue());
        long lastVersion = this.ruleFileDao.findMaxVersionByRuleId(rule.getRuleId());
        lastVersion++;
        fileEneity.setVersion(lastVersion);
        this.ruleFileDao.save(fileEneity);

        // 更新发布版本
        if (isPublish) {
            rule.setPublishVersion(lastVersion);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    rule.getRuleInfo(), this);
            this.ruleInfoDao.saveOrUpdate(returnObj);
        }
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RuleManager#deleteRuleFile(com.huashu.boss.sm.dto.RuleInfoDTO, com.huashu.boss.sm.dto.RuleFileDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void deleteRuleFile(RuleInfoDTO rule, RuleFileDTO ruleFile,
            AppRequest appRequest) throws Exception {
        this.ruleFileDao.delete(ruleFile.getRuleFile());
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RuleManager#publishRuleFile(com.huashu.boss.sm.dto.RuleInfoDTO, com.huashu.boss.sm.dto.RuleFileDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void publishRuleFile(RuleInfoDTO rule, RuleFileDTO ruleFile,
            AppRequest appRequest) throws Exception {
        
        rule.setPublishVersion(ruleFile.getVersion());
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                rule.getRuleInfo(), this);
        this.ruleInfoDao.saveOrUpdate(returnObj);
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findRule(java.lang.Long,
     *      java.lang.String)
     */
    public List findRule(Long categoryId, String ruleName) {
        return this.ruleInfoDao.findRule(categoryId, ruleName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findMaxVersionByRuleId(java.lang.Long)
     */
    public long findMaxVersionByRuleId(Long ruleId) {
        return this.ruleFileDao.findMaxVersionByRuleId(ruleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#deleteRuleFile(com.huashu.boss.sm.entity.RuleFile)
     */
    public void deleteRuleFile(RuleFileDTO ruleFile) {
        if (null == ruleFile) {
            throw new IllegalArgumentException();
        }
        this.ruleFileDao.delete(ruleFile.getRuleFile());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findPublishRuleFileOfRule(java.lang.String)
     */
    public RuleFileDTO findPublishedFileOfRule(String ruleCode) {
        if (null == ruleCode) {
            throw new IllegalArgumentException();
        }
        RuleFile ruleFile = this.ruleFileDao.findPublishedFileOfRule(ruleCode);
        
        if(ruleFile != null){
            return new RuleFileDTO(ruleFile);
        }else{
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findAllRule()
     */
    public List findAllRules() {
        List result = this.ruleInfoDao.findAll();

        return EntityDTOConverter.converRuleInfo2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findAllRuleCategory()
     */
    public List findAllRuleCategory() {
        return EntityDTOConverter.converRuleCategory2DTO(this.ruleCategoryDao
                .findAll());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RuleManager#findRuleFileByRuleId(java.lang.Long)
     */
    public List findRuleFileByRuleId(Long ruleId) {
        return this.ruleFileDao.findRuleFileByRuleId(ruleId);
    }

    /**
     * @spring.property ref="RuleCategoryDao"
     * @param ruleCategoryDao
     *                the ruleCategoryDao to set
     */
    public void setRuleCategoryDao(RuleCategoryDao ruleCategoryDao) {
        this.ruleCategoryDao = ruleCategoryDao;
    }

    /**
     * @spring.property ref="RuleFileDao"
     * @param ruleFileDao
     *                the ruleFileDao to set
     */
    public void setRuleFileDao(RuleFileDao ruleFileDao) {
        this.ruleFileDao = ruleFileDao;
    }

    /**
     * @spring.property ref="RuleInfoDao"
     * @param ruleInfoDao
     *                the ruleInfoDao to set
     */
    public void setRuleInfoDao(RuleInfoDao ruleInfoDao) {
        this.ruleInfoDao = ruleInfoDao;
    }

    public Object getEntity(Class typeClass, Serializable id) {
        String name = typeClass.getName();
        if (name.equals(RuleCategoryDTO.class.getName())) {
            return new RuleCategoryDTO(this.ruleCategoryDao.getById(id));
        }
        if (name.equals(RuleFileDTO.class.getName())) {
            RuleFileDTO obj = this.ruleFileDao.getById(id);
            return obj;
        }
        if (name.equals(RuleInfoDTO.class.getName())) {
            return new RuleInfoDTO(this.ruleInfoDao.getById(id));
        }
        return null;
    }

    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        if(typeClass.equals(RuleCategory.class)){
            List result = this.ruleCategoryDao.query(hql, params, page);
            return EntityDTOConverter.converRuleCategory2DTO(result);
        }
        
        if(typeClass.equals(RuleInfo.class)){
            List result = this.ruleInfoDao.query(hql, params, page);
            return EntityDTOConverter.converRuleInfo2DTO(result);
        }
        return null;
    }

    public List loadByIds(Class typeClass, Serializable[] ids) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class, java.io.Serializable)
     */
    public Object getEntityObject(Class clazz, Serializable key) {
        if(clazz.equals(RuleInfo.class)){
            return this.ruleInfoDao.getById(key);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object obj){
        this.ruleInfoDao.saveOrUpdate(obj);
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