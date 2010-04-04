package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;
import com.ft.sm.entity.Affiche;
import org.springframework.context.ApplicationContext;

/**
 * 验证信息类别是否有其他关联
 * 
 */
public class ValidateInfoCategoryTag extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7828106621795225545L;

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
            // 判断信息分类是否在其它表中存在关联关系
            exist = queryHelper.exist(Affiche.class,
                    Affiche.PROP_CATEGORY_ID, categoryId);
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
