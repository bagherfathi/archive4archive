package com.renhenet.fw.waf;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class ExceptionHander extends ExceptionHandler {
	private static final String SYSTEM_ERROR = "systemError";
	private static final Log log = LogFactory.getLog(ExceptionHander.class);

	public ExceptionHander() {
	}

	public ActionForward execute(Exception ex, ExceptionConfig ae,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		super.execute(ex, ae, mapping, formInstance, request, response);
		log.info("=== exception catched by ExceptionHander ====");

		ActionForward forward = null;
		ActionMessage error = null;
		String property = null;

		if (ae.getPath() != null) {
			forward = new ActionForward(ae.getPath());
			forward.setModule("/");
		} else {
			forward = mapping.findForward(SYSTEM_ERROR);
		}

		StringWriter out = new StringWriter();
		ex.printStackTrace(new PrintWriter(out));

		request.setAttribute("errorsInfo", out.toString());

		log.warn(ex);
		ex.printStackTrace();

		storeException(request, property, error, forward, ae.getScope());
		return forward;
	}
}