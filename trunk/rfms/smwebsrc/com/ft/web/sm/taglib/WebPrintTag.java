package com.ft.web.sm.taglib;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.template.TemplateContext;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.template.impl.VelocityTemplateEngine;
import com.ft.commons.web.SpringWebUtils;

/**
 * @author <a href="mailto:liuliang2@126.com">liuliang</a>
 * @version 1.0
 */
public class WebPrintTag extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8476253038858508900L;

	private String templateName;

    private String printDivId;

    private String contextPath;

    private String dataMap;

    private String org;

    private String categoryCode;

    /**
     * @return Returns the categoryCode.
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode
     *            The categoryCode to set.
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @return Returns the org.
     */
    public String getOrg() {
        return org;
    }

    /**
     * @param org
     *            The org to set.
     */
    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * @return Returns the contextPath.
     */
    public String getContextPath() {
        HttpServletRequest request = (HttpServletRequest) this.pageContext
                .getRequest();
        this.contextPath = request.getContextPath();
        return this.contextPath;
    }

    /**
     * @param contextPath
     *            The contextPath to set.
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * @return Returns the dataMap.
     */
    public String getDataMap() {
        return dataMap;
    }

    /**
     * @param dataMap
     *            The dataMap to set.
     */
    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * @return Returns the templateName.
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName
     *            The templateName to set.
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * 
     */
    public WebPrintTag() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {

        Evaluator aEvaluator = new Evaluator();
        if (this.templateName == null) {
            this.templateName = "";
        }
        if (this.categoryCode == null) {
            this.categoryCode = "";
        }
        Object tempObj = aEvaluator.evaluate("templateName", this.templateName,
                String.class, this, pageContext);
        String atemplateName = "";
        if (tempObj != null) {
            atemplateName = (String) tempObj;
        } else {
            atemplateName = this.templateName;
        }

        String aprintDivId = (String) aEvaluator.evaluate("printId",
                this.printDivId, String.class, this, pageContext);

        Object ob = aEvaluator.evaluate("dataMap", this.dataMap, Map.class,
                this, pageContext);
        Map map = (Map) ob;
        // 组织
        OrganizationDTO orgDTO = null;
        if (this.org != null) {
            orgDTO = (OrganizationDTO) aEvaluator.evaluate("org", this.org,
                    OrganizationDTO.class, this, pageContext);
        }
        String aCategoryCode = (String) aEvaluator.evaluate("categoryCode",
                this.categoryCode, String.class, this, pageContext);
        if (aCategoryCode != null && !aCategoryCode.equals("")) {
            categoryCode = aCategoryCode;
        }

        map.put("printDivId", aprintDivId);// 要打印的Div ID
        map.put("contextPath", this.getContextPath());

        StringWriter writer = new StringWriter();
        // 获取模版引擎
        VelocityTemplateEngine velocityTemplateEngine = (VelocityTemplateEngine) SpringWebUtils
                .getBean(pageContext, "vmTemplateEngine");
        TemplateContext templateContext = new TemplateContext();
        templateContext.addAttribute("print", map);

        try {
            // 解释模版
            if (templateName != null && templateName.length() > 0) {
                velocityTemplateEngine.execute(templateContext, atemplateName,
                        writer);
            } else {
                velocityTemplateEngine.execute(templateContext, orgDTO,
                        categoryCode, writer);
            }
        } catch (Exception e) {
            TagUtils.getInstance().write(pageContext,
                    "<div class='NOPRINT'>找不到打印模版" + atemplateName + "</div>");
            e.printStackTrace();
        }
        // WebUIContextImpl.getWebUIContext().getTemplateEngine().execute(adataMap,
        // atemplateName+".vm", write);
        TagUtils.getInstance().write(pageContext, writer.toString());
        return SKIP_BODY;
    }

    /**
     * @return Returns the printDivId.
     */
    public String getPrintDivId() {
        return printDivId;
    }

    /**
     * @param printDivId
     *            The printDivId to set.
     */
    public void setPrintDivId(String printDivId) {
        this.printDivId = printDivId;
    }

}
