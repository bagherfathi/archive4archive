package com.ft.webui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.webui.model.ButtonItem;

/**
 * @jsp.tag name="panel" display-name="PanelTag" body-content="JSP"
 * 
 */
public class PanelTag extends TemplateTagSupport {
    private static final long serialVersionUID = 1L;

    private String icon;

    private String title;

    private String url;

    private String width;

    private String classId;

    private String style;

    private String bundle;

    private String key;

    private String background;

    private String bodyBackground;

    private String locale;

    private List buttons = new ArrayList();

    private String valign;

    private String align;

    private String onclick;

    private String visible = "on"; //  «∑Òœ‘ æpanel

    /**
     * @return Returns the onclick.
     * @throws JspException
     */
    public String getOnclick() {
	return onclick;
    }

    /**
     * @jsp.attribute description="onclick" required="false" rtexprvalue="true"
     * @param onclick
     *                The onclick to set.
     */
    public void setOnclick(String onclick) {
	this.onclick = onclick;
    }

    public String getUrl() {
	return url;
    }

    /**
     * @jsp.attribute description="url" required="false" rtexprvalue="true"
     * @param onclick
     *                The onclick to set.
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @return Returns the icon.
     */
    public String getIcon() {
	if (icon == null) {
	    return icon;
	}
	if (icon.startsWith("/")) {
	    HttpServletRequest request = (HttpServletRequest) this.pageContext
		    .getRequest();
	    return request.getContextPath() + icon;
	} else {
	    return icon;
	}

    }

    /**
     * @jsp.attribute description="icon" required="false" rtexprvalue="true"
     * @param icon
     *                The icon to set.
     */
    public void setIcon(String icon) {
	this.icon = icon;
    }

    /**
     * @return Returns the title.
     * @throws JspException
     */
    public String getTitle() throws JspException {
	String value = "";

	if (title != null) {
	    /*
	     * try{ value =
	     * WebUIContextImpl.getWebUIContext().getMessage(pageContext,
	     * this.bundle, this.locale, this.title); }catch(Exception e){
	     * return this.title ; }
	     */
	    try {
		value = TagUtils.getInstance().message(pageContext,
			this.bundle, this.locale, this.title);
	    } catch (Exception e) {

	    }
	    if (value == null) {
		return this.title;
	    } else {
		if (value.indexOf("???") == 0) {
		    return this.title;
		} else {
		    return value;
		}
	    }
	} else {
	    return null;
	    // TagUtils.getInstance()
	    // .message(
	    // pageContext, this.bundle, this.locale, this.key);
	}
    }

    /**
     * @jsp.attribute description="title" required="false" rtexprvalue="true"
     * @param title
     *                The title to set.
     */
    public void setTitle(String title) {

	Evaluator Evalutator = new Evaluator();
	try {
	    this.title = (String) Evalutator.evaluate("title", title,
		    String.class, this, pageContext);
	} catch (JspException e) {
	    throw new RuntimeException(e);
	}

	// if(title.startsWith("${") && title.endsWith("}")){
	// this.title = (String)ExpressionEvaluator.eval(
	// title,
	// new VariableResolver() {
	// public Object resolveVariable(String arg0)
	// throws ELException {
	// return pageContext.getAttribute(arg0);
	// }
	// });
	//            
	// }else{
	// this.title = title;
	// }
	// this.title = title;
    }

    /**
     * @return Returns the align.
     */
    public String getAlign() {
	return align;
    }

    /**
     * @jsp.attribute description="align" required="false" rtexprvalue="true"
     * @param align
     *                The align to set.
     */
    public void setAlign(String align) {
	this.align = align;
    }

    /**
     * @return Returns the valign.
     */
    public String getValign() {
	return valign;
    }

    /**
     * @jsp.attribute description="valign" required="false" rtexprvalue="true"
     * @param valign
     *                The valign to set.
     */
    public void setValign(String valign) {
	this.valign = valign;
    }

    /**
     * @return Returns the width.
     */
    public String getWidth() {
	return width;
    }

    /**
     * @jsp.attribute description="width" required="false" rtexprvalue="true"
     * @param width
     *                The width to set.
     */
    public void setWidth(String width) {
	this.width = width;
    }

    public void clean() {
	this.align = null;
	this.icon = null;
	this.title = null;
	this.url = null;
	this.width = null;
	this.valign = null;
	this.bundle = null;
	this.key = null;
	this.buttons.clear();
    }

    public void release() {
	super.release();
	this.clean();
    }

    public String getlassId() {
	return classId;
    }

    /**
     * @jsp.attribute description="classId" required="false" rtexprvalue="true"
     * @param width
     *                The classId to set.
     */
    public void setClassId(String classId) {
	this.classId = classId;
    }

    public String getStyle() {
	return style;
    }

    /**
     * @jsp.attribute description="style" required="false" rtexprvalue="true"
     * @param width
     *                The classId to set.
     */
    public void setStyle(String style) {
	this.style = style;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.media.web.tag.VMTagSupport#getVarName()
     */
    public String getVarName() {
	return "panel";
    }

    public String getBundle() {
	return bundle;
    }

    public String getKey() {
	return key;
    }

    /**
     * @jsp.attribute description="bundle" required="false" rtexprvalue="true"
     * @param width
     *                The bundle to set.
     */
    public void setBundle(String bundle) {
	this.bundle = bundle;
    }

    /**
     * @jsp.attribute description="key" required="false" rtexprvalue="true"
     * @param width
     *                The bundle to set.
     */
    public void setKey(String key) {
	this.key = key;
    }

    public String getLocale() {
	return locale;
    }

    /**
     * @jsp.attribute description="locale" required="false" rtexprvalue="true"
     * @param width
     *                The bundle to set.
     */
    public void setLocale(String locale) {
	this.locale = locale;
    }

    public void addButton(ButtonItem item) {
	this.buttons.add(item);
    }

    public List getButtons() {
	return buttons;
    }

    public int doEndTag() throws JspException {
	// added by libf,2006/11/2
	if (!this.visible.toLowerCase().equals("on")){
	    this.clean();
	    return EVAL_PAGE;
	}
	// end added

	int result = super.doEndTag();
	this.clean();

	return result;
    }

    public int doStartTag() throws JspException {
	// added by libf,2006/11/2
	if (!this.visible.toLowerCase().equals("on"))
	    return EVAL_BODY_INCLUDE;
	// end added

	if (width == null) {
	    width = "100%";
	}

	return super.doStartTag();
    }

    public String getBackground() {
	return background;
    }

    /**
     * @jsp.attribute description="background" required="false"
     *                rtexprvalue="true"
     * @param width
     *                The bundle to set.
     */
    public void setBackground(String background) {
	this.background = background;
    }

    public String getBodyBackground() {
	return bodyBackground;
    }

    /**
     * @jsp.attribute description="bodyBackground" required="false"
     *                rtexprvalue="true"
     * @param width
     *                The bundle to set.
     */
    public void setBodyBackground(String bodyBackground) {
	this.bodyBackground = bodyBackground;
    }

    /**
     * @return the visible
     */
    public String getVisible() {
	return visible;
    }

    /**
     * @param visible
     *                the visible to set
     */
    public void setVisible(String visible) {
	Evaluator Evalutator = new Evaluator();
	try {
	    this.visible = (String) Evalutator.evaluate("visible", visible,
		    String.class, this, pageContext);
	} catch (JspException e) {
	    throw new RuntimeException(e);
	}
    }
}
