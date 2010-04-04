package com.ft.web.sm.priv.resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 查询操作员所有权限表单类
 * 
 * @struts.form name="queryOperatorPrivilegeForm"
 * 
 * @version 1.0
 */
public class QueryOperatorPrivilegeForm extends BaseValidatorForm {

    private static final long serialVersionUID = 5255668941445742491L;

    /**
     * 存放操作员信息
     */
    private OperatorDTO operator;

    private String act;

    private String loginName;

    private String opName;

    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        if (null == operator) {
            operator = new OperatorDTO();
        }
    }

    /**
     * @struts.entity-field initial="opId"
     * @return
     */
    public OperatorDTO getOperator() {
        return operator;
    }

    public void setOperator(OperatorDTO operator) {
        this.operator = operator;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the opName
     */
    public String getOpName() {
        return opName;
    }

    /**
     * @param opName
     *                the opName to set
     */
    public void setOpName(String opName) {
        this.opName = opName;
    }
}
