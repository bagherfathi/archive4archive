package com.ft.struts;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ActionFormAttributeLookuper {
    public abstract void lookup(
        HttpServletRequest request, HttpServletResponse response,
        ActionForm form, ActionMapping mapping);

    public abstract void handleMessage(
        Action action, ActionMapping mapping, HttpServletRequest request);
}
