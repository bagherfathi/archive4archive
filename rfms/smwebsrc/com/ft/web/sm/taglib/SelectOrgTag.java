package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.OrgManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

public class SelectOrgTag extends TagSupport {

    private static final long serialVersionUID = 450663963371697351L;

    // «¯”ÚID
    private String rootId;

    // ∑µªÿ÷µ
    private String inputName;

    private String orgId;
    
    private String size = "25";

    public int doStartTag() throws JspException {

        try {
            OrgManager orgManager = (OrgManager) SpringWebUtils.getBean(
                    pageContext.getServletContext(), "orgManager");

            String rootIdstr = "";
            String orgName = "";
            String orgIdstr = "";
            if (orgId != null) {
                Evaluator aEvaluator = new Evaluator();
                Long aOrgId = (Long) aEvaluator.evaluate("orgId", this.orgId,
                        Long.class, this, pageContext);
                OrganizationDTO org = orgManager.findOrgById(aOrgId);
                if (rootId != null) {
                    Long aRootId = (Long) aEvaluator.evaluate("rootId",
                            this.rootId, Long.class, this, pageContext);
                    rootIdstr = aRootId.toString();
                    OrganizationDTO root = orgManager.findOrgById(aRootId);
                    if (org.getPath().startsWith(root.getPath())) {
                        orgName = org.getName();
                        orgIdstr = org.getOrgId().toString();
                    }
                } else {
                    orgName = org.getName();
                    orgIdstr = org.getOrgId().toString();
                }
            }

            StringBuffer buffer = new StringBuffer();
            buffer.append("<input type=\"text\" ");
            buffer.append("size=\"").append(size).append("\" ");
            buffer.append("readonly=\"true\" ");
            buffer.append("id=\"").append("select_org").append("\" ");
            buffer.append("value=\"").append(orgName).append("\" />");

            HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                    .getRequest();
            buffer.append("<img src=\"").append(httpRequest.getContextPath())
                    .append("/images/icon_sel.gif").append("\" ");
            buffer.append("onclick =\"").append("openSelectOrgTree()").append(
                    "\" />");
            buffer.append("<input type=\"hidden\" ");
            buffer.append("name=\"").append(this.inputName).append("\" ");
            buffer.append("id=\"").append(this.inputName).append("\" ");
            buffer.append("value=\"").append(orgIdstr).append("\" />");

            buffer.append("\r\n");

            buffer.append("<script>");
            buffer.append("function openSelectOrgTree(){");
            buffer.append("flag=window.open(\"");
            buffer.append(httpRequest.getContextPath()).append(
                    "/sm/dialog.do?act=opOrg").append("&rootId=").append(
                    rootIdstr);
            buffer.append("&inputName=").append(this.inputName);
            buffer.append("&orgId=\"+document.getElementById('").append(
                    this.inputName).append("').value,");
            buffer.append("\"tree\",");
            buffer
                    .append("'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,");
            buffer.append("resizable=no,location=no, status=yes');");
            buffer.append("flag.focus();}");
            buffer.append("</script>");

            TagUtils.getInstance().write(this.pageContext, buffer.toString());
            return SKIP_BODY;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * @return Returns the regionId.
     */
    public String getRootId() {
        return rootId;
    }

    /**
     * @param orgId
     *                The orgId to set.
     */
    public void setRootId(String orgId) {
        this.rootId = orgId;
    }

    /**
     * @return Returns the inputName.
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * @param inputName
     *                The inputName to set.
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *                the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }
}
