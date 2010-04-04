package com.ft.web.sm.data.bank;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.BankErrorDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 托收代码维护页面数据类
 * 
 * @struts.form name="bankErrorForm"
 * 
 * @version 1.0
 */
public class BankErrorForm extends BaseValidatorForm {

    private static final long serialVersionUID = -4561691265558982867L;

    /**
     * 银行委托错误代码信息
     */
    private BankErrorDTO bankError;

    private String bankErrorCode;
    
    private String bankId;
    

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);

        if (null == bankError) {
            bankError = new BankErrorDTO();
        }
    }

    /**
     * @struts.entity-field initial="bankErrorId"
     * @return Returns the domain.
     */
    public BankErrorDTO getBankError() {
        return bankError;
    }

    public void setBankError(BankErrorDTO bankError) {
        this.bankError = bankError;
    }

    /**
     * @return the bankErrorCode
     */
    public String getBankErrorCode() {
        return bankErrorCode;
    }

    /**
     * @param bankErrorCode
     *                the bankErrorCode to set
     */
    public void setBankErrorCode(String bankErrorCode) {
        this.bankErrorCode = bankErrorCode;
    }

    /**
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
