package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupOrg;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperOrg;

/**
 * Organization 实体数据访问类
 * 
 * @spring.bean id="OrganizationDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class OrganizationDao extends BaseDao {

    /**
     * 构造函数
     */
    public OrganizationDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Organization.class;
    }

    /**
     * 根据ID查找对象
     */
    public Organization getById(Serializable key) {
        return (Organization) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public Organization loadById(Serializable key) {
        return (Organization) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询操作员组可访问组织。
     * 
     * @param groupId
     *            操作员组ID。
     * @return 组织实体列表。
     */
    public List findAcessOrgByGroupId(long groupId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from RelGroupOrg rgo,Organization org where 1=1");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append("=?");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_ORG_ID).append("=")
                .append("org.").append(Organization.PROP_ORG_ID);
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");

        return this.query(hql.toString(), new Object[] { new Long(groupId),
                new Long(status) });
    }

    /**
     * 查询所有组织，按照组织名称排序。
     * 
     * @return 组织实体列表。
     */
    public List findAllOrgsOrderByOrgName() {
        return this.query("from Organization as org order by org."
                + Organization.PROP_ORG_NAME);
    }

    /**
     * 查询根组织。
     * 
     * @return 组织实体。
     */
    public Organization findRootOrg() {
        StringBuffer hql = new StringBuffer("from Organization org");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=org.").append(Organization.PROP_PARENT_ID);
        List result = this.query(hql.toString());
        return result.size() > 0 ? (Organization) result.get(0) : null;
    }

    /**
     * 查询操作员组可访问组织。
     * 
     * @param groupId
     *            操作员组ID。
     * @return 组织实体列表。
     */
    public List findAcessOrgByGroupId(Long groupId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelGroupOrg rgo");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=rgo.").append(RelGroupOrg.PROP_ORG_ID);
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        return this.query(hql.toString(), new Object[] { groupId,
                new Long(status) });
    }

    /**
     * 查询操作员组可访问组织，包括子组织。
     * 
     * @param groupId
     *            操作员组ID。
     * @return 组织实体列表。
     */
    public List findAcessOrgByGroupIdIncludeChild(Long groupId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelGroupOrg rgo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || rgo.").append(RelGroupOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        return this.query(hql.toString(), new Object[] { groupId,
                new Long(status) });
    }

    /**
     * 查询操作员自己的可访问组织。
     * 
     * @param operatorId
     *            操作员ID。
     * @return 操作员可访问组织列表。
     */
    public List findAcessOrgByOperatorId(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=roo.").append(RelOperOrg.PROP_ORG_ID);
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    public List findAcessOrgByOperatorIdIncludeChild(Long operatorId,
            long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    /**
     * 获取操作员的继承的可访问组织，包括子组织
     * 
     * @param opId
     * @param status
     * @return
     */
    public List findAccessOrgForOperatorInGroupsWithChild(Long opId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelGroupOrg rgo , RelOperGroup rog");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || rgo.").append(RelGroupOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append("=");
        hql.append(" rgo.").append(RelGroupOrg.PROP_GROUP_ID);
        return this.query(hql.toString(),
                new Object[] { new Long(status), opId });
    }

    /**
     * 查询操作员自己的可访问组织，包括子组织。
     * 
     * @param operatorId
     *            操作员ID。
     * @param status
     *            组织状态。
     * @return
     */
    public List findAccessOrgIdsOfOperator(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org.orgId from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        hql.append(" order by org.").append(Organization.PROP_ORG_NAME);
        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    /**
     * 查询操作员可访问组织所在路径中的所有组织。
     * 
     * @param operatorId
     * @param status
     * @return
     */
    public List findOrgsInLocationOfAccessOrgs(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " || '#'");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    /**
     * 查询操作员继承组的可访问组织。
     * 
     * @param operatorId
     *            操作员ID。
     * @return 操作员所在组可访问组织列表。
     */
    public List findAccessOrgForOperatorInGroups(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org from Organization org,RelGroupOrg rgo,RelOperGroup rog");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=rgo.").append(RelGroupOrg.PROP_ORG_ID);
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");

        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    /**
     * 查询操作员继承组的可访问组织ID，包括子组织
     * 
     * @param operatorId
     *            操作员ID
     * @param status
     *            组织状态
     * @return
     */
    public List findAccessOrgIdsOfOperatorInGroups(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org.orgId from Organization org,RelGroupOrg rgo,RelOperGroup rog");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || rgo.").append(RelGroupOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID).append(
                "=rog.").append(RelOperGroup.PROP_GROUP_ID);
        hql.append(" and rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        hql.append(" order by org.").append(Organization.PROP_ORG_NAME);

        return this.query(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

    /**
     * 查询操作员可分配的组织。
     * 
     * @param operatorId
     *            操作员ID。
     * @return 组织实体列表。
     */
    public List findAvailableAccessOrgsForOperator(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer("select org from Organization org");
        hql.append(" where org.").append(Organization.PROP_STATUS).append("=?");
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append(
                " not in ");
        hql.append("(");
        hql.append(" select roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " from RelOperOrg roo");
        hql.append(" where roo.").append(RelOperOrg.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(")");

        return this.query(hql.toString(), new Object[] { new Long(status),
                operatorId });
    }

    /**
     * 查询组织的所有上级组织（包括组织自己），并按照父子关系排序。
     * 
     * @param org
     *            组织机构对象。
     * @return
     */
    @SuppressWarnings("unchecked")
	public List findLocationOfOrg(String[] orgIds) {
        List orgIdsList = new ArrayList();
        for (int i = 0; i < orgIds.length; i++) {
            String orgId = orgIds[i];
            if (null != orgId && orgId.length() > 0) {
                orgIdsList.add(orgId);
            }
        }

        StringBuffer hql = new StringBuffer("from Organization org");
        hql.append(" where org.orgId in ");

        return this.queryIn(hql.toString(), (String[]) orgIdsList
                .toArray(new String[0]), " order by org.orgPath");
    }

    /**
     * 根据ssoCode查询组织信息
     * 
     * @param ssoCodes
     *            组织在SSO系统中的惟一代码
     * @return 组织实体列表
     */
    public List findOrgsBySSOCodes(String[] ssoCodes) {
        StringBuffer hql = new StringBuffer("from Organization org");
        hql.append(" where org.").append(Organization.PROP_SSO_CODE).append(
                " in ");

        return this.queryIn(hql.toString(), ssoCodes);
    }

    /**
     * 查询指定组织的子组织。
     * 
     * @param org
     *                指定组织机构。
     * @param orgType
     *                组织类型
     * @param includeAll
     *            是否包括所有子孙组织。
     * @param includeSelf
     *            是否包括本组织机构。
     * @return
     */
    public List findChildrenOfOrg(Organization org, long orgType,
            boolean includeAll, boolean includeSelf) {
        StringBuffer hql = new StringBuffer("from Organization org where 1=1");
        List<Object> params = new ArrayList<Object>();

        if (includeAll) {
            hql.append(" and org.").append(Organization.PROP_ORG_PATH).append(
                    " like ?");
            params.add(org.getOrgPath() + "%");
        } else {
            hql.append(" and org.").append(Organization.PROP_PARENT_ID).append(
                    "=?");
            params.add(new Long(org.getOrgId()));
        }

        if (!includeSelf) {
            hql.append(" and org.").append(Organization.PROP_ORG_ID).append(
                    "!=?");
            params.add(new Long(org.getOrgId()));
        }

        if (orgType >=0) {
            hql.append(" and org.").append(Organization.PROP_ORG_TYPE).append(
                    "=?");
            params.add(new Long(orgType));
        }

        hql.append(" order by org.").append(Organization.PROP_ORG_PATH);

        return this.query(hql.toString(), params.toArray(new Object[0]));
    }
    
    /**
     * 查询子组织并按名称排序。
     * @param orgId
     * @param orgType
     * @return
     */
    public List findChildrenOfOrgOrderByName(Long orgId,long orgType){
        StringBuffer hql = new StringBuffer("from Organization org where 1=1");
        List<Object> params = new ArrayList<Object>();
        
        hql.append(" and org.").append(Organization.PROP_PARENT_ID).append(
        "=?");
        params.add(orgId);
        
        hql.append(" and org.").append(Organization.PROP_ORG_ID).append(
        "!=?");
        params.add(orgId);
        
        if (orgType >=0) {
            hql.append(" and org.").append(Organization.PROP_ORG_TYPE).append(
                    "=?");
            params.add(new Long(orgType));
        }
        
        hql.append(" order by org.").append(Organization.PROP_ORG_NAME);
        
        return this.query(hql.toString(), params.toArray(new Object[0]));
    }
    
    /**
     * 查询当前操作员的登陆可访问组织与指定操作员的可访问组织的交集
     * @param opId 指定操作员Id
     * @return
     * @throws Exception
     */

    public List findAccessOrgForOperatorInLoginOrg(Long opId) {
        StringBuffer hql = new StringBuffer(
                "select distinct org from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=? ");
//        hql.append(" and org.").append(RelOperOrg.PROP_ORG_ID).append(" in (");
//        String idsStr = "";
//        for (int i = 0; i < ids.length; i++) {
//            idsStr = idsStr + ids[i].toString();
//            if( i !=  ids.length -1){
//              idsStr = idsStr +",";
//            }
//        }
//        
//        hql.append(idsStr).append(")");
        return this.query(hql.toString(),new Object[]{opId,new Long(OrganizationDTO.STATUS_NORMAL)});

    }
    
    /**
     * 查询当前操作员的登陆可访问组织与指定操作员组的可访问组织的交集
     * @param groupId 指定操作员组Id
     * @return
     * @throws Exception
     */
    public List findAccessOrgForGroupInLoginOrg(Long groupId) {
        StringBuffer hql = new StringBuffer(
                "select distinct org from Organization org,RelGroupOrg rgo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || rgo.").append(RelGroupOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_GROUP_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
//        hql.append(" and org.").append(RelGroupOrg.PROP_ORG_ID).append(" in (");
//        String idsStr = "";
//        for (int i = 0; i < ids.length; i++) {
//            idsStr = idsStr + ids[i].toString();
//            if( i !=  ids.length -1){
//              idsStr = idsStr +",";
//            }
//        }
//        hql.append(idsStr);
//        hql.append(idsStr).append(")");
        return this.query(hql.toString(),new Object[]{groupId , new Long(OrganizationDTO.STATUS_NORMAL)});

    }

    /**
     * @param orgType
     * @return
     */
    public List findOrgsByType(int orgType) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Organization org ");
        hql.append(" where org.").append(Organization.PROP_ORG_TYPE);
        hql.append(" =?");
        return this.query(hql.toString(),new Object[]{new Long(orgType)});
    }
    
    
    /**
     * 根据用户的可访问组织获取相应的数据区域的标识(ID)数组
     * 		当可访问组织为分公司(org_id=1),获取该分公司下的所有数据区域
     *      当可访问组织为数据区域，返回数据区域
     *      当可访问组织为营业厅,代理商,部门,返回这些节点上级数据区域节点
     * @param orgIds 用户的可访问组织，包括用户所属用户组(group)的可访问组织
     * @return
     */
    public List findAccessRegionIdsByOrgIds(List orgs) {
        Map<Long,Long> accessBusiHallIds = new HashMap<Long,Long>();  //
        List<Organization> accessCompany = new ArrayList<Organization>();
        List<Long> accessRegionIds = new ArrayList<Long>();
        int ct=0;
        for(Iterator it=orgs.iterator();it.hasNext();) {
        	Organization org = (Organization) it.next();
        	if(org.getOrgType()==1) {
        		accessCompany.add(org);
        	}else if(org.getOrgType()==4) {
        		accessRegionIds.add(new Long(org.getOrgId()));
        	}else if(org.getOrgType()==0||org.getOrgType()==2||org.getOrgType()==3) {
        		String[] orgArr = org.getOrgPath().split("#");
        		for(int i=0;i<orgArr.length;i++) {
					try {
						long tId = Long.parseLong(orgArr[i]);
						accessBusiHallIds.put(new Long(tId),new Long(tId));
					} catch (Throwable e) {
						continue;
					}        			
        		}
        	}else {
        		//do nothing
        	}
        	ct++;
        }
        
        HashMap<Long,Long> orgIdMap = new HashMap<Long,Long>();
        StringBuffer hql = new StringBuffer();
        //所有的部门，营业厅，代理商上级的数据区域
        hql.append(" select org.orgId");
    	hql.append(" from Organization org ");
    	hql.append(" where org.orgType=4");
    	hql.append(" and org.orgId in ");
    	hql.append(longSet2String(accessBusiHallIds.keySet()));
    	List ids = this.query(hql.toString());
    	for(Iterator it=ids.iterator();it.hasNext();) {
    		Long id = (Long) it.next();
    		orgIdMap.put(id,id);
    	}
    	
    	//所有分公司下级的数据区域
    	hql = new StringBuffer("");
    	hql.append(" select org.orgId");
    	hql.append(" from Organization org ");
    	hql.append(" where org.orgType=4 and ( 1=2 ");
    	for(Iterator it=accessCompany.iterator();it.hasNext();) {
    		Organization org = (Organization) it.next();
    		hql.append(" or org.orgPath like '" + org.getOrgPath() + "%' ");
    	}
        hql.append(")");
        ids = this.query(hql.toString());
    	for(Iterator it=ids.iterator();it.hasNext();) {
    		Long id = (Long) it.next();
    		orgIdMap.put(id,id);
    	}
        
        //所有数据区域
    	hql = new StringBuffer("");
        hql.append(" select org.orgId");
    	hql.append(" from Organization org ");
    	hql.append(" where org.orgType=4");
    	hql.append(" and org.orgId in ");
    	hql.append(longList2String(accessRegionIds));
        ids = this.query(hql.toString());
    	for(Iterator it=ids.iterator();it.hasNext();) {
    		Long id = (Long) it.next();
    		orgIdMap.put(id,id);
    	}
    	
    	List<Long> accessRegionArr = new ArrayList<Long>();
    	Iterator it = orgIdMap.keySet().iterator();
    	while(it.hasNext()) {
    		accessRegionArr.add((Long)it.next());
    	}
        
        return accessRegionArr;
    }
    
    private String longList2String(List longList) {
    	StringBuffer sb = new StringBuffer("(-1,");
    	for(Iterator it=longList.iterator();it.hasNext();) {
    		Long l = (Long) it.next();
    		sb.append(l.longValue()+",");
    	}
    	return sb.substring(0,sb.length()-1)+")"; 
    }
    
    private String longSet2String(Set longSet) {
    	StringBuffer sb = new StringBuffer("(-1,");
    	for(Iterator it=longSet.iterator();it.hasNext();) {
    		Long l = (Long) it.next();
    		sb.append(l.longValue()+",");
    	}
    	return sb.substring(0,sb.length()-1)+")"; 
    }
}