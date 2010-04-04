
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RuleInfo;

/**
 * RuleInfo 实体数据访问类
 * 
 * @spring.bean id="RuleInfoDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleInfoDao extends BaseDao {

    /**
     * 构造函数
     */
    public RuleInfoDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RuleInfo.class;
    }

    /**
     * 根据ID查找对象
     */
    public RuleInfo getById(Serializable key) {
        return (RuleInfo) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public RuleInfo loadById(Serializable key) {
        return (RuleInfo) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据规则类别和规则名称查询规则
     * 
     * @param categoryId
     * @param ruleName
     * @return
     */
    public List findRule(Long categoryId, String ruleName) {
        List rules = new ArrayList();
        List<Object> params = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer("from RuleInfo r ");
        if ((null == categoryId || categoryId.longValue() == 0)
                && (null == ruleName || ruleName.length() == 0)) {
            rules = this.query(hql.toString());
        } else {
            hql.append("where ");
            if (null != categoryId && categoryId.longValue() != 0) {
                hql.append("r.categoryId = ? ");
                params.add(categoryId);
            }
            if (null != ruleName && ruleName.length() > 0) {
                if (null != categoryId && categoryId.longValue() != 0) {
                    hql.append("and ");
                }
                hql.append("r.ruleName like ? ");
                params.add("%" + ruleName + "%");
            }
            rules = this.query(hql.toString(), params.toArray(new Object[0]));
        }

        return rules;
    }
}