package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.BankError;

/**
 * 银行托收错误代码实体封装类。
 * 
 */
public class BankErrorDTO implements HisDTO {
    private static final long serialVersionUID = 9160348821103143975L;

    public static final int STATUS_DISABLE = 1;

    public static final int STATUS_NORMAL = 0;

    public static final String DEFAULT_BANK_CODE = "all";

    private BankError bankError;

    public BankErrorDTO() {
        this.bankError = new BankError();
    }

    public BankErrorDTO(BankError bankError) {
        this.bankError = bankError;
    }

    /**
     * 托收错误代码。
     * 
     * @return
     */
    public String getErrorCode() {
        return this.bankError.getBankErrorCode();
    }

    public void setErrorCode(String errorCode) {
        this.bankError.setBankErrorCode(errorCode);
    }

    /**
     * 托收错误代码描述信息。
     * 
     * @return
     */
    public String getErrorDesc() {
        return this.bankError.getBankErrorDesc();
    }

    public void setErrorDesc(String errorDesc) {
        this.bankError.setBankErrorDesc(errorDesc);
    }

    /**
     * 错误信息ID。
     * 
     * @return Returns the id.
     */
    public Long getErrorId() {
        return new Long(this.bankError.getBankErrorId());
    }

    public void setErrorId(Long errorId) {
        this.bankError.setBankErrorId(errorId.longValue());
    }

    /**
     * 托收错误代码状态。
     * 
     */
    public long getStatus() {
        return this.bankError.getStatus();
    }

    public void setStatus(long status) {
        this.bankError.setStatus(status);
    }
    
    public Long getBankId(){
        return new Long(bankError.getBankId());
    }
    
    public void setBankId(Long bankId){
        this.bankError.setBankId(bankId.longValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.bankError;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.bankError = (BankError) target;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.bankError.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.bankError.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.bankError.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.bankError.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.bankError.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.bankError.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.bankError.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.bankError.setValidDate(validDate);
    }
}
