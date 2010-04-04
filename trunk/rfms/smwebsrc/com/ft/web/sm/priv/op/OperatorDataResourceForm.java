package com.ft.web.sm.priv.op;

import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 设置操作员业务权限页面Form Bean。
 * 
 * @version 1.0
 * 
 * @struts.form name="opDataResourceForm"
 */
public class OperatorDataResourceForm extends BaseValidatorForm{
    private static final long serialVersionUID = 7149569408287310136L;

    private OperatorDTO op;

    /**
     * @struts.entity-field initial="opId"
     * @return
     */
    public OperatorDTO getOp() {
        return op;
    }

    public void setOp(OperatorDTO op) {
        this.op = op;
    }
}
