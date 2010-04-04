package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumGroupDTO;

/**
 * 系统数据实体和系统数据组管理接口。
 * 
 */
public interface EnumManager extends EntityManager, XmlTreeManager {
    /**
     * 创建系统数据。
     * 
     * @param enumData
     *                系统数据实体。
     * @return
     */
    public Long saveEnumData(EnumDTO enumData) throws Exception;

    /**
     * 更新系统数据。
     * 
     * @param enumData
     *                系统数据实体。
     */
    public void updateEnumData(EnumDTO enumData, AppRequest appRequest)
            throws Exception;

    /**
     * 禁用系统数据。
     * 
     * @param enumId
     *                系统数据ID。
     */
    public void disableEnumData(Long enumId, AppRequest appRequest)
            throws Exception;

    /**
     * 创建系统数据组。
     * 
     * @param group
     *                系统数据组实体。
     * @return
     */
    public Long saveEnumDataGroup(EnumGroupDTO group) throws Exception;

    /**
     * 更新系统数据组。
     * 
     * @param group
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updateEnumDataGroup(EnumGroupDTO group, AppRequest appRequest)
            throws Exception;

    /**
     * 创建系统数据类别。
     * 
     * @param enumCategory
     * @return
     */
    public Long saveEnumCategory(EnumCategoryDTO enumCategory) throws Exception;

    /**
     * 更新系统数据类别。
     * 
     * @param enumCategory
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updatenumCategory(EnumCategoryDTO enumCategory,
            AppRequest appRequest) throws Exception;

    /**
     * 查找所有系统数据组。
     * 
     * @return
     */
    public List findAllEnumGroups() throws Exception;

    /**
     * 删除指定系统数据组。
     * 
     * @param enumDataGroupId
     *                系统数据组ID。
     */
    public void deleteEnumDateGroup(Long enumDataGroupId, AppRequest appRequest)
            throws Exception;

    /**
     * 删除指定系统数据类别。
     * 
     * @param enumCategoryId
     */
    public void deleteEnumCategory(Long enumCategoryId, AppRequest appRequest)
            throws Exception;

    /**
     * 启用系统数据。
     * 
     * @param enumId
     *                系统数据ID。
     */
    public void enableEnumData(Long enumId, AppRequest appRequest)
            throws Exception;

    /**
     * 根据系统数据ID查询系统数据。
     * 
     * @param enumId
     *                系统数据组ID。
     * @return
     */
    public EnumDTO findEnumDataById(Long enumId) throws Exception;

    /**
     * 根据系统数据类别ID查询系统数据类别。
     * 
     * @param categoryId
     *                系统数据类别ID。
     * @return
     */
    public EnumCategoryDTO findEnumCategoryById(Long categoryId)
            throws Exception;

    /**
     * 查询指定类别下的所有系统数据。
     * 
     * @param categoryId
     *                系统数据类别ID。
     * @return
     */
    public List findEnumDatasByCategory(Long categoryId) throws Exception;

    /**
     * 查询指定类别下所有的系统数据。
     * 
     * @param categoryCode
     *                系统数据类别代码。
     * @return
     */
    public List findEnumDatasByCategory(String categoryCode) throws Exception;

    /**
     * 查询指定系统数据下的所有数据条目。
     * 
     * @param categoryCode
     *                系统数据类别代码。
     * @param enumCode
     *                系统数据代码。
     * @return
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode)
            throws Exception;

    /**
     * 查询指定系统数据下的所有数据条目。
     * 
     * @param categoryCode
     *                系统数据类别代码。
     * @param enumCode
     *                系统数据代码。
     * @param linkValue
     *                系统数据条目关联值。
     * @return
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode,
            String linkValue) throws Exception;

    /**
     * 查询枚举数据组中的枚举类别。
     * 
     * @param groupId
     *                枚举数据组ID。
     * @return
     */
    public List findEnumCategoriesOfGroup(Long groupId) throws Exception;

    /**
     * 查询枚举数据组中的枚举数据（包括枚举数据中的条目）。
     * 
     * @param groupId
     *                枚举数据组ID。
     * @return
     */
    public List findEnumsWithEntriesOfGroup(Long groupId) throws Exception;

    /**
     * 查询枚举数据组中的枚举数据（不包括枚举数据组中条目）。
     * 
     * @param groupId
     *                枚举数据组ID。
     * @return
     */
    public List findEnumsOfGroup(Long groupId) throws Exception;

    /**
     * 查询枚举数据中的枚举条目。
     * 
     * @param enumId
     *                枚举数据ID。
     * @return
     */
    public List findEntriesByEnum(Long enumId) throws Exception;
}
