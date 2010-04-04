package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 业务权限单选标签。
 * 
 * @version 1.0
 */
public class SelectDataResourceTag extends TagSupport{
    private static final long serialVersionUID = 6144411336984096681L;

    private int size = 25;

    private String inputName;
    
    private String selEntryId;

    public int doStartTag() throws JspException {      
        String selEntryTitle = "";
        String selEntryIdStr = "";
        if (StringUtils.isNotEmpty(selEntryId )) {
            Evaluator aEvaluator = new Evaluator();
            Long selId = (Long) aEvaluator.evaluate("selEntryId", this.selEntryId,
                    Long.class, this, pageContext);
            
            if (selId.longValue() != 0) {
                ResourceManager resManager = (ResourceManager) SpringWebUtils
                        .getBean(pageContext.getServletContext(),
                                "resourceManager");
                DataResourceEntryDTO entry;
                try {
                    entry = resManager.findDataResourceEntryById(selId);
                    if (entry != null) {
                        selEntryTitle = entry.getTitle();
                        selEntryIdStr = entry.getEntryId().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"text\" ");
        buffer.append("size=\"").append(size).append("\" ");
        buffer.append("readonly=\"true\" ");
        buffer.append("id=\"").append("select_").append(this.inputName).append(
                "\" ");
        buffer.append("value=\"").append(selEntryTitle).append("\" />");

        HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                .getRequest();
        buffer.append("<img src=\"").append(httpRequest.getContextPath())
                .append("/images/icon_sel.gif").append("\" ");
        
        buffer.append("onclick =\"").append("openSelect")
                .append(this.inputName).append("Tree()").append("\" />");
        buffer.append("<input type=\"hidden\" ");
        buffer.append("name=\"").append(this.inputName).append("\" ");
        buffer.append("id=\"").append(this.inputName).append("\" ");
        buffer.append("value=\"").append(selEntryIdStr).append("\" />");

        buffer.append("\r\n");
        
        buffer.append("<script>");
        buffer.append("function openSelect").append(this.inputName).append(
                "Tree(){");
        buffer.append(this.inputName).append("flag=window.open(\"");
        buffer.append(httpRequest.getContextPath()).append(
                "/sm/dialog.do?act=dataResourceTree");
        
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
     * @return the selEntryId
     */
    public String getSelEntryId() {
        return selEntryId;
    }

    /**
     * @param selEntryId the selEntryId to set
     */
    public void setSelEntryId(String selEntryId) {
        this.selEntryId = selEntryId;
    }
}
