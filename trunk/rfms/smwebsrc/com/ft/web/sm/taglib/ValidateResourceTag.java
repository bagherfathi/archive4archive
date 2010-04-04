package com.ft.web.sm.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.sm.entity.RelRoleRes;
import com.ft.entity.EntityQuery;
import com.ft.hibernate.helper.EntityQueryHelper;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 用于判断Resource是否可以被删除
 * 
 * @version 1.0
 */
public class ValidateResourceTag extends TagSupport {

    private static final long serialVersionUID = -5186952152020800119L;

    private String var;

    private String id;

    public int doStartTag() throws JspException {

        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);

        EntityQuery queryHelper = (EntityQuery) app
                .getBean(EntityQueryHelper.NAME);

        ResourceManager resourceManager = (ResourceManager) app
                .getBean("resourceManager");

        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("id", this.id, Long.class, this,
                pageContext);

        try {
            Long resourceId = aId;
            boolean exist = false;
            List temp = resourceManager.findChildren(resourceId);
            if (temp != null && temp.size() > 0)
                exist = true;
            if (!exist) {
                exist = queryHelper.exist(RelRoleRes.class,
                        RelRoleRes.PROP_RESOURCE_ID, resourceId);
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
