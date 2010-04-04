package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.RelOperRole;
import org.springframework.context.ApplicationContext;

/**
 * 对Role做验证处理
 * 
 * @version 1.0
 */
public class ValidateRoleTag extends TagSupport {

    private static final long serialVersionUID = -1750852398568018663L;

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
            Long roleId = aId;
            boolean exist = false;

            exist = queryHelper.exist(RelGroupRole.class,
                    RelGroupRole.PROP_ROLE_ID, roleId);
            if (!exist) {
                exist = queryHelper.exist(RelOperRole.class,
                        RelOperRole.PROP_ROLE_ID, roleId);
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
