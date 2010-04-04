package com.ft.web.sm.taglib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.RegionManager;
import com.ft.sm.dto.RegionDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * Class comments.
 * 
 * @version 1.0
 */
public class SelectOrgRegionTag extends TagSupport{
    private static Log logger = LogFactory.getLog(SelectOrgRegionTag.class);
    
    private static final long serialVersionUID = -8678721677066030210L;
    // ·µ»ØÖµ
    private String inputName;
    private String orgId;
    private String size = "25";
    private String regionId;
    private String separator = "/";

    public int doStartTag() throws JspException {
        String regionName = "";
        
        String hiddenValue = "";
        
        try{
            if (regionId != null) {
                Evaluator aEvaluator = new Evaluator();
                Long aRegionId = (Long) aEvaluator.evaluate("regionId", this.regionId,
                        Long.class, this, pageContext);
                
                if(aRegionId.longValue() > 0){
                    RegionManager regionManager = (RegionManager)SpringWebUtils.getBean(pageContext.getServletContext(),"regionManager");
                  //  RegionDTO region = regionManager.findRegionById(aRegionId);
                    
                    List result = regionManager.findRegionLocation(aRegionId);
                    
                    StringBuffer buf = new StringBuffer();
                    for (int i = 0; i < result.size(); i++) {
                        RegionDTO region = (RegionDTO) result.get(i);
                        if(i==0){
                            buf.append(region.getRegionName());
                        }else{
                            buf.append(this.separator);
                            buf.append(region.getRegionName());
                        }
                    }
                    
                    regionName = buf.toString();
                    hiddenValue = String.valueOf(aRegionId);
                }
            }
        }catch(Exception ex){
            logger.error("Not found region,region id=" + regionId,ex);
        }
        
        String identity = inputName.replace('.', '_');
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"text\" ");
        buffer.append("size=\"").append(size).append("\" ");
        buffer.append("readonly=\"true\" ");
        buffer.append("id=\"").append("select_").append(this.inputName).append(
                "\" ");
        buffer.append("value=\"").append(regionName).append("\" />");

        HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                .getRequest();
        buffer.append("<img src=\"").append(httpRequest.getContextPath())
                .append("/images/icon_sel.gif").append("\" ");
        buffer.append("onclick =\"").append("openSelect")
                .append(identity).append("Tree()").append("\" />");
        buffer.append("<input type=\"hidden\" ");
        buffer.append("name=\"").append(this.inputName).append("\" ");
        buffer.append("id=\"").append(this.inputName).append("\" ");
        buffer.append("value=\"").append(hiddenValue).append("\" />");

        buffer.append("\r\n");

        buffer.append("<script>");
        buffer.append("function openSelect").append(identity).append(
                "Tree(){");
        buffer.append(identity).append("flag=window.open(\"");
        buffer.append(httpRequest.getContextPath()).append(
                "/sm/selRegion.do");
        buffer.append("?orgId=").append(this.orgId);
        buffer.append("&inputName=").append(this.inputName);
        buffer.append("&regionId=\"+document.getElementById('").append(
                this.inputName).append("').value,");
        buffer.append("\"tree").append(identity).append("\",");
        buffer
                .append("'height=500,width=600,top=100,left=400,toolbar=no,menubar=no,scrollbars=yes,");
        buffer.append("resizable=yes,location=no, status=yes');");
        buffer.append(identity).append("flag.focus();}");
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
        Evaluator Evalutator = new Evaluator();
        try {
            this.inputName  = (String) Evalutator.evaluate("inputName",inputName,String.class,this,pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
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

    /**
     * @return the regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId(String orgId) {
        Evaluator Evalutator = new Evaluator();
        try {
            this.orgId  = (String) Evalutator.evaluate("orgId",orgId,String.class,this,pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the separator
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    
}
