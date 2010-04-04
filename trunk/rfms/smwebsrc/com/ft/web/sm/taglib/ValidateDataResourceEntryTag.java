package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.sm.entity.RelRoleDataRes;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 对业务权限是否可删做验证
 * 
 * @version 1.0
 */
public class ValidateDataResourceEntryTag extends TagSupport {

    private static final long serialVersionUID = 9029584843600191649L;

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

            Long entryId = aId;
            boolean exist = false;

            exist = queryHelper.exist(RelRoleDataRes.class,
                    RelRoleDataRes.PROP_ENTRY_ID, entryId);

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
