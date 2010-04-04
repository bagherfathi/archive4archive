package com.ft.web.sm.taglib;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.busi.sm.model.OrgManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;
import com.ft.webui.context.WebUIContextImpl;

/**
 * 生成组织树的XML数据
 * 
 * @version 1.0
 * 
 */
public class OrgTreeXmlTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String orgId;

    @SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
        OrgManager orgManager = (OrgManager) SpringWebUtils.getBean(pageContext
                .getServletContext(), "orgManager");
        Evaluator aEvaluator = new Evaluator();
        Long aId = (Long) aEvaluator.evaluate("id", this.orgId, Long.class,
                this, pageContext);
        // List accessOrgs = orgManager.findAllAccessOrgsForOperator(currentUser
        // .getOperatorId());
        // List allOrgs = orgManager.findAllOrgOrderByOrgName();
        // List roots = findRoots(allOrgs);
        // OrganizationDTO[] orgsArray = ListToArray(allOrgs);
        //	
        // for (int i = 0; i < orgsArray.length; i++) {
        // orgsArray[i].getChildren().addAll(
        // findChild(orgsArray[i], orgsArray));
        // }
        // for (Iterator iter = roots.iterator(); iter.hasNext();) {
        // OrganizationDTO element = (OrganizationDTO) iter.next();
        // element.getChildren().addAll(findChild(element, orgsArray));
        // }
        try {
            List allOrgs = orgManager.findAllOrgOrderByOrgName();
            OrganizationDTO[] orgsArray = (OrganizationDTO[]) allOrgs
                    .toArray(new OrganizationDTO[allOrgs.size()]);
            for (int i = 0; i < orgsArray.length; i++) {
                orgsArray[i].getChildren().addAll(
                        findChild(orgsArray[i], orgsArray));
            }
            HashMap params = new HashMap();
            // params.put("roots", roots);
            OrganizationDTO root = new OrganizationDTO();

            if (aId.longValue() == 0) {
                root = orgManager.findRootOrg();
            } else {
                root = orgManager.findOrgById(aId);
            }
            root.getChildren().addAll(findChild(root, orgsArray));
            params.put("root", root);
            StringWriter write = new StringWriter();
            WebUIContextImpl.getWebUIContext().getTemplateEngine().execute(
                    params, "orgTreeXml.vm", write);
            TagUtils.getInstance().write(pageContext, write.toString());
            return SKIP_BODY;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }

    /**
     * 找出指定组织的子组织
     * 
     * @param org
     * @param allOrg
     * @return
     */
    private List findChild(OrganizationDTO org, OrganizationDTO[] allOrg) {
        List<Object> childs = new ArrayList<Object>();
        long id = org.getOrgId().longValue();
        for (int i = 0; i < allOrg.length; i++) {
            long cpid = allOrg[i].getParentOrgId().longValue();
            if (id == cpid
                    && org.getParentOrgId().longValue() != allOrg[i].getOrgId()
                            .longValue()) {
                childs.add(allOrg[i]);
            }
        }
        return childs;
    }

    /**
     * @return the orgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     *                the orgId to set
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
