package com.ft.common.security.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ft.common.security.PermissionCheckHelper;

/**
 * 用于权限检查的taglib
 * 
 * @version 1.0
 */
public class CheckPermissionTag extends TagSupport {
    private static final long serialVersionUID = 8903426553115504150L;

    private boolean checkPermissionSuccessful;

    private String resourceKey;

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.vod.taglib.BaseSimpleTag#doStartTag()
     */
    public int doStartTag() throws JspException {
        checkPermissionSuccessful = PermissionCheckHelper.checkPermission(
                (HttpServletRequest) pageContext.getRequest(), resourceKey);
        return EVAL_BODY_INCLUDE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.vod.taglib.BaseSimpleTag#doEndTag()
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.vod.taglib.BaseSimpleTag#release()
     */
    public void release() {

    }

    /**
     * @return Returns the checkPermissionSuccessful.
     */
    public boolean isCheckPermissionSuccessful() {
        return checkPermissionSuccessful;
    }

    /**
     * @param checkPermissionSuccessful
     *                The checkPermissionSuccessful to set.
     */
    public void setCheckPermissionSuccessful(boolean checkPermissionSuccessful) {
        this.checkPermissionSuccessful = checkPermissionSuccessful;
    }

    /**
     * @return Returns the resourceKey.
     */
    public String getResourceKey() {
        return resourceKey;
    }

    /**
     * @param resourceKey
     *                The resourceKey to set.
     */
    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
}
