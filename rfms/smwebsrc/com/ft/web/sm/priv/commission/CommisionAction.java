package com.ft.web.sm.priv.commission;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.CommissionManager;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.ResourceRepository;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.ConsignPermitDTO;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.ResourceDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 委托权限设置页面控制类
 * 
 * @struts.action path="/consign" name="commisionForm" scope="request"
 *                validate="false" parameter="act"
 *                input="privilege.consign.list.operators"
 * 
 * @struts.tiles name="privilege.consign.list.operators" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/commission/listOperator.jsp"
 * 
 * @struts.action-forward name="edit" path="privilege.consign.edit"
 * @struts.action-forward name="select" path="privilege.consign.select"
 * @struts.action-forward name="listCommission"
 *                        path="privilege.consign.list.commission"
 * 
 * @spring.bean id="consignFunctionAction"
 * 
 * @version 1.0
 * 
 */
public class CommisionAction extends BaseAction {

    private static Log logger = LogFactory.getLog(CommisionAction.class);

    private OperatorManager operatorManager;

    private ResourceTreeBuilder resourceTreeBuilder;

    private CommissionManager commissionManager;

    private ResourceManager resourceManager;

    private ResourceRepository resourceRepository;

    private OrgManager orgManager;

    /**
     * @spring.property ref = "resourceRepository"
     * 
     * @param resourceRepository
     */
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * @spring.property ref="resourceTreeBuilder"
     */
    public void setResourceTreeBuilder(ResourceTreeBuilder resourceTreeBuilder) {
        this.resourceTreeBuilder = resourceTreeBuilder;
    }

