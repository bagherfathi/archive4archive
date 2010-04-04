package com.ft.hibernate.taglib;

import com.ft.entity.EntityQuery;

import com.ft.hibernate.helper.EntityQueryHelper;

import org.springframework.context.ApplicationContext;

import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * 加载实体的Tag
 */
public class EntityLoadTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private Serializable entityId;
    private String entityName;
    private String var;
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Serializable getEntityId() {
        return entityId;
    }

    public void setEntityId(Serializable entityId) {
        this.entityId = entityId;
    }

    public int doStartTag() throws JspException {
        ApplicationContext app =
            PageContextUtils.getApplicationContext(this.pageContext);

        EntityQuery queryHelper =
            (EntityQuery) app.getBean(EntityQueryHelper.NAME);

        try {
            Object obj =
                queryHelper.load(Class.forName(entityName), entityId);

            this.pageContext.setAttribute(var, obj);

            return SKIP_BODY;
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
