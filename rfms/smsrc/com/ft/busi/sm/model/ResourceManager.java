package com.ft.busi.sm.model;

import java.util.Date;
import java.util.List;

import com.ft.busi.dto.AppRequest;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.ResourceDTO;

/**
 * 功能权限实体和业务权限实体管理接口.
 * 
 * @version 1.0
 * 
 * @ejb.client jndiName="ejb/sm/resourceManager" id="resourceManager"
 *             homepackage="com.huashu.boss.busi.sm.ejb"
 */
public interface ResourceManager extends EntityManager,XmlTreeManager {
    /**
     * 创建菜单权限
     * 
     * @param menu
     *                功能权限菜单数据
     * @param parentMenu
     *                父项功能权限数据
     * @param appRequest 
     *                受理记录
     * @return 功能权限实体数据
     */
    public ResourceDTO addResource(ResourceDTO menu, ResourceDTO parentMenu,AppRequest appRequest)
            throws Exception;

    /**
     * 更新菜单/按钮权限
     * 
     * @param resource
     *                功能权限实体数据
     * @param appRequest
     *                受理记录
     */
    public void updateResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception;

    /**
     * 批更新权限的排序信息
     * 
     * @param resource
     *                功能权限列表
     */
    public void updateResources(List resource) throws Exception;

    /**
     * 查选子权限
     * 
     * @param resourceId
     *                功能权限ID
     * @return 所有子功能权限数据的列表
     */
    public List findChildren(Long resourceId) throws Exception;

    /**
     * 查找所有功能权限
     * 
     * @return 所有功能权限数据列表
     */
    public List findAllResources() throws Exception;

    /**
     * 创建业务权限
     * 
     * @param dataResource
     *                业务权限实体数据
     * @param appRequest 受理记录
     * @return 业务权限实体ID
     */
    public Long addDataResource(DataResourceDTO dataResource, AppRequest appRequest) throws Exception;

    /**
     * 删除业务权限
     * 
     * @param appRequest
     *                受理记录
     * @param dataResources
     *                业务权限列表
     */
    public void deleteDataResource(DataResourceDTO dr, AppRequest appRequest)
            throws Exception;

    /**
     * 更新业务权限
     * 
     * @param dataResource
     *                业务权限实体数据
     * @param dataResources
     *                业务权限列表
     */
    public void updateDataResource(DataResourceDTO dataResource,
            AppRequest appRequest) throws Exception;

    /**
     * 获得所有的业务权限
     * 
     * @return 所有业务权限实体数据列表
     */
    public List findAllDataResource() throws Exception;

    /**
     * 获得所有的业务权限条目
     * 
     * @return 所有业务权限条目数据列表
     */
    public List findAllDataResourceEntry() throws Exception;

    /**
     * 删除功能权限
     * 
     * @param resource
     *                功能权限
     * @param appRequest
     *                受理记录
     */
    public void deleteResource(ResourceDTO resource, AppRequest appRequest)
            throws Exception;

    /**
     * 查询角色的功能权限
     * 
     * @param roleId
     *                角色ID
     * @return 角色所拥有的功能权限数据列表
     */
    public List findResourceOfRole(Long roleId) throws Exception;

    /**
     * 查询角色的功能权限，包括所有的子功能权限。
     * 
     * @param roleId
     *                角色ID。
     * @return 角色所拥有的功能权限列表。
     */
    public List findAllCheckedResourceByRoleId(Long roleId) throws Exception;

    /**
     * 查询角色的业务权限
     * 
     * @param roleId
     *                角色ID
     * @return 角色所拥有的业务权限数据列表
     */
    public List findDataResourcesOfRole(Long roleId) throws Exception;

    /**
     * 查询操作员所有的功能权限
     * 
     * @param operatorId
     *                操作员ID
     * @param orgId
     *                组织ID
     * @return 操作员所拥有的功能权限数据列表
     */
    public List findAllACLResourcesOfOperator(Long operatorId, Long orgId,
            Date time) throws Exception;

    /**
     * 查询操作员除了委托外的其他功能权限
     * 
     * @param operatorId
     *                操作员ID
     * @param orgId
     *                组织ID
     * @return 功能权限数据列表
     */
    public List findACLResourcesExcludeConsignedOfOperator(Long operatorId,
            Long orgId) throws Exception;

    /**
     * 查询操作员的业务权限
     * 
     * @param operatorId
     *                操作员ID
     * @return 业务权限数据列表
     */
    public List findDataResourceEntriesOfOperator(Long operatorId)
            throws Exception;

