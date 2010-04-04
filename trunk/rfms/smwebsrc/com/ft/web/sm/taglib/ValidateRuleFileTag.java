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
 * 验证规则文件与规则是否存在关联
 * 
 * @version 1.0
 */
public class ValidateRuleFileTag extends TagSupport {

    private static final long serialVersionUID = 8162178496103691784L;

    private String id;

    private String var;

    public int doStartTag() throws JspException {

        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);

        EntityQuery queryHelper = (EntityQuery) app
                .getBean(EntityQueryHelper.NAME);
        Evaluator aEvaluator = new Evaluator();

        try {
            Long ruleFileId = (Long) aEvaluator.evaluate("id", this.id,
                    Long.class, this, pageContext);
            boolean exist = false;
            // 判断规则分类是否在其它表中存在关联关系
            exist = queryHelper.exist(RuleInfo.class, "ruleFileId", ruleFileId);
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
