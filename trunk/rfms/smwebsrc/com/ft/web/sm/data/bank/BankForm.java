package com.ft.web.sm.data.bank;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.BankDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 银行信息维护页面数据类
 * 
 * @struts.form name="bankForm"
 * 
 * @version 1.0
 */
public class BankForm extends BaseValidatorForm {
    private static final long serialVersionUID = -1156232596015383928L;
    private String bankName;
    private String bankCode;

    private BankDTO bank;

    private long orgId;

    /**
     * 银行信息数据实体
     * 
     * @struts.entity-field initial="bankId"
     * @return
     */
    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
    
    

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == bank) {
            bank = new BankDTO();
        }
    }
}
