package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.entity.TaskTrigger;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

public class ValidateTaskJob extends TagSupport {

    private static final long serialVersionUID = -3401731870828775660L;

    private String id;

    private String var;

    public int doStartTag() throws JspException {
        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);
        EntityQuery queryHelper = (EntityQuery) app
                .getBean(EntityQueryHelper.NAME);
        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("id", this.id, Long.class, this,
                pageContext);
        try {
            boolean exist = false;
            exist = queryHelper.exist(TaskTrigger.class,
                    TaskTrigger.PROP_JOB_ID, aId);
            this.pageContext.setAttribute(var, new Boolean(exist));
            return SKIP_BODY;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
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
