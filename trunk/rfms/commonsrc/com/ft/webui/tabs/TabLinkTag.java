package com.ft.webui.tabs;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 *
 *        JSP Tag that renders an XHTML </code>a</code> element that handles targeting
 *        a specific tab on the current page or another page.
 */
public final class TabLinkTag extends TagSupport {
    static final String QUESTION_MARK = "?";
    static final String EQUALS = "=";
    static final String AMPERSAND = "&amp;";
    public static final String PARAM_NAME_TAB_PANE_ID =
        "orgDitchnetTabPaneId";
    private String id;
    private String href;
    private String selectedTabPaneId;

    public void setId(final String id) {
        this.id = id;
    }

    public void setHref(final String href) {
        this.href = href;
    }

    public void setSelectedTabPaneId(final String selectedTabPaneId) {
        this.selectedTabPaneId = selectedTabPaneId;
    }

    private boolean hrefHasQueryString() {
        return href.indexOf(QUESTION_MARK) > -1;
    }

    private String getUrlParamString() {
        String prefix;

        if (hrefHasQueryString()) {
            prefix = AMPERSAND;
        } else {
            prefix = QUESTION_MARK;
        }

        StringBuffer buff = new StringBuffer();
        buff.append(prefix).append(PARAM_NAME_TAB_PANE_ID).append(EQUALS)
            .append(selectedTabPaneId);

        return buff.toString();
    }

    public void doTag() throws JspException, IOException {
        StringWriter evalResult = new StringWriter();
        StringBuffer buff = evalResult.getBuffer();

        buff.append("\n<a ");

        if (isHrefSameAsRequestURI()) {
            buff.append("onclick=\"org.ditchnet.jsp.")
                .append("TabUtils.tabLinkClicked(event,'")
                .append(selectedTabPaneId)
                .append("'); return false;\" href=\"")
                .append(getRequest().getRequestURL());
        } else {
            buff.append("href=\"").append(href).append(getUrlParamString());
        }

        if ((null != id) && (0 != id.length())) {
            buff.append(" id=\"").append(id).append("\"");
        }

        buff.append("\">");

       // getJspBody().invoke(evalResult);

        buff.append("</a>\n");

        pageContext.getOut().print(buff);
    }

    private boolean isHrefSameAsRequestURI() {
        return null == href;
    }

    private HttpServletRequest getRequest() {
      
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();

        return request;
    }
}
