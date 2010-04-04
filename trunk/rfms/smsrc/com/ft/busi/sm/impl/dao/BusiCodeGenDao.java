package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BusiCodeGen;

/**
 * BusiCodeGen 实体数据访问类
 * 
 * @spring.bean id="busiCodeGenDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BusiCodeGenDao extends BaseDao {

    /**
     * 构造函数
     */
    public BusiCodeGenDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return BusiCodeGen.class;
    }

    /**
     * 根据ID查找对象
     */
    public BusiCodeGen getById(Serializable key) {
        return (BusiCodeGen) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public BusiCodeGen loadById(Serializable key) {
        return (BusiCodeGen) this.getHibernateTemplate().load(
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
    public BusiCodeGen getBusiCodeGenByCode(String busiCodeGenCode,
            long busiCodeRuleId) {
        String hql = " from BusiCodeGen bg where bg.busiCodeGenCode =?"
                + " and bg.busiCodeRuleId=?";
        List list = this.query(hql, new Object[] { busiCodeGenCode,
                new Long(busiCodeRuleId) });
        if ((list != null) && (list.size() == 1))
            return (BusiCodeGen) list.get(0);
        return null;
    }
}