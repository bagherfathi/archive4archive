package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOperGroup;

/**
 * RelOperGroup 实体数据访问类
 * 
 * @spring.bean id="RelOperGroupDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperGroupDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelOperGroupDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelOperGroup.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelOperGroup getById(Serializable key) {
        return (RelOperGroup) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelOperGroup loadById(Serializable key) {
        return (RelOperGroup) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除操作员的操作员组。
     * 
     * @param operatorId
     *                操作员ID。
     * @param groupId
     *                操作员组ID。
     */
    public void deleteRelOperGroup(Long operatorId, Long groupId) {
        StringBuffer hql = new StringBuffer("from RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append("=?");

        this.deleteFromQuery(hql.toString(),
                new Object[] { operatorId, groupId });

    }
    /**
     * 查询操作员和组的关系
     * @param operatorId
     * @param groupId
     */

	public RelOperGroup findRelOperGroup(Long operatorId, Long groupId) {
		StringBuffer hql = new StringBuffer("from RelOperGroup rog");
        hql.append(" where rog.").append(RelOperGroup.PROP_OPERATOR_ID).append(
                "=?");
        hql.append(" and rog.").append(RelOperGroup.PROP_GROUP_ID).append("=?");
        
        return (RelOperGroup) this.getSingle(hql.toString(), new Object[]{operatorId,groupId});
		
	}
}