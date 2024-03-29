package com.ft.common.security.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * �ж�Ȩ�޼��ʧ�ܵ�taglib.
 * 
 * @version 1.0
 */
public class CheckPermissionFailedTag extends TagSupport {
    private static final long serialVersionUID = -7138758204534780206L;

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.vod.taglib.BaseSimpleTag#doStartTag()
     */
    public int doStartTag() throws JspException {
        CheckPermissionTag checkPermission = (CheckPermissionTag) TagSupport
                .findAncestorWithClass(this, CheckPermissionTag.class);
        if (!checkPermission.isCheckPermissionSuccessful()) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
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
        // TODO Auto-generated method stub

    }
}
