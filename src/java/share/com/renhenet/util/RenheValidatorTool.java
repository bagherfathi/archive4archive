package com.renhenet.util;

import org.apache.velocity.tools.struts.ValidatorTool;

public class RenheValidatorTool extends ValidatorTool {

	protected String getJavascript(String formName, boolean getStatic)
			throws Exception {
		String results = super.getJavascript(formName, getStatic);
		String results1 = new String(results.getBytes("ISO8859-1"), "GBK");

		return results1;
	}
}