package com.ft.webui;

import com.ft.webui.model.ButtonItem;

import org.apache.struts.taglib.TagUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * °´Å¥µÄTag
 * @jsp.tag name="button" display-name="ButtonTag" body-content="empty"
 *
 */
public class ButtonTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private ButtonItem button;

    public void release() {
        super.release();
        button = null;
    }

    /*
     *  (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        PanelTag panelTag =
            (PanelTag) findAncestorWithClass(this, PanelTag.class);

        if (panelTag != null) {
            panelTag.addButton(this.button);
        } else {
            TagUtils.getInstance()
                    .write(this.pageContext, this.button.getHtml());
        }

        return super.doEndTag();
    }

    /*
     *  (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /**
     * @jsp.attribute description="display button icon"
     *                required="false" rtexprvalue="true"
     */
    public void setIcon(String icon) {
        button.setIcon(icon);
    }

    /**
     * @jsp.attribute description="like html input attribute 'name'"
     *                required="false" rtexprvalue="true"
     */
    public void setName(String name) {
        button.setName(name);
    }

    /**
     * @jsp.attribute description="like html input attribute 'onclick'"
     *                required="false" rtexprvalue="true"
     */
    public void setOnclick(String onclick) {
        button.setOnclick(onclick);
    }

    /**
     * @jsp.attribute description="like html input attribute 'style'"
     *                required="false" rtexprvalue="true"
     */
    public void setStyle(String style) {
        button.setStyle(style);
    }

    /**
    * @jsp.attribute description="like html input attribute 'class'"
    *                required="false" rtexprvalue="true"
    */
    public void setStyleClass(String styleClass) {
        button.setStyleClass(styleClass);
    }

    /**
    * @jsp.attribute description="like html input attribute 'id'"
    *                required="false" rtexprvalue="true"
    */
    public void setStyleId(String styleId) {
        button.setStyleId(styleId);
    }

    /**
    * @jsp.attribute description="display button text eg) save ,update"
    *                required="false" rtexprvalue="true"
    */
    public void setTitle(String title) {
        button.setTitle(title);
    }

    /**
    * @jsp.attribute description="like submit ,reset "
    *                required="false" rtexprvalue="true"
     * @param type
     */
    public void setType(String type) {
        button.setType(type);
    }

    /**
    * @jsp.attribute description="button bind value"
    *                required="false" rtexprvalue="true"
     * @param type
     */
    public void setValue(String value) throws JspException {
        if (value != null) {
            String result =
                TagUtils.getInstance().message(
                    pageContext, null, null, value);

            if (result == null) {
                button.setValue(value);
            } else {
                if (result.indexOf("???") == 0) {
                    button.setValue(value);
                } else {
                    button.setValue(result);
                }
            }
        } else {
            button.setValue(value);
        }
    }

    public void setPageContext(PageContext arg0) {
        this.button = new ButtonItem();
        super.setPageContext(arg0);
    }
}
