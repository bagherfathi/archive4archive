
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOrgRegion;

/**
 * RelOrgRegion ʵ�����ݷ�����
 * 
 * @spring.bean id="RelOrgRegionDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOrgRegionDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelOrgRegionDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelOrgRegion.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOrgRegion getById(Serializable key) {
        return (RelOrgRegion) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelOrgRegion loadById(Serializable key) {
        return (RelOrgRegion) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ��֯�ɷ��������ϵ
     * 
     * @param orgId
     *                ��֯ID
     * @param regionId
     *                ����ID
     * @return ��֯�ɷ��������ϵ
     */
    public void deleteRelOrgRegion(Long orgId, Long regionId) {
        StringBuffer hql = new StringBuffer("from RelOrgRegion ror");
        hql.append(" where ror.").append(RelOrgRegion.PROP_ORG_ID).append("=?");
        hql.append(" and ror.").append(RelOrgRegion.PROP_REGION_ID)
                .append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { orgId, regionId });
    }
}