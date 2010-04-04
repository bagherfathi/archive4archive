/**
 * @{#} EnumManagerImpl.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by WASU.
 */
package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.model.BusiAppService;
import com.ft.busi.model.BusiBaseService;
import com.ft.busi.sm.impl.dao.EnumCategoryDao;
import com.ft.busi.sm.impl.dao.EnumDao;
import com.ft.busi.sm.impl.dao.EnumEntryDao;
import com.ft.busi.sm.impl.dao.EnumGroupDao;
import com.ft.busi.sm.model.EnumManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumEntryDTO;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.EnumCategory;
import com.ft.sm.entity.EnumEntry;
import com.ft.sm.entity.EnumGroup;

/**
 * 系统数据实体和系统数据组实体管理实现类.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 *  
 * @spring.bean id="enumManagerImpl"
 */
public class EnumManagerImpl implements EnumManager,BusiBaseService {
    private EnumGroupDao enumGroupDao;

    private EnumCategoryDao enumCategoryDao;

    private EnumDao enumDao;

    private EnumEntryDao enumEntryDao;
    
    private BusiAppService appService;

    /**
     * @spring.property ref="EnumGroupDao"
     * @param enumGroupDao
     *                the enumGroupDao to set
     */
    public void setEnumGroupDao(EnumGroupDao enumGroupDao) {
        this.enumGroupDao = enumGroupDao;
    }

    /**
     * @spring.property ref="EnumCategoryDao"
     * @param enumCategoryDao
     *                the enumCategoryDao to set
     */
    public void setEnumCategoryDao(EnumCategoryDao enumCategoryDao) {
        this.enumCategoryDao = enumCategoryDao;
    }

    /**
     * @spring.property ref="EnumDao"
     * @param enumDao
     *                the enumDao to set
     */
    public void setEnumDao(EnumDao enumDao) {
        this.enumDao = enumDao;
    }

    /**
     * @spring.property ref="EnumEntryDao"
     * @param enumEntryDao
     *                the enumEntryDao to set
     */
    public void setEnumEntryDao(EnumEntryDao enumEntryDao) {
        this.enumEntryDao = enumEntryDao;
    }
    
