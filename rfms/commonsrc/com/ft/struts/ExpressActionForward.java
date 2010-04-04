package com.ft.struts;

import com.ft.commons.expr.ExpressionEvaluator;
import com.ft.commons.expr.VariableResolver;

import org.apache.struts.action.ActionForward;


import javax.servlet.http.HttpServletRequest;


public class ExpressActionForward extends ActionForward {
	private static final long serialVersionUID = 2915495369032872136L;

	/**
	 * 
	 */
	public ExpressActionForward() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param path
	 */
	public ExpressActionForward(String path) {
		super(path);
	}

	/**
	 * @param path
	 * @param redirect
	 */
	public ExpressActionForward(String path, boolean redirect) {
		super(path, redirect);

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param path
	 * @param redirect
	 */
	public ExpressActionForward(String name, String path, boolean redirect) {
		super(name, path, redirect);

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param path
	 * @param redirect
	 * @param module
	 */
	public ExpressActionForward(String name, String path, boolean redirect,
			String module) {
		super(name, path, redirect, module);
	}

	/**
	 * @param copyMe
	 */
	public ExpressActionForward(ActionForward copyMe) {
		super(copyMe);
	}

	public String getPathRequest(final HttpServletRequest request) {
		String path = (String) ExpressionEvaluator.eval(this.getPath(),
				new VariableResolver() {
					public Object resolveVariable(String arg0)
							{
						return request.getAttribute(arg0);
					}
				});

		return path;
     
	}
}
