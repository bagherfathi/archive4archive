package com.ft.webui.tabs;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 *
 *        JSP Tag that renders an XHTML <code>input</code> element of type
 *        <code>button</code> with a JavaScript handler to switch the focus of
 *        a specified tab container to the next tab index.
 */
public final class NextTabButtonTag extends TagSupport {
    private String id;
    private String tabContainerId;

    public void setId(final String id) {
        this.id = id;
    }

    public void setTabContainerId(final String tabContainerId) {
        this.tabContainerId = tabContainerId;
    }

    public void doTag() throws IOException, JspException {
        StringWriter evalResult = new StringWriter();
        StringBuffer buff = evalResult.getBuffer();

        buff.append("\n\t<input type=\"button\" ")
            .append("class=\"ditch-next-tab-button\" ")
            .append("onclick=\"org.ditchnet.jsp.")
            .append("TabUtils.nextTabButtonClicked(event,'")
            .append(tabContainerId).append("'); return false;\"");

        if ((null != id) && (0 != id.length())) {
            buff.append(" id=\"").append(id).append("\"");
        }

        buff.append(" value=\"");
        //getJspBody().invoke(evalResult);
        buff.append("\" />\n");

        pageContext.getOut().print(buff);
    }
}
