package com.ft.webui.model;

import java.io.Serializable;

public class ButtonItem extends InputModel implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String icon;
    protected String title;
    protected String styleClass;
    protected String styleId;
    protected String style;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml() {
        StringBuffer buffer = new StringBuffer("<input ");

        if (type != null) {
            buffer.append("type=\"").append(this.type).append("\" ");
        } else {
            buffer.append("type=\"\"");
        }

        if (name != null) {
            buffer.append("name=\"").append(this.name).append("\" ");
        }

        if (value != null) {
            buffer.append("value=\"").append(this.value).append("\" ");
        }

        if (onclick != null) {
            buffer.append("onclick=\"").append(this.onclick).append("\" ");
        }

        if (styleClass != null) {
            buffer.append("class=\"").append(this.styleClass).append("\" ");
        }

        if (styleId != null) {
            buffer.append("id=\"").append(this.styleId).append("\" ");
        }

        if (style != null) {
            buffer.append("style=\"").append(this.styleId).append("\" ");
        }

        buffer.append("/>");

        return buffer.toString();
    }
}
