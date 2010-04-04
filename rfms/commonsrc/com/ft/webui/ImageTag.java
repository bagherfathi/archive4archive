package com.ft.webui;

import com.ft.hibernate.taglib.PageContextUtils;

import org.apache.struts.taglib.TagUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="img" display-name="ImageTag" body-content="empty"
 *
 */
public class ImageTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String src;
    private String width;
    private String height;
    private String border;
    private String onclick;
    private String name;
    private String styleClass;
    private String valign;
    private String align;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int doStartTag() throws JspException {
        String contextPath = PageContextUtils.getContextPath(pageContext);
        StringBuffer buffer = new StringBuffer();
        buffer.append("<img src=\"").append(contextPath).append(src)
              .append("\"");

        if (this.valign != null) {
            buffer.append(" valign=\"").append(valign).append("\"");
        }

        if (this.align != null) {
            buffer.append(" align=\"").append(align).append("\"");
        }

        if (this.styleClass != null) {
            buffer.append(" class=\"").append(styleClass).append("\"");
        }

        if (this.name != null) {
            buffer.append(" name=\"").append(name).append("\"");
        }

        if (this.width != null) {
            buffer.append(" width=\"").append(width).append("\"");
        }

        if (this.height != null) {
            buffer.append(" height=\"").append(height).append("\"");
        }

        if (this.border != null) {
            buffer.append(" border=\"").append(border).append("\"");
        }

        if (this.onclick != null) {
            buffer.append(" onclick=\"").append(onclick).append("\"");
        }

        buffer.append("/>");
        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        interlRelease();

        return super.doStartTag();
    }

    public void interlRelease() {
        this.src = null;
        this.width = null;
        this.height = null;
        this.border = null;
        this.onclick = null;
        this.name = null;
        this.styleClass = null;
        this.align = null;
    }

    public void release() {
        interlRelease();
        super.release();
    }

    public String getBorder() {
        return border;
    }

    /**
    * @jsp.attribute description=" html img border attribute"
    *    required="false" rtexprvalue="true"
     * @param border
     */
    public void setBorder(String border) {
        this.border = border;
    }

    public String getHeight() {
        return height;
    }

    /**
    * @jsp.attribute description=" html img hei attribute"
    *    required="false" rtexprvalue="true"
     * @param border
     */
    public void setHeight(String height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    /**
    * @jsp.attribute description=" html img src attribute"
    *    required="false" rtexprvalue="true"
     * @param border
     */
    public void setSrc(String src) {
        this.src = src;
    }

    public String getWidth() {
        return width;
    }

    /**
    * @jsp.attribute description=" html img width attribute"
    *    required="false" rtexprvalue="true"
     * @param border
     */
    public void setWidth(String width) {
        this.width = width;
    }

    public String getOnclick() {
        return onclick;
    }

    /**
    * @jsp.attribute description=" html img onclick attribute"
    *    required="false" rtexprvalue="true"
     * @param onclick
     */
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    /**
     *
     * @return Returns the clazz.
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @jsp.attribute description=" html img class attribute"
     *    required="false" rtexprvalue="true"
     * @param clazz The clazz to set.
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return Returns the valign.
     */
    public String getValign() {
        return valign;
    }

    /**
     * @jsp.attribute description=" html img class valign"
     *    required="false" rtexprvalue="true"
     * @param valign The valign to set.
     */
    public void setValign(String valign) {
        this.valign = valign;
    }

    /**
     * @return Returns the align.
     */
    public String getAlign() {
        return align;
    }

    /**
     * @jsp.attribute description=" html img class align"
     *    required="false" rtexprvalue="true"
     * @param align The align to set.
     */
    public void setAlign(String align) {
        this.align = align;
    }
}
