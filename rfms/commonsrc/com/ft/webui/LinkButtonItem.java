package com.ft.webui;

import org.apache.commons.lang.StringUtils;

import com.ft.webui.model.ButtonItem;

public class LinkButtonItem extends ButtonItem {



    private String href;
    private String onClick;
    private String target;
    private String value;
    private String id;

    /**
     * @return Returns the href.
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href The href to set.
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return Returns the onClick.
     */
    public String getOnClick() {
        return onClick;
    }

    /**
     * @param onClick The onClick to set.
     */
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getHtml() {
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.isNotEmpty(styleClass)) {
            buffer.append("<span class=\"" + styleClass + "\">");
        }
        buffer.append("<a href=\"" + href + "\"");
        if (StringUtils.isNotEmpty(onClick)) {
            buffer.append(" onClick=\"" + onClick + "\"");
        }
        if (StringUtils.isNotEmpty(target)) {
            buffer.append(" target=\"" + target + "\"");
        }
        
        //modified by libf,2006/11/23
        /*
        buffer.append(">" + value + "</a>");
        if (StringUtils.isNotEmpty(styleClass)) {
            buffer.append("</span>");
        }*/
        buffer.append(">");
        
        if(StringUtils.isNotEmpty(id)){
            buffer.append("<span id=\"").append(id).append("\">");
            buffer.append(value).append("</span>");
        }else{
            buffer.append(value);
        }
        
        buffer.append("</a>");
        
        if (StringUtils.isNotEmpty(styleClass)) {
            buffer.append("</span>");
        }
        //end modified
        
        return buffer.toString();
    }
}
