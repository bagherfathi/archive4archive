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
 * Region ʵ�����ݷ�����
 * 
 * @spring.bean id="RegionDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RegionDao extends BaseDao {

    /**
     * ���캯��
     */
    public RegionDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Region.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Region getById(Serializable key) {
        return (Region) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Region loadById(Serializable key) {
        return (Region) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ�������򣬰��ո�ID��������
     * 
     * @return ����ʵ���б�
     */
    public List findAllRegionOrderByParent() {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" order by region.").append(Region.PROP_PARENT_ID);

        return this.query(hql.toString());
    }

    /**
     * ��ѯ������
     * 
     * @return ����ʵ��
     */
    public Region findRootRegion() {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_REGION_ID).append(
                "=region.").append(Region.PROP_PARENT_ID);

        List result = this.query(hql.toString());
        return result.size() > 0 ? (Region) result.get(0) : null;
    }


    /**
     * ��ѯ��֯��Ӧ�����򣬲�����������
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
     * ��ѯ��֯�ɷ�������
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
     * ��ѯ��֯�ɷ�������
     * @param searchType ��ѯ����,searchType=1ʱ,���������ƾ�ȷ��ѯ;��searchType!=1ʱ,����������ģ����ѯ
     * @param regionType ��������,����������ʹ���0,��ѯ������,�����������С��0,��ѯ���д���regionType*(-1)������
     *                    ����:-4,��ѯ�������ʹ���4������
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
     * ��ѯ��֯�Ŀɷ�����������ϲ�����
     * @param orgId    ��֯����ID��
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
     * ��ѯ����regionId��path�е�С��
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
     * ����ָ�������������
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
     * ��ѯ��֯�Ŀɷ�������
     * 
     * @param orgId
     *                ��֯ID��
     * @return ����ʵ���б�
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
     * ��ѯָ����֯����������֯�Ŀɷ�������
     * 
     * @param orgPath
     *                ��֯·����
     * @return ����ʵ���б�
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
     * ��ѯ�����µ�������
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
     * ��ѯ�����µ�������
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
     * ����regionPathɾ������
     * @param regionPath
     */
	public void deleteRegionsByRegionPath(String regionPath) {
        StringBuffer hql = new StringBuffer("from Region region");
        hql.append(" where region.").append(Region.PROP_REGION_PATH).append(
                " like '%" +  regionPath + "%'");

        this.deleteFromQuery(hql.toString(), new Object[] { });
	}
	
	/**
	 * ���ݸ��ڵ��ȡ�ӽ�������б�ͬʱ���˲��ٿɷ�����������Ľڵ�
	 *   ���˹���������ڵ�ɷ��ʣ��ӽ��ؿɷ���
	 *           ����ֶνڿɷ��ʣ����ڵ���Ҫ��ʾ
	 * @param parentId
	 * @param accessRegions �ɷ��ʵ���������Ids
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
	 * ������������������ϼ��ڵ��·��,���������Ƿ����û��Ŀɷ�����������Χ��
	 * ���磺#1000#1001#1011#�����и��ڵ��·����
	 * #1000#;#1000#1001#;#1001#1001#1011#��ѯ��������·���Ƿ��ڿɷ�������������
	 * @param region ��������
	 * @param orgIds ��������Ids
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
	
	//������������������ϼ��ڵ��·��,��������
	//���磺#1000#1001#1011#�����и��ڵ��·����
	//#1000#;#1000#1001#;#1001#1001#1011#��ѯ��������·���Ƿ��ڿɷ�������������
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
	
	//�ַ�������ת����in��ѯ��������
    @SuppressWarnings("unused")
	private String strArr2Instr(String[] strs) {
    	StringBuffer sb = new StringBuffer("('-1'");
		for(int i=0;i<strs.length;i++) {
			sb.append(",'"+strs[i]+"'");
		}
    	sb.append(")");
    	return sb.toString();
    }
    
	//�ַ�������ת����in��ѯ��������
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