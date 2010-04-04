package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.RegionDTO;

/**
 * 区域实体管理接口。
 * 
 * @ejb.client jndiName="ejb/sm/regionManager" id="regionManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface RegionManager extends EntityManager,XmlTreeManager{
    /**
     * 创建/更新区域信息。
     * 
     * @param region
     *                区域实体数据。
     * @return 区域实体ID。
     */
    public Long saveRegion(RegionDTO region) throws Exception;

    /**
     * 查询根区域。
     * 
     * @return 区域实体数据。
     */
    public RegionDTO getRootRegion() throws Exception;

    /**
     * 查询所有区域实体。
     * 
     * @return 区域数据列表。
     */
    public List findAllRegionOrderByParent() throws Exception;

    /**
     * 禁用指定区域ID的区域信息。
     * 
     * @param regionId
     *                区域ID。
     */
    public void disableRegion(Long regionId) throws Exception;

    /**
     * 启用区域信息。
     * 
     * @param regionId
     *                区域ID。
     */
    public void enableRegion(Long regionId) throws Exception;
    
    /**
     * 查询组织可访问区域
     * @param orgIds    组织机构ID数组
     * @return
     * @throws Exception
     */
    public List findRegionsOfOrgs(Long[] orgIds) throws Exception;
    
    /**
     * 查询组织可访问区域
     * @param orgIds      数据区域组织ID
     * @param regionName  区域名称，可为null
     * @param regionId    区域ID， 可为null
     * @param regionType  要查找的区域类型
     * @param searchType  查询方式，模糊查询或精确查询
     * @return
     * @throws Exception
     */
    public List findRegionsOfOrgs(Long[] orgIds,String regionName,Long regionId,long regionType,int searchType) throws Exception;
    
    /**
     * 查询组织可访问区域，只查询其可访问区域的最上层区域。
     * @param orgId
     * @return
     * @throws Exception
     */
    public List findRefionsOfOrg(Long orgId) throws Exception;

    /**
     * 查询区域下的子区域。
     * 
     * @param regionId
     *                区域ID。
     * @return
     */
    public List findRegionByParentId(Long regionId) throws Exception;

    /**
     * 根据区域，查找下面所有的正常状态子区域。
     * 
     * @param regionId
     *                区域ID。
     * @param allChild
     *                是否所有的子区域。
     * @return 区域对象列表。
     */
    public List findRegionByParentId(Long regionId, boolean allChild)
            throws Exception;

    /**
     * 根据ID获得区域信息。
     * 
     * @param regionId
     * @return
     */
    public RegionDTO findRegionById(Long regionId) throws Exception;

    /**
     * 查询组织可访问区域列表。
     * 
     * @param orgId
     *                组织ID。
     * @return 可访问区域列表。
     */
    public List findAccessRegionByOrgId(Long orgId) throws Exception;

    /**
     * 查询指定组织的下所有组织的可访问区域。
     * 
     * @param orgPath
     *                组织路径。
     * @return 可访问区域列表。
     */
    public List findAccessRegionOfChildrenOrgByOrgId(String orgPath)
            throws Exception;

    /**
     * 获取区域路径中的区域，按照父子关系排序。
     * 
     * @param regionId
     *                区域ID。
     * @return
     */
    public List findRegionLocation(Long regionId) throws Exception;
    
    /**
     * 根据区域代码获取区域实体。
     * @param regionCode    区域代码。
     * @return
     * @throws Exception
     */
    public RegionDTO findRegionByCode(String regionCode) throws Exception;
}
