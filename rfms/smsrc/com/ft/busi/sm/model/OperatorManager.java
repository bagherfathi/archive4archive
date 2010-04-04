package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * 操作员管理接口。
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/operatorManager" id="operatorManager"
 *             homepackage="com.huashu.boss.busi.sm.ejb"
 */
public interface OperatorManager extends EntityManager {
	/**
	 * 根据组织查询操作员。
	 * 
	 * @param orgId
	 *            组织ID。
	 * @return
	 */
	public List findOpByOrg(Long orgId) throws Exception;

	/**
	 * 保存或更新操作员。
	 * 
	 * @param op
	 *            操作员实体。
	 * @param org
	 *            操作员所属组织实体。
	 * @return
	 */
	public Long saveOrUpdateOperator(OperatorDTO op, OrganizationDTO org)
			throws Exception;

	/**
	 * 保存操作员所在的组。
	 * 
	 * @grantor 授权的操作员。
	 * @param op
	 *            授权对象。
	 * @param groups
	 *            授予的操作员组。
	 */
	public void saveOperatorGroup(OperatorDTO grantor, OperatorDTO op,
			GroupDTO[] groups) throws Exception;

	/**
	 * 修改操作员的密码。
	 * 
	 * @param op
	 *            操作员实体。
	 * @param oldPassword
	 *            原来的密码。
	 * @param newPassword
	 *            新的密码。
	 * @return
	 */
	public boolean changePassword(OperatorDTO op, String oldPassword,
			String newPassword) throws Exception;

	/**
	 * 修改操作员的密码。
	 * 
	 * @param op
	 *            操作员实体。
	 * @param newPassword
	 *            新的密码。
	 * @return
	 */
	public void changePassword(OperatorDTO op, String newPassword)
			throws Exception;

	/**
	 * 保存操作员的可访问的组织。
	 * 
	 * @grantor 授权的操作员。
	 * @param op
	 *            授权对象。
	 * @param orgs
	 *            授予的可访问组织。
	 */
	public void saveOperatorAccessOrg(OperatorDTO grantor, OperatorDTO op,
			OrganizationDTO[] orgs) throws Exception;

	/**
	 * 设置操作员在组织中拥有的角色。
	 * 
	 * @grantor 授权的操作员。
	 * @param op
	 *            授权对象。
	 * @param roles
	 *            授予的角色。
	 * @param org
	 *            可访问组织。
	 * @param roleType
	 *            角色类型。
	 */
	public void saveOperatorRoleForOrg(OperatorDTO grantor, OperatorDTO op,
			RoleDTO[] roles, OrganizationDTO org, long roleType)
			throws Exception;

	/**
	 * 根据登陆名获取操作员。
	 * 
	 * @param loginName
	 *            操作员登陆名。
	 * @return
	 */
	public OperatorDTO findOperatorByLoginName(String loginName)
			throws Exception;

	/**
	 * 禁止操作员。
	 * 
	 * @param ops
	 *            操作员数组。
	 */
	public void disableOperator(OperatorDTO[] ops) throws Exception;

	/**
	 * 解禁操作员。
	 * 
	 * @param op
	 *            操作员实体。
	 */
	public void enableOperator(OperatorDTO op) throws Exception;

    /**
     * 查询操作员可委托的其他操作员。 操作员可以将其拥有的权限委托给其可访问组织中的其他操作员。
     * 
     * @param operatorId
     *                操作员ID。
     * @param opName
     *                操作员名称。
     * @param loginName
     *                操作员登陆名称。
     * @param orgId 
     *                操作员所属组织。
     * @return
     */
    public List findCanConsignedOperators(Long operatorId, String opName,
            String loginName,Long orgId,boolean likeOrg) throws Exception;

	/**
	 * 查询操作员所有的功能角色关系。
	 * 
	 * @param operatorId
	 *            操作员ID。
	 * @param roleType
	 *            角色类型。
	 * @param virtual 是否包括虚拟角色           
	 * @return OperatorRoleForOrg对象列表。
	 */
	public List findOperatorRoleForOrgsOfOperator(Long operatorId, Long roleType,boolean virtual)
			throws Exception;

	/**
	 * 查询指定功能权限所分配给的操作员列表。
	 * 
	 * @param resourceId
	 *            功能权限ID。
	 * @return
	 */
	public List findOperatorsByResource(Long orgId, Long resourceId)
			throws Exception;

	/**
	 * 查询业务权限分配给的操作员。
	 * 
	 * @param entryId
	 *            业务权限条目ID。
	 * @return
	 */
	public List findOperatorOfDataResourceEntry(Long entryId) throws Exception;
        