    /**
     * 查询操作员除了委托外的其他全部功能权限
     * 
     * @param operatorId
     *                操作员ID
     * @return 功能权限数据列表
     */
    public List findAllResourcesExcludeConsignedOfOperator(Long operatorId)
            throws Exception;

    /**
     * 查找操作员组所拥有的全部功能权限
     * 
     * @param groupId
     *                操作员组ID
     * @return
     */
    public List findAllResourceOfGroup(Long groupId) throws Exception;

    /**
     * 添加业务权限条目信息
     * 
     * @param entry
     *                业务权限条目数据
     * @param appRequest 受理记录
     * @return 业务权限条目ID
     */
    public Long addDataResourceEntry(DataResourceEntryDTO entry, AppRequest appRequest)
            throws Exception;

    /**
     * 删除业务权限条目信息
     * 
     * @param entry
     *                业务权限条目数据
     * @param appRequest
     *                受理记录
     */
    public void deleteDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception;

    /**
     * 更新业务权限条目信息
     * 
     * @param entry
     *                业务权限条目数据
     * @param appRequest
     *                受理记录
     */
    public void updateDataResourceEntry(DataResourceEntryDTO entry,
            AppRequest appRequest) throws Exception;

    /**
     * 获得id对应的业务权限条目信息
     * 
     * @param id
     *                业务权限ID
     * @return 业务权限条目数据
     */
    public DataResourceEntryDTO findDataResourceEntryById(Long id)
            throws Exception;

    /**
     * 通过Path获得他所对应的所有子资源
     * 
     * @param path
     *                功能权限Path数据
     * @return 功能权限数据列表
     */
    public List findChildResourceByPatch(String path) throws Exception;

    /**
     * 导入功能权限树
     * 
     * @param resourceList
     *                功能权限列表
     */
    // public List addImportResource(List resourceList) throws Exception;
    /**
     * 判断同级是否重复
     * 
     * @param resource
     *                功能权限数据
     * @param parent
     *                父项功能权限数据
     * @return
     */
    public boolean isExistResource(ResourceDTO resource, ResourceDTO parent)
            throws Exception;

    /**
     * 判断Url是否重复，不包含""和"/"
     * 
     * @param url
     *                功能权限url数据
     * @param resourceId
     *                功能权限ID
     * @return
     */
    public boolean isExistMenuUrl(String url, Long resourceId) throws Exception;

    /**
     * 判断根节点是否存在
     * 
     * @param resourceName
     *                功能权限名称
     * @return
     */
    // public boolean isExistRoot(String resourceName);
    /**
     * 判断这条业务权限条目标题是否已在业务权限中存在
     * 
     * @param entryId
     *                条目Id
     * @param title
     *                业务权限条目标题
     * @param resourceId
     *                业务权限Id
     * @return
     */
    public boolean isExistEntryByDataResource(Long entryId, String title,
            Long resourceId) throws Exception;
    
    /**
     * 检查单值型业务权限的值是否重复
     * @param resourceId  业务权限ID
     * @param entryId     条目ID 
     * @param entryValue  条目值
     * @return
     */
    public boolean isEntryValueDuplicatedOfSingleDataResource(Long entryId,
            Long resourceId, String entryValue) throws Exception;

    /**
     * 查询业务权限
     * 
     * @param opId
     *                操作员ID。
     * @param code
     *                业务权限代码
     * @return 业务权限DTO
     */
    public DataResourceDTO getDataResource(Long opId, String code)
            throws Exception;
    
    public DataResourceDTO getDataResource(Long opId, String code, OrganizationDTO orgDto);

    /**
     * 获取指定ID的业务权限中的所有条目。
     * 
     * @param resourceId
     *                业务权限ID。
     * @return
     */
    public List findEntryByDataResourceId(Long resourceId) throws Exception;
    
    /**
     * 查询直接赋予操作员的权限。
     * @param operatorId    操作员ID。
     * @param orgId         组织机构ID。
     * @return
     */
    public List findACLResourcesOfOp(Long operatorId,Long orgId) throws Exception;

    /**
     * 获取操作员在登录组织下的权限。
     * @param loginOpId
     * @param loginOrg
     * @param date
     * @return
     */
    public List findAllACLResourcesOfOperator(Long loginOpId,
            OrganizationDTO loginOrg, Date date) throws Exception;

    
}
