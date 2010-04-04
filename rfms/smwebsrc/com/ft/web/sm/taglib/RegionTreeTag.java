package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

/**
 * 区域树的Tag
 * 
 * @version 1.0
 */
public class RegionTreeTag extends TagSupport {
    private static final long serialVersionUID = 450663963371697351L;

    // 区域ID
    private Long regionId;

    // 显示级别
    private Long level;

    // 样式
    private Long style;

    // 返回值
    private String inputName;

    public int doStartTag() throws JspException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"").append("text").append("\" ");
        buffer.append("size=\"").append("15").append("\" ");
        buffer.append("readonly=\"").append("true").append("\" ");
        buffer.append("value=\"").append("\" ");
        buffer.append("id=\"").append("regionName").append("\" />");

        HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
                .getRequest();
        // buffer.append("<img src='<c:url value=\"/images/icon_sel.gif\"/>' ");
        buffer.append("<img src=\"").append(httpRequest.getContextPath())
                .append("/images/icon_sel.gif").append("\" ");
        buffer.append("onclick =\"").append("openTree()").append("\" />");
        buffer.append("<input type=\"").append("hidden").append("\" ");
        buffer.append("name=\"").append(this.inputName).append("\" ");
        buffer.append("value=\"").append("\" />");

        buffer.append("\r\n");

        buffer.append("<script>");
        buffer.append("function openTree(){");
        buffer.append("flag=window.open(\"");
        buffer.append(httpRequest.getContextPath()).append(
                "/sm/dialog.do?act=regionTree&level=").append(this.level);
        buffer.append("&style=").append(this.style).append("&regionId=")
                .append(regionId).append("&inputName=").append(this.inputName);
        buffer.append("\",");
        buffer.append("\"tree\",");
        buffer
                .append("'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,");
        buffer.append("resizable=no,location=no, status=yes');");
        buffer.append("flag.focus();}");
        buffer.append("</script>");

        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        return SKIP_BODY;
    }

    /**
     * @return Returns the level.
     */
    public Long getLevel() {
        return level;
    }

    /**
     * @param level
     *                The level to set.
     */
    public void setLevel(Long level) {
        this.level = level;
    }

    /**
     * @return Returns the regionId.
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * @param regionId
     *                The regionId to set.
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * @return Returns the style.
     */
    public Long getStyle() {
        return style;
    }

    /**
     * @param style
     *                The style to set.
     */
    public void setStyle(Long style) {
        this.style = style;
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
}
