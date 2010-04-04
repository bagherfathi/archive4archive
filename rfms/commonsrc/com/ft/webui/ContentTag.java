package com.ft.webui;

import org.apache.commons.lang.StringUtils;

import org.apache.struts.taglib.TagUtils;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * @jsp.tag name="content" display-name="ContentTag" body-content="JSP"
 * @author Coffee
 *
 */
public class ContentTag extends BodyTagSupport {
    private static final long serialVersionUID = 1L;
    private boolean html;
    private boolean script;
    private boolean line;

    public boolean isHtml() {
        return html;
    }

    /**
    * @jsp.attribute description="enable html"
    *                required="false" rtexprvalue="true"
     * @param html
     */
    public void setHtml(boolean html) {
        this.html = html;
    }

    public boolean isLine() {
        return line;
    }

    /**
    * @jsp.attribute description="enable br html tag"
    *                required="false" rtexprvalue="true"
     * @param line
     */
    public void setLine(boolean line) {
        this.line = line;
    }

    public boolean isScript() {
        return script;
    }

    /**
    * @jsp.attribute description="enable br javascript tag"
    *                required="false" rtexprvalue="true"
     * @param script
     */
    public void setScript(boolean script) {
        this.script = script;
    }

    public int doEndTag() throws JspException {
        String content = this.getBodyContent().getString();
        content = StringUtils.replace(content, "\n", "<br/>");

        Pattern p = Pattern.compile("[<][/]*[Ss][Cc][Rr][Ii][Pp][Tt]");
        Matcher m = p.matcher(content);
        content = m.replaceAll("*");
        TagUtils.getInstance().write(pageContext, content);

        try {
            this.bodyContent.clear();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }
}
