package com.ft.busi.sm.service;

import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;

/**
 * ����Ա����֯��Ȩ�޹�����ⲿ�ӿڡ�
 * 
 * @version 1.0
 */
public interface SecurityService {
    /**
     * ��ѯ����Ա��
     * 
     * @param orgId
     *                ����Ա������֯ID��Ϊnullʱ��ѯ������֯�µĲ���Ա��
     * @param name
     *                ����Ա���ƣ���ָ��ʱΪnull��
     * @param loginName
     *                ����Ա��½������ָ��ʱΪnull��
     * @return ��ѯ���Ĳ���Ա��
     */
    public OperatorDTO[] findOperators(Long orgId, String name, String loginName);
    

    /**
     * ��ȡ����֯
     * @return
     */
    public OrganizationDTO findRootOrg(); 
    /**
     * �½�һ������Ա��
     * 
     * @param op
     *                ����Ա��
     * @param orgId
     *                ��֯ID��
     * @param roleIds
     *                ��ɫ��ID��
     * @param groupIds
     *                ��ID��
     * @return
     */
    public OperatorDTO createOpreator(OperatorDTO op, Long orgId,
            Long[] roleIds, Long[] groupIds);

    /**
     * ��ѯ����Ա��
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public OperatorDTO findOperatorById(Long operatorId);

    /**
     * ���ݲ���Ա��¼���ƻ�ȡ����Ա��
     * 
     * @param loginName
     * @return
     */
    public OperatorDTO findOperatorByLoginName(String loginName);

    /**
     * ������֯�������������е�������
     * 
     * @param orgId
     *                ��֯ID��
     * @param allChild
     *                �Ƿ����е�������
     * @return ����������顣
     */
    public RegionDTO[] findRegionByOrgId(Long orgId, boolean allChild);

    /**
     * �������򣬲����������е�������
     * 
     * @param regionId
     *                ����ID��
     * @param allChild
     *                �Ƿ����е�������
     * @return ����������顣
     */
    public RegionDTO[] findRegionByParentId(Long regionId, boolean allChild);

    /**
     * ��ѯҵ��Ȩ�ޡ�
     * 
     * @param opId
     *                ����ԱID��
     * @param code
     *                ҵ��Ȩ�޴��롣
     * @param orgId
     *                ��֯ID��
     * @return ҵ��Ȩ��DTO��
     */
    public DataResourceDTO getDataResource(Long opId, String code,OrganizationDTO orgDto);

    /**
     * ��ѯ�����·���е����򣬰��ո��ӹ�ϵ����
     * 
     * @param regionId
     * @return
     */
    public RegionDTO[] getRegionLocation(Long regionId);

    /**
     * ��������ID��ѯ������Ϣ��
     * 
     * @param regionId
     *                ����ID��
     * @return
     */
    public RegionDTO getRegionById(Long regionId);

    /**
     * ��ȡ��������ɷ���������ָ�������������Ƶ���������
     * 
     * @param orgId
     *                ��֯ID
     * @param regionName
     *                ��������
     * @param regionType
     *                ��������
     * @return
     */
    public RegionDTO getRegionOfOrg(Long orgId, long regionType,
            String regionName);
    
