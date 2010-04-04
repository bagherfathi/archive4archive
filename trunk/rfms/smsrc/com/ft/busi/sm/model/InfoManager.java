package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.sm.dto.AfficheDTO;
import com.ft.sm.dto.AttachDTO;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.sm.dto.InfoSharedDTO;
import com.ft.sm.dto.OperatorDTO;

/**
 * ��Ϣ��Ϣά���ӿڡ�
 * 
 * 
 * @ejb.client jndiName="ejb/sm/infoManager" id="infoManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface InfoManager extends EntityManager {

    /**
     * ������Ϣ���ࡣ
     * 
     * @param category
     *                ��Ϣ����ʵ�����ݡ�
     */
    public Long saveInfoGategory(InfoCategoryDTO category) throws Exception;

    /**
     * ������Ϣ�������ݡ�
     * 
     * @param category
     *                ��Ϣ����ʵ�����ݡ�
     */
    public void updateInfoGategory(InfoCategoryDTO category) throws Exception;

    /**
     * ɾ����Ϣ���ࡣ
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     */
    public void deleteInfoCategory(Long categoryId) throws Exception;

    /**
     * ���湫����Ϣ��
     * 
     * @param affiche
     *                ������Ϣʵ�����ݡ�
     */
    public Long saveAffiche(AfficheDTO affiche,Long[] orgIds) throws Exception;
    
    /**
     * ��������
     * @param affiche    ������Ϣ
     * @param publisher  ������
     * @param orgIds     ������֯ID�б�
     * @return
     */
    public Long createAffiche(AfficheDTO affiche,OperatorDTO publisher,Long[] orgIds);

    /**
     * ɾ��������Ϣ��
     * 
     * @param afficheId
     *                ����ID��
     */
    public void deleteAffiche(Long afficheId) throws Exception;

    /**
     * ���¹�����Ϣ��
     * 
     * @param affiche
     *                ������Ϣʵ�����ݡ�
     */
    public void updateAffiche(AfficheDTO affiche) throws Exception;

    /**
     * ������е���Ϣ�������ݡ�
     * 
     * @return ��Ϣ���������б�
     */
    public List findAllCategories() throws Exception;

    /**
     * ͨ��categoryId���InfoCategory����
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     * @return ��Ϣ����ʵ�����ݡ�
     */
    public InfoCategoryDTO findInfoCategoryById(Long categoryId)
            throws Exception;

    /**
     * ���棯����������Ϣ��
     * 
     * @param infoShared
     *                ������Ϣʵ�����ݡ�
     */
    public Long saveInfoShared(InfoSharedDTO infoShared) throws Exception;

    /**
     * ��ø�����
     * 
     * @param sharedId
     *                ������ϢID��
     * @return ��Ϣ����ʵ�����ݡ�
     */
    public AttachDTO findAttachBySharedId(Long sharedId) throws Exception;

    /**
     * ���渽����
     * 
     * @param attach
     *                ��Ϣ����ʵ�����ݡ�
     */
    public Long saveAttach(AttachDTO attach) throws Exception;

    /**
     * ɾ��������Ϣ��
     * 
     * @param attachId
     *                ����ID��
     */
    public void deleteAttach(Long attachId) throws Exception;

    /**
     * ɾ��������Ϣ��
     * 
     * @param infoId
     *                ������ϢID��
     */
    public void deleteInfoShared(Long infoId) throws Exception;

    /**
     * ���¹�����Ϣ��
     * 
     * @param shared
     *                ������Ϣʵ�����ݡ�
     */
    public void updateInfoShared(InfoSharedDTO shared) throws Exception;

    /**
     * ��������֯�Ĺ�����Ϣ��
     * 
     * @param orgId
     * @return
     */
    public List findAllAfficheByOrg(Long orgId) throws Exception;

    /**
     * ͨ����ѯ������ù�����Ϣ�б�
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     * @param title
     *                ������Ϣ���⡣
     * @param publisher
     *                ��Ϣ�����ߡ�
     * @param beginTime
     *                ����ʱ�� ��ʼ��
     * @param endTime
     *                ����ʱ�� ������
     * @return
     */
    public List findInfoShared(Long categoryId, String title, String publisher,
            Date beginTime, Date endTime) throws Exception;

    /**
     * ��ѯ��Ϣ��������Ϣ������
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     * @return
     */
    public Integer findInfoNumberOfCategory(Long categoryId) throws Exception;

    /**
     * ��ѯ��Ϣ���������ֲ�����Ϣ��
     * 
     * @param categoryId
     *                ��Ϣ����ID��
     * @return
     */
    public InfoSharedDTO findLastPublishedInfo(Long categoryId)
            throws Exception;
    
}
