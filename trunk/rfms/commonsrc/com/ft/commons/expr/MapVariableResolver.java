package com.ft.commons.expr;

import java.util.Map;

/**
 * @author <a href="mailto:liuliang2@126.com">¡ı¡¡</a>
 * @version 1.0
 */
public class MapVariableResolver implements VariableResolver {
	private Map context;

	public void setContext(Map context) {
		this.context = context;
	}

	public Object resolveVariable(String arg0) {
		return context.get(arg0);
	}

}
