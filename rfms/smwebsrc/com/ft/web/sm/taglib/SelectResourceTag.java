package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.security.ResourceRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 用于选择功能权限的树标签。
 * 
 * @version 1.0
 */
public class SelectResourceTag extends TagSupport {
    private static final long serialVersionUID = -2663900736543801048L;

    private int size = 25;

    private String inputName;
    
    private String selResourceId;

    public int doStartTag() throws JspException {
        String selResourceTitle = "";
        String selResourceIdstr = "";
        if (StringUtils.isNotEmpty(selResourceId )) {
            Evaluator aEvaluator = new Evaluator();
            Long selId = (Long) aEvaluator.evaluate("selResourceId", this.selResourceId,
                    Long.class, this, pageContext);
            
            if (selId.longValue() != 0) {
                ResourceRepository resRepository = (ResourceRepository) SpringWebUtils
                        .getBean(pageContext.getServletContext(),
                                "resourceRepository");
                ResourceDTO resource = resRepository.getResource(selId);
                if (resource != null) {
                    selResourceTitle = resource.getTitle();
                    selResourceIdstr = resource.getResourceId().toString();
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"text\" ");
        buffer.append("size=\"").append(size).append("\" ");
        buffer.append("readonly=\"true\" ");
        buffer.append("id=\"").append("select_").append(this.inputName).append(
                "\" ");
        buffer.append("value=\"").append(selResourceTitle).append("\" />");

        HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                .getRequest();
        buffer.append("<img src=\"").append(httpRequest.getContextPath())
                .append("/images/icon_sel.gif").append("\" ");
        
        buffer.append("onclick =\"").append("openSelect")
                .append(this.inputName).append("Tree()").append("\" />");
        buffer.append("<input type=\"hidden\" ");
        buffer.append("name=\"").append(this.inputName).append("\" ");
        buffer.append("id=\"").append(this.inputName).append("\" ");
        buffer.append("value=\"").append(selResourceIdstr).append("\" />");

        buffer.append("\r\n");
        
        buffer.append("<script>");
        buffer.append("function openSelect").append(this.inputName).append(
                "Tree(){");
        buffer.append(this.inputName).append("flag=window.open(\"");
        buffer.append(httpRequest.getContextPath()).append(
                "/sm/dialog.do?act=resourceTree");
        
        buffer.append("&inputName=").append(this.inputName);
        buffer.append("&selResourceId=\"+document.getElementById('").append(
                this.inputName).append("').value,");
        buffer.append("\"tree").append(this.inputName).append("\",");
        buffer
                .append("'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,");
        buffer.append("resizable=no,location=no, status=yes');");
        buffer.append(this.inputName).append("flag.focus();}");
        buffer.append("</script>");

        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        return SKIP_BODY;
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
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size
     *                the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the selResourceId
     */
    public String getSelResourceId() {
        return selResourceId;
    }

    /**
     * @param selResourceId the selResourceId to set
     */
    public void setSelResourceId(String selResourceId) {
        this.selResourceId = selResourceId;
    }
}
