package com.ft.webui.util;

import org.apache.struts.taglib.TagUtils;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * ½âÂëµÄTag
 * @jsp.tag name="decode"
 *         display-name="DecodeTag" body-content="empty"
 *
 */
public class DecodeTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String value;
    private String charset;

    public String getCharset() {
        return charset;
    }

    /**
     * @jsp.attribute description="charset"
     *                required="false" rtexprvalue="true"
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getValue() {
        return value;
    }

    /**
     * @jsp.attribute description="value"
     *                required="false" rtexprvalue="true"
     */
    public void setValue(String value) {
        this.value = value;
    }

    public int doStartTag() throws JspException {
        if (charset == null) {
            this.charset = "UTF-8";
        }

        try {
            TagUtils.getInstance()
                    .write(pageContext, URLDecoder.decode(value, charset));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JspException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
