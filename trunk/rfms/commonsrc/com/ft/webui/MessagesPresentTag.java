package com.ft.webui;

import org.apache.struts.Globals;

import javax.servlet.jsp.JspException;


/**
 * @jsp.tag name="messagesPresent" display-name="MessagesPresentTag" body-content="JSP"
 */
public class MessagesPresentTag extends org.apache.struts.taglib.logic.MessagesPresentTag {
    private static final long serialVersionUID = 1L;

    protected boolean condition(boolean desired) throws JspException {
        name = Globals.ERROR_KEY;

        return super.condition(desired);
    }
}
