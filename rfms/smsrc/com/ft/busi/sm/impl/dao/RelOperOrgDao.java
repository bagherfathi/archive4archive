
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelOperOrg;

/**
 * RelOperOrg 实体数据访问类
 * 
 * @spring.bean id="RelOperOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelOperOrgDao extends BaseDao {

    /**
     * 构造函数
     */
    public RelOperOrgDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RelOperOrg.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelOperOrg getById(Serializable key) {
        return (RelOperOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RelOperOrg loadById(Serializable key) {
        return (RelOperOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 删除操作员可访问组织。
     * 
     * @param operatorId
     *                操作员ID。
     * @param long1
     *                可访问组织机构ID。
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
     * 查询可访问组织关系。
     * 
     * @param operatorId
     *                操作员ID。
     * @param orgId
     *                可访问组织机构ID。
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