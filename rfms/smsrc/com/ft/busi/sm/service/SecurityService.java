package com.ft.busi.sm.service;

import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;

/**
 * 操作员，组织，权限管理的外部接口。
 * 
 * @version 1.0
 */
public interface SecurityService {
    /**
     * 查询操作员。
     * 
     * @param orgId
     *                操作员所属组织ID，为null时查询所有组织下的操作员。
     * @param name
     *                操作员名称，不指定时为null。
     * @param loginName
     *                操作员登陆名，不指定时为null。
     * @return 查询到的操作员。
     */
    public OperatorDTO[] findOperators(Long orgId, String name, String loginName);
    

    /**
     * 获取根组织
     * @return
     */
    public OrganizationDTO findRootOrg(); 
    /**
     * 新建一个操作员。
     * 
     * @param op
     *                操作员。
     * @param orgId
     *                组织ID。
     * @param roleIds
     *                角色组ID。
     * @param groupIds
     *                组ID。
     * @return
     */
    public OperatorDTO createOpreator(OperatorDTO op, Long orgId,
            Long[] roleIds, Long[] groupIds);

    /**
     * 查询操作员。
     * 
     * @param operatorId
     *                操作员ID。
     * @return
     */
    public OperatorDTO findOperatorById(Long operatorId);

    /**
     * 根据操作员登录名称获取操作员。
     * 
     * @param loginName
     * @return
     */
    public OperatorDTO findOperatorByLoginName(String loginName);

    /**
     * 根据组织，查找下面所有的子区域。
     * 
     * @param orgId
     *                组织ID。
     * @param allChild
     *                是否所有的子区域。
     * @return 区域对象数组。
     */
    public RegionDTO[] findRegionByOrgId(Long orgId, boolean allChild);

    /**
     * 根据区域，查找下面所有的子区域。
     * 
     * @param regionId
     *                区域ID。
     * @param allChild
     *                是否所有的子区域。
     * @return 区域对象数组。
     */
    public RegionDTO[] findRegionByParentId(Long regionId, boolean allChild);

    /**
     * 查询业务权限。
     * 
     * @param opId
     *                操作员ID。
     * @param code
     *                业务权限代码。
     * @param orgId
     *                组织ID。
     * @return 业务权限DTO。
     */
    public DataResourceDTO getDataResource(Long opId, String code,OrganizationDTO orgDto);

    /**
     * 查询区域的路径中的区域，按照父子关系排序。
     * 
     * @param regionId
     * @return
     */
    public RegionDTO[] getRegionLocation(Long regionId);

    /**
     * 根据区域ID查询区域信息。
     * 
     * @param regionId
     *                区域ID。
     * @return
     */
    public RegionDTO getRegionById(Long regionId);

    /**
     * 获取数据区域可访问区域中指定行政区域名称的行政区域。
     * 
     * @param orgId
     *                组织ID
     * @param regionName
     *                区域名称
     * @param regionType
     *                区域类型
     * @return
     */
    public RegionDTO getRegionOfOrg(Long orgId, long regionType,
            String regionName);
    
    /**
     * 获取指定组织可访问组织。
     * 
     * 查询指定组织可访问的组织。可访问组织类型包括分公司、数据区域、营业厅。用于查询登录组织可访问的组织。
     * 查询组织可访问分公司时，如果该组织类型为分公司，则其可访问分公司为该组织以及其所有的子分公司；否则为该组织所属分公司；
     * 查询组织可访问区域时，如果该组织属于某个数据区域下的组织，则可访问数据区域为其所属数据区域；否则其可访问区域为该组织下所有的数据区域；
     * 查询组织可访问营业厅时，如果该组织为营业厅，则其可访问组织为该营业厅所在数据区域下所有营业厅，包括代理商营业厅；若该组织为代理商，则其
     * 访问营业厅为该组织以及所有子营业厅；若该组织为数据区域，则为其下所有的营业厅；若组织为分公司，则为其下所有数据区域下的营业厅。
     * 
     * @param orgId      指定组织的标识。 
     * @param orgType    可访问的组织类型，包括1：分公司；2：营业厅；4：数据区域
     * @param includeAll 对于获取分公司下的数据区域、营业厅时，标志是否获取其下子分公司下的组织。只有指定组织为分公司时有效。
     * @return
     */
    public OrganizationDTO[] getAccessOrgsOfOrg(Long orgId,Long orgType,boolean includeAll);
    
    /**
     * 获取指定组织可访问组织ID。
     * 
     * 参看getAccessOrgsOfOrg。
     * 
     * @param orgId      指定组织的标识。 
     * @param orgType    可访问的组织类型，包括1：分公司；2：营业厅；4：数据区域
     * @param includeAll 对于获取分公司下的数据区域、营业厅时，标志是否获取其下子分公司下的组织。只有指定组织为分公司时有效。
     * @return
     */
    public Long[] getAccessOrgIdsOfOrg(Long orgId,Long orgType,boolean includeAll);
    
    /**
     * 获取指定组织所属分公司ID。
     * @param orgId    组织ID。
     * @return
     */
    public Long getCompanyIdOfOrg(Long orgId);
    
    /**
     * 获取指定组织所属分公司。
     * @param orgId    组织ID。
     * @return
     */
    public OrganizationDTO getCompanyOfOrg(Long orgId);
    
    /**
     * 获取指定组织机构。
     * @param orgId    组织机构ID。
     * @return
     */
    public OrganizationDTO getOrgById(Long orgId);
    
    /**
     * 查询所有系统中所有组织机构。
     * @param orgType    组织机构类型：-1：所有类型；0：部门；1：分公司；2：营业厅；3：代理商；4；数据区域
     * @return
     */
    public OrganizationDTO[] getAllOrgs(long orgType);
    
    /**
     * 获取角色中在指定分公司下的操作员。
     * @param roleId
     * @param orgId
     * @return
     */
    public OperatorDTO[] getOperatorOfRoleInOrg(Long roleId,OrganizationDTO org);
    
    /**
     * 创建操作员。
     * @param opName     操作员名称
     * @param loginName  操作员登录名称
     * @param password   操作员登录密码
     * @param orgId      操作员所属组织ID
     * @return
     */
    public long createOperator(String opName,String loginName,String password,long orgId);
    
    /**
     * 更新操作员。
     * @param opId      操作员ID。
     * @param opName    操作员名称。
     * @param loginName 操作员登录名称。
     * @param password  操作员登录密码。
     * @param orgId     操作员所属组织ID。
     * @return
     */
    public boolean updateOperator(long opId,String opName,String loginName,String password,long orgId);
    
    /**
     * 禁止操作员
     * @param opId
     * @return
     */
    public boolean deleteOperator(long opId);
}
