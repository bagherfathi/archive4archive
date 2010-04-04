package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

/**
 * 按钮样式的链接
 * 
 * @jsp.tag name="linkButton" display-name="LinkButtonTag" body-content="empty"
 * 
 */
public class LinkButtonTag extends TagSupport {

    private static final long serialVersionUID = 4099223878177145830L;

    private LinkButtonItem linkButtonItem ;
    
    private boolean standOnly =false;

    public int doStartTag() throws JspException {
       return super.doStartTag();
    }

    /*
     *  (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        PanelTag panelTag =
            (PanelTag) findAncestorWithClass(this, PanelTag.class);

        if (panelTag != null && !standOnly) {
            panelTag.addButton(this.linkButtonItem);
        } else {
            TagUtils.getInstance()
                    .write(this.pageContext, this.linkButtonItem.getHtml());
        }
        return super.doEndTag();
    }

    /**
     * @jsp.attribute description="like html a content" required="true"
     *                rtexprvalue="true"
     * @param value
     * @throws JspException 
     */
    public void setValue(String value) throws JspException {
        if (value != null) {
            String result =
                TagUtils.getInstance().message(
                    pageContext, null, null, value);

            if (result == null) {
                linkButtonItem.setValue(value);
            } else {
                if (result.indexOf("???") == 0) {
                    linkButtonItem.setValue(value);
                } else {
                    linkButtonItem.setValue(result);
                }
            }
        } else {
            linkButtonItem.setValue(value);
        } 
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#getHref()
     */
    public String getHref() {
        return linkButtonItem.getHref();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#getIcon()
     */
    public String getIcon() {
        return linkButtonItem.getIcon();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#getName()
     */
    public String getName() {
        return linkButtonItem.getName();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#getOnchange()
     */
    public String getOnchange() {
        return linkButtonItem.getOnchange();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#getOnclick()
     */
    public String getOnclick() {
        return linkButtonItem.getOnclick();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#getOnClick()
     */
    public String getOnClick() {
        return linkButtonItem.getOnClick();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#getStyle()
     */
    public String getStyle() {
        return linkButtonItem.getStyle();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#getStyleClass()
     */
    public String getStyleClass() {
        return linkButtonItem.getStyleClass();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#getStyleId()
     */
    public String getStyleId() {
        return linkButtonItem.getStyleId();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#getTarget()
     */
    public String getTarget() {
        return linkButtonItem.getTarget();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#getTitle()
     */
    public String getTitle() {
        return linkButtonItem.getTitle();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#getType()
     */
    public String getType() {
        return linkButtonItem.getType();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#getValue()
     */
    public String getValue() {
        return linkButtonItem.getValue();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return linkButtonItem.hashCode();
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#setIcon(java.lang.String)
     */
    public void setIcon(String icon) {
        linkButtonItem.setIcon(icon);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#setHref(java.lang.String)
     */
    public void setHref(String href) {
        linkButtonItem.setHref(href);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#setStyleClass(java.lang.String)
     */
    public void setStyleClass(String styleClass) {
        linkButtonItem.setStyleClass(styleClass);
    }
    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#setName(java.lang.String)
     */
    public void setName(String name) {
        linkButtonItem.setName(name);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#setOnchange(java.lang.String)
     */
    public void setOnchange(String onchange) {
        linkButtonItem.setOnchange(onchange);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#setOnclick(java.lang.String)
     */
    public void setOnclick(String onclick) {
        linkButtonItem.setOnclick(onclick);
    }
    
    public void setOnClick(String onclick) {
        linkButtonItem.setOnClick(onclick);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#setStyle(java.lang.String)
     */
    public void setStyle(String style) {
        linkButtonItem.setStyle(style);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#setStyleId(java.lang.String)
     */
    public void setStyleId(String styleId) {
        linkButtonItem.setStyleId(styleId);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.LinkButtonItem#setTarget(java.lang.String)
     */
    public void setTarget(String target) {
        linkButtonItem.setTarget(target);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.ButtonItem#setTitle(java.lang.String)
     */
    public void setTitle(String title) {
        linkButtonItem.setTitle(title);
    }

    /* (non-Javadoc)
     * @see com.ft.webui.model.InputModel#setType(java.lang.String)
     */
    public void setType(String type) {
        linkButtonItem.setType(type);
    }
    
    public void setPageContext(PageContext arg0) {
        this.linkButtonItem = new LinkButtonItem();
        super.setPageContext(arg0);
    }

    /**
     * @return Returns the standOnly.
     */
    public boolean isStandOnly() {
        return standOnly;
    }

    /**
     * @param standOnly The standOnly to set.
     */
    public void setStandOnly(boolean standOnly) {
        this.standOnly = standOnly;
    }
    
    public void setId(String id){
	this.linkButtonItem.setId(id);
    }
    
    public String getId(){
	return this.linkButtonItem.getId();
    }
   
}
