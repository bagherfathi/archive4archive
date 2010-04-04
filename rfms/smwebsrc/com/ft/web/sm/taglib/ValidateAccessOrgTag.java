package com.ft.web.sm.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.exception.CommonRuntimeException;
import com.ft.common.security.OrgRepository;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 
 * 验证组织是否可访问
 * 
 * @version 1.0
 */

public class ValidateAccessOrgTag extends TagSupport {

    private static final long serialVersionUID = -3401731870828775660L;

    private String id;

    private String var;

    public int doStartTag() throws JspException {
        boolean flag = false;
        OrgRepository orgRepository = (OrgRepository) SpringWebUtils.getBean(pageContext
                .getServletContext(), "orgRepository");
        
        List accessOrgs = OperatorSessionHelper
                .getAccessOrgsOfLoginOp((HttpServletRequest) this.pageContext
                        .getRequest());
        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("id", this.id, Long.class, this,
                pageContext);
        try {
            OrganizationDTO org = orgRepository.getOrgDTOById(aId);
            for (Iterator iter = accessOrgs.iterator(); iter.hasNext();) {
                OrganizationDTO element = (OrganizationDTO) iter.next();
                if (org != null && org.getPath().startsWith(element.getPath())
                        && org.getStatus() != OrganizationDTO.STATUS_DISABLE) {
                    flag = true;
                    break;
                }
            }
            this.pageContext.setAttribute(var, new Boolean(flag));
            return SKIP_BODY;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
