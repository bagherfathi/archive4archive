package com.ft.webui;

import org.apache.struts.taglib.TagUtils;

import javax.servlet.jsp.JspException;


/**
 * @jsp.tag name="titleBar" display-name="TitleBarTag" body-content="JSP"
 *
 */
public class TitleBarTag extends TemplateTagSupport {
    private static final long serialVersionUID = 1L;
    private String name = "titleBar";
    private String icon;
    private String title = "";
    private String bundle;
    private String key;
    private String locale;
    private String background;
    private String width;
    private String moreicon;
    private String moreURL;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMoreicon() {
        return moreicon;
    }

    public void setMoreicon(String moreicon) {
        this.moreicon = moreicon;
    }

    /**
     * @return Returns the icon.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon to set.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the title.
     * @throws JspException
     */
    public String getTitle() throws JspException {
        if (title != null) {
            try {
                String value =
                    TagUtils.getInstance()
                            .message(
                        pageContext, this.bundle, this.locale, this.title);

                if (value == null) {
                    return this.title;
                } else {
                    if (value.indexOf("???") == 0) {
                        return this.title;
                    } else {
                        return value;
                    }
                }
            } catch (Exception e) {
                return title;
            }
        } else {
            return TagUtils.getInstance()
                           .message(
                pageContext, this.bundle, this.locale, this.key);
        }
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /* (non-Javadoc)
     * @see com.onewaveinc.media.web.tag.VMTagSupport#getVarName()
     */
    public String getVarName() {
        return "bar";
    }

    public String getBundle() {
        return bundle;
    }

    public String getKey() {
        return key;
    }

    public String getLocale() {
        return locale;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMoreURL() {
        return moreURL;
    }

    public void setMoreURL(String moreURL) {
        this.moreURL = moreURL;
    }

    public int doStartTag() throws JspException {
        if (this.width == null) {
            this.width = "100%";
        }

        return super.doStartTag();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
