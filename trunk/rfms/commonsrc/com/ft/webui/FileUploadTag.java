package com.ft.webui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.TextTag;

/**
 * @author soler
 * 
 */
public class FileUploadTag extends TextTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8830293214577303359L;

	private String formName;
	
	
	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}


	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}


	public int doEndTag() throws JspException {

		super.doEndTag();
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		String contextPath = request.getContextPath();
		StringBuffer upload = new StringBuffer("<img src=\"" + contextPath
				+ "/images/upimg.png\" onclick=\"uploadface();\"/>");
		upload.append("<script>");
		upload.append("function uploadface(){");
		upload.append("var re=window.showModalDialog('").append(contextPath);
		upload
				.append("/cms/upfiles.do','文件上传','dialogHeight:150px;dialogWidth:350px;dialogLeft:400;dialogTop:200;center:yes');");
		upload.append("if(re!=undefined){");
		upload.append("document."+this.formName+"[\""+this.property+"\"].value=re;");
		upload.append("}");
		upload.append("}");
		upload.append("</script>");
		TagUtils.getInstance().write(super.pageContext, upload.toString());
		return 6;
	}

}
