/**
 * 
 */
package com.ft.webui.table;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

/**
 * @author solar
 */
public class ColumnTag extends org.extremecomponents.table.tag.ColumnTag {

	/**
	 * 
	 */
	public ColumnTag() {
		// TODO Auto-generated constructor stub
	}
    private String title;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int doEndTag() throws JspException {
		String temp="";
		try {
            temp = TagUtils.getInstance()
                            .message(
                    pageContext, null, null, this.title);

            if (temp == null) {
            	temp = this.title;
            } else if (temp.indexOf("???") == 0) {
            	temp = this.title;
            }
        } catch (JspException e) {
            e.printStackTrace();
        }
        super.setTitle(temp);
		return super.doEndTag();
	}

}
