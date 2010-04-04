package com.ft.web.sm.priv.op;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.security.ResourceRepository;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.web.sm.BaseAction;
import com.ft.web.sm.priv.resource.ResourceFilter;

/**
 * 设置操作员功能权限页面控制类。
 * 
 * @version 1.0
 * @spring.bean id="opResourceAction"
 * 
 * 
 * @struts.action path="/opResource" name="opResourceForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.resource.config"
 * 
 * @struts.tiles name="sm.op.resource.config" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/opResourceConfig.jsp"
 */
public class OperatorResourceAction extends BaseAction {
    private ResourceManager resourceManager;

    private OrgManager orgManager;

    private OperatorManager opManager;

    /**
     * 功能权限实体缓存
     */
    private ResourceRepository resourceRepository;

    /**
     * 设置操作员可访问功能权限。
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward configResource(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorResourceForm opPermissionForm = (OperatorResourceForm) form;
        OperatorDTO op = opPermissionForm.getOp();
        OrganizationDTO org = opPermissionForm.getOrg();
        if (org != null && org.getOrgId().longValue() == 0) {
            org = null;
        }
        // 获取当前登录操作员可访问组织
        OrganizationDTO[] loginOrgs = OperatorSessionHelper
                .getAccessOrgsOfLoginOrg(request, true);
        long[] ids = ArrayUtils.EMPTY_LONG_ARRAY;
        for (int i = 0; i < loginOrgs.length; i++) {
            ids = ArrayUtils.add(ids, loginOrgs[i].getOrgId().longValue());
        }
        List accessOrgs = this.orgManager.findAccessOrgForOperatorInLoginOrg(op
                .getOperatorId() ,ids);

        if (org == null && !accessOrgs.isEmpty()) {
            org = (OrganizationDTO) accessOrgs.get(0);
        }
        if (org != null) {
            Long orgId = org.getOrgId();
            List acls = this.resourceManager.findACLResourcesOfOp(op
                    .getOperatorId(), orgId);
            // 如果组织不是分公司，则查找出该组织所属的分公司
            if (org != null
                    && org.getType() != OrganizationDTO.ORG_TYPE_COMPANY) {
                orgId = this.orgManager.findCompanyIdOfOrg(orgId);
            }
            List resourcesOfOp = this.getResourceList(orgId, acls);
            request.setAttribute("resourcesOfOp", resourcesOfOp);
        }
        request.setAttribute("accessOrgs", accessOrgs);

        return mapping.getInputForward();
    }

    /**
     * 保存设置。
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveConfig(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String[] selectedIds = request.getParameterValues("ids");

        OperatorResourceForm opPermissionForm = (OperatorResourceForm) form;
        OperatorDTO op = opPermissionForm.getOp();
        OrganizationDTO org = opPermissionForm.getOrg();

        Long[] resourceIds = filterSelectedResource(selectedIds);
        this.opManager.saveOperatorResourceForOrg(opPermissionForm
                .getCurrentUser(), op, org, resourceIds);

        return this
                .getRedirectForwardAction("op.do?act=view&selectedPane=role&opId="
                        + op.getOperatorId().longValue()
                        + "&"
                        + plusParams(opPermissionForm));
    }

    /**
     * 根据访问控制列表获取可访问的功能权限。
     * 
     * @param ACLResourceList
     * @return
     */
    private List getResourceList(Long orgId, List ACLResourceList) {
        PermissionChecker checker = new PermissionChecker();
        checker.addOrgPermission(orgId, ACLResourceList);
        List<ResourceDTO> result = new ArrayList<ResourceDTO>();

        List allResources = this.resourceRepository.iterator();
        for (Iterator iter = allResources.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            if (checker.checkPermission(orgId, element.getResourcePath())) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * 对于选择的功能权限进行过滤。
     * 
     * @param resourceIds
     * @return
     */
    private Long[] filterSelectedResource(String[] resourceIds) {
        if (resourceIds == null)
            return new Long[0];

        List<ResourceDTO> selectedResource = new ArrayList<ResourceDTO>();

        List allResources = this.resourceRepository.iterator();
        for (Iterator iter = allResources.iterator(); iter.hasNext();) {
            ResourceDTO element = (ResourceDTO) iter.next();
            for (int i = 0; i < resourceIds.length; i++) {
                if (String.valueOf(element.getResourceId()).equals(
                        resourceIds[i])) {
                    selectedResource.add(element);
                    break;
                }
            }
        }

        List aclList = ResourceFilter.filter(selectedResource);
        Long[] ret = new Long[aclList.size()];

        for (int i = 0; i < aclList.size(); i++) {
            ret[i] = ((ResourceDTO) aclList.get(i)).getResourceId();
        }

        return ret;
    }

    /**
     * @spring.property ref="resourceManager"
     * @param resourceManager
     *            the resourceManager to set
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref = "resourceRepository"
     * 
     * @param resourceRepository
     */
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * @spring.property ref="operatorManager"
     * @param opManager
     *            the opManager to set
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }

    private String plusParams(OperatorResourceForm opForm) {
        StringBuffer url = new StringBuffer();
        url.append("orgId_s=").append(opForm.getOrgId_s().toString());
        url.append("&loginName=").append(opForm.getLoginName());
        url.append("&name=").append(opForm.getName());
        url.append("&listOp_p=").append(opForm.getListOp_p());
        return url.toString();
    }
}
