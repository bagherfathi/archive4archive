package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BusiCodeRule;

/**
 * BusiCodeRule 实体数据访问类
 * 
 * @spring.bean id="busiCodeRuleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BusiCodeRuleDao extends BaseDao {

    /**
     * 构造函数
     */
    public BusiCodeRuleDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return BusiCodeRule.class;
    }

    /**
     * 根据ID查找对象
     */
    public BusiCodeRule getById(Serializable key) {
        return (BusiCodeRule) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public BusiCodeRule loadById(Serializable key) {
        return (BusiCodeRule) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据业务编码规则查找编码规则定义
     * 
     * @param busiCodeRuleCode
     * @return
     */
    public BusiCodeRule getBusiCodeRuleByCode(String busiCodeRuleCode) {
        String hql = " from BusiCodeRule cr where cr.busiCodeRuleCode =?";
        List list = this.query(hql, new String[] { busiCodeRuleCode });
        if ((list != null) && (list.size() == 1))
            return (BusiCodeRule) list.get(0);
        return null;
    }

}