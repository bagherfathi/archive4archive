package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * ����Ա����ӿڡ�
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/operatorManager" id="operatorManager"
 *             homepackage="com.huashu.boss.busi.sm.ejb"
 */
public interface OperatorManager extends EntityManager {
	/**
	 * ������֯��ѯ����Ա��
	 * 
	 * @param orgId
	 *            ��֯ID��
	 * @return
	 */
	public List findOpByOrg(Long orgId) throws Exception;

	/**
	 * �������²���Ա��
	 * 
	 * @param op
	 *            ����Աʵ�塣
	 * @param org
	 *            ����Ա������֯ʵ�塣
	 * @return
	 */
	public Long saveOrUpdateOperator(OperatorDTO op, OrganizationDTO org)
			throws Exception;

	/**
	 * �������Ա���ڵ��顣
	 * 
	 * @grantor ��Ȩ�Ĳ���Ա��
	 * @param op
	 *            ��Ȩ����
	 * @param groups
	 *            ����Ĳ���Ա�顣
	 */
	public void saveOperatorGroup(OperatorDTO grantor, OperatorDTO op,
			GroupDTO[] groups) throws Exception;

	/**
	 * �޸Ĳ���Ա�����롣
	 * 
	 * @param op
	 *            ����Աʵ�塣
	 * @param oldPassword
	 *            ԭ�������롣
	 * @param newPassword
	 *            �µ����롣
	 * @return
	 */
	public boolean changePassword(OperatorDTO op, String oldPassword,
			String newPassword) throws Exception;

	/**
	 * �޸Ĳ���Ա�����롣
	 * 
	 * @param op
	 *            ����Աʵ�塣
	 * @param newPassword
	 *            �µ����롣
	 * @return
	 */
	public void changePassword(OperatorDTO op, String newPassword)
			throws Exception;

	/**
	 * �������Ա�Ŀɷ��ʵ���֯��
	 * 
	 * @grantor ��Ȩ�Ĳ���Ա��
	 * @param op
	 *            ��Ȩ����
	 * @param orgs
	 *            ����Ŀɷ�����֯��
	 */
	public void saveOperatorAccessOrg(OperatorDTO grantor, OperatorDTO op,
			OrganizationDTO[] orgs) throws Exception;

	/**
	 * ���ò���Ա����֯��ӵ�еĽ�ɫ��
	 * 
	 * @grantor ��Ȩ�Ĳ���Ա��
	 * @param op
	 *            ��Ȩ����
	 * @param roles
	 *            ����Ľ�ɫ��
	 * @param org
	 *            �ɷ�����֯��
	 * @param roleType
	 *            ��ɫ���͡�
	 */
	public void saveOperatorRoleForOrg(OperatorDTO grantor, OperatorDTO op,
			RoleDTO[] roles, OrganizationDTO org, long roleType)
			throws Exception;

	/**
	 * ���ݵ�½����ȡ����Ա��
	 * 
	 * @param loginName
	 *            ����Ա��½����
	 * @return
	 */
	public OperatorDTO findOperatorByLoginName(String loginName)
			throws Exception;

	/**
	 * ��ֹ����Ա��
	 * 
	 * @param ops
	 *            ����Ա���顣
	 */
	public void disableOperator(OperatorDTO[] ops) throws Exception;

	/**
	 * �������Ա��
	 * 
	 * @param op
	 *            ����Աʵ�塣
	 */
	public void enableOperator(OperatorDTO op) throws Exception;

    /**
     * ��ѯ����Ա��ί�е���������Ա�� ����Ա���Խ���ӵ�е�Ȩ��ί�и���ɷ�����֯�е���������Ա��
     * 
     * @param operatorId
     *                ����ԱID��
     * @param opName
     *                ����Ա���ơ�
     * @param loginName
     *                ����Ա��½���ơ�
     * @param orgId 
     *                ����Ա������֯��
     * @return
     */
    public List findCanConsignedOperators(Long operatorId, String opName,
            String loginName,Long orgId,boolean likeOrg) throws Exception;

	/**
	 * ��ѯ����Ա���еĹ��ܽ�ɫ��ϵ��
	 * 
	 * @param operatorId
	 *            ����ԱID��
	 * @param roleType
	 *            ��ɫ���͡�
	 * @param virtual �Ƿ���������ɫ           
	 * @return OperatorRoleForOrg�����б�
	 */
	public List findOperatorRoleForOrgsOfOperator(Long operatorId, Long roleType,boolean virtual)
			throws Exception;

	/**
	 * ��ѯָ������Ȩ����������Ĳ���Ա�б�
	 * 
	 * @param resourceId
	 *            ����Ȩ��ID��
	 * @return
	 */
	public List findOperatorsByResource(Long orgId, Long resourceId)
			throws Exception;

	/**
	 * ��ѯҵ��Ȩ�޷�����Ĳ���Ա��
	 * 
	 * @param entryId
	 *            ҵ��Ȩ����ĿID��
	 * @return
	 */
	public List findOperatorOfDataResourceEntry(Long entryId) throws Exception;
        
        /**
         * ��ѯҵ��Ȩ�޷�����Ĳ���Ա��
         * 
         * @param entryIds
         *            ҵ��Ȩ����ĿID���顣
         * @return
         */
        public List findOperatorOfDataResourceEntry(Long[] entryIds) throws Exception;

