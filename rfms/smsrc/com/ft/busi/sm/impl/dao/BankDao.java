package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.BankDTO;
import com.ft.sm.entity.Bank;

/**
 * Bank ʵ�����ݷ�����
 * 
 * @spring.bean id="BankDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BankDao extends BaseDao {

    /**
     * ���캯��
     */
    public BankDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Bank.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Bank getById(Serializable key) {
        return (Bank) this.getHibernateTemplate().get(getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Bank loadById(Serializable key) {
        return (Bank) this.getHibernateTemplate()
                .load(getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ����״̬��ѯ������Ϣ��
     * 
     * @param status
     *                ������Ϣ״̬��
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
     * ���ݱ��������
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
     * �������Ʋ�����
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