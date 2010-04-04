package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.Bank;

/**
 * 银行实体封装类。
 * 
 */
public class BankDTO implements HisDTO {
    private static final long serialVersionUID = 6473431521434425277L;

    public static final long STATUS_DISABLE = 1;

    public static final long STATUS_NORMAL = 0;

    private Bank bank;

    public BankDTO() {
        bank = new Bank();
    }

    public BankDTO(Bank bank) {
        this.bank = bank;
    }
    
    /**
     * 银行代码。
     * 
     */
    public String getBankCode() {
        return this.bank.getBankCode();
    }

    public void setBankCode(String bankCode) {
        this.bank.setBankCode(bankCode);
    }

    /**
     * 银行信息ID。
     * 
     */
    public Long getBankId() {
        return new Long(this.bank.getBankId());
    }

    public void setBankId(Long bankId) {
        this.bank.setBankId(bankId.longValue());
    }

    /**
     * 银行名称。
     * 
     */
    public String getBankName() {
        return this.bank.getBankName();
    }

    public void setBankName(String bankName) {
        this.bank.setBankName(bankName);
    }

    /**
     * 银行描述信息。
     * 
     * @return
     */
    public String getDescription() {
        return this.bank.getBankDesc();
    }

    public void setDescription(String description) {
        this.bank.setBankDesc(description);
    }

    /**
     * 银行信息状态。
     * 
     */
    public long getStatus() {
        return this.bank.getStatus();
    }

    public void setStatus(long status) {
        this.bank.setStatus(status);
    }

    /**
     * 银行类型。
     * 
     * @return
     */
    public long getBankType() {
        return this.bank.getBankType();
    }

    public void setBankType(long bankType) {
        this.bank.setBankType(bankType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.bank;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.bank = (Bank) target;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.bank.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.bank.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.bank.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.bank.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.bank.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.bank.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.bank.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.bank.setValidDate(validDate);
    }
}
