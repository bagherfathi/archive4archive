package com.ft.webui;

import org.apache.commons.lang.StringUtils;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import javax.servlet.jsp.JspException;


/**
 * @jsp.tag name="LabeledInput"
 * display-name="LabeledInputTag" body-content="empty"
 * @author Coffee
 *
 */
public class LabeledInputTag extends TemplateTagSupport {
    private static final long serialVersionUID = 1L;
    private String label;
    private String width;
    private String required;
    private String classId;
    private String classStyle;
    private String style;
    private String bundle;
    private String key;
    private String locale;
    private int colspan;

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return Returns the label.
     */
    public String getLabel() {
        String value = "";

        if (this.key != null) {
            try {
                return TagUtils.getInstance()
                               .message(
                    pageContext, this.bundle, this.locale, this.key);
            } catch (JspException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotEmpty(this.label)) {
            try {
                value = TagUtils.getInstance()
                                .message(
                        pageContext, this.bundle, this.locale, this.label);

                if (value == null) {
                    value = this.label;
                } else if (value.indexOf("???") == 0) {
                    value = this.label;
                }
            } catch (JspException e) {
                e.printStackTrace();
            }
        } else {
            return "";
        }

        return value;
    }

    /**
     * @param label
     *            The label to set.
     */
    public void setLabel(String label) {
        Evaluator Evalutator = new Evaluator();
        try {
            this.label  = (String) Evalutator.evaluate("lable",label,String.class,this,pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Returns the width.
     */
    public String getWidth() {
        return width;
    }

    /**
     * @param width
     *            The width to set.
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * @return Returns the required.
     */
    public String getRequired() {
        return required;
    }

    /**
     * @param required
     *            The required to set.
     */
    public void setRequired(String required) {
        this.required = required;
    }

    /**
     * @return Returns the classId.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId
     *            The classId to set.
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * @return Returns the classStyle.
     */
    public String getClassStyle() {
        return classStyle;
    }

    /**
     * @param classStyle
     *            The classStyle to set.
     */
    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    /**
     * @return Returns the style.
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style
     *            The style to set.
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
        return "webui";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public int doEndTag() throws JspException {
        this.colspan = 0;

        return super.doEndTag();
    }
}
