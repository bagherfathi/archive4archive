package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseValidatorForm;


/**
 * 操作员自服务数据类
 * 
 * @version 1.0
 * 
 * @struts.form name="opSelfForm"
 */
public class OpSelfForm extends BaseValidatorForm{

    private static final long serialVersionUID = 1L;
    
    private OperatorDTO opDTO;
    
    private String oldPassword;
    
    private String newPassword;
    
    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the opDTO
     */
    public OperatorDTO getOpDTO() {
        return opDTO;
    }

    /**
     * @param opDTO the opDTO to set
     */
    public void setOpDTO(OperatorDTO opDTO) {
        this.opDTO = opDTO;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest httpRequest) {
        if (this.opDTO == null) {
            this.opDTO = OperatorSessionHelper.getLoginOp(httpRequest);
        }
        super.reset(mapping, httpRequest);
    }

}
