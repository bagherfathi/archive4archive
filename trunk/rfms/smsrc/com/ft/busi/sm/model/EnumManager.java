package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.EnumDTO;
import com.ft.sm.dto.EnumGroupDTO;

/**
 * ϵͳ����ʵ���ϵͳ���������ӿڡ�
 * 
 */
public interface EnumManager extends EntityManager, XmlTreeManager {
    /**
     * ����ϵͳ���ݡ�
     * 
     * @param enumData
     *                ϵͳ����ʵ�塣
     * @return
     */
    public Long saveEnumData(EnumDTO enumData) throws Exception;

    /**
     * ����ϵͳ���ݡ�
     * 
     * @param enumData
     *                ϵͳ����ʵ�塣
     */
    public void updateEnumData(EnumDTO enumData, AppRequest appRequest)
            throws Exception;

    /**
     * ����ϵͳ���ݡ�
     * 
     * @param enumId
     *                ϵͳ����ID��
     */
    public void disableEnumData(Long enumId, AppRequest appRequest)
            throws Exception;

    /**
     * ����ϵͳ�����顣
     * 
     * @param group
     *                ϵͳ������ʵ�塣
     * @return
     */
    public Long saveEnumDataGroup(EnumGroupDTO group) throws Exception;

    /**
     * ����ϵͳ�����顣
     * 
     * @param group
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updateEnumDataGroup(EnumGroupDTO group, AppRequest appRequest)
            throws Exception;

    /**
     * ����ϵͳ�������
     * 
     * @param enumCategory
     * @return
     */
    public Long saveEnumCategory(EnumCategoryDTO enumCategory) throws Exception;

    /**
     * ����ϵͳ�������
     * 
     * @param enumCategory
     * @param appRequest
     * @return
     * @throws Exception
     */
    public void updatenumCategory(EnumCategoryDTO enumCategory,
            AppRequest appRequest) throws Exception;

    /**
     * ��������ϵͳ�����顣
     * 
     * @return
     */
    public List findAllEnumGroups() throws Exception;

    /**
     * ɾ��ָ��ϵͳ�����顣
     * 
     * @param enumDataGroupId
     *                ϵͳ������ID��
     */
    public void deleteEnumDateGroup(Long enumDataGroupId, AppRequest appRequest)
            throws Exception;

    /**
     * ɾ��ָ��ϵͳ�������
     * 
     * @param enumCategoryId
     */
    public void deleteEnumCategory(Long enumCategoryId, AppRequest appRequest)
            throws Exception;

    /**
     * ����ϵͳ���ݡ�
     * 
     * @param enumId
     *                ϵͳ����ID��
     */
    public void enableEnumData(Long enumId, AppRequest appRequest)
            throws Exception;

    /**
     * ����ϵͳ����ID��ѯϵͳ���ݡ�
     * 
     * @param enumId
     *                ϵͳ������ID��
     * @return
     */
    public EnumDTO findEnumDataById(Long enumId) throws Exception;

    /**
     * ����ϵͳ�������ID��ѯϵͳ�������
     * 
     * @param categoryId
     *                ϵͳ�������ID��
     * @return
     */
    public EnumCategoryDTO findEnumCategoryById(Long categoryId)
            throws Exception;

    /**
     * ��ѯָ������µ�����ϵͳ���ݡ�
     * 
     * @param categoryId
     *                ϵͳ�������ID��
     * @return
     */
    public List findEnumDatasByCategory(Long categoryId) throws Exception;

    /**
     * ��ѯָ����������е�ϵͳ���ݡ�
     * 
     * @param categoryCode
     *                ϵͳ���������롣
     * @return
     */
    public List findEnumDatasByCategory(String categoryCode) throws Exception;

    /**
     * ��ѯָ��ϵͳ�����µ�����������Ŀ��
     * 
     * @param categoryCode
     *                ϵͳ���������롣
     * @param enumCode
     *                ϵͳ���ݴ��롣
     * @return
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode)
            throws Exception;

    /**
     * ��ѯָ��ϵͳ�����µ�����������Ŀ��
     * 
     * @param categoryCode
     *                ϵͳ���������롣
     * @param enumCode
     *                ϵͳ���ݴ��롣
     * @param linkValue
     *                ϵͳ������Ŀ����ֵ��
     * @return
     */
    public List findEnumEntriesByEnum(String categoryCode, String enumCode,
            String linkValue) throws Exception;

    /**
     * ��ѯö���������е�ö�����
     * 
     * @param groupId
     *                ö��������ID��
     * @return
     */
    public List findEnumCategoriesOfGroup(Long groupId) throws Exception;

    /**
     * ��ѯö���������е�ö�����ݣ�����ö�������е���Ŀ����
     * 
     * @param groupId
     *                ö��������ID��
     * @return
     */
    public List findEnumsWithEntriesOfGroup(Long groupId) throws Exception;

    /**
     * ��ѯö���������е�ö�����ݣ�������ö������������Ŀ����
     * 
     * @param groupId
     *                ö��������ID��
     * @return
     */
    public List findEnumsOfGroup(Long groupId) throws Exception;

    /**
     * ��ѯö�������е�ö����Ŀ��
     * 
     * @param enumId
     *                ö������ID��
     * @return
     */
    public List findEntriesByEnum(Long enumId) throws Exception;
}
