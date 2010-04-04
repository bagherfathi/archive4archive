package com.ft.struts;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class HttpMethodAction extends Action {
    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return doGet(mapping, form, request, response);
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            return doPost(mapping, form, request, response);
        }

        return mapping.getInputForward();
    }

    protected ActionForward doGet(
        ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String forward = request.getParameter("forward");

        if (forward == null) {
            return mapping.getInputForward();
        } else {
            return mapping.findForward(forward);
        }
    }

    protected ActionForward doPost(
        ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        return doGet(mapping, form, request, response);
    }
}
