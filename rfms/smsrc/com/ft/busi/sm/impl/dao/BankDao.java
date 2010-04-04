package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.entity.Bank;

/**
 * Bank 实体数据访问类
 * 
 * @spring.bean id="BankDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BankDao extends BaseDao {

    /**
     * 构造函数
     */
    public BankDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return Bank.class;
    }

    /**
     * 根据ID查找对象
     */
    public Bank getById(Serializable key) {
        return (Bank) this.getHibernateTemplate().get(getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public Bank loadById(Serializable key) {
        return (Bank) this.getHibernateTemplate()
                .load(getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据状态查询银行信息。
     * 
     * @param status
     *                银行信息状态。
     * @return
     */
    public List findBanksByStatus(Long status) {
        StringBuffer hql = new StringBuffer(
                "select new com.ft.sm.dto.BankDTO(bank)");
        hql.append(" from Bank bank");
        hql.append(" where bank.").append(Bank.PROP_STATUS).append("=?");

        return this.query(hql.toString(), new Object[] { status });
    }
    
    /**
     * 根据编码查银行
     * @param bankCode
     * @return
     */
    public BankDTO findBankByCode(String bankCode) {
        StringBuffer hql = new StringBuffer(
        "select new com.ft.sm.dto.BankDTO(bank)");
        hql.append(" from Bank bank");
        hql.append(" where bank.").append(Bank.PROP_BANK_CODE).append("=?");
        hql.append(" and bank.").append(Bank.PROP_STATUS).append("=?");

        List dtos =  this.query(hql.toString(), new Object[] { bankCode,new Long(BankDTO.STATUS_NORMAL) });
        
        if(dtos!=null&&dtos.size()>=1)
            return (BankDTO) dtos.get(0);
        
        return null;
    }
    
    /**
     * 根据名称查银行
     * @param bankName
     * @return
     */
    public BankDTO findBankByName(String bankName) {
        StringBuffer hql = new StringBuffer(
        "select new com.ft.sm.dto.BankDTO(bank)");
        hql.append(" from Bank bank");
        hql.append(" where bank.").append(Bank.PROP_BANK_NAME).append("=?");
        hql.append(" and bank.").append(Bank.PROP_STATUS).append("=?");
        
        List dtos =  this.query(hql.toString(), new Object[] { bankName,new Long(BankDTO.STATUS_NORMAL) });
        
        if(dtos!=null&&dtos.size()>=1)
            return (BankDTO) dtos.get(0);
        
        return null;
    }
}