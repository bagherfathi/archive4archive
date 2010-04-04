
package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ft.busi.sm.impl.dao.GroupDao;
import com.ft.busi.sm.impl.dao.OrganizationDao;
import com.ft.busi.sm.impl.dao.RegionDao;
import com.ft.busi.sm.model.RegionManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RegionDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.Region;

/**
 * 区域信息管理实现类.
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 * 
 * @spring.aop-bean id="regionManager" parent="transactionProxyFactoryBean"
 *                  target="regionManagerImpl"
 * 
 * @spring.bean id="regionManagerImpl"
 */
public class RegionManagerImpl implements RegionManager {

    private RegionDao regionDao;
    
    private OrganizationDao organizationao;
    
    private GroupDao groupDao;

    /**
     * @spring.property ref = "RegionDao"
     * @param regionDao
     *                The regionDao to set.
     */
    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }
    
    /**
     * @spring.property ref = "OrganizationDao"
     * @param organizationDao
     */
    public void setOrganizationDao(OrganizationDao organizationDao) {
    	this.organizationao = organizationDao;
    }
    
    /**
     * @spring.property ref = "GroupDao"
     * @param groupDao
     */
    public void setGroupDao(GroupDao groupDao) {
    	this.groupDao = groupDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.RegionManager#deleteRegion(java.lang.Long)
     */
    public void disableRegion(Long regionId) {
        if (null == regionId) {
            throw new IllegalArgumentException();
        }

        Region region = this.regionDao.getById(regionId);
        if (region != null) {
            region.setStatus(RegionDTO.STATUS_DISABLE);
            this.regionDao.update(region);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.RegionManager#findAllRegionOrderByParent()
     */
    public List findAllRegionOrderByParent() {
        List result = this.regionDao.findAllRegionOrderByParent();

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.RegionManager#getRootRegion()
     */
    public RegionDTO getRootRegion() {
        Region region = this.regionDao.findRootRegion();

        if (region != null)
            return new RegionDTO(region, region);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.sm.model.RegionManager#saveRegion(com.huashu.boss.sm.entity.basedata.Region)
     */
    public Long saveRegion(RegionDTO regionDto) {
        if (null == regionDto) {
            throw new IllegalArgumentException();
        }

        Region region = (Region) regionDto.getTarget();
        if (region.getRegionId() <= 0) {
            this.regionDao.saveOrUpdate(region);
            region.setRegionPath(regionDto.getParent().getRegionPath()
                    + region.getRegionId() + RegionDTO.PATH_SEPARATOR);
            this.regionDao.saveOrUpdate(region);
        } else {
            this.regionDao.update(region);
        }

        return new Long(region.getRegionId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#enableRegion(java.lang.Long)
     */
    public void enableRegion(Long regionId) {
        if (null == regionId) {
            throw new IllegalArgumentException();
        }

        Region region = this.regionDao.getById(regionId);
        if (region != null) {
            region.setStatus(RegionDTO.STATUS_NORMAL);
            this.regionDao.update(region);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#deleteRegion(java.lang.Long)
     */
    public void deleteRegion(Long regionId) {
    	if (null == regionId) {
    		throw new IllegalArgumentException();
    	}
    	Region region = this.regionDao.getById(regionId);
    	if (region != null) { 
    		this.regionDao.deleteRegionsByRegionPath(region.getRegionPath());
    	}
    	
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionByOrgId(java.lang.Long,
     *      boolean)
     */
    public List findRegionsOfOrgs(Long[] orgIds) {
        List result = this.regionDao.findAccessRegionsOfOrgs(orgIds);

        return EntityDTOConverter.converRegion2DTO(result);
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionsOfOrgs(java.lang.Long[], java.lang.String, java.lang.Long, long, int)
     */
    public List findRegionsOfOrgs(Long[] orgIds,String regionName,Long regionId,long regionType,int searchType) {
        List result = this.regionDao.findAccessRegionsOfOrgs(orgIds,regionName,regionId,regionType,searchType);
        Set<Long> ids = new HashSet<Long>();
        for(Iterator it=result.iterator();it.hasNext();) {
        	Region region = (Region) it.next();
        	ids.add(new Long(region.getRegionId()));
        }
        List regions = this.regionDao.findRegionsInPath(ids, RegionDTO.REGION_TYPE_XIAOQU);
        return EntityDTOConverter.converRegion2DTO(regions);
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRefionsOfOrg(java.lang.Long)
     */
    public List findRefionsOfOrg(Long orgId) throws Exception{
        List result = this.regionDao.findRegionByOrgId(orgId);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionByParentId(java.lang.Long)
     */
    public List findRegionByParentId(Long regionId) {
        List result = this.regionDao.findChildrenOfRegion(regionId);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionByParentId(java.lang.Long,
     *      boolean)
     */
    public List findRegionByParentId(Long regionId, boolean allChild) {
        List result = this.regionDao.findChildrenOfRegion(regionId, new Long(
                RegionDTO.STATUS_NORMAL), allChild);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionById(java.lang.Long)
     */
    public RegionDTO findRegionById(Long regionId) {
        if (null == regionId) {
            throw new IllegalArgumentException();
        }

        Region region = this.regionDao.getById(regionId);

        if (region != null) {
            Region parent = this.regionDao.getById(new Long(region
                    .getParentId()));
            return new RegionDTO(parent, region);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        Region region = this.regionDao.getById(id);

        if (region != null) {
            Region parent = this.regionDao.getById(new Long(region
                    .getParentId()));
            return new RegionDTO(parent, region);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List result = this.regionDao.query(hql, params, page);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = this.regionDao.loadByIds(Region.class, ids);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findAccessRegionByOrgId(java.lang.Long)
     */
    public List findAccessRegionByOrgId(Long orgId) {
        List result = this.regionDao.findAccessRegionByOrgId(orgId);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findAccessRegionOfChildrenOrgByOrgId(java.lang.String)
     */
    public List findAccessRegionOfChildrenOrgByOrgId(String orgPath) {
        List result = this.regionDao
                .findAccessRegionOfChildrenOrgByOrgId(orgPath);

        return EntityDTOConverter.converRegion2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionLocation(java.lang.Long)
     */
    @SuppressWarnings("unchecked")
	public List findRegionLocation(Long regionId) {
        List result = new ArrayList();

        // 查询区域信息
        Region region = this.regionDao.getById(regionId);
        if (region == null)
            return result;

        // 解析区域路径中的区域ID
        List<Long> regionIds = new ArrayList<Long>();
        String[] locationIds = region.getRegionPath().split(
                RegionDTO.PATH_SEPARATOR);
        for (int i = 0; i < locationIds.length; i++) {
            String id = locationIds[i];
            if (id != null && id.length() > 0) {
                regionIds.add(new Long(id));
            }
        }

        // 查询路径中区域
        List regionList = this.regionDao.loadByIds(Region.class,
                (Long[]) regionIds.toArray(new Long[0]));

        // 按照路径中先后顺序排序
        for (int i = 0; i < regionIds.size(); i++) {
            Long id = (Long) regionIds.get(i);
            for (Iterator iterator = regionList.iterator(); iterator.hasNext();) {
                Region element = (Region) iterator.next();
                if (id.longValue() == element.getRegionId()) {
                    result.add(new RegionDTO(element));
                    break;
                }
            }
        }

        return result;
    }
    
    
    
    /* (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.RegionManager#findRegionByCode(java.lang.String)
     */
    public RegionDTO findRegionByCode(String regionCode) throws Exception {
        Region region = (Region)this.regionDao.getEntityByIdentityAttribute(Region.class, Region.PROP_REGION_CODE, regionCode);
        
        if(region != null){
            return new RegionDTO(region);
        }
        
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findNodeChildren(java.lang.Long, java.lang.String,long)
     */
    @SuppressWarnings("unchecked")
	public List findNodeChildrenByOperator(Long nodeId,String type,Long operatorId) {
    	//操作员的访问组织
    	List orgs = this.organizationao.
    		findAcessOrgByOperatorId(operatorId,OrganizationDTO.STATUS_NORMAL);

    	//操作员所在用户组的可访问组织
    	List groups = this.groupDao.findGroupsByOperator(operatorId);
    	for(Iterator it=groups.iterator();it.hasNext();) {
    		Group group = (Group)it.next();
    		List gpOrgs = this.organizationao.findAcessOrgByGroupId(group.getGroupId(), OrganizationDTO.STATUS_NORMAL);
    		for(Iterator iter=gpOrgs.iterator();iter.hasNext();) {
    			Organization org = (Organization) iter.next();
    			orgs.add(org);
    		}
    	}

    	//操作员的可访问数据区域
    	List accessRegionIds = this.organizationao.findAccessRegionIdsByOrgIds(orgs);
    	//操作员可访问的行政区域
    	List regions = this.regionDao.findRegionsByOrgIds((Long[])accessRegionIds.toArray(new Long[0]));
    	
    	//操作员的NodeId下一级的可访问行政区域
        List result = this.regionDao.findChildrenOfRegionOrderByPaerntIdAndInOrgIds(nodeId,regions);

        return EntityDTOConverter.converRegion2DTO(result);
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.XmlTreeManager#findNodeChildren(java.lang.Long, java.lang.String)
     */
    public List findNodeChildren(Long nodeId,String type) {
        List result = this.regionDao.findChildrenOfRegionOrderByName(nodeId);

        return EntityDTOConverter.converRegion2DTO(result);
    }
    
    /*
     * (non-Javadoc)
     * @see com.huashu.boss.busi.sm.model.TreeManager#findRootNode()
     */
    public XmlTreeNode findRootNode() {
        return this.getRootRegion();
    }
}
