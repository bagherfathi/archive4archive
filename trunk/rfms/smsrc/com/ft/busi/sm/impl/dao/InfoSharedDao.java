package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.InfoShared;

/**
 * InfoShared ʵ�����ݷ�����
 * 
 * @spring.bean id="InfoSharedDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class InfoSharedDao extends BaseDao {

    /**
     * ���캯��
     */
    public InfoSharedDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return InfoShared.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public InfoShared getById(Serializable key) {
        return (InfoShared) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public InfoShared loadById(Serializable key) {
        return (InfoShared) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ��������Ϣ��
     * 
     * @param infoId
     *                ������ϢID��
     */
    public void deleteInfoById(Long infoId) {
        StringBuffer hql = new StringBuffer("from InfoShared info");
        hql.append(" where info.").append(InfoShared.PROP_INFO_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { infoId });

    }

    /**
     * ��ѯ��Ϣ��������Ϣ������
     * 
     * @param categoryId
     * @return
     */
    public Integer findInfoNumberOfCategory(Long categoryId) {
        StringBuffer hql = new StringBuffer("select count(info.infoId)");
        hql.append(" from InfoShared info");
        hql.append(" where info.").append(InfoShared.PROP_CATEGORY_ID).append(
                "=?");

        List result = this.query(hql.toString(), new Object[] { categoryId });

        return result.size() > 0 ? (Integer) result.get(0) : new Integer(0);
    }
}