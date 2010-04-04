package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.RegionDTO;

/**
 * ����ʵ�����ӿڡ�
 * 
 * @ejb.client jndiName="ejb/sm/regionManager" id="regionManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface RegionManager extends EntityManager,XmlTreeManager{
    /**
     * ����/����������Ϣ��
     * 
     * @param region
     *                ����ʵ�����ݡ�
     * @return ����ʵ��ID��
     */
    public Long saveRegion(RegionDTO region) throws Exception;

    /**
     * ��ѯ������
     * 
     * @return ����ʵ�����ݡ�
     */
    public RegionDTO getRootRegion() throws Exception;

    /**
     * ��ѯ��������ʵ�塣
     * 
     * @return ���������б�
     */
    public List findAllRegionOrderByParent() throws Exception;

    /**
     * ����ָ������ID��������Ϣ��
     * 
     * @param regionId
     *                ����ID��
     */
    public void disableRegion(Long regionId) throws Exception;

    /**
     * ����������Ϣ��
     * 
     * @param regionId
     *                ����ID��
     */
    public void enableRegion(Long regionId) throws Exception;
    
    /**
     * ��ѯ��֯�ɷ�������
     * @param orgIds    ��֯����ID����
     * @return
     * @throws Exception
     */
    public List findRegionsOfOrgs(Long[] orgIds) throws Exception;
    
    /**
     * ��ѯ��֯�ɷ�������
     * @param orgIds      ����������֯ID
     * @param regionName  �������ƣ���Ϊnull
     * @param regionId    ����ID�� ��Ϊnull
     * @param regionType  Ҫ���ҵ���������
     * @param searchType  ��ѯ��ʽ��ģ����ѯ��ȷ��ѯ
     * @return
     * @throws Exception
     */
    public List findRegionsOfOrgs(Long[] orgIds,String regionName,Long regionId,long regionType,int searchType) throws Exception;
    
    /**
     * ��ѯ��֯�ɷ�������ֻ��ѯ��ɷ�����������ϲ�����
     * @param orgId
     * @return
     * @throws Exception
     */
    public List findRefionsOfOrg(Long orgId) throws Exception;

    /**
     * ��ѯ�����µ�������
     * 
     * @param regionId
     *                ����ID��
     * @return
     */
    public List findRegionByParentId(Long regionId) throws Exception;

    /**
     * �������򣬲����������е�����״̬������
     * 
     * @param regionId
     *                ����ID��
     * @param allChild
     *                �Ƿ����е�������
     * @return ��������б�
     */
    public List findRegionByParentId(Long regionId, boolean allChild)
            throws Exception;

    /**
     * ����ID���������Ϣ��
     * 
     * @param regionId
     * @return
     */
    public RegionDTO findRegionById(Long regionId) throws Exception;

    /**
     * ��ѯ��֯�ɷ��������б�
     * 
     * @param orgId
     *                ��֯ID��
     * @return �ɷ��������б�
     */
    public List findAccessRegionByOrgId(Long orgId) throws Exception;

    /**
     * ��ѯָ����֯����������֯�Ŀɷ�������
     * 
     * @param orgPath
     *                ��֯·����
     * @return �ɷ��������б�
     */
    public List findAccessRegionOfChildrenOrgByOrgId(String orgPath)
            throws Exception;

    /**
     * ��ȡ����·���е����򣬰��ո��ӹ�ϵ����
     * 
     * @param regionId
     *                ����ID��
     * @return
     */
    public List findRegionLocation(Long regionId) throws Exception;
    
    /**
     * ������������ȡ����ʵ�塣
     * @param regionCode    ������롣
     * @return
     * @throws Exception
     */
    public RegionDTO findRegionByCode(String regionCode) throws Exception;
}
