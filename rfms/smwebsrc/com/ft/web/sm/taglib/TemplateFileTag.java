package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 根据模板类别分离模板文件信息
 * 
 * @version 1.0
 */
public class TemplateFileTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String categoryId;

    private String fileInfos;

    private String var;

    public int doStartTag() throws JspException {
        /*
        List result = new ArrayList();

        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("categoryId", this.categoryId,
                Long.class, this, pageContext);
        List templateFileInfos = (List) aEvaluator.evaluate("fileInfos",
                this.fileInfos, List.class, this, pageContext);

        for (Iterator iter = templateFileInfos.iterator(); iter.hasNext();) {
            TemplateFileInfoDTO element = (TemplateFileInfoDTO) iter.next();
            if (element.getTemplate().getCategoryId() == aId.longValue()) {
                result.add(element);
            }
        }
        this.pageContext.setAttribute(var, result);
        */
        return SKIP_BODY;
    }

    /**
     * @return Returns the categoryId.
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     *                The categoryId to set.
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return Returns the fileInfos.
     */
    public String getFileInfos() {
        return fileInfos;
    }

    /**
     * @param fileInfos
     *                The fileInfos to set.
     */
    public void setFileInfos(String fileInfos) {
        this.fileInfos = fileInfos;
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
