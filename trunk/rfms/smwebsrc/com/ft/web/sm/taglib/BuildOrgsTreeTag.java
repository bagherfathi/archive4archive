package com.ft.web.sm.taglib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.OrgManager;
import com.ft.common.security.OrgsTreeBuilder;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.tree.TreeNode;
import com.ft.commons.web.SpringWebUtils;

public class BuildOrgsTreeTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String orgType;

    private String orgId;

    private String var;
    
    //多个组织类型之间使用"#"分隔，只有orgType为-1时有效。
    private String noInclude;

    @SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {

        HttpServletRequest request = (HttpServletRequest) this.pageContext
                .getRequest();
        OrgManager orgManager = (OrgManager) SpringWebUtils.getBean(
                this.pageContext, "orgManager");
        
        Evaluator aEvaluator = new Evaluator();
        Long aOrgType = (Long) aEvaluator.evaluate("orgType", this.orgType,
                Long.class, this, pageContext);

        if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_AGENT) {
            aOrgType = new Long(OrganizationDTO.ORG_TYPE_BUSIHALL);
        }

        OrganizationDTO[] childrens = new OrganizationDTO[0];
        Long aOrgId = null;

        if (StringUtils.isEmpty(orgId)) {
            if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_COMPANY) {
                childrens = OperatorSessionHelper
                        .getAccessCompaniesOfLoginOrg(request);
            } else if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_REGION) {
                childrens = OperatorSessionHelper
                        .getAccessRegionsOfLoginOrg(request);
            } else if (aOrgType.longValue() == OrganizationDTO.ORG_TYPE_BUSIHALL) {
                childrens = OperatorSessionHelper
                        .getAccessBusiHallsOfLoginOrg(request);
            } else if (aOrgType.longValue() == 100) {
                List childrensList  = OperatorSessionHelper.getAccessOrgsOfLoginOp(request);
                childrens = (OrganizationDTO[]) childrensList
                .toArray(new OrganizationDTO[childrensList.size()]);
            }else if (aOrgType.longValue() == 200) {
                List childrensList  = orgManager.findOrgsByType(OrganizationDTO.ORG_TYPE_COMPANY);
                childrens = (OrganizationDTO[]) childrensList
                .toArray(new OrganizationDTO[childrensList.size()]);
            } else {
                childrens = OperatorSessionHelper.getAccessOrgsOfLoginOrg(
                        request, true);
            }
        } else {
            aOrgId = (Long) aEvaluator.evaluate("orgId", this.orgId,
                    Long.class, this, pageContext);

            try {
                List childrensList = orgManager.findAccessOrgsOfOrg(aOrgId,
                        aOrgType.longValue(), true);
                
                if(noInclude != null && noInclude.length() > 0){
                    for (Iterator iterator = childrensList.iterator(); iterator
                            .hasNext();) {
                        OrganizationDTO orgDto = (OrganizationDTO) iterator.next();
                        if(noInclude.indexOf(orgDto.getType()+ "")  != -1){
                            iterator.remove();
                        }
                    }
                }
                childrens = (OrganizationDTO[]) childrensList
                        .toArray(new OrganizationDTO[childrensList.size()]);
            } catch (Exception ex) {
                throw new JspException(ex);
            }
        }

        OrgsTreeBuilder orgsTreeBuilder = (OrgsTreeBuilder) SpringWebUtils
                .getBean(this.pageContext, "orgsTreeBuilder");

        TreeNode root = orgsTreeBuilder.buildOrgNode(Arrays.asList(childrens));

        this.pageContext.setAttribute(var, root);
        return SKIP_BODY;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     *            the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *            the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }

    public String getNoInclude() {
        return noInclude;
    }

    public void setNoInclude(String noInclude) {
        this.noInclude = noInclude;
    }
}
