
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOperOrg;

/**
 * RelOperOrg ʵ�����ݷ�����
 * 
 * @spring.bean id="RelOperOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperOrgDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelOperOrgDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelOperOrg.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperOrg getById(Serializable key) {
        return (RelOperOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOperOrg loadById(Serializable key) {
        return (RelOperOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ������Ա�ɷ�����֯��
     * 
     * @param operatorId
     *                ����ԱID��
     * @param long1
     *                �ɷ�����֯����ID��
     */
    public void deleteRelOperOrg(Long operatorId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelOperOrg roo");
        hql.append(" where roo.").append(RelOperOrg.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and roo.").append(RelOperOrg.PROP_ORG_ID).append("=?");

        this
                .deleteFromQuery(hql.toString(), new Object[] { operatorId,
                        orgId });

    }

    /**
     * ��ѯ�ɷ�����֯��ϵ��
     * 
     * @param operatorId
     *                ����ԱID��
     * @param orgId
     *                �ɷ�����֯����ID��
     * @return
     */
    public RelOperOrg findRelOperOrg(Long operatorId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelOperOrg roo");
        hql.append(" where roo.").append(RelOperOrg.PROP_OPERATOR_ID).append(
                "=?");
        List<Object> params = new ArrayList<Object>();
        params.add(operatorId);
        if(orgId != null){
            hql.append(" and roo.").append(RelOperOrg.PROP_ORG_ID).append("=?");
            params.add(orgId);
        }
        return (RelOperOrg) this.getSingle(hql.toString(), params.toArray());
    }
}