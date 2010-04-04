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
 * Organization ʵ�����ݷ�����
 * 
 * @spring.bean id="OrganizationDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class OrganizationDao extends BaseDao {

    /**
     * ���캯��
     */
    public OrganizationDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Organization.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Organization getById(Serializable key) {
        return (Organization) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Organization loadById(Serializable key) {
        return (Organization) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ����Ա��ɷ�����֯��
     * 
     * @param groupId
     *            ����Ա��ID��
     * @return ��֯ʵ���б�
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
     * ��ѯ������֯��������֯��������
     * 
     * @return ��֯ʵ���б�
     */
    public List findAllOrgsOrderByOrgName() {
        return this.query("from Organization as org order by org."
                + Organization.PROP_ORG_NAME);
    }

    /**
     * ��ѯ����֯��
     * 
     * @return ��֯ʵ�塣
     */
    public Organization findRootOrg() {
        StringBuffer hql = new StringBuffer("from Organization org");
        hql.append(" where org.").append(Organization.PROP_ORG_ID).append(
                "=org.").append(Organization.PROP_PARENT_ID);
        List result = this.query(hql.toString());
        return result.size() > 0 ? (Organization) result.get(0) : null;
    }

    /**
     * ��ѯ����Ա��ɷ�����֯��
     * 
     * @param groupId
     *            ����Ա��ID��
     * @return ��֯ʵ���б�
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
     * ��ѯ����Ա��ɷ�����֯����������֯��
     * 
     * @param groupId
     *            ����Ա��ID��
     * @return ��֯ʵ���б�
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
     * ��ѯ����Ա�Լ��Ŀɷ�����֯��
     * 
     * @param operatorId
     *            ����ԱID��
     * @return ����Ա�ɷ�����֯�б�
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
     * ��ȡ����Ա�ļ̳еĿɷ�����֯����������֯
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
     * ��ѯ����Ա�Լ��Ŀɷ�����֯����������֯��
     * 
     * @param operatorId
     *            ����ԱID��
     * @param status
     *            ��֯״̬��
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
     * ��ѯ����Ա�ɷ�����֯����·���е�������֯��
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
     * ��ѯ����Ա�̳���Ŀɷ�����֯��
     * 
     * @param operatorId
     *            ����ԱID��
     * @return ����Ա������ɷ�����֯�б�
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
     * ��ѯ����Ա�̳���Ŀɷ�����֯ID����������֯
     * 
     * @param operatorId
     *            ����ԱID
     * @param status
     *            ��֯״̬
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
     * ��ѯ����Ա�ɷ������֯��
     * 
     * @param operatorId
     *            ����ԱID��
     * @return ��֯ʵ���б�
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
     * ��ѯ��֯�������ϼ���֯��������֯�Լ����������ո��ӹ�ϵ����
     * 
     * @param org
     *            ��֯��������
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
     * ����ssoCode��ѯ��֯��Ϣ
     * 
     * @param ssoCodes
     *            ��֯��SSOϵͳ�е�Ωһ����
     * @return ��֯ʵ���б�
     */
    public List findOrgsBySSOCodes(String[] ssoCodes) {
        StringBuffer hql = new StringBuffer("from Organization org");
        hql.append(" where org.").append(Organization.PROP_SSO_CODE).append(
                " in ");

        return this.queryIn(hql.toString(), ssoCodes);
    }

    /**
     * ��ѯָ����֯������֯��
     * 
     * @param org
     *                ָ����֯������
     * @param orgType
     *                ��֯����
     * @param includeAll
     *            �Ƿ��������������֯��
     * @param includeSelf
     *            �Ƿ��������֯������
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
     * ��ѯ����֯������������
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
     * ��ѯ��ǰ����Ա�ĵ�½�ɷ�����֯��ָ������Ա�Ŀɷ�����֯�Ľ���
     * @param opId ָ������ԱId
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
     * ��ѯ��ǰ����Ա�ĵ�½�ɷ�����֯��ָ������Ա��Ŀɷ�����֯�Ľ���
     * @param groupId ָ������Ա��Id
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
     * �����û��Ŀɷ�����֯��ȡ��Ӧ����������ı�ʶ(ID)����
     * 		���ɷ�����֯Ϊ�ֹ�˾(org_id=1),��ȡ�÷ֹ�˾�µ�������������
     *      ���ɷ�����֯Ϊ�������򣬷�����������
     *      ���ɷ�����֯ΪӪҵ��,������,����,������Щ�ڵ��ϼ���������ڵ�
     * @param orgIds �û��Ŀɷ�����֯�������û������û���(group)�Ŀɷ�����֯
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
        //���еĲ��ţ�Ӫҵ�����������ϼ�����������
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
    	
    	//���зֹ�˾�¼�����������
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
        
        //������������
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