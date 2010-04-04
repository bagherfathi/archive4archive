package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;
import com.ft.sm.entity.DataResourceEntry;
import org.springframework.context.ApplicationContext;

/**
 * 对业务权限验证,若业务权限或其条目信息和其他信息相关联则页面不能对其作删除操作
 * 
 * @version 1.0
 */
public class ValidateDataResourceTag extends TagSupport {

    private static final long serialVersionUID = -6448087270620316643L;

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
            boolean exist = false;
            exist = queryHelper.exist(DataResourceEntry.class, DataResourceEntry.PROP_RESOURCE_ID, aId);
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
