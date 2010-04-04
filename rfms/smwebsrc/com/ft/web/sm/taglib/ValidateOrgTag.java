package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperOrg;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * @version 1.0 验证组织是否可以删除
 * 
 */
public class ValidateOrgTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private String var;

    private String id;

    public int doStartTag() throws JspException {
        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);
        EntityQuery queryHelper = (EntityQuery) app
                .getBean(EntityQueryHelper.NAME);
        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("id", this.id, Long.class, this,
                pageContext);

        try {
            Long orgId = aId;
            boolean exist = false;
            exist = queryHelper.exist(Operator.class, Operator.PROP_ORG_ID,
                    orgId);
            if (!exist) {
                exist = queryHelper.exist(Organization.class,
                        Organization.PROP_PARENT_ID, orgId);
            }if (!exist) {
                exist = queryHelper.exist(RelOperOrg.class,
                		RelOperOrg.PROP_ORG_ID, orgId);
            }
            this.pageContext.setAttribute(var, new Boolean(exist));
            return SKIP_BODY;
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