    /**
     * ��ȡָ����֯�ɷ�����֯��
     * 
     * ��ѯָ����֯�ɷ��ʵ���֯���ɷ�����֯���Ͱ����ֹ�˾����������Ӫҵ�������ڲ�ѯ��¼��֯�ɷ��ʵ���֯��
     * ��ѯ��֯�ɷ��ʷֹ�˾ʱ���������֯����Ϊ�ֹ�˾������ɷ��ʷֹ�˾Ϊ����֯�Լ������е��ӷֹ�˾������Ϊ����֯�����ֹ�˾��
     * ��ѯ��֯�ɷ�������ʱ���������֯����ĳ�����������µ���֯����ɷ�����������Ϊ�������������򣻷�����ɷ�������Ϊ����֯�����е���������
     * ��ѯ��֯�ɷ���Ӫҵ��ʱ���������֯ΪӪҵ��������ɷ�����֯Ϊ��Ӫҵ��������������������Ӫҵ��������������Ӫҵ����������֯Ϊ�����̣�����
     * ����Ӫҵ��Ϊ����֯�Լ�������Ӫҵ����������֯Ϊ����������Ϊ�������е�Ӫҵ��������֯Ϊ�ֹ�˾����Ϊ�����������������µ�Ӫҵ����
     * 
     * @param orgId      ָ����֯�ı�ʶ�� 
     * @param orgType    �ɷ��ʵ���֯���ͣ�����1���ֹ�˾��2��Ӫҵ����4����������
     * @param includeAll ���ڻ�ȡ�ֹ�˾�µ���������Ӫҵ��ʱ����־�Ƿ��ȡ�����ӷֹ�˾�µ���֯��ֻ��ָ����֯Ϊ�ֹ�˾ʱ��Ч��
     * @return
     */
    public OrganizationDTO[] getAccessOrgsOfOrg(Long orgId,Long orgType,boolean includeAll);
    
    /**
     * ��ȡָ����֯�ɷ�����֯ID��
     * 
     * �ο�getAccessOrgsOfOrg��
     * 
     * @param orgId      ָ����֯�ı�ʶ�� 
     * @param orgType    �ɷ��ʵ���֯���ͣ�����1���ֹ�˾��2��Ӫҵ����4����������
     * @param includeAll ���ڻ�ȡ�ֹ�˾�µ���������Ӫҵ��ʱ����־�Ƿ��ȡ�����ӷֹ�˾�µ���֯��ֻ��ָ����֯Ϊ�ֹ�˾ʱ��Ч��
     * @return
     */
    public Long[] getAccessOrgIdsOfOrg(Long orgId,Long orgType,boolean includeAll);
    
    /**
     * ��ȡָ����֯�����ֹ�˾ID��
     * @param orgId    ��֯ID��
     * @return
     */
    public Long getCompanyIdOfOrg(Long orgId);
    
    /**
     * ��ȡָ����֯�����ֹ�˾��
     * @param orgId    ��֯ID��
     * @return
     */
    public OrganizationDTO getCompanyOfOrg(Long orgId);
    
    /**
     * ��ȡָ����֯������
     * @param orgId    ��֯����ID��
     * @return
     */
    public OrganizationDTO getOrgById(Long orgId);
    
    /**
     * ��ѯ����ϵͳ��������֯������
     * @param orgType    ��֯�������ͣ�-1���������ͣ�0�����ţ�1���ֹ�˾��2��Ӫҵ����3�������̣�4����������
     * @return
     */
    public OrganizationDTO[] getAllOrgs(long orgType);
    
    /**
     * ��ȡ��ɫ����ָ���ֹ�˾�µĲ���Ա��
     * @param roleId
     * @param orgId
     * @return
     */
    public OperatorDTO[] getOperatorOfRoleInOrg(Long roleId,OrganizationDTO org);
    
    /**
     * ��������Ա��
     * @param opName     ����Ա����
     * @param loginName  ����Ա��¼����
     * @param password   ����Ա��¼����
     * @param orgId      ����Ա������֯ID
     * @return
     */
    public long createOperator(String opName,String loginName,String password,long orgId);
    
    /**
     * ���²���Ա��
     * @param opId      ����ԱID��
     * @param opName    ����Ա���ơ�
     * @param loginName ����Ա��¼���ơ�
     * @param password  ����Ա��¼���롣
     * @param orgId     ����Ա������֯ID��
     * @return
     */
    public boolean updateOperator(long opId,String opName,String loginName,String password,long orgId);
    
    /**
     * ��ֹ����Ա
     * @param opId
     * @return
     */
    public boolean deleteOperator(long opId);
}
