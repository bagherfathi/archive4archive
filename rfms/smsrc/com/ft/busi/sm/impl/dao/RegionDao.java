package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Region;
import com.ft.sm.entity.RelOrgRegion;

/**
 * Region 实体数据访问类
 * 
 * @spring.bean id="RegionDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RegionDao extends BaseDao {

    /**
     * 构造函数
     */
    public RegionDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Region.class;
    }

    /**
     * 根据ID查找对象
     */
    public Region getById(Serializable key) {
        return (Region) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public Region loadById(Serializable key) {
        return (Region) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询所有区域，按照父ID升序排序。
     * 
     * @return 区域实体列表
     */
    public List findAllRegionOrderByParent() {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" order by region.").append(Region.PROP_PARENT_ID);

        return this.query(hql.toString());
    }

    /**
     * 查询根区域。
     * 
     * @return 区域实体
     */
    public Region findRootRegion() {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_REGION_ID).append(
                "=region.").append(Region.PROP_PARENT_ID);

        List result = this.query(hql.toString());
        return result.size() > 0 ? (Region) result.get(0) : null;
    }


    /**
     * 查询组织对应的区域，不包括子区域
     */
    public List findRegionsByOrgIds(Long[] orgIds) {
    	StringBuffer hql = new StringBuffer("");
        hql.append(" from Region region where 1=1 ");
        //hql.append(" and region.").append(Region.PROP_STATUS).append("=0");        		
        hql.append(" and region.regionId in (");
        hql.append(" select ror.regionId from RelOrgRegion ror");
        hql.append(" where ror.orgId in (-1");
        for(int i=0;i<orgIds.length;i++) {
        	hql.append(","+orgIds[i]);
        }
        hql.append(" ))");
        return this.query(hql.toString());
    }
    
    /**
     * 查询组织可访问区域。
     */
    public List findAccessRegionsOfOrgs(Long[] orgIds){
        StringBuffer hql = new StringBuffer("select distinct region");
        hql.append(" from Region region,RelOrgRegion ror");
        hql.append(" where region.").append(Region.PROP_REGION_PATH).append(" like '%#' || ror.").append(RelOrgRegion.PROP_REGION_ID).append(" || '#%'");
        hql.append(" and region.").append(Region.PROP_STATUS).append("=0");
        hql.append(" and ror.").append(RelOrgRegion.PROP_ORG_ID).append(" in ");
        return this.queryIn(hql.toString(), orgIds);
    }
    
    /**
     * 查询组织可访问区域。
     * @param searchType 查询类型,searchType=1时,按区域名称精确查询;当searchType!=1时,按区域名称模糊查询
     * @param regionType 区域类型,如果区域类型大于0,查询改区域,如果区域类型小于0,查询所有大于regionType*(-1)的区域
     *                    比如:-4,查询区域类型大于4的区域
     */
    public List findAccessRegionsOfOrgs(Long[] orgIds,String regionName,Long regionId,long regionType,int searchType){
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("select distinct region");
        hql.append(" from Region region,RelOrgRegion ror where 1=1");
        if(regionId != null && regionId.longValue() > 0){
            hql.append(" and region.").append(Region.PROP_REGION_PATH).append(" like '%#' || ? || '#%'");
            params.add(regionId);
        }else{
            hql.append(" and region.").append(Region.PROP_REGION_PATH).append(" like '%#' || ror.").append(RelOrgRegion.PROP_REGION_ID).append(" || '#%'");
            hql.append(" and ror.").append(RelOrgRegion.PROP_ORG_ID).append(" in (-1");
            for (int i = 0; i < orgIds.length; i++) {
                hql.append(",?");
                params.add(orgIds[i]);
            }
            hql.append(")");
        }
        
        if(regionName != null && regionName.length() > 0){
            if(searchType == 1){
                hql.append(" and region.").append(Region.PROP_REGION_NAME).append(" = ?");
                params.add(regionName);
            }else{
                hql.append(" and region.").append(Region.PROP_REGION_NAME).append(" like ?");
                params.add( regionName + "%");
            }
        }
        
        hql.append(" and region.").append(Region.PROP_STATUS).append("=0");
        if(regionType>=0) {
        	hql.append(" and region.").append(Region.PROP_REGION_TYPE).append("=?");
        	params.add(new Long(regionType));
        }else if(regionType<-1){
        	hql.append(" and region.").append(Region.PROP_REGION_TYPE).append(">?");
        	params.add(new Long(-1*regionType));
        }else if (regionType==-1) {
        	; //donothing
        }
        
        hql.append(" order by region.parentId,region.regionName");

        return this.query(hql.toString(),params.toArray(new Object[0]));
    }
    
    /**
     * 查询组织的可访问区域的最上层区域。
     * @param orgId    组织机构ID。
     * @return
     */
    public List findAccessRegionsOfOrg(Long orgId){
        StringBuffer hql = new StringBuffer("select region");
        hql.append(" from Region region,RelOrgRegion ror");
        hql.append(" where region.").append(Region.PROP_REGION_ID).append("=ror.").append(RelOrgRegion.PROP_REGION_ID);
        hql.append(" and region.").append(Region.PROP_STATUS).append("=0");
        hql.append(" and ror.").append(RelOrgRegion.PROP_ORG_ID).append("=?");
        
        return this.query(hql.toString(),new Object[]{orgId});
    }
    
    /**
     * 查询所有regionId在path中的小区
     * @param regionIds
     * @return
     */
    public List findRegionsInPath(Set regionIds,long regionType){
    	List<Object> params = new ArrayList<Object>();
    	StringBuffer hql = new StringBuffer("select region");
    	hql.append(" from Region region");
    	hql.append(" where region.").append(Region.PROP_REGION_TYPE).append("=?");
    	params.add(new Long(regionType));
    	hql.append(" and ( 1=2 ");
    	for(Iterator it=regionIds.iterator();it.hasNext();) {
    		Long regionId = (Long) it.next();
    		hql.append(" or region.").append(Region.PROP_REGION_PATH).append(" like '%#'||?||'#%'");
    		params.add(regionId);
    	}
    	hql.append(")");
    	hql.append(" order by region.parentId,region.regionName");
    	try {
    		@SuppressWarnings("unused")
			List l = this.query(hql.toString(),params.toArray(new Object[0]));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return this.query(hql.toString(),params.toArray(new Object[0]));
    }

    /**
     * 查找指定区域的子区域。
     * 
     * @param
     * @return
     */
    public List findChildrenOfRegion(Long regionId, Long status,
            boolean allChild) {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_REGION_ID)
                .append("!=?");

        if (allChild) {
            hql.append(" and region.").append(Region.PROP_REGION_PATH).append(
                    " like '%' || ? || '%'");
        } else {
            hql.append(" and region.").append(Region.PROP_PARENT_ID).append(
                    "=?");
        }

        if (null == status) {
            return this.query(hql.toString(),
                    new Object[] { regionId, regionId });
        } else {
            hql.append(" and region.").append(Region.PROP_STATUS).append("=?");
            return this.query(hql.toString(), new Object[] { regionId,
                    regionId, status });
        }
    }

    /**
     * 查询组织的可访问区域。
     * 
     * @param orgId
     *                组织ID。
     * @return 区域实体列表。
     */
    public List findAccessRegionByOrgId(Long orgId) {
        StringBuffer query = new StringBuffer(
                "select reg from RelOrgRegion acr,Region reg");
        // modified by libf,2006/12/08
        //query.append(" where reg.regionId=acr.regionId");
        query.append(" where reg.").append(Region.PROP_REGION_PATH).append(
                " like '%#' || acr.").append(RelOrgRegion.PROP_REGION_ID)
                .append(" || '#%'");
        //   end modified
        query.append(" and acr.orgId = ?");

        return this.query(query.toString(), new Object[] { orgId });
    }
    
    public List findRegionByOrgId(Long orgId){
    	 StringBuffer query = new StringBuffer(
         "select reg from RelOrgRegion acr,Region reg");
    	 query.append(" where reg.regionId=acr.regionId");
    	 query.append(" and acr.orgId = ?");

         return this.query(query.toString(), new Object[] { orgId });
    }

    /**
     * 查询指定组织的下所有组织的可访问区域。
     * 
     * @param orgPath
     *                组织路径。
     * @return 区域实体列表。
     */
    public List findAccessRegionOfChildrenOrgByOrgId(String orgPath) {
        StringBuffer query = new StringBuffer("select distinct reg");
        query.append(" from RelOrgRegion acr,Region reg,Organization org");
        query.append(" where reg.regionId=acr.regionId");
        query.append(" and org.orgId = acr.orgId and org.orgPath like ");
        query.append("'%" + orgPath + "%" + "#" + "'");
        return this.query(query.toString());
    }

    /**
     * 查询区域下的子区域。
     * 
     * @return
     */
    public List findChildrenOfRegion(Long parentId) {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_PARENT_ID).append("=?");
        hql.append(" and region.").append(Region.PROP_REGION_ID).append(
                "!=region.").append(Region.PROP_PARENT_ID);
        hql.append(" order by region.").append(Region.PROP_REGION_ID).append(" desc");

        return this.query(hql.toString(), new Object[] { parentId });
    }
    
    /**
     * 查询区域下的子区域。
     * 
     * @return
     */
    public List findChildrenOfRegionOrderByName(Long parentId) {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_PARENT_ID).append("=?");
        hql.append(" and region.").append(Region.PROP_REGION_ID).append(
                "!=region.").append(Region.PROP_PARENT_ID);
        hql.append(" order by region.").append(Region.PROP_REGION_NAME).append(" desc");

        return this.query(hql.toString(), new Object[] { parentId });
    }

    /**
     * 根据regionPath删除区域
     * @param regionPath
     */
	public void deleteRegionsByRegionPath(String regionPath) {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_REGION_PATH).append(
                " like '%" +  regionPath + "%'");

        this.deleteFromQuery(hql.toString(), new Object[] { });
	}
	
	/**
	 * 根据父节点获取子结点区域列表，同时过滤不再可访问行政区域的节点
	 *   过滤规则：如果父节点可访问，子结点必可访问
	 *           如果字段节可访问，父节点需要显示
	 * @param parentId
	 * @param accessRegions 可访问的行政区域Ids
	 * @return
	 */
	public List findChildrenOfRegionOrderByPaerntIdAndInOrgIds(Long parentId,List accessRegions)  {
		List<Region> regions = new ArrayList<Region>();
		List children = this.findChildrenOfRegion(parentId);
		for(Iterator it=children.iterator();it.hasNext();) {
			Region region = (Region) it.next();
			if(isRegionInPath(region,accessRegions)) {
				regions.add(region);
			}
		}
		return regions;
	}
	
	/**
	 * 给定行政区域的所有上级节点的路径,包括本身，是否在用户的可访问行政区域范围内
	 * 例如：#1000#1001#1011#的所有父节点的路径是
	 * #1000#;#1000#1001#;#1001#1001#1011#查询下面三个路径是否在可访问行政区域内
	 * @param region 行政区域
	 * @param orgIds 数据区域Ids
	 * @return
	 */
	private boolean isRegionInPath(Region region,List regions) {
		for(Iterator it=regions.iterator();it.hasNext();) {
			Region filter = (Region) it.next();
			if(region.getRegionPath().indexOf(filter.getRegionPath())>=0)
				return true;
			if(filter.getRegionPath().indexOf(region.getRegionPath())>=0)
				return true;
		}
		return false;
	}
	
	//给定行政区域的所有上级节点的路径,包括本身；
	//例如：#1000#1001#1011#的所有父节点的路径是
	//#1000#;#1000#1001#;#1001#1001#1011#查询下面三个路径是否在可访问行政区域内
	@SuppressWarnings("unused")
	private String[] subPath(String regionPath) {
		List<String> paths = new ArrayList<String>();
		String[] regionIds = regionPath.split("#");
		StringBuffer sb = new StringBuffer("#");
		for(int i=0;i<regionIds.length;i++) {
			if("".equals(regionIds[i])) continue;
			sb.append(regionIds[i]+"#");
			paths.add(sb.toString());
		}
		return (String[])paths.toArray(new String[0]);
	}
	
	//字符串数组转换成in查询条件数组
    @SuppressWarnings("unused")
	private String strArr2Instr(String[] strs) {
    	StringBuffer sb = new StringBuffer("('-1'");
		for(int i=0;i<strs.length;i++) {
			sb.append(",'"+strs[i]+"'");
		}
    	sb.append(")");
    	return sb.toString();
    }
    
	//字符串数组转换成in查询条件数组
    @SuppressWarnings("unused")
	private String longArr2Instr(Long[] longs) {
    	StringBuffer sb = new StringBuffer("(-1");
		for(int i=0;i<longs.length;i++) {
			sb.append(","+longs[i].longValue());
		}
    	sb.append(")");
    	return sb.toString();
    }
    
}