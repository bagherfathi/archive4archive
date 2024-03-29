package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.sm.entity.RelOperGroup;
import com.ft.sm.entity.RelOperOrg;
import com.ft.sm.entity.RelOperRole;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * ��֤����Ա
 * 
 * @version 1.0
 */
public class ValidateOpTag extends TagSupport {

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
            Long opId = aId;
            boolean exist = false;
            exist = queryHelper.exist(RelOperOrg.class,
                    RelOperOrg.PROP_OPERATOR_ID, opId);
            if (!exist) {
                exist = queryHelper.exist(RelOperGroup.class,
                        RelOperGroup.PROP_OPERATOR_ID, opId);
            }
            if (!exist) {
                exist = queryHelper.exist(RelOperRole.class,
                        RelOperRole.PROP_OPERATOR_ID, opId);
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
