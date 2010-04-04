package com.ft.webui;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.taglib.TagUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;


/**
 * @jsp.tag name="messages" display-name="MessagesTag" body-content="JSP"
 *
 */
public class MessagesTag extends org.apache.struts.taglib.html.MessagesTag {
    private static final long serialVersionUID = 1L;

    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    protected void collectMessages(
        ActionMessages actionMessages, ActionMessages actionErrors) {
        ActionMessages sessionMessages =
            (ActionMessages) pageContext.getAttribute(
                Globals.MESSAGE_KEY, PageContext.SESSION_SCOPE);

        if (sessionMessages != null) {
            actionMessages.add(sessionMessages);
            pageContext.removeAttribute(
                Globals.MESSAGE_KEY, PageContext.SESSION_SCOPE);
        }

        ActionErrors sessionErrors =
            (ActionErrors) pageContext.getAttribute(
                Globals.ERROR_KEY, PageContext.SESSION_SCOPE);

        if (sessionErrors != null) {
            actionErrors.add(sessionErrors);
            pageContext.removeAttribute(
                Globals.ERROR_KEY, PageContext.SESSION_SCOPE);
        }
    }

    public int doStartTag() throws JspException {
        processed = false;

        // Were any messages specified?
        ActionMessages messages = null;

        // Make a local copy of the name attribute that we can modify.
        String name = this.name;

        if ("true".equalsIgnoreCase(message)) {
            name = Globals.MESSAGE_KEY;
        }

        try {
            messages = TagUtils.getInstance()
                               .getActionMessages(pageContext, name);
        } catch (JspException e) {
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        this.iterator = (property == null) ? messages.get()
                                           : messages.get(property);

        if (!this.iterator.hasNext()) {
            return SKIP_BODY;
        }

        ActionMessage report = (ActionMessage) this.iterator.next();
        String msg =
            TagUtils.getInstance()
                    .message(
                pageContext, bundle, locale, report.getKey(),
                report.getValues());

        if (msg != null) {
            pageContext.setAttribute(id, msg);
        } else {
            pageContext.removeAttribute(id);
        }

        if ((header != null) && (header.length() > 0)) {
            String headerMessage =
                TagUtils.getInstance()
                        .message(pageContext, bundle, locale, header);

            if (headerMessage != null) {
                TagUtils.getInstance().write(pageContext, headerMessage);
            }
        }

        processed = true;

        return (EVAL_BODY_BUFFERED);
    }

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
     */
    public int doAfterBody() throws JspException {
        String msg;

        // Render the output from this iteration to the output stream
        if (bodyContent != null) {
            TagUtils.getInstance()
                    .writePrevious(pageContext, bodyContent.getString());
            bodyContent.clearBody();
        }

        // Decide whether to iterate or quit
        if (iterator.hasNext()) {
            ActionMessage report = (ActionMessage) iterator.next();

            msg = TagUtils.getInstance()
                          .message(
                    pageContext, bundle, locale, report.getKey(),
                    report.getValues());

            pageContext.setAttribute(id, msg);

            return (EVAL_BODY_BUFFERED);
        } else {
            return (SKIP_BODY);
        }
    }

    public void setPageContext(PageContext arg0) {
        super.setPageContext(arg0);

        ActionMessages actionMessages =
            (ActionMessages) this.pageContext.getAttribute(
                Globals.MESSAGE_KEY, PageContext.REQUEST_SCOPE);
        ActionMessages actionErrors =
            (ActionMessages) this.pageContext.getAttribute(
                Globals.ERROR_KEY, PageContext.REQUEST_SCOPE);

        if (actionMessages == null) {
            actionMessages = new ActionMessages();
            pageContext.setAttribute(
                Globals.MESSAGE_KEY, actionMessages, PageContext.REQUEST_SCOPE);
        }

        if (actionErrors == null) {
            actionErrors = new ActionMessages();
            pageContext.setAttribute(
                Globals.ERROR_KEY, actionErrors, PageContext.REQUEST_SCOPE);
        }

        collectMessages(actionMessages, actionErrors);
    }
}
