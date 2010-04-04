package com.ft.web.sm.data.bank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.BankDTO;
import com.ft.sm.dto.RelBankOrgDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 银行绑定组织页面Form Bean.
 * @struts.form name="bankOrgForm"
 * @version 1.0
 */
public class BankOrgForm extends BaseValidatorForm{
    private static final long serialVersionUID = 818884379355518412L;
    private BankDTO bank;
    private RelBankOrgDTO relBankOrg;
    private List accessOrgs;
    
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

    /**
     * @struts.entity-field initial="relBankOrgId"
     * @return the relBankOrg
     */
    public RelBankOrgDTO getRelBankOrg() {
        return relBankOrg;
    }

    /**
     * @param relBankOrg the relBankOrg to set
     */
    public void setRelBankOrg(RelBankOrgDTO relBankOrg) {
        this.relBankOrg = relBankOrg;
    }
    
    /**
     * @return the accessOrgs
     */
    public List getAccessOrgs() {
        return accessOrgs;
    }

    /**
     * @param accessOrgs the accessOrgs to set
     */
    public void setAccessOrgs(List accessOrgs) {
        this.accessOrgs = accessOrgs;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);

        if (null == relBankOrg) {
            relBankOrg = new RelBankOrgDTO();
        }
    }
}
