package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * ����Ա�����ӿڡ�
 * 
 * 
 * @ejb.client jndiName="ejb/sm/groupManager" id="groupManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface GroupManager extends EntityManager {
    /**
     * �������Ա��ɷ�����֯������
     * 
     * @param ��Ȩ�ˡ�
     * @param selectOrgs
     *                �ɷ�����֯�б�
     * @param group
     *                ����Ա��ʵ�塣
     */
    public void saveGroupAccessOrg(OperatorDTO grantor,
            OrganizationDTO[] selectOrgs, GroupDTO group) throws Exception;

    /**
     * �������Ա�顣
     * 
     * @param group
     *                ����Ա�顣
     * @param appRequest
     *                �����¼��
     * @return
     */
    public Long saveGroup(GroupDTO group, AppRequest appRequest)
            throws Exception;

    /**
     * ɾ������Ա�顣
     * 
     * @param groups
     *                ����Ա��ʵ�����顣
     * @param appRequest
     *                �����¼��
     */
    public void deleteGroup(GroupDTO[] groups, AppRequest request)
            throws Exception;

    /**
     * ������,��ɫ,��֯�Ĺ�ϵ��
     * 
     * @param grantor
     *                ��Ȩ�ˡ�
     * @param group
     *                ����Ա��ʵ�塣
     * @param selectRoles
     *                ��ɫ���顣
     * @param org
     *                �ɷ�����֯ʵ�塣
     */
    public void saveGroupRoleForOrg(OperatorDTO grantor, GroupDTO group,
            RoleDTO[] selectRoles, OrganizationDTO org) throws Exception;

    /**
     * ��ѯ����Ա�����н�ɫ��ϵ��
     * 
     * @param groupId
     *                ����Ա��ID��
     * @return RelGroupRoleDTO�����б�
     */
    public List findGroupRoleForOrgsByGroupId(Long groupId) throws Exception;

    /**
     * ��ѯ����Ա������ӵ�еĽ�ɫ��
     * 
     * @param operatorId
     *                ����ԱID��
     * @param role_type_data
     *                ��ɫ���͡�
     * @return
     */
    public List findGroupRoleForOrgsOfOperator(Long operatorId, Long roleType)
            throws Exception;

    /**
     * ��ѯ����Ա�����顣
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findGroupsByOperator(Long operatorId) throws Exception;
    
    /**
     * ��ѯ����Ա�����顣
     * 
     * @param operatorId
     *                ����ԱID��
     * @param orgId  
     *                ��֯ID��
     * @return
     */
    public List findGroupsByOperator(Long operatorId,Long orgId) throws Exception;

    /**
     * ��ѯ����Ա�ɷ����顣
     * 
     * @param operatorId
     *                ����ԱID��
     * @return
     */
    public List findAvailableGroupsByOperator(Long operatorId,Long orgId) throws Exception;

    /**
     * ��ѯ����Ա�����пɷ�����֯��ϵ��
     * 
     * @param operatorId
     *                ����ԱID��
     * @return GroupAccessOrg�����б�
     */
    public List findGroupAccessOrgsOfOperator(Long operatorId) throws Exception;

    /**
     * ��ѯ�͹���Ȩ����صĲ���Ա�顣
     * 
     * @param resourceId
     *                ����Ȩ��ID��
     * @param orgId   ��֯����ID��
     * @return
     */
    public List findGroupsByResource(Long orgId,Long resourceId) throws Exception;

    /**
     * �������еĲ���Ա��
     * 
     * @return
     */
    public List findAllGroup() throws Exception;

     /**
     * ��ѯ����Ա�顣
     * @param opId  ��ǰ����Ա
	 * @param orgId  ��֯ID ����Ϊnull
	 * @param groupName  ���� ����Ϊnull
	 * @param opLoginName   ����Ա��½�� ����Ϊnull
	 * @return
     */
    public List searchGroup(Long orgId, String groupName, boolean includeAll) throws Exception;

}
