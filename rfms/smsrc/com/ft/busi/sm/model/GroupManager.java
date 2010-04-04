package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * 操作员组管理接口。
 * 
 * 
 * @ejb.client jndiName="ejb/sm/groupManager" id="groupManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface GroupManager extends EntityManager {
    /**
     * 保存操作员组可访问组织机构。
     * 
     * @param 授权人。
     * @param selectOrgs
     *                可访问组织列表。
     * @param group
     *                操作员组实体。
     */
    public void saveGroupAccessOrg(OperatorDTO grantor,
            OrganizationDTO[] selectOrgs, GroupDTO group) throws Exception;

    /**
     * 保存操作员组。
     * 
     * @param group
     *                操作员组。
     * @param appRequest
     *                受理记录。
     * @return
     */
    public Long saveGroup(GroupDTO group, AppRequest appRequest)
            throws Exception;

    /**
     * 删除操作员组。
     * 
     * @param groups
     *                操作员组实体数组。
     * @param appRequest
     *                受理记录。
     */
    public void deleteGroup(GroupDTO[] groups, AppRequest request)
            throws Exception;

    /**
     * 保存组,角色,组织的关系。
     * 
     * @param grantor
     *                授权人。
     * @param group
     *                操作员组实体。
     * @param selectRoles
     *                角色数组。
     * @param org
     *                可访问组织实体。
     */
    public void saveGroupRoleForOrg(OperatorDTO grantor, GroupDTO group,
            RoleDTO[] selectRoles, OrganizationDTO org) throws Exception;

    /**
     * 查询操作员组所有角色关系。
     * 
     * @param groupId
     *                操作员组ID。
     * @return RelGroupRoleDTO对象列表。
     */
    public List findGroupRoleForOrgsByGroupId(Long groupId) throws Exception;

    /**
     * 查询操作员所在组拥有的角色。
     * 
     * @param operatorId
     *                操作员ID。
     * @param role_type_data
     *                角色类型。
     * @return
     */
    public List findGroupRoleForOrgsOfOperator(Long operatorId, Long roleType)
            throws Exception;

    /**
     * 查询操作员所在组。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findGroupsByOperator(Long operatorId) throws Exception;
    
    /**
     * 查询操作员所在组。
     * 
     * @param operatorId
     *                操作员ID。
     * @param orgId  
     *                组织ID。
     * @return
     */
    public List findGroupsByOperator(Long operatorId,Long orgId) throws Exception;

    /**
     * 查询操作员可分配组。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAvailableGroupsByOperator(Long operatorId,Long orgId) throws Exception;

    /**
     * 查询操作员在组中可访问组织关系。
     * 
     * @param operatorId
     *                操作员ID。
     * @return GroupAccessOrg对象列表。
     */
    public List findGroupAccessOrgsOfOperator(Long operatorId) throws Exception;

    /**
     * 查询和功能权限相关的操作员组。
     * 
     * @param resourceId
     *                功能权限ID。
     * @param orgId   组织机构ID。
     * @return
     */
    public List findGroupsByResource(Long orgId,Long resourceId) throws Exception;

    /**
     * 查找所有的操作员组
     * 
     * @return
     */
    public List findAllGroup() throws Exception;

     /**
     * 查询操作员组。
     * @param opId  当前操作员
	 * @param orgId  组织ID 可以为null
	 * @param groupName  组名 可以为null
	 * @param opLoginName   操作员登陆名 可以为null
	 * @return
     */
    public List searchGroup(Long orgId, String groupName, boolean includeAll) throws Exception;

}
