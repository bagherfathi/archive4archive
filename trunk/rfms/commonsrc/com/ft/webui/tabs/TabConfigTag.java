package com.ft.webui.tabs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ft.webui.context.WebUIContextImpl;


/**
 *        JSP Tag that renders XHTML </code>script</code> and </code>style</code> elements
 *        that link to the CSS and JavaScript resources necessary to render the
 *        tab components on the current page. The resources that are linked to are
 *        the resources placed in a directory named </code>/org.ditchnet.taglib/</code>
 *        in the web app's root directory. These resources were placed there by
 *        the {@link org.ditchnet.jsp.taglib.tabs.listener.TabServletContextListener}.
 */
public final class TabConfigTag extends TagSupport {
    private String contextPath;

    public void doTag() throws JspException, IOException {
        StringBuffer buff = new StringBuffer();

        findContextPath();
        renderScriptTag(buff);
        renderStyleTag(buff);

        pageContext.getOut().print(buff);
    }

    private void findContextPath() {
     
        HttpServletRequest req =
            (HttpServletRequest) pageContext.getRequest();
        contextPath = req.getContextPath();
    }

    private void renderScriptTag(final StringBuffer buff) {
        String uri =
            WebUIContextImpl.getWebUIContext().getConfiguration()
                            .getString("tab.script.uri");
        uri = getEncodedContextRelativePath(uri);

        buff.append("\n\n\t<script type=\"text/javascript\" ").append(
            "src=\"").append(uri).append("\">").append("</script>\n");
    }

    private void renderStyleTag(final StringBuffer buff) {
        String uri =
            WebUIContextImpl.getWebUIContext().getConfiguration()
                            .getString("tab.style.uri");
        getEncodedContextRelativePath(uri);

        buff.append("\t<link type=\"text/css\" rel=\"stylesheet\" ")
            .append("href=\"").append(uri).append("\" />\n\n");
    }

    private String getEncodedContextRelativePath(final String uri) {
        return contextPath + uri;
    }
}
