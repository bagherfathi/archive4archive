package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.sm.entity.RelOperGroup;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * ��֤����Ա��
 * 
 * @version 1.0
 */
public class ValidateGroupTag extends TagSupport {

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
            Long entityId = aId;
            boolean exist = false;
            exist = queryHelper.exist(RelOperGroup.class,
                    RelOperGroup.PROP_GROUP_ID, entityId);

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
