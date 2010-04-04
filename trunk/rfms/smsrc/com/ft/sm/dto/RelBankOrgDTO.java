package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.Bank;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelBankOrg;

/**
 * Class comments.
 * 
 */
public class RelBankOrgDTO implements HisDTO{
    private static final long serialVersionUID = 3833650031978262993L;
    private RelBankOrg relBankOrg;
    private Organization org;
    private Bank bank;

    public RelBankOrgDTO(){
        this.relBankOrg = new RelBankOrg();
    }
    
    public RelBankOrgDTO(RelBankOrg relBankOrg){
        this.relBankOrg = relBankOrg;
    }
    
    public RelBankOrgDTO(RelBankOrg relBankOrg,Organization org){
        this.relBankOrg = relBankOrg;
        this.org = org;
    }
    
    public RelBankOrgDTO(RelBankOrg relBankOrg,Bank bank,Organization org){
        this.relBankOrg = relBankOrg;
        this.org = org;
        this.bank = bank;
    }
    
    /**
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return relBankOrg;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.relBankOrg = (RelBankOrg)target;
    }

    /**
     * @return the org
     */
    public Organization getOrg() {
        return org;
    }

    /**
     * @param org the org to set
     */
    public void setOrg(Organization org) {
        this.org = org;
    }

    /**
     * @return the relBankOrg
     */
    public RelBankOrg getRelBankOrg() {
        return relBankOrg;
    }

    /**
     * @param relBankOrg the relBankOrg to set
     */
    public void setRelBankOrg(RelBankOrg relBankOrg) {
        this.relBankOrg = relBankOrg;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.relBankOrg.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.relBankOrg.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.relBankOrg.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.relBankOrg.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.relBankOrg.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.relBankOrg.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.relBankOrg.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.relBankOrg.setValidDate(validDate);
    }
}
