package com.ft.webui;

import com.ft.hibernate.taglib.PageContextUtils;

import org.apache.struts.taglib.TagUtils;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="resource" display-name="ResourceTag" body-content="JSP"
 *
 */
public class ResourceTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String src;

    public String getSrc() {
        return src;
    }

    /**
    * @jsp.attribute description="src"
    * required="false" rtexprvalue="true"
    */
    public void setSrc(String src) {
        this.src = src;
    }

    public int doStartTag() throws JspException {
        String contextPath = PageContextUtils.getContextPath(pageContext);

        try {
            TagUtils.getInstance()
                    .write(
                this.pageContext,
                URLDecoder.decode(contextPath + src, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return super.doStartTag();
    }
}
