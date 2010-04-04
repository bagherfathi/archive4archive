package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankError;

/**
 * BankError 实体数据访问类
 * 
 * @spring.bean id="BankErrorDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BankErrorDao extends BaseDao {

    /**
     * 构造函数
     */
    public BankErrorDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return BankError.class;
    }

    /**
     * 根据ID查找对象
     */
    public BankError getById(Serializable key) {
        return (BankError) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public BankError loadById(Serializable key) {
        return (BankError) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询指定银行的托收错误信息
     * 
     * @param bankId
     *                银行标识。
     * @return 银行托收错误实体列表
     */
    public List findBankErrorsByBank(Long bankId) {
        StringBuffer hql = new StringBuffer("from BankError error");
        hql.append(" where error.").append(BankError.PROP_BANK_ID).append("=?");
       
        return this.query(hql.toString(), new Object[] { bankId });
    }
}