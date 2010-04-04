package com.ft.webui;

import com.ft.hibernate.taglib.PageContextUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="pageNav" display-name="PageNavTag" body-content="JSP"
 *
 */
public class PageNavTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private Object pageBean;
    private String requestURL;
    private String includeParam;
    private String excludeParam;
    private String pageParamName;
    private String url;
    private List params;
    private String template;

    public String getPageURL(int i) {
        return this.url + this.pageParamName + "=" + i;
    }

    public int doStartTag() throws JspException {
        this.ansyleURL();

        TemplateUtils templateUtils = TemplateUtils.getInstance(pageContext);

        try {
            templateUtils.merge(
                this.template, "pageNav", this, this.pageContext.getOut());
        } catch (Exception e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public void ansyleURL() {
        params = new ArrayList();

        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        Enumeration names = request.getParameterNames();

        while (names.hasMoreElements()) {
            String paramName = (String) names.nextElement();

            if (!paramName.equals(this.pageParamName)) {
                params.add(paramName);
            }
        }

        if (requestURL == null) {
            requestURL = request.getRequestURL().toString();
        }

        if (includeParam != null) {
            StringTokenizer tokens = new StringTokenizer(includeParam, ",");

            //List includeParams = new ArrayList();
            while (tokens.hasMoreTokens()) {
                String name = tokens.nextToken();

                if (!params.contains(name)) {
                    params.remove(name);
                }
            }

            //			for (Iterator iter = params.iterator(); iter.hasNext();) {
            //				String element = (String) iter.next();
            //				if(!includeParams.contains(element)){
            //					iter.remove() ;
            //				}
            //				
            //			}
        }

        if (excludeParam != null) {
            StringTokenizer tokens = new StringTokenizer(excludeParam, ",");

            while (tokens.hasMoreTokens()) {
                String name = tokens.nextToken();

                if (params.contains(name)) {
                    params.remove(name);
                }
            }
        }

        StringBuffer queueString = new StringBuffer();

        for (Iterator iter = params.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            queueString.append(element).append("=")
                       .append(request.getParameter(element)).append("&");
        }

        if (this.requestURL.indexOf("/") == 0) {
            this.requestURL = PageContextUtils.getContextPath(pageContext)
                + this.requestURL;
        }

        this.url = this.requestURL + "?" + queueString.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageParamName() {
        return pageParamName;
    }

    public void setPageParamName(String pageParamName) {
        this.pageParamName = pageParamName;
    }

    public String getExcludeParam() {
        return excludeParam;
    }

    public void setExcludeParam(String excludeParam) {
        this.excludeParam = excludeParam;
    }

    public String getIncludeParam() {
        return includeParam;
    }

    public void setIncludeParam(String includeParam) {
        this.includeParam = includeParam;
    }

    public Object getPageBean() {
        return pageBean;
    }

    public void setPageBean(Object pageBean) {
        this.pageBean = pageBean;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void release() {
        this.excludeParam = null;
        this.includeParam = null;
        this.pageBean = null;
        this.pageParamName = null;

        if (this.params != null) {
            this.params.clear();
            this.params = null;
        }

        this.template = null;
        super.release();
    }
}
