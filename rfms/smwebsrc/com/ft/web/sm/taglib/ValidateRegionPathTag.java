package com.ft.web.sm.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.sm.dto.RegionDTO;

/**
 * 验证区域之间的关系
 * 
 * @version 1.0
 */

public class ValidateRegionPathTag extends TagSupport {

    private static final long serialVersionUID = -5296817572526888029L;

    private String regionList;

    private String regionPath;

    private String var;

    public int doStartTag() throws JspException {
        boolean flag = false;
        Evaluator aEvaluator = new Evaluator();
        String path = (String) aEvaluator.evaluate("regionPath",
                this.regionPath, String.class, this, pageContext);
        List aList = (List) aEvaluator.evaluate("regionList", this.regionList,
                List.class, this, pageContext);
        if (aList == null || aList.isEmpty()) {
            this.pageContext.setAttribute(var, new Boolean(flag));
            return SKIP_BODY;
        }
        for (Iterator iter = aList.iterator(); iter.hasNext();) {
            RegionDTO element = (RegionDTO) iter.next();
            if (path.startsWith(element.getRegionPath())) {
                flag = true;
                break;
            }
        }
        this.pageContext.setAttribute(var, new Boolean(flag));
        return SKIP_BODY;
    }

    /**
     * @return Returns the regionList.
     */
    public String getRegionList() {
        return regionList;
    }

    /**
     * @param regionList
     *                The regionList to set.
     */
    public void setRegionList(String regionList) {
        this.regionList = regionList;
    }

    /**
     * @return Returns the regionPath.
     */
    public String getRegionPath() {
        return regionPath;
    }

    /**
     * @param regionPath
     *                The regionPath to set.
     */
    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    /**
     * @return Returns the var.
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *                The var to set.
     */
    public void setVar(String var) {
        this.var = var;
    }

}
