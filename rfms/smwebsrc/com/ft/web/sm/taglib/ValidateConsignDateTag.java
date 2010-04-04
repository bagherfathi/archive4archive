package com.ft.web.sm.taglib;

import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

/**
 * 验证委托时间
 * 
 * @version 1.0
 */
public class ValidateConsignDateTag extends TagSupport {

    private static final long serialVersionUID = 7571162247280579083L;

    private String date;

    private String var;

    public int doStartTag() throws JspException {

        try {
            Evaluator aEvaluator = new Evaluator();
            Date aDate = (Date) aEvaluator.evaluate("date", this.date,
                    Date.class, this, pageContext);
            boolean flag = false;
            Date now = new Date();
            if (aDate.before(now)) {
                flag = true;
            }
            this.pageContext.setAttribute(var, new Boolean(flag));
            return SKIP_BODY;
        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
