/**
 * @{#} InitActionForm.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by Onewave Inc.
 */
package com.ft.struts;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;


public interface InitActionForm {
    public void init(ActionMapping mapping, HttpServletRequest request);
}
