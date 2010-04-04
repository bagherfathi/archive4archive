package com.ft.busi.sm.model;

import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * 角色管理接口.
 * 
 * @ejb.client jndiName="ejb/sm/roleManager" id="roleManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface RoleManager extends EntityManager {
    /**
     * 更新功能角色
     * 
     * @param grantor
     *                执行操作的操作员
     * @param role
     *                角色
     * @param resources
     *                分配给角色的功能权限
     * @param longs
     * @param appRequest
     *                受理记录
     */
    public void updateRole(OperatorDTO grantor, RoleDTO role, List resources,
            Long[] orgIds, AppRequest appRequest) throws Exception;

    /**
     * 删除角色信息
     * 
     * @param grantor
     *                操作员实体数据
     * @param role
     *                角色实体数据
     * @param appRequest
     *                受理记录
     */
    public void deleteRole(OperatorDTO grantor, RoleDTO role,
            AppRequest appRequest) throws Exception;

    /**
     * 创建功能角色
     * 
     * @param role
     *                执行操作的操作员
     * @param resources
     *                功能权限列表
     * @param orgIds
     *                适用组织id 可以为空
     * @param appRequest
     *                受理记录
     * @return 功能权限角色实体ID
     */
    public Long addRole(OperatorDTO grantor, RoleDTO role, List resources,
            Long[] orgIds, AppRequest appRequest) throws Exception;

    /**
     * 创建业务角色
     * 
     * @param grantor
     *                操作员实体数据
     * @param role
     *                角色实体数据
     * @param entryList
     *                业务权限条目数据列表
     * @param appRequest
     *                受理记录
     * @return 业务权限角色ID
     */
    public Long addDataRole(OperatorDTO grantor, RoleDTO role, List entryList,
            AppRequest appRequest) throws Exception;

    /**
     * 更新业务角色
     * 
     * @param grantor
     *                操作员实体数据
     * @param role
     *                角色实体数据
     * @param entryList
     *                业务权限条目数据列表
     * @param appRequest
     *                受理记录
     */
    public void updateDataRole(OperatorDTO grantor, RoleDTO role,
            List entryList, AppRequest appRequest) throws Exception;

    /**
     * 查询操作员组在组织拥有的角色
     * 
     * @param groupId
     *                操作员组ID
     * @param orgId
     *                组织ID，若orgId为null时，查询操作员组所有角色
     * @return 角色数据列表
     */
    public List findRolesOfGroup(Long groupId, Long orgId) throws Exception;

    /**
     * 查询操作员组可分配的角色
     * 
     * @param groupId
     *                操作员组ID
     * @param orgId
     *                组织ID，若orgId为null时，查询操作员组所有可分配角色
     * @return 角色数据列表
     */
    public List findAvailableRolesOfGroup(Long groupId, Long orgId)
            throws Exception;

    /**
     * 查询操作员在组织中的角色
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                组织ID
     * @param roleType
     *                角色类型
     * @return 角色数据列表
     */
    public List findRolesOfOperator(Long opId, Long orgId, long roleType)
            throws Exception;

    /**
     * 查询操作员在组织中可设置的角色
     * 
     * @param opId
     *                操作员ID
     * @param orgId
     *                组织ID
     * @param roleType
     *                角色类型
     * @return 角色数据列表
     */
    public List findAvailableRolesOfOperator(Long opId, Long orgId,
            long roleType) throws Exception;

    /**
     * 根据ID查询角色
     * 
     * @param roleId
     *                角色ID
     * @return 角色实体数据
     */
    public RoleDTO findRoleById(Long roleId) throws Exception;

    /**
     * 获取指定类型的角色。角色分为功能角色和业务角色。
     * 
     * @param roleType
     * @return
     */
    public List findRoleByType(Long roleType) throws Exception;

    /**
     * 查询角色。
     * 
     * @param roleName
     *                角色名称
     * @param roleType
     *                角色类型
     * @return
     */
    public RoleDTO findRoleByName(String roleName, long roleType);

    /**
     * 根据所属组织和角色名查询角色
     * 
     * @param roleName
     *                角色名称 可以为null
     * @param roleType
     *                角色类型
     * @return
     */
    public List findRoleByRoleName(String roleName, long roleType);

    /**
     * 根据适应组织查询角色
     * 
     * @param orgId
     *                适应组织ID
     * @param roleType
     *                角色类型
     */
    public List findRoleByAdaptOrgId(Long orgId, long roleType);
    
    /**
     * 获取所有业务角色对应业务权限关系。
     * @return
     * @throws Exception
     */
    public List findAllRelRoleDataRes() throws Exception;
    
    /**
     * 根据角色标识和组织标识查询在该组织下该角色的所有员工
     * @param roleId
     * @param orgId
     * @return
     * @throws Exception
     */
    public List findOperatorsOfRole(Long roleId,Long orgId)throws Exception;

}
