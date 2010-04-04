package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.sm.entity.RuleInfo;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 验证规则分类是否存在其他关联
 * 
 * @version 1.0
 */
public class ValidateRuleCategoryTag extends TagSupport {

    private static final long serialVersionUID = 7021082998918336809L;

    private String id;

    private String var;

    public int doStartTag() throws JspException {

        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);

        EntityQuery queryHelper = (EntityQuery) app
                .getBean(EntityQueryHelper.NAME);
        Evaluator aEvaluator = new Evaluator();

        try {
            Long categoryId = (Long) aEvaluator.evaluate("id", this.id,
                    Long.class, this, pageContext);
            boolean exist = false;
            // 判断规则分类是否在其它表中存在关联关系
            exist = queryHelper.exist(RuleInfo.class, "categoryId", categoryId);
            this.pageContext.setAttribute(var, new Boolean(exist));
            return SKIP_BODY;
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *                The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the var.
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *                The var to set.
     */
    public void setVar(String var) {
        this.var = var;
    }

}
