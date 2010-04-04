package com.ft.webui;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

public class FormTableTag extends TemplateTagSupport {

    private static final long serialVersionUID = 1L;

    private String width = "95%";

    private String styleClass = "table-bg";

    private String cellspacing = "1";

    private String cellpadding = "2";
    
    private String visible = "on"; //  «∑Òœ‘ ætable

    public int doEndTag() throws JspException {
	if (!this.visible.toLowerCase().equals("on"))
	    return EVAL_PAGE;

	return super.doEndTag();
    }

    public int doStartTag() throws JspException {
	if (!this.visible.toLowerCase().equals("on"))
	    return EVAL_BODY_INCLUDE;
	
	return super.doStartTag();
    }
    
    public String getCellpadding() {
	return cellpadding;
    }

    public void setCellpadding(String cellpadding) {
	this.cellpadding = cellpadding;
    }

    public String getCellspacing() {
	return cellspacing;
    }

    public void setCellspacing(String cellspacing) {
	this.cellspacing = cellspacing;
    }

    public String getStyleClass() {
	return styleClass;
    }

    public void setStyleClass(String styleClass) {
	this.styleClass = styleClass;
    }

    public String getWidth() {
	return width;
    }

    public void setWidth(String width) {
	Evaluator Evalutator = new Evaluator();
	try {
	    this.width = (String) Evalutator.evaluate("width", width,
		    String.class, this, pageContext);
	} catch (JspException e) {
	    throw new RuntimeException(e);
	}
    }

    public String getVarName() {
	return "formTable";
    }

    /**
     * @return the visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }
}