        /**
         * 查询业务权限分配给的操作员。
         * 
         * @param entryIds
         *            业务权限条目ID数组。
         * @return
         */
        public List findOperatorOfDataResourceEntry(Long[] entryIds) throws Exception;

	/**
	 * 根据SSO惟一编码查询操作员。
	 * 
	 * @param ssoCode
	 *            操作员在SSO系统中的惟一编码。
	 * @return
	 */
	public OperatorDTO findOperatorBySSOCode(String ssoCode) throws Exception;

	/**
	 * 更新操作员信息。
	 * 
	 * @param op
	 */
	public void updateOperator(OperatorDTO op) throws Exception;

	/**
	 * 查询操作员。
	 * 
	 * @param orgId
	 *            操作员所属组织ID，为null时查询所有组织下的操作员。
	 * @param name
	 *            操作员名称，不指定时为null。
	 * @param loginName
	 *            操作员登陆名，不指定时为null。
	 * @return 查询到的操作员。
	 */
	public OperatorDTO[] findOperators(Long orgId, String name, String loginName)
			throws Exception;

	/**
	 * 新建一个操作员。
	 * 
	 * @param op
	 *            操作员。
	 * @param orgId
	 *            组织ID。
	 * @param roleIds
	 *            角色组Id。
	 * @param groupIds
	 *            组Id。
	 * @return
	 */
	public OperatorDTO createOpreator(OperatorDTO op, Long orgId,
			Long[] roleIds, Long[] groupIds) throws Exception;

	/**
	 * 根据操作员标识查询操作员。
	 * 
	 * @param operatorId
	 *            操作员标识。
	 * @return
	 */
	public OperatorDTO findOperatorById(Long operatorId) throws Exception;

	/**
	 * 查询操作员组中的操作员。
	 * 
	 * @param groupId
	 *            操作员组ID。
	 * @return
	 */
	public List findOperatorsOfGroup(Long groupId) throws Exception;

	/**
	 * 根据角色查询的操作员。
	 * 
	 * @param roleId
	 *            角色ID。
	 * @return
	 */
	public List findOperatorOfRole(Long roleId) throws Exception;

	/**
	 * 设置操作员可访问角色。
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
	 * 批量设置操作员的角色
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
	 * 批量删除操作员的角色
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
	 * 批量保存操作员的可访问组织
	 * 
	 * @param currentUser
	 * @param ops
	 * @param orgs
	 */
	public void batchSaveOperatorAccessOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception;

	/**
	 * 批量删除操作员的可访问组织
	 * 
	 * @param currentUser
	 * @param ops
	 * @param orgs
	 */
	public void batchDeleteOperatorAccessOrg(OperatorDTO currentUser,
			OperatorDTO[] ops, OrganizationDTO[] orgs) throws Exception;

	/**
	 * 批量设置操作员的组
	 * 
	 * @param currentUser
	 * @param ops
	 * @param groups
	 */
	public void batchSaveOperatorGroup(OperatorDTO currentUser,
			OperatorDTO[] ops, GroupDTO[] groups) throws Exception;

	/**
	 * 批量删除操作员的组
	 * 
	 * @param currentUser
	 * @param ops
	 * @param groups
	 */
	public void batchDeleteOperatorGroup(OperatorDTO currentUser,
			OperatorDTO[] ops, GroupDTO[] groups) throws Exception;

	/**
	 * 复制操作员的权限
	 * 
	 * @param currentUser
	 *            当前操作员
	 * @param sourceOp
	 *            源操作员
	 * @param targetOps
	 *            目标操作员
	 * @param types
	 *            复制的权限 1.功能角色 2.业务角色 3.可访问组织 4.组
	 */
	public void copyOperator(OperatorDTO currentUser, Long sourceOpId,
			OperatorDTO[] targetOps, long[] types) throws Exception;
    
	/**
	 * 查询操作员可操作的操作员
	 * @param operatorId
	 * @return
	 */
    public List findOperatorsInAccessOrgs(String loginName,String name,Long operatorId , Long loginOrgId);
	
	/**
	 * 查询操作员
	 * @param opId  当前操作员
	 * @param orgId  组织ID
	 * @param loginName  操作员登陆名 可以为null
	 * @param name   操作员姓名 可以为null
         * @param includeAll 是否包括指定组织下所有子组织中的操作员。
	 * @return  operatorDTO集合
	 */
	public List searchOperator(Long orgId, String loginName, String name,boolean includeAll);

    
    public List searchOperator(String orgIdStr);
}
