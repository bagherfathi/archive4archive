package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Region;

/**
 * 组织机构管理接口。
 * 
 * @ejb.client jndiName="ejb/sm/orgManager" id="orgManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface OrgManager extends EntityManager,XmlTreeManager {
    /**
     * 根据组织ID查找组织。
     * 
     * @param orgId
     *                组织ID。
     * @return 组织实体。
     */
    public OrganizationDTO findOrgById(Long orgId) throws Exception;

    /**
     * 创建组织机构信息。
     * 
     * @param org
     *                组织实体。
     * @param parentOrg
     *                父组织实体。
     * @return 组织ID。
     */
    public Long save(OrganizationDTO org, OrganizationDTO parentOrg)
            throws Exception;
    
    /**
     * 更新组织机构信息。
     * @param org    组织机构。
     * @param parentOrg    父组织机构。
     * @throws Exception
     */
    public void update(OrganizationDTO org, OrganizationDTO parentOrg) throws Exception;

    /**
     * 查找系统所有组织机构。
     * 
     * @return 组织实体列表。
     */
    public List findAllOrgOrderByOrgName() throws Exception;

    /**
     * 查找组织机构根节点。
     * 
     * @return 组织实体。
     */
    public OrganizationDTO findRootOrg() throws Exception;

    /**
     * 保存组织可访问区域。
     * 
     * @param org
     *                组织实体。
     * @param selectRegion
     *                可访问区域列表。
     */
    public void saveOrgAccessRegion(OrganizationDTO org, Region[] selectRegion)
            throws Exception;

    /**
     * 禁止组织。
     * 
     * @param orgs
     *                组织实体数组。
     */
    public void disableOrg(OrganizationDTO[] orgs) throws Exception;

    /**
     * 解禁组织。
     * 
     * @param org
     *                组织实体。
     */
    public void enableOrg(OrganizationDTO org) throws Exception;

    /**
     * 获取操作员组的可访问组织。
     * 
     * @param groupId
     *                操作员组ID。
     * @return Organization对象列表。
     */
    public List findAcessOrgByGroupId(Long groupId) throws Exception;
    
    /**
     * 获取操作员组的可访问组织，包括子组织。
     * 
     * @param groupId
     *                操作员组ID。
     * @return Organization对象列表。
     */
    public List findAcessOrgByGroupIdWithChild(Long groupId) throws Exception;

    /**
     * 查询操作员自己的可访问组织，不包括其所在组的。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAccessOrgsForOperator(Long operatorId) throws Exception;
    
    /**
     * 查询操作员自己的可访问组织，包括子组织，不包括其所在组的。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAccessOrgsForOperatorWithChild(Long operatorId) throws Exception;

    /**
     * 查询操作员所在组的可访问组织。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAccessOrgForOperatorInGroups(Long operatorId)
            throws Exception;

    /**
     * 查询操作员所有可访问组织。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAllAccessOrgsForOperator(Long operatorId) throws Exception;
    
    /**
     * 查询操作员所有可访问组织,包括其子孙组织。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public List findAllAccessOrgsForOperatorIncludeChildren(Long operatorId) throws Exception;
    
    /**
     * 查询操作员所有可访问组织，包括子组织。
     * @param operatorId    操作员ID。
     * @return
     * @throws Exception
     */
    public List findAllAccessOrgIdsOfOperatorIncludeChild(Long operatorId) throws Exception;

    /**
     * 根据SSO惟一编码查询组织。
     * 
     * @param ssoCode
     *                组织在SSO系统中的惟一编码。
     * @return
     */
    public OrganizationDTO findOrgBySSOCode(String ssoCode) throws Exception;

    /**
     * 查询指定组织ID的子组织。
     * 
     * @param parentId
     * @return
     */
    public List findChildrenOrgs(Long parentId) throws Exception;
    
    /**
     * 查询角色的适应组织
     * @param roleId
     * @return
     * @throws Exception
     */
    public List findOrgsOfRole(Long roleId) throws Exception;

    
    /**
     * 查询指定组织可访问的组织。可访问组织类型包括分公司、数据区域、营业厅。用于查询登录组织可访问的组织。
     * 查询组织可访问分公司时，如果该组织类型为分公司，则其可访问分公司为该组织以及其所有的子分公司；否则为该组织所属分公司；
     * 查询组织可访问区域时，如果该组织属于某个数据区域下的组织，则可访问数据区域为其所属数据区域；否则其可访问区域为该组织下所有的数据区域；
     * 查询组织可访问营业厅时，如果该组织为营业厅，则其可访问组织为该营业厅所在数据区域下所有营业厅，包括代理商营业厅；若该组织为代理商，则其
     * 访问营业厅为该组织以及所有子营业厅；若该组织为数据区域，则为其下所有的营业厅；若组织为分公司，则为其下所有数据区域下的营业厅。
     * 
     * @param orgId       指定组织机构ID。
     * @param lorgType    可访问组织类型。
     * @param includeAll 对于获取分公司下的数据区域、营业厅时，标志是否获取其下子分公司下的组织。只有指定组织为分公司时有效。
     * @return
     */
    public List findAccessOrgsOfOrg(Long orgId, long lorgType,boolean includeAll) throws Exception;

    /**
     * 查询指定组织可访问组织ID。
     * @param orgId       指定组织机构ID。
     * @param lorgType    可访问组织类型。
     * @param includeAll 对于获取分公司下的数据区域、营业厅时，标志是否获取其下子分公司下的组织。只有指定组织为分公司时有效。
     * @return
     */
    public List findAccessOrgIdsOfOrg(Long orgId, long lorgType,boolean includeAll) throws Exception;

    /**
     * 查询指定组织所属分公司ID。
     * @param orgId    指定组织ID。
     * @return
     */
    public Long findCompanyIdOfOrg(Long orgId) throws Exception;

    /**
     * 查询指定组织所属分公司。
     * @param orgId
     * @return
     */
    public OrganizationDTO findCompanyOfOrg(Long orgId) throws Exception;
    
    /**
     * 查询操作员继承的可访问组织，包括子组织。
     * @param opId
     * @return
     * @throws Exception
     */
    
    public List findAccessOrgForOperatorInGroupsWithChild(Long opId) throws Exception;
    
    /**
     * 查询当前操作员的登陆可访问组织与指定操作员的可访问组织的交集
     * @param opId 指定操作员Id
     * @param ids  当前操作员的登陆可访问组织id
     * @return
     * @throws Exception
     */
    public List findAccessOrgForOperatorInLoginOrg(Long opId , long ids[] ) throws Exception;
    
    /**
     * 查询当前操作员的登陆可访问组织与指定操作员组的可访问组织的交集
     * @param groupId 指定操作员组Id
     * @param ids  当前操作员的登陆可访问组织id
     * @return
     * @throws Exception
     */
    public List findAccessOrgForGroupInLoginOrg(Long groupId , long ids[] ) throws Exception;
    
    /**
     * 查询指定分公司可访问的组织。
     * @param org    分公司组织机构。
     * @param type   组织类型，-1时为所有组织类型
     * @param includeChildCompany   是否包括其子分公司下的组织。
     * @return
     * @throws Exception
     */
    public List findAccessOrgOfCompany(OrganizationDTO org,long type,boolean includeChildCompany) throws Exception;

    /**
     * 根据组织类型查询组织
     * @param orgType 组织类型
     * @return
     */
    public List findOrgsByType(int orgType);
   
    
}