    /**
     * @spring.property ref="busiAppService"
     * @param appService
     *                the appService to set
     */
    public void setAppService(BusiAppService appService) {
        this.appService = appService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.EnumManager#deleteEnumData(java.lang.Long)
     */
    public void disableEnumData(Long enumId,AppRequest appRequest) throws Exception{
        if (enumId == null) {
            throw new IllegalArgumentException();
        }

        com.ft.sm.entity.Enum enumData = this.enumDao.getById(enumId);

        if (enumData != null) {
            //enumData.setStatus(EnumDTO.STATUS_DISABLE);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    enumData, this);
            ((com.ft.sm.entity.Enum)returnObj).setStatus(EnumDTO.STATUS_DISABLE);
            this.enumDao.update(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.EnumManager#deleteEnumDateGroup(java.lang.Long)
     */
    public void deleteEnumDateGroup(Long enumDataGroupId,AppRequest appRequest) throws Exception{
        if (enumDataGroupId == null) {
            throw new IllegalArgumentException();
        }

        EnumGroup enumGroup = this.enumGroupDao.getById(enumDataGroupId);
        Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                enumGroup, this);
        this.enumGroupDao.saveOrUpdate(returnObj);
        
        //this.enumGroupDao.deleteEnumDateGroup(enumDataGroupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.EnumManager#saveEnumData(com.huashu.boss.sm.entity.basedata.EnumData)
     */
    public Long saveEnumData(EnumDTO enumDto) {
        if (enumDto == null) {
            throw new IllegalArgumentException();
        }

        com.ft.sm.entity.Enum enumData = (com.ft.sm.entity.Enum) enumDto.getTarget();
        List enumEntries = enumDto.getEntries();

        // 保存枚举数据
        this.enumDao.saveOrUpdate(enumData);

        // 保存枚举数据条目
        for (Iterator iter = enumEntries.iterator(); iter.hasNext();) {
            EnumEntry entry = (EnumEntry) iter.next();
            entry.setEnumId(enumData.getEnumId());
        }

        this.enumEntryDao.batchSave(enumEntries);

        return new Long(enumData.getEnumId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.EnumManager#saveEnumDataGroup(com.huashu.boss.sm.entity.basedata.EnumDataGroup)
     */
    public Long saveEnumDataGroup(EnumGroupDTO enumGroupDto) {
        if (enumGroupDto == null) {
            throw new IllegalArgumentException();
        }

        EnumGroup enumGroup = (EnumGroup) enumGroupDto.getTarget();
        this.enumGroupDao.saveOrUpdate(enumGroup);

        return new Long(enumGroup.getGroupId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.EnumManager#updateEnumData(com.huashu.boss.sm.entity.basedata.EnumData,
     *      java.util.List)
     */
    public void updateEnumData(EnumDTO enumDto,AppRequest appRequest) throws Exception{
        // 删除枚举数据原有的数据条目
        //this.enumEntryDao.deleteEntriesByEnum(enumDto.getEnumId());
        List result = this.enumEntryDao.findEntriesByEnum(enumDto.getEnumId());
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                    object, this);
            this.enumEntryDao.saveOrUpdate(returnObj);
        }

        // 更新枚举数据
        com.ft.sm.entity.Enum enumData = (com.ft.sm.entity.Enum) enumDto.getTarget();
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                enumData, this);
        this.enumDao.saveOrUpdate(returnObj);
        //this.enumDao.update(enumDto.getTarget());

        // 保存枚举数据条目
        List enumEntries = enumDto.getEntries();
        for (Iterator iter = enumEntries.iterator(); iter.hasNext();) {
            EnumEntry entry = (EnumEntry) iter.next();
            entry.setEnumId(enumData.getEnumId());
        }

        this.enumEntryDao.batchSave(enumEntries);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#enableEnumData(java.lang.Long)
     */
    public void enableEnumData(Long enumId,AppRequest appRequest) throws Exception{
        if (enumId == null) {
            throw new IllegalArgumentException();
        }

        com.ft.sm.entity.Enum enumData = this.enumDao.getById(enumId);

        if (enumData != null) {
            enumData.setStatus(EnumDTO.STATUS_NORMAL);
            Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                    enumData, this);
            this.enumDao.update(returnObj);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumDataById(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public EnumDTO findEnumDataById(Long enumId) {
        // 查询枚举数据
    	com.ft.sm.entity.Enum enumData = this.enumDao.getById(enumId);
        // 查询枚举数据中的数据条目
        List entries = this.enumEntryDao.findEntriesByEnum(enumId);

        if (enumData != null) {
            EnumDTO ret = new EnumDTO(enumData);
            ret.getEntries().addAll(entries);
            return ret;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#saveEnumCategory(com.huashu.boss.sm.entity.EnumCategory)
     */
    public Long saveEnumCategory(EnumCategoryDTO enumCategoryDto) {
        if (enumCategoryDto == null) {
            throw new IllegalArgumentException();
        }

        EnumCategory enumCategory = (EnumCategory) enumCategoryDto.getTarget();

        this.enumCategoryDao.saveOrUpdate(enumCategory);
        return new Long(enumCategory.getCategoryId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#deleteEnumCategory(java.lang.Long)
     */
    public void deleteEnumCategory(Long enumCategoryId,AppRequest appRequest) throws Exception{
        if (enumCategoryId == null) {
            throw new IllegalArgumentException();
        }

        EnumCategory enumCategory = this.enumCategoryDao.getById(enumCategoryId);
        Object returnObj = appService.deleteAndsettingHistoryObject(appRequest,
                enumCategory, this);
        this.enumCategoryDao.saveOrUpdate(returnObj);
        //this.enumCategoryDao.deleteEnumCategory(enumCategoryId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumCategoryById(java.lang.Long)
     */
    public EnumCategoryDTO findEnumCategoryById(Long categoryId) {
        EnumCategory enumCategory = this.enumCategoryDao.getById(categoryId);

        return new EnumCategoryDTO(enumCategory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumDatasByCategory(java.lang.Long)
     */
    public List findEnumDatasByCategory(Long categoryId) {
        List result = this.enumDao.findEnumDatasByCategory(categoryId);

        return EntityDTOConverter.converEnum2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumDatasByCategory(java.lang.String)
     */
    public List findEnumDatasByCategory(String categoryCode) {
        List result = this.enumDao.findEnumDatasByCategory(categoryCode);

        return EntityDTOConverter.converEnum2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumEntriesByEnum(java.lang.String,
     *      java.lang.String)
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode) {
        List result = this.enumEntryDao.findEnumEntriesByEnum(categoryCode,
                enumCode);

        return EntityDTOConverter.converEnumEntry2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumEntriesByEnum(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode,
            String linkValue) {
        List result = this.enumEntryDao.findEnumEntriesByEnum(categoryCode,
                enumCode, linkValue);

        return EntityDTOConverter.converEnumEntry2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findAllEnumGroups()
     */
    public List findAllEnumGroups() {
        List result = this.enumGroupDao.findAll();

        return EntityDTOConverter.converEnumGroup2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(EnumGroupDTO.class)) {
            EnumGroup enumGroup = this.enumGroupDao.getById(id);
            return new EnumGroupDTO(enumGroup);
        }

        if (typeClass.equals(EnumCategoryDTO.class)) {
            EnumCategory category = this.enumCategoryDao.getById(id);
            return new EnumCategoryDTO(category);
        }

        if (typeClass.equals(EnumDTO.class)) {
        	com.ft.sm.entity.Enum enumData = this.enumDao.getById(id);
            return new EnumDTO(enumData);
        }

        if (typeClass.equals(EnumEntryDTO.class)) {
            EnumEntry entry = this.enumEntryDao.getById(id);
            return new EnumEntryDTO(entry);
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
            PageBean page) {
        List result = null;
        if (typeClass.equals(EnumGroup.class)) {
            result = this.enumGroupDao.query(hql, params, page);
            return EntityDTOConverter.converEnumGroup2DTO(result);
        }

        if (typeClass.equals(EnumCategory.class)) {
            result = this.enumCategoryDao.query(hql, params, page);
            return EntityDTOConverter.converEnumCategory2DTO(result);
        }

        if (typeClass.equals(Enum.class)) {
            result = this.enumDao.query(hql, params, page);
            return EntityDTOConverter.converEnum2DTO(result);
        }

        if (typeClass.equals(EnumEntry.class)) {
            result = this.enumEntryDao.query(hql, params, page);
            return EntityDTOConverter.converEnumEntry2DTO(result);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = null;
        if (typeClass.equals(EnumGroupDTO.class)) {
            result = this.enumGroupDao.loadByIds(EnumGroup.class, ids);
            return EntityDTOConverter.converEnumGroup2DTO(result);
        }

        if (typeClass.equals(EnumCategoryDTO.class)) {
            result = this.enumCategoryDao.loadByIds(EnumCategory.class, ids);
            return EntityDTOConverter.converEnumCategory2DTO(result);
        }

        if (typeClass.equals(EnumDTO.class)) {
            result = this.enumDao.loadByIds(Enum.class, ids);
            return EntityDTOConverter.converEnum2DTO(result);
        }

        if (typeClass.equals(EnumEntryDTO.class)) {
            result = this.enumEntryDao.loadByIds(EnumEntry.class, ids);
            return EntityDTOConverter.converEnumEntry2DTO(result);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumCategoriesOfGroup(java.lang.Long)
     */
    public List findEnumCategoriesOfGroup(Long groupId) {
        List result = this.enumCategoryDao.findEnumCategoriesOfGroup(groupId);
        return EntityDTOConverter.converEnumCategory2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumWithEntriesOfGroup(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findEnumsWithEntriesOfGroup(Long groupId) {
        // 查询枚举数据组中的枚举数据
        List enums = this.enumDao.findEnumsOfGroup(groupId);

        // 查询枚举数据组中枚举数据条目
        List entries = this.enumEntryDao.findEntriesOfGroup(groupId);

        // 将枚举数据条目转换为Map
        Map entriesMap = this.mapEnumEntries(entries);

        // 组装为EnumDTO列表返回
        List ret = new ArrayList();
        for (Iterator iterator = enums.iterator(); iterator.hasNext();) {
        	com.ft.sm.entity.Enum enumData = (com.ft.sm.entity.Enum) iterator.next();
            EnumDTO enumDto = new EnumDTO(enumData);
            List entriesOfEnum = (List) entriesMap.get(enumData.getEnumId()
                    + "");

            if (entriesOfEnum != null) {
                enumDto.getEntries().addAll(entriesOfEnum);
            }

            ret.add(enumDto);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEnumsOfGroup(java.lang.Long)
     */
    public List findEnumsOfGroup(Long groupId) {
        // 查询枚举数据组中的枚举数据
        List enums = this.enumDao.findEnumsOfGroup(groupId);

        return EntityDTOConverter.converEnum2DTO(enums);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EnumManager#findEntriesByEnum(java.lang.Long)
     */
    public List findEntriesByEnum(Long enumId) {
        List entries = this.enumEntryDao.findEntriesByEnum(enumId);

        return EntityDTOConverter.converEnumEntry2DTO(entries);
    }

    /**
     * 按照系统数据对数据条目分组
     * 
     * @param enumEntries
     * @return
     */
    @SuppressWarnings("unchecked")
	private Map mapEnumEntries(List enumEntries) {
        Map ret = new HashMap();

        for (Iterator iter = enumEntries.iterator(); iter.hasNext();) {
            EnumEntry element = (EnumEntry) iter.next();
            List entries = (List) ret.get(element.getEnumId() + "");

            if (entries == null) {
                entries = new ArrayList();
                ret.put(element.getEnumId() + "", entries);
            }

            entries.add(element);
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findNodeChildren(java.lang.Long, java.lang.Integer)
     */
     public List findNodeChildren(Long nodeId,String type) {
        List result = new ArrayList();
        if(nodeId.longValue() == XmlTreeNode.MOCK_ROOT_NODE_ID){
            List enumGroups = this.enumGroupDao.findAllOrderByName();
            return EntityDTOConverter.converEnumGroup2DTO(enumGroups);
        }
        if(type.equals("group")){
            List cateList = this.enumCategoryDao.findEnumCategoriesOfGroupOrderByName(nodeId);
            return EntityDTOConverter.converEnumCategory2DTO(cateList);
        }
        return result;
     }

     /*
      * (non-Javadoc)
      * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findRootNode()
      */
     public XmlTreeNode findRootNode() {
        XmlTreeNode root = new XmlTreeNode();
        root.setNodeName("root");
        root.setNodeId(new Long(XmlTreeNode.MOCK_ROOT_NODE_ID));
        root.setNodeName("系统参数");
        root.setNodeType("root");
        return root;
     }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.EnumManager#updateEnumDataGroup(com.huashu.boss.sm.dto.EnumGroupDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void updateEnumDataGroup(EnumGroupDTO group, AppRequest appRequest)
            throws Exception {
        EnumGroup enumGroup = (EnumGroup)group.getTarget();
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                enumGroup, this);
        this.enumGroupDao.saveOrUpdate(returnObj);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.EnumManager#updatenumCategory(com.huashu.boss.sm.dto.EnumCategoryDTO, com.huashu.boss.busi.dto.AppRequest)
     */
    public void updatenumCategory(EnumCategoryDTO enumCategory,
            AppRequest appRequest) throws Exception {
        EnumCategory category = (EnumCategory)enumCategory.getTarget();
        Object returnObj = appService.saveAndsettingHistoryObject(appRequest,
                category, this);
        this.enumCategoryDao.saveOrUpdate(returnObj);
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#getEntityObject(java.lang.Class, java.io.Serializable)
     */
    public Object getEntityObject(Class entityClass, Serializable key) {
        if(entityClass.equals(EnumGroup.class)){
            return this.enumGroupDao.getById(key);
        }
        
        if(entityClass.equals(EnumCategory.class)){
            return this.enumCategoryDao.getById(key);
        }
        
        if(entityClass.equals(Enum.class)){
            return this.enumDao.getById(key);
        }
        
        if(entityClass.equals(EnumEntry.class)){
            return this.enumEntryDao.getById(key);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.huashu.boss.busi.model.BusiBaseService#saveHisObject(java.lang.Object)
     */
    public void saveHisObject(Object object){
        this.enumDao.saveOrUpdate(object);
    }
}
