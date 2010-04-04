package com.ft.webui;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ScriptTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String methodName;

	private String validatorMethodName;

	public int doStartTag() throws JspException {

		JspWriter writer = pageContext.getOut();
		try {
			writer.print(this.renderJavascript());

		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}

		return EVAL_BODY_BUFFERED;

	}

	/**
	 * Constructs the beginning &lt;script&gt; element depending on XHTML
	 * status.
	 * 
	 * @since Struts 1.2
	 */
	protected String renderJavascript() {
		StringBuffer start = new StringBuffer(
				"<script language=\"Javascript1.1\"><!--");
	

		start.append(
            "    function methodName(form){\r\n");
		start.append(
			"         trimForm(form);\r\n");
		if(this.validatorMethodName!=null){
		start.append(
			"       return "+validatorMethodName+"(form);\r\n" );
		}
		start.append(
			"   }" );
		start.append("--></script> \n");
		return start.toString();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getValidatorMethodName() {
		return validatorMethodName;
	}

	public void setValidatorMethodName(String validatorMethodName) {
		this.validatorMethodName = validatorMethodName;
	}

	public int doEndTag() throws JspException {
		this.methodName = null;
		this.validatorMethodName = null;
		return super.doEndTag();
	}

	

	

}
