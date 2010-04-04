package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResource;

/**
 * DataResource ʵ�����ݷ�����
 * 
 * @spring.bean id="DataResourceDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceDao extends BaseDao {

    /**
     * ���캯��
     */
    public DataResourceDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return DataResource.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResource getById(Serializable key) {
        return (DataResource) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResource loadById(Serializable key) {
        return (DataResource) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ɾ��ҵ��Ȩ��
     * 
     * @param dataResourceId
     *                ����Ȩ��ʵ��ID
     */
    public void deleteDataResource(Long dataResourceId) {
        StringBuffer hql = new StringBuffer("from DataResource res");
        hql.append(" where res.").append(DataResource.PROP_RESOURCE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { dataResourceId });
    }
    
    /**
     * ��ѯϵͳ�����е�ҵ��Ȩ�ޡ�
     * @return
     */
    public List findAllDataResource(){
        StringBuffer hql = new StringBuffer("from DataResource dataResource");
        hql.append(" where dataResource.").append(DataResource.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString());
    }
}