package com.ft.webui;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * ÈÕÀúµÄTag
 * @jsp.tag name="calendar" display-name="calendarTag" body-content="empty"
 *
 */
public class CalendarInputTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String property;
    private String onclick;
    private String img = "/images/look_day.gif";
    private String value;
    private String readonly;
    private String id;
    private String format ="yyyy-MM-dd";
    protected String name = Constants.BEAN_KEY;
    private int size = 25;
    private String onpropertychange;
    private boolean defaultToday;
    
 

	/**
	 * @return the defaultToday
	 */
	public boolean isDefaultToday() {
		return defaultToday;
	}

	/**
	 * @param defaultToday the defaultToday to set
	 */
	public void setDefaultToday(boolean defaultToday) {
		this.defaultToday = defaultToday;
	}

	/*
     *  (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
    	
         HttpServletRequest request =
            (HttpServletRequest) this.pageContext.getRequest();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<input type=\"").append("text").append("\" ");
        buffer.append("name=\"").append(this.property).append("\" ");
        buffer.append("size=\"").append(this.size).append("\" ");
        buffer.append("id=\"").append(this.id).append("\" ");
        if(this.onpropertychange != null){
            buffer.append("onpropertychange=\"").append(onpropertychange).append("\" ");
        }
        
        //buffer.append("size=\"22\"  id=\"").append(this.id).append("\" ");
        if(this.readonly==null || !this.readonly.trim().equalsIgnoreCase("false")){
        buffer.append("readonly=\"")
              .append("true").append(
            "\" ");
        }
        String tempValue=(this.value == null) ? this.realValue() : this.value;
        
        buffer.append("value=\"")
              .append(tempValue)
              .append("\" >");

        buffer.append("<img border=\"0\" src=\"")
              .append(request.getContextPath()).append(this.img).append(
            "\" ");
        buffer.append(" align=\"absmiddle\" class=\"cur-hand\"");
        buffer.append(" onclick=\"").append(getOnClickHtml()).append("\">");
        TagUtils.getInstance().write(this.pageContext, buffer.toString());
        this.release();

        return SKIP_BODY;
    }

    private String getOnClickHtml(){
    	if(this.getOnclick()!=null){
    		return  this.onclick;
    	}else{
            if(StringUtils.isNotEmpty(this.format)){
                return "calendar("+ this.id+ ",'"+ this.format+"');return false;\"";
            }else{
                return "calendar("+ this.id+ ");return false;\"";
            }
    	}    	
    }
    
   
    public void release() {
        this.id = null;
        this.img = "/images/look_day.gif";
        this.onclick = null;
        this.property = null;
        this.value = null;
        this.readonly = "false";
        this.format ="yyyy-MM-dd";
    }

    protected String realValue() throws JspException {
        Object value = null;

        try {
            value = TagUtils.getInstance()
                            .lookup(pageContext, name, property, null);
        } catch (Exception e) {
            // e.printStackTrace() ;
        }
        if(value==null || !(value instanceof java.util.Date)){
        	if(this.isDefaultToday()){
        		value=new java.util.Date();
        	}
        }
        	
        if ((value != null) && (value instanceof java.util.Date)) {
            SimpleDateFormat simpleDataFormat =
                new SimpleDateFormat("yyyy-MM-dd");

            return simpleDataFormat.format(value);
        } else {
            return "";
        }
    }

    public String getImg() {
        return img;
    }

    /**
    * @jsp.attribute description="dipslay image"
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    public String getOnclick() {
        return onclick;
    }

    /**
    * @jsp.attribute description="onclick"
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    /**
    * @jsp.attribute description="bind from attribute"
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    /**
    * @jsp.attribute description="display value"
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
    * @jsp.attribute description="is read only "
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public String isReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    /**
    * @jsp.attribute description="classId "
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
    * @jsp.attribute description="input name"
    *                required="false" rtexprvalue="true"
     * @param img
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @jsp.attribute description="input size"
    *                required="false" rtexprvalue="true"
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the onpropertychange
     */
    public String getOnpropertychange() {
        return onpropertychange;
    }

    /**
     * @param onpropertychange the onpropertychange to set
     */
    public void setOnpropertychange(String onpropertychange) {
        this.onpropertychange = onpropertychange;
    }
}
