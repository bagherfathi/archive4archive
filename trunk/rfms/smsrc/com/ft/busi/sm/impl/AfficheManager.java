package com.ft.busi.sm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.entity.Affiche;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelAfficheOrg;
import com.ft.sm.entity.RelGroupOrg;
import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperOrg;

public class AfficheManager extends HibernateDaoSupport{
    
	/**
	 * ��ȡ�û��Ŀɷ�����֯�Ĺ���
	 * @param operatorId �û���ID
	 * @return
	 */
    public List findAllAfficheByOrg(Long operatorId) {
    	List orgIds = findAllAccessOrgIdsOfOperatorIncludeChild(operatorId);
    	StringBuffer strIds = new StringBuffer("-1");
    	for(Iterator it=orgIds.iterator();it.hasNext();) {
    		Long orgId = (Long) it.next();
    		strIds.append(',');
    		strIds.append(orgId.longValue());
    	}
        StringBuffer hql = new StringBuffer("select affiche");
        hql.append(" from Affiche affiche");
        hql.append(" where affiche.").append(Affiche.PROP_VALID_TIME).append(
                "<=?");
        hql.append(" and affiche.").append(Affiche.PROP_EXPIRE_TIME).append(
                ">=?");
        hql.append(" and affiche.").append(Affiche.PROP_AFFICHE_ID).append(
                "=rao.").append(RelAfficheOrg.PROP_AFFICHE_ID);
        hql.append(" and affiche.afficheId in ( " +
        		"select rao.afficheId from RelAfficheOrg rao " +
        		"where rao.").append(RelAfficheOrg.PROP_ORG_ID).append(" in (").append(strIds).append(")" +
        		")");
        hql.append(" order by affiche.").append(Affiche.PROP_AFFICHE_LEVEL)
                .append(" desc ,affiche.").append(Affiche.PROP_PUBLISH_TIME).append(" desc");

        Date now = new Date();
        return this.getHibernateTemplate().find(hql.toString(), new Object[] {now, now, operatorId});
    }
    
    /*
     * ���Ҳ���Ա�ɷ�����֯ID�б������ɷ�����֯������֯�����ɷ�����֯�̳У�
     * 
     * @see com.huashu.boss.busi.sm.model.OrgManager#findAllAccessOrgIdsOfOperatorIncludeChild(java.lang.Long)
     */
    private List findAllAccessOrgIdsOfOperatorIncludeChild(Long operatorId) {
        List<Long> result = new ArrayList<Long>();

		try {
			// ��ѯ����Ա�Լ��ɷ��ʵ���֯ID�б�
			List accessOrgIdsOfOp = findAccessOrgIdsOfOperator(operatorId,
					OrganizationDTO.STATUS_NORMAL);
			for (Iterator iterator = accessOrgIdsOfOp.iterator(); iterator
					.hasNext();) {
				Long orgId = (Long) iterator.next();
				if (!result.contains(orgId)) {
					result.add(orgId);
				}
			}
			// ��ѯ����Ա������Ŀɷ�����֯ID�б�
			List accessOrgIdsOfOpInGroup = findAccessOrgIdsOfOperatorInGroups(
					operatorId, OrganizationDTO.STATUS_NORMAL);
			for (Iterator iter = accessOrgIdsOfOpInGroup.iterator(); iter
					.hasNext();) {
				Long element = (Long) iter.next();
				if (!result.contains(element)) {
					result.add(element);
				}
			}
		} catch (Exception e) {
			//do nothing
		}        
		return result;
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
    private List findAccessOrgIdsOfOperator(Long operatorId, long status) {
        StringBuffer hql = new StringBuffer(
                "select org.orgId from Organization org,RelOperOrg roo");
        hql.append(" where org.").append(Organization.PROP_ORG_PATH).append(
                " like '%#' || roo.").append(RelOperOrg.PROP_ORG_ID).append(
                " || '#%'");
        hql.append(" and roo.").append(RelOperOrg.PROP_OPERATOR_ID)
                .append("=?");
        hql.append(" and org.").append(Organization.PROP_STATUS).append("=?");
        hql.append(" order by org.").append(Organization.PROP_ORG_NAME);
        return this.getHibernateTemplate().find(hql.toString(), new Object[] { operatorId,
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
    private List findAccessOrgIdsOfOperatorInGroups(Long operatorId, long status) {
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

        return this.getHibernateTemplate().find(hql.toString(), new Object[] { operatorId,
                new Long(status) });
    }

}
