package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelGroupOrg;

/**
 * RelGroupOrg ʵ�����ݷ�����
 * 
 * @spring.bean id="RelGroupOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelGroupOrgDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelGroupOrgDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelGroupOrg.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelGroupOrg getById(Serializable key) {
        return (RelGroupOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelGroupOrg loadById(Serializable key) {
        return (RelGroupOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * @param groupId
     * @param long1
     */
    public void deleteRelGroupOrg(Long groupId, Long orgId) {
        StringBuffer hql = new StringBuffer("from RelGroupOrg rgo");
        hql.append(" where rgo.").append(RelGroupOrg.PROP_GROUP_ID)
                .append("=?");
        hql.append(" and rgo.").append(RelGroupOrg.PROP_ORG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { groupId, orgId });
    }
}