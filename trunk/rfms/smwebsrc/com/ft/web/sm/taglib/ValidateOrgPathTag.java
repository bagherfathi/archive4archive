package com.ft.web.sm.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.sm.dto.OrganizationDTO;

public class ValidateOrgPathTag extends TagSupport {

    private static final long serialVersionUID = 8431580467953348148L;

    private String parentPath;

    private String path;

    private String var;

    private String orgList;

    public int doStartTag() throws JspException {
        boolean flag = false;
        Evaluator aEvaluator = new Evaluator();
        String path = (String) aEvaluator.evaluate("path", this.path,
                String.class, this, pageContext);
        if (this.parentPath != null) {
            String pPath = (String) aEvaluator.evaluate("parentPath",
                    this.parentPath, String.class, this, pageContext);
            if (path.startsWith(pPath)) {
                flag = true;
            }
        } else if (this.orgList != null) {
            List aList = (List) aEvaluator.evaluate("orgList", this.orgList,
                    List.class, this, pageContext);
            if (aList != null) {
                for (Iterator iter = aList.iterator(); iter.hasNext();) {
                    OrganizationDTO element = (OrganizationDTO) iter.next();
                    if (path.startsWith(element.getPath())) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        this.pageContext.setAttribute(var, new Boolean(flag));
        return SKIP_BODY;
    }

    /**
     * @return Returns the parentPath.
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * @param parentPath
     *            The parentPath to set.
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * @return Returns the path.
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            The path to set.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return Returns the var.
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *            The var to set.
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * @return the orgList
     */
    public String getOrgList() {
        return orgList;
    }

    /**
     * @param orgList
     *            the orgList to set
     */
    public void setOrgList(String orgList) {
        this.orgList = orgList;
    }

}
