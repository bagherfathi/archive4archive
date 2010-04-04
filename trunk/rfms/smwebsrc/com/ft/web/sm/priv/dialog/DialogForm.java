package com.ft.web.sm.priv.dialog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;

/**
 * µ¯³öÒ³ÃæForm Bean
 * 
 * @version 1.0
 * @struts.form name = "dialogForm"
 */
public class DialogForm extends ActionForm {

    private static final long serialVersionUID = 8023695408055907819L;

    private Organization org;

    private Operator op;

    private Long templateId;

    /**
     * @struts.entity-field initial="opId"
     * @return Returns the attach.
     */

    public Operator getOp() {
        return op;
    }

    public void setOp(Operator op) {
        this.op = op;
    }

    /**
     * @struts.entity-field initial="orgId"
     * @return Returns the attach.
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    /**
     * @return Returns the templateId.
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId
     *                The templateId to set.
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == org) {
            org = new Organization();
        }
        if (null == op) {
            op = new Operator();
        }

    }
}