    /**
     * @spring.property ref="commissionManager"
     * @param commissionManager
     *            The commissionManager to set.
     */
    public void setCommissionManager(CommissionManager commissionManager) {
        this.commissionManager = commissionManager;
    }

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * @spring.property ref="operatorManager"
     */
    public void setOperatorManager(OperatorManager operatorManager) {
        this.operatorManager = operatorManager;
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     *            the orgManager to set
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 查询委托对象
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchOperator(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommisionForm cfForm = (CommisionForm) form;
        OperatorDTO op = cfForm.getCurrentUser();

        // 查询条件
        Long orgId = cfForm.getSearchOrgId();
        String opName = cfForm.getSearchName();
        String loginName = cfForm.getSearchLoginName();

        List result = null;

        if (orgId == null || orgId.longValue() == 0) {
            orgId = OperatorSessionHelper.getLoginOrg(request).getOrgId();
            result = this.operatorManager.findCanConsignedOperators(op
                    .getOperatorId(), opName, loginName, orgId, true);
        } else {
            result = this.operatorManager.findCanConsignedOperators(op
                    .getOperatorId(), opName, loginName, orgId, false);
        }
        request.setAttribute("opList", result);
        return mapping.getInputForward();
    }

    /**
     * 设置功能权限委托权限
     * 
     * @struts.tiles name="privilege.consign.select" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/commission/selectCommision.jsp"
     */
    public ActionForward selectComissionResource(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // 获取当前登陆用户可访问组织列表
        List accessOrgs = getAccessOrgs(form, request);
        request.setAttribute("accessOrgs", accessOrgs);

        CommisionForm cfForm = (CommisionForm) form;
        Long orgId = cfForm.getOrgId();
        if (orgId == null) {
            orgId = new Long(-1);
        }

        // Long orgId = OperatorSessionHelper.getCurrentOrg(request).getOrgId();
        // 查询当前操作员权限
        List aclList = this.resourceManager
                .findACLResourcesExcludeConsignedOfOperator(cfForm
                        .getCurrentUser().getOperatorId(), orgId);
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(aclList);

        request.setAttribute("root", this.resourceTreeBuilder
                .buildTreeNode(checker));

        // 查询已委托的权限
        /*
         * List consignedPrivilege =
         * this.commissionManager.findCommissions(cfForm
         * .getCurrentUser().getOperatorId(), cfForm.getConsignee()
         * .getOperatorId(), orgId, ConsignPermitDTO.RESOURCE_TYPE);
         * 
         * request.setAttribute("consignedPrivileges", consignedPrivilege);
         */
        request.setAttribute("orgId", orgId);
        return mapping.findForward("select");
    }

    /**
     * 过滤选择的权限标识找出子项
     * 
     * @param allSelectedIds
     * @return
     */
    private Long[] findSelectedChildIds(String[] allSelectedIds) {

        List<Long> childList = new ArrayList<Long>();
        if (null != allSelectedIds) {
            List allResource = this.resourceRepository.iterator();
            for (int i = 0; i < allSelectedIds.length; i++) {
                String ids = allSelectedIds[i];
                Long id = Long.valueOf(ids);
                boolean isChild = true;
                for (Iterator iter = allResource.iterator(); iter.hasNext();) {
                    ResourceDTO res = (ResourceDTO) iter.next();
                    if (id.longValue() == res.getParentId().longValue()) {
                        isChild = false;
                    }
                }
                if (isChild) {
                    childList.add(id);
                }
            }
        }

        return (Long[]) childList.toArray(new Long[0]);
    }

    /**
     * 保存功能权限委托设置
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveResourceComission(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String actionCode = ActionDefinition.SYS_UPDATE_COMMISION;
        CommisionForm cfForm = (CommisionForm) form;
        OperatorDTO consigner = cfForm.getCurrentUser();
        OperatorDTO consignee = cfForm.getConsignee();
        Long orgId = cfForm.getOrgId();
        Date startDate = cfForm.getStartDate();
        Date endDate = cfForm.getEndDate();

        /*
         * List consignedPrivileges = this.commissionManager.findCommissions(
         * consigner.getOperatorId(), consignee.getOperatorId(), orgId,
         * ConsignPermitDTO.RESOURCE_TYPE);
         */

        String code = consigner.getContact().getName() + "#"
                + consignee.getContact().getName();
        // 从页面获取选择的权限标识
        String[] selectedIds = request.getParameterValues("selectedIds");
        // 过滤选择的权限标识找出子项
        Long[] childIds = findSelectedChildIds(selectedIds);

        /*
         * // 获取新增的权限ID Long[] addResourceIds =
         * this.getAddResourceIds(consignedPrivileges, childIds); // 获取需要删除的委托记录
         * List removedCommisions = this.getRemovedConsignPrivileges(
         * consignedPrivileges, childIds);
         */
        try {
            /*
             * List addCommisions = this.createConsignPrivileges(consigner,
             * consignee, orgId, startDate, endDate,
             * ConsignPermitDTO.RESOURCE_TYPE, addResourceIds);
             * 
             * this.commissionManager.updateCommissions(addCommisions,
             * removedCommisions);
             */
            this.commissionManager.saveCommissions(consigner, consignee, orgId,
                    startDate, endDate, childIds);
        } catch (Exception e) {
            // 记录保存失败日志
            logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + code, e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
        return this.searchOperator(mapping, form, request, response);
    }

    // 获取添加的权限标识
    /*
     * private Long[] getAddResourceIds(List consignedResources, String[]
     * selectedIds) { List ret = new ArrayList();
     * 
     * if (selectedIds == null) { return new Long[0]; }
     * 
     * if (consignedResources == null || consignedResources.size() <= 0) { for
     * (int i = 0; i < selectedIds.length; i++) { String id = selectedIds[i]; if
     * (id != null && id.length() > 0) { ret.add(new Long(id)); } } } else { for
     * (int j = 0; j < selectedIds.length; j++) { String selId = selectedIds[j];
     * 
     * boolean in = false; for (Iterator iter = consignedResources.iterator();
     * iter .hasNext();) { ConsignPermitDTO element = (ConsignPermitDTO)
     * iter.next(); if (String.valueOf(element.getResourceId()).equals(selId)) {
     * in = true; break; } }
     * 
     * if (selId != null && selId.length() > 0 && !in) { ret.add(new
     * Long(selId)); } } }
     * 
     * return (Long[]) ret.toArray(new Long[0]); }
     */

    // 获取删除的委托记录
    /*
     * private List getRemovedConsignPrivileges(List consignedResources,
     * String[] selectedIds) { List ret = new ArrayList();
     * 
     * if (selectedIds == null || selectedIds.length <= 0) { return
     * consignedResources; } else { for (Iterator iter =
     * consignedResources.iterator(); iter.hasNext();) { ConsignPermitDTO
     * element = (ConsignPermitDTO) iter.next(); boolean in = false; for (int i =
     * 0; i < selectedIds.length; i++) { if
     * (selectedIds[i].equals(String.valueOf(element .getResourceId()))) { in =
     * true; break; } }
     * 
     * if (!in) { ret.add(element); } } }
     * 
     * return ret; }
     */

    // 构建委托记录
    /*
     * private List createConsignPrivileges(OperatorDTO consigner, OperatorDTO
     * consignee, Long orgId, Date validTime, Date expireTime, int resourceType,
     * Long[] resourceIds) { List result = new ArrayList(); for (int i = 0; i <
     * resourceIds.length; i++) { ConsignPermitDTO newConsign = new
     * ConsignPermitDTO();
     * newConsign.setConsignerId(consigner.getOperatorId().longValue());
     * newConsign.setConsignerOrgId(consigner.getOrg().getOrgId());
     * newConsign.setConsigneeId(consignee.getOperatorId());
     * newConsign.setConsigneeOrgId(consignee.getOrg().getOrgId());
     * newConsign.setOrgId(orgId.longValue());
     * newConsign.setExpireTime(expireTime); newConsign.setValidTime(validTime);
     * newConsign.setResourceId(resourceIds[i].longValue());
     * newConsign.setResourceType(resourceType);
     * 
     * result.add(newConsign); } return result; }
     */

    /**
     * 获取委托者和委托对象的可访问组织的交集
     * 
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    private List getAccessOrgs(ActionForm form, HttpServletRequest request)
            throws Exception {
        CommisionForm cfForm = (CommisionForm) form;
        OperatorDTO consignee = cfForm.getConsignee();
        List accessOrgs = OperatorSessionHelper.getAccessOrgsOfLoginOp(request);
        List consigneeAccessOrgs = this.orgManager
                .findAllAccessOrgsForOperatorIncludeChildren(consignee
                        .getOperatorId());

        List<OrganizationDTO> result = new ArrayList<OrganizationDTO>();
        for (Iterator iter = accessOrgs.iterator(); iter.hasNext();) {
            OrganizationDTO org = (OrganizationDTO) iter.next();
            for (Iterator iterator = consigneeAccessOrgs.iterator(); iterator
                    .hasNext();) {
                OrganizationDTO element = (OrganizationDTO) iterator.next();
                if (org.getOrgId().equals(element.getOrgId())) {
                    result.add(org);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 查询委托记录
     * 
     * @struts.tiles name="privilege.consign.list.commission"
     *               extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/commission/searchCommission.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchCommissions(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CommisionForm cfForm = (CommisionForm) form;
        OperatorDTO consigner = cfForm.getCurrentUser();
        List result = this.commissionManager.findCommissions(consigner
                .getOperatorId(), cfForm.getConsignee().getOperatorId());

        request.setAttribute("commissionList", result);
        return mapping.findForward("listCommission");
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recoverAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommisionForm cfForm = (CommisionForm) form;
        OperatorDTO consigner = cfForm.getCurrentUser();
        String consigneeId = (String) request.getParameter("consigneeId");
        this.commissionManager.removeCommission(consigner.getOperatorId(), Long
                .valueOf(consigneeId));
        return this.searchCommissions(mapping, form, request, response);
    }

    /**
     * 更新委托信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateCommission(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String actionCode = ActionDefinition.SYS_UPDATE_COMMISION;
        CommisionForm cfForm = (CommisionForm) form;
        ConsignPermitDTO cp = cfForm.getConsignPrivilege();
        try {
            this.commissionManager.updateCommission(cp);
        } catch (Exception e) {
            // 记录更新失败日志
            logger.log(request, actionCode, cp.getConsignId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + cp.getConsignId() + "", e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, cp.getConsignId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return searchCommissions(mapping, form, request, response);
    }

    /**
     * 回收权限
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward reclaim(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String actionCode = ActionDefinition.SYS_DELETE_COMMISION;
        String[] selectedIds = request.getParameterValues("selectedIds");
        StringBuffer strBuffer = new StringBuffer("");
        for (int i = 0; i < selectedIds.length; i++) {
            strBuffer.append(selectedIds[i] + ",");
        }
        try {
            commissionManager.removeCommission(selectedIds);
        } catch (Exception e) {
            // 记录回收失败日志
            logger.log(request, actionCode, strBuffer.toString(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + strBuffer.toString(), e);
            throw e;
        }

        // 记录操作日志
        logger.log(request, actionCode, strBuffer.toString(),
                ActionDefinition.ACTION_SUCCESS);
        return this.searchCommissions(mapping, form, request, response);
    }

    /**
     * 编辑委托记录。
     * 
     * @struts.tiles name="privilege.consign.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/commission/editCommission.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editCommission(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //CommisionForm cfForm = (CommisionForm) form;

        /*ConsignPermitDTO cp = cfForm.getConsignPrivilege();
        Long resourceId = new Long(cp.getResourceId());
        ResourceDTO resource = this.resourceRepository.getResource(resourceId);
        cfForm.setResourceName(resource.getTitle());*/

        return mapping.findForward("edit");
    }
}
