package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.security.OrgRepository;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * ×éÖ¯Ê÷µÄTag
 * 
 * @version 1.0
 */
public class OrgsTreeTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String orgId;

    private String selOrgId;

    private String inputName;

    private String method;

    private String orgType;

    private String size = "25";

    public int doStartTag() throws JspException {


        //String orgName = "";
        //String orgIdstr = "";
        String selOrgIdstr = "";
        String selOrgName = "";
        Evaluator aEvaluator = new Evaluator();
        
        /*
        if (StringUtils.isNotEmpty(orgId )) {
            Long aOrgId = (Long) aEvaluator.evaluate("orgId", this.orgId,
                    Long.class, this, pageContext);
            if (aOrgId.longValue() != 0) {
                OrgRepository orgRepository = (OrgRepository) SpringWebUtils
                        .getBean(pageContext.getServletContext(),
                                "orgRepository");
                OrganizationDTO org = orgRepository.getOrgDTOById(aOrgId);
                if (org != null) {
                    orgName = org.getName();
                    orgIdstr = org.getOrgId().toString();
                }
            }
        }
        */
        
        if (StringUtils.isNotEmpty(selOrgId )) {
            Long aSelOrgId = (Long) aEvaluator.evaluate("aSelOrgId", this.selOrgId,
                    Long.class, this, pageContext);
            
            if (aSelOrgId.longValue() != 0) {
                OrgRepository orgRepository = (OrgRepository) SpringWebUtils
                        .getBean(pageContext.getServletContext(),
                                "orgRepository");
                OrganizationDTO org = orgRepository.getOrgDTOById(aSelOrgId);
                if (org != null) {
                    selOrgName = org.getName();
                    selOrgIdstr = org.getOrgId().toString();
                }
            }
        }
        
        Long aOrgType= (Long) aEvaluator.evaluate("orgType", this.orgType,
                Long.class, this, pageContext);

        String identity = inputName.replace('.', '_');
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"text\" ");
        buffer.append("size=\"").append(size).append("\" ");
        buffer.append("readonly=\"true\" ");
        buffer.append("id=\"").append("select_").append(this.inputName).append(
                "\" ");
        buffer.append("value=\"").append(selOrgName).append("\" />");

        HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                .getRequest();
        buffer.append("<img src=\"").append(httpRequest.getContextPath())
                .append("/images/icon_sel.gif").append("\" ");
        buffer.append("onclick =\"").append("openSelect")
                .append(identity).append("Tree()").append("\" />");
        buffer.append("<input type=\"hidden\" ");
        buffer.append("name=\"").append(this.inputName).append("\" ");
        buffer.append("id=\"").append(this.inputName).append("\" ");
        buffer.append("value=\"").append(selOrgIdstr).append("\" />");

        buffer.append("\r\n");

        buffer.append("<script>");
        buffer.append("function openSelect").append(identity).append(
                "Tree(){");
        buffer.append(this.inputName).append("flag=window.open(\"");
        buffer.append(httpRequest.getContextPath()).append(
                "/sm/dialog.do?act=orgsTree");
        
        buffer.append("&orgType=").append(aOrgType.longValue());
        buffer.append("&inputName=").append(this.inputName);
        if (StringUtils.isNotEmpty(method )) {
            buffer.append("&method=").append(this.method);
        }
        if(this.orgId!=null){
        buffer.append("&orgId=").append(this.orgId);
        }
        buffer.append("&selOrgId=\"+document.getElementById('").append(
                this.inputName).append("').value,");
        buffer.append("\"tree").append(identity).append("\",");
        buffer
                .append("'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,");
        buffer.append("resizable=no,location=no, status=yes');");
        buffer.append(identity).append("flag.focus();}");
        buffer.append("</script>");

        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        return SKIP_BODY;
    }

    /**
     * @return the inputName
     */
    public String getInputName() {
        return inputName;
    }

    /**
     * @param inputName
     *            the inputName to set
     */
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method
     *            the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     *            the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the selOrgId
     */
    public String getSelOrgId() {
        return selOrgId;
    }

    /**
     * @param selOrgId
     *            the selOrgId to set
     */
    public void setSelOrgId(String selOrgId) {
        this.selOrgId = selOrgId;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

}
