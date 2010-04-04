package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResource;

/**
 * DataResource 实体数据访问类
 * 
 * @spring.bean id="DataResourceDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceDao extends BaseDao {

    /**
     * 构造函数
     */
    public DataResourceDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return DataResource.class;
    }

    /**
     * 根据ID查找对象
     */
    public DataResource getById(Serializable key) {
        return (DataResource) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public DataResource loadById(Serializable key) {
        return (DataResource) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除业务权限
     * 
     * @param dataResourceId
     *                功能权限实体ID
     */
    public void deleteDataResource(Long dataResourceId) {
        StringBuffer hql = new StringBuffer("from DataResource res");
        hql.append(" where res.").append(DataResource.PROP_RESOURCE_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { dataResourceId });
    }
    
    /**
     * 查询系统中所有的业务权限。
     * @return
     */
    public List findAllDataResource(){
        StringBuffer hql = new StringBuffer("from DataResource dataResource");
        hql.append(" where dataResource.").append(DataResource.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString());
    }
}