	/**
	 * ����SSOΩһ�����ѯ����Ա��
	 * 
	 * @param ssoCode
	 *            ����Ա��SSOϵͳ�е�Ωһ���롣
	 * @return
	 */
	public OperatorDTO findOperatorBySSOCode(String ssoCode) throws Exception;

	/**
	 * ���²���Ա��Ϣ��
	 * 
	 * @param op
	 */
	public void updateOperator(OperatorDTO op) throws Exception;

	/**
	 * ��ѯ����Ա��
	 * 
	 * @param orgId
	 *            ����Ա������֯ID��Ϊnullʱ��ѯ������֯�µĲ���Ա��
	 * @param name
	 *            ����Ա���ƣ���ָ��ʱΪnull��
	 * @param loginName
	 *            ����Ա��½������ָ��ʱΪnull��
	 * @return ��ѯ���Ĳ���Ա��
	 */
	public OperatorDTO[] findOperators(Long orgId, String name, String loginName)
			throws Exception;

	/**
	 * �½�һ������Ա��
	 * 
	 * @param op
	 *            ����Ա��
	 * @param orgId
	 *            ��֯ID��
	 * @param roleIds
	 *            ��ɫ��Id��
	 * @param groupIds
	 *            ��Id��
	 * @return
	 */
	public OperatorDTO createOpreator(OperatorDTO op, Long orgId,
			Long[] roleIds, Long[] groupIds) throws Exception;

	/**
	 * ���ݲ���Ա��ʶ��ѯ����Ա��
	 * 
	 * @param operatorId
	 *            ����Ա��ʶ��
	 * @return
	 */
	public OperatorDTO findOperatorById(Long operatorId) throws Exception;

	/**
	 * ��ѯ����Ա���еĲ���Ա��
	 * 
	 * @param groupId
	 *            ����Ա��ID��
	 * @return
	 */
	public List findOperatorsOfGroup(Long groupId) throws Exception;

	/**
	 * ���ݽ�ɫ��ѯ�Ĳ���Ա��
	 * 
	 * @param roleId
	 *            ��ɫID��
	 * @return
	 */
	public List findOperatorOfRole(Long roleId) throws Exception;

	/**
	 * ���ò���Ա�ɷ��ʽ�ɫ��
	 * 
	 * @param grantor
	 * @param op
	 * @param org
	 * @param resourceIds
	 * @throws Exception
	 */
	public void saveOperatorResourceForOrg(OperatorDTO grantor, OperatorDTO op,
			OrganizationDTO org, Long[] resourceIds) throws Exception;

	/**
	 * �������ò���Ա�Ľ�ɫ
	 * 
	 * @param currentUser
	 * @param ops
	 * @param roles
	 * @param org
	 * @param roleType
	 */
	public void batchSaveOperatorRoleForOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, RoleDTO[] roles, OrganizationDTO org,
			long roleType) throws Exception;

	/**
	 * ����ɾ������Ա�Ľ�ɫ
	 * 
	 * @param currentUser
	 * @param ops
	 * @param roles
	 * @param org
	 * @param role_type_function
	 */
	public void batchDeleteOperatorRoleForOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, RoleDTO[] roles, OrganizationDTO org,
			long role_type_function) throws Exception;

	/**
	 * �����������Ա�Ŀɷ�����֯
	 * 
	 * @param currentUser
	 * @param ops
	 * @param orgs
	 */
	public void batchSaveOperatorAccessOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception;

	/**
	 * ����ɾ������Ա�Ŀɷ�����֯
	 * 
	 * @param currentUser
	 * @param ops
	 * @param orgs
	 */
	public void batchDeleteOperatorAccessOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception;

	/**
	 * �������ò���Ա����
	 * 
	 * @param currentUser
	 * @param ops
	 * @param groups
	 */
	public void batchSaveOperatorGroup(OperatorDTO currentUser,
			OperatorDTO[] ops, GroupDTO[] groups) throws Exception;

	/**
	 * ����ɾ������Ա����
	 * 
	 * @param currentUser
	 * @param ops
	 * @param groups
	 */
	public void batchDeleteOperatorGroup(OperatorDTO currentUser,
			OperatorDTO[] ops, GroupDTO[] groups) throws Exception;

	/**
	 * ���Ʋ���Ա��Ȩ��
	 * 
	 * @param currentUser
	 *            ��ǰ����Ա
	 * @param sourceOp
	 *            Դ����Ա
	 * @param targetOps
	 *            Ŀ�����Ա
	 * @param types
	 *            ���Ƶ�Ȩ�� 1.���ܽ�ɫ 2.ҵ���ɫ 3.�ɷ�����֯ 4.��
	 */
	public void copyOperator(OperatorDTO currentUser, Long sourceOpId,
			OperatorDTO[] targetOps, long[] types) throws Exception;
    
	/**
	 * ��ѯ����Ա�ɲ����Ĳ���Ա
	 * @param operatorId
	 * @return
	 */
    public List findOperatorsInAccessOrgs(String loginName,String name,Long operatorId , Long loginOrgId);
	
	/**
	 * ��ѯ����Ա
	 * @param opId  ��ǰ����Ա
	 * @param orgId  ��֯ID
	 * @param loginName  ����Ա��½�� ����Ϊnull
	 * @param name   ����Ա���� ����Ϊnull
         * @param includeAll �Ƿ����ָ����֯����������֯�еĲ���Ա��
	 * @return  operatorDTO����
	 */
	public List searchOperator(Long orgId, String loginName, String name,boolean includeAll);

    
    public List searchOperator(String orgIdStr);
}
