package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Region;

/**
 * ��֯��������ӿڡ�
 * 
 * @ejb.client jndiName="ejb/sm/orgManager" id="orgManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface OrgManager extends EntityManager,XmlTreeManager {
    /**
     * ������֯ID������֯��
     * 
     * @param orgId
     *                ��֯ID��
     * @return ��֯ʵ�塣
     */
    public OrganizationDTO findOrgById(Long orgId) throws Exception;

    /**
     * ������֯������Ϣ��
     * 
     * @param org
     *                ��֯ʵ�塣
     * @param parentOrg
     *                ����֯ʵ�塣
     * @return ��֯ID��
     */
    public Long save(OrganizationDTO org, OrganizationDTO parentOrg)
            throws Exception;
    
    /**
     * ������֯������Ϣ��
     * @param org    ��֯������
     * @param parentOrg    ����֯������
     * @throws Exception
     */
    public void update(OrganizationDTO org, OrganizationDTO parentOrg) throws Exception;

    /**
     * ����ϵͳ������֯������
     * 
     * @return ��֯ʵ���б�
     */
    public List findAllOrgOrderByOrgName() throws Exception;

    /**
     * ������֯�������ڵ㡣
     * 
     * @return ��֯ʵ�塣
     */
    public OrganizationDTO findRootOrg() throws Exception;

    /**
     * ������֯�ɷ�������
     * 
     * @param org
     *                ��֯ʵ�塣
     * @param selectRegion
     *                �ɷ��������б�
     */
    public void saveOrgAccessRegion(OrganizationDTO org, Region[] selectRegion)
            throws Exception;

    /**
     * ��ֹ��֯��
     * 
     * @param orgs
     *                ��֯ʵ�����顣
     */
    public void disableOrg(OrganizationDTO[] orgs) throws Exception;

    /**
     * �����֯��
     * 
     * @param org
     *                ��֯ʵ�塣
     */
    public void enableOrg(OrganizationDTO org) throws Exception;

    /**
     * ��ȡ����Ա��Ŀɷ�����֯��
     * 
     * @param groupId
     *                ����Ա��ID��
     * @return Organization�����б�
     */
    public List findAcessOrgByGroupId(Long groupId) throws Exception;
    
    /**
     * ��ȡ����Ա��Ŀɷ�����֯����������֯��
     * 
     * @param groupId
     *                ����Ա��ID��
     * @return Organization�����б�
     */
    public List findAcessOrgByGroupIdWithChild(Long groupId) throws Exception;

    /**
     * ��ѯ����Ա�Լ��Ŀɷ�����֯����������������ġ�
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAccessOrgsForOperator(Long operatorId) throws Exception;
    
    /**
     * ��ѯ����Ա�Լ��Ŀɷ�����֯����������֯����������������ġ�
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAccessOrgsForOperatorWithChild(Long operatorId) throws Exception;

    /**
     * ��ѯ����Ա������Ŀɷ�����֯��
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAccessOrgForOperatorInGroups(Long operatorId)
            throws Exception;

    /**
     * ��ѯ����Ա���пɷ�����֯��
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAllAccessOrgsForOperator(Long operatorId) throws Exception;
    
    /**
     * ��ѯ����Ա���пɷ�����֯,������������֯��
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAllAccessOrgsForOperatorIncludeChildren(Long operatorId) throws Exception;
    
    /**
     * ��ѯ����Ա���пɷ�����֯����������֯��
     * @param operatorId    ����ԱID��
     * @return
     * @throws Exception
     */
    public List findAllAccessOrgIdsOfOperatorIncludeChild(Long operatorId) throws Exception;

    /**
     * ����SSOΩһ�����ѯ��֯��
     * 
     * @param ssoCode
     *                ��֯��SSOϵͳ�е�Ωһ���롣
     * @return
     */
    public OrganizationDTO findOrgBySSOCode(String ssoCode) throws Exception;

    /**
     * ��ѯָ����֯ID������֯��
     * 
     * @param parentId
     * @return
     */
    public List findChildrenOrgs(Long parentId) throws Exception;
    
    /**
     * ��ѯ��ɫ����Ӧ��֯
     * @param roleId
     * @return
     * @throws Exception
     */
    public List findOrgsOfRole(Long roleId) throws Exception;

    
    /**
     * ��ѯָ����֯�ɷ��ʵ���֯���ɷ�����֯���Ͱ����ֹ�˾����������Ӫҵ�������ڲ�ѯ��¼��֯�ɷ��ʵ���֯��
     * ��ѯ��֯�ɷ��ʷֹ�˾ʱ���������֯����Ϊ�ֹ�˾������ɷ��ʷֹ�˾Ϊ����֯�Լ������е��ӷֹ�˾������Ϊ����֯�����ֹ�˾��
     * ��ѯ��֯�ɷ�������ʱ���������֯����ĳ�����������µ���֯����ɷ�����������Ϊ�������������򣻷�����ɷ�������Ϊ����֯�����е���������
     * ��ѯ��֯�ɷ���Ӫҵ��ʱ���������֯ΪӪҵ��������ɷ�����֯Ϊ��Ӫҵ��������������������Ӫҵ��������������Ӫҵ����������֯Ϊ�����̣�����
     * ����Ӫҵ��Ϊ����֯�Լ�������Ӫҵ����������֯Ϊ����������Ϊ�������е�Ӫҵ��������֯Ϊ�ֹ�˾����Ϊ�����������������µ�Ӫҵ����
     * 
     * @param orgId       ָ����֯����ID��
     * @param lorgType    �ɷ�����֯���͡�
     * @param includeAll ���ڻ�ȡ�ֹ�˾�µ���������Ӫҵ��ʱ����־�Ƿ��ȡ�����ӷֹ�˾�µ���֯��ֻ��ָ����֯Ϊ�ֹ�˾ʱ��Ч��
     * @return
     */
    public List findAccessOrgsOfOrg(Long orgId, long lorgType,boolean includeAll) throws Exception;

    /**
     * ��ѯָ����֯�ɷ�����֯ID��
     * @param orgId       ָ����֯����ID��
     * @param lorgType    �ɷ�����֯���͡�
     * @param includeAll ���ڻ�ȡ�ֹ�˾�µ���������Ӫҵ��ʱ����־�Ƿ��ȡ�����ӷֹ�˾�µ���֯��ֻ��ָ����֯Ϊ�ֹ�˾ʱ��Ч��
     * @return
     */
    public List findAccessOrgIdsOfOrg(Long orgId, long lorgType,boolean includeAll) throws Exception;

    /**
     * ��ѯָ����֯�����ֹ�˾ID��
     * @param orgId    ָ����֯ID��
     * @return
     */
    public Long findCompanyIdOfOrg(Long orgId) throws Exception;

    /**
     * ��ѯָ����֯�����ֹ�˾��
     * @param orgId
     * @return
     */
    public OrganizationDTO findCompanyOfOrg(Long orgId) throws Exception;
    
    /**
     * ��ѯ����Ա�̳еĿɷ�����֯����������֯��
     * @param opId
     * @return
     * @throws Exception
     */
    
    public List findAccessOrgForOperatorInGroupsWithChild(Long opId) throws Exception;
    
    /**
     * ��ѯ��ǰ����Ա�ĵ�½�ɷ�����֯��ָ������Ա�Ŀɷ�����֯�Ľ���
     * @param opId ָ������ԱId
     * @param ids  ��ǰ����Ա�ĵ�½�ɷ�����֯id
     * @return
     * @throws Exception
     */
    public List findAccessOrgForOperatorInLoginOrg(Long opId , long ids[] ) throws Exception;
    
    /**
     * ��ѯ��ǰ����Ա�ĵ�½�ɷ�����֯��ָ������Ա��Ŀɷ�����֯�Ľ���
     * @param groupId ָ������Ա��Id
     * @param ids  ��ǰ����Ա�ĵ�½�ɷ�����֯id
     * @return
     * @throws Exception
     */
    public List findAccessOrgForGroupInLoginOrg(Long groupId , long ids[] ) throws Exception;
    
    /**
     * ��ѯָ���ֹ�˾�ɷ��ʵ���֯��
     * @param org    �ֹ�˾��֯������
     * @param type   ��֯���ͣ�-1ʱΪ������֯����
     * @param includeChildCompany   �Ƿ�������ӷֹ�˾�µ���֯��
     * @return
     * @throws Exception
     */
    public List findAccessOrgOfCompany(OrganizationDTO org,long type,boolean includeChildCompany) throws Exception;

    /**
     * ������֯���Ͳ�ѯ��֯
     * @param orgType ��֯����
     * @return
     */
    public List findOrgsByType(int orgType);
   
    
}