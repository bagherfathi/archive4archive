package com.ft.web.sm.priv.org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.OrgManager;
import com.ft.busi.sm.model.RegionManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgRepository;
import com.ft.common.session.OperatorSessionHandler;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.sm.entity.Region;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 组织维护类
 * 
 * @spring.bean name="orgAction"
 * 
 * @struts.action name="orgForm" path="/org" scope="request" input="sm.org.list"
 *                parameter="act" validate="false"
 * 
 * @struts.action-forward name="create" path="sm.org.create"
 * @struts.action-forward name="view" path="sm.org.view"
 * @struts.action-forward name="edit" path="sm.org.edit"
 * @struts.action-forward name="regionList" path="sm.org.region.list"
 * @struts.action-forward name="regionConfig" path="sm.org.region.config"
 * 
 * @struts.tiles name="sm.org.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/org/listOrg.jsp"
 * 
 * @version 1.0
 */
public class OrgAction extends BaseAction {

    private static Log logger = LogFactory.getLog(OrgAction.class);

    private OrgManager orgManager;

    private RegionManager regionManager;

   // private SyncProxy syncProxy;

    private OrgRepository orgRepository;
    
    private OperatorSessionHandler operatorSessionHandler;

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // OrgForm orgForm = (OrgForm) form;
        // orgManager.findAllAccessOrgsForOperator(orgForm.getCurrentUser()
        // .getOperatorId());
        //OrganizationDTO org = orgManager.findRootOrg();
        
        OrganizationDTO org = OperatorSessionHelper.getLoginOrg(request);
        if(org.getParent() == null){
            org.setParent(orgRepository.getOrgDTOById(org.getParentOrgId()).getOrg());
        }
        request.setAttribute("org", org);
        return mapping.findForward("view");
    }

    /**
     * @struts.tiles name="sm.org.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/org/viewOrg.jsp"
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("view");
    }

    /**
     * @struts.tiles name="sm.org.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/org/createOrg.jsp"
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("create");
    }

    /**
     * @struts.tiles name="sm.org.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/org/editOrg.jsp"
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 保存组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        String flag = XmlTreeNode.ADD_NODE_FLAG;
        try {
            // 和SSO同步
            //this.syncProxy.addOrg(org, orgForm.getParentOrg());
            // Long id = orgManager.saveOrg(org, orgForm.getParentOrg());
            Long id = this.orgRepository.save(org, orgForm.getParentOrg());
            org.setOrgId(id);
            
            operatorSessionHandler.reset(request, response, OperatorSessionHelper.getLoginOrg(request).getOrgId());
            
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_ORG, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_ADD_ORG
                    + " failed,action sequence =" + "", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_ADD_ORG, org.getCode(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("org.do?act=view&orgId="
                + org.getOrgId().longValue() + "&flag=" + flag);
    }

    /**
     * 更新组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        try {
            // 和SSO同步
            //this.syncProxy.updateOrg(org, orgForm.getParentOrg());
            // orgManager.saveOrg(org, orgForm.getParentOrg());
            this.orgRepository.update(org, orgForm.getParentOrg());
            operatorSessionHandler.reset(request, response, OperatorSessionHelper.getLoginOrg(request).getOrgId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_ORG, org.getCode(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_UPDATE_ORG
                    + " failed,action sequence =" + org.getCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_UPDATE_ORG, org.getCode(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("org.do?act=view&orgId="
                + org.getOrgId().longValue() + "&flag=" + flag);
    }

    /**
     * 更改父节点
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward changeParent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        String flag = XmlTreeNode.MOVE_NODE_FLAG;
        try {
            // 和SSO同步
            //this.syncProxy.updateOrg(org, orgForm.getParentOrg());
            // orgManager.saveOrg(org, orgForm.getParentOrg());
            this.orgRepository.update(org, orgForm.getParentOrg());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_CHANGE_PARENT_ORG, org
                    .getCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_CHANGE_PARENT_ORG
                    + " failed,action sequence =" + org.getCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_CHANGE_PARENT_ORG, org
                .getCode(), ActionDefinition.ACTION_SUCCESS);
        //return mapping.findForward("view");
        return this.getRedirectForwardAction("org.do?act=view&orgId="
                + org.getOrgId().longValue() + "&flag=" + flag);
    }

    /**
     * 跳转到可访问区域列表
     * 
     * @struts.tiles name="sm.org.region.list" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/org/listOrgRegion.jsp"
     */
    public ActionForward regionList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        List orgRegions = this.regionManager.findAccessRegionByOrgId(orgForm
                .getOrg().getOrgId());
        request.setAttribute("orgRegions", orgRegions);
        return mapping.findForward("regionList");
    }

    /**
     * 跳转到设置可访问区域页面
     * 
     * @struts.tiles name="sm.org.region.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/org/configOrgRegion.jsp"
     */
    public ActionForward regionConfig(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        List regions = this.regionManager.findRefionsOfOrg(org
                .getOrgId());
//        List childrenRegions = this.regionManager
//                .findAccessRegionOfChildrenOrgByOrgId(org.getPath());
//        List parentRegions = null;
//        boolean isRoot = false;
//        if (org.getOrgId().longValue() != (org.getParent().getOrgId())) {
//            parentRegions = this.regionManager
//                    .findAccessRegionByOrgId(new Long(org.getParent()
//                            .getOrgId()));
//        } else {
//            isRoot = true;
//        }
//        request.setAttribute("isRoot", new Boolean(isRoot));
//        request.setAttribute("childrenRegions", childrenRegions);
//        request.setAttribute("parentRegions", parentRegions);
        request.setAttribute("regions", regions);
        return mapping.findForward("regionConfig");
    }

    /**
     * 保存区域设置
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward regionSave(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        try {
            orgManager.saveOrgAccessRegion(org, filterRegion(orgForm
                    .getRegions()));
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_ORG_REGION, org
                    .getCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_SAVE_ORG_REGION
                    + " failed,action sequence =" + org.getCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_ORG_REGION,
                org.getCode(), ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("org.do?act=regionList&orgId="
                + org.getOrgId().longValue());
    }

    /**
     * 过滤区域信息
     * 
     * @param regionList
     * @return
     */
    private Region[] filterRegion(Region[] regions) {
        List<Region> result = new ArrayList<Region>();

        for (int i = 0; i < regions.length; i++) {
            boolean save = true;
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Region element = (Region) iter.next();
                if (regions[i].getRegionPath().startsWith(
                        element.getRegionPath())) {
                    save = false;
                    break;
                }
            }
            if (save) {
                result.add(regions[i]);
            }
        }
        return (Region[]) result.toArray(new Region[result.size()]);

    }

    /**
     * 禁止多个组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        String[] ids = request.getParameterValues("orgIds");
        String allId = "";
        for (int i = 0; i < ids.length; i++) {
            allId = ids[i] + ",";
        }

        OrganizationDTO[] orgs = orgForm.getOrgs();
        try {
            // 和SSO同步
           // for (int i = 0; i < orgs.length; i++) {
                //this.syncProxy.disableOrganization(orgs[i]);
           // }
            // orgManager.disableOrg(orgs);
            this.orgRepository.disableOrg(orgs);
            operatorSessionHandler.reset(request, response, OperatorSessionHelper.getLoginOrg(request).getOrgId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DISABLE_ORG, allId,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DISABLE_ORG
                    + " failed,action sequence =" + allId, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DISABLE_ORG, allId,
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 禁止单个组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward disableSingle(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        try {
            // 和SSO同步
            //this.syncProxy.disableOrganization(org);
            // orgManager.disableOrg(new OrganizationDTO[] { org });
            this.orgRepository.disableOrg(new OrganizationDTO[] { org });
            operatorSessionHandler.reset(request, response, OperatorSessionHelper.getLoginOrg(request).getOrgId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DISABLE_ORG,
                    org.getCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_DISABLE_ORG
                    + " failed,action sequence =" + org.getCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DISABLE_ORG, org.getCode(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("org.do?act=view&orgId="
                + org.getOrgId().longValue() + "&flag=" + flag);
        // return mapping.findForward("view");
    }

    /**
     * 解禁组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recover(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OrgForm orgForm = (OrgForm) form;
        OrganizationDTO org = orgForm.getOrg();
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        try {
            // 和SSO同步
            //this.syncProxy.enableOrganization(org);
            // orgManager.enableOrg(org);
            this.orgRepository.enableOrg(org);
            operatorSessionHandler.reset(request, response, OperatorSessionHelper.getLoginOrg(request).getOrgId());
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_RECOVER_ORG,
                    org.getCode(), ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_RECOVER_ORG
                    + " failed,action sequence =" + org.getCode(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_RECOVER_ORG, org.getCode(),
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("org.do?act=view&orgId="
                + org.getOrgId().longValue() + "&flag=" + flag);
        // return mapping.findForward("view");
    }

    /**
     * @spring.property ref="orgManager"
     * @param orgManager
     */
    public void setOrgManager(OrgManager orgManager) {
        this.orgManager = orgManager;
    }

    /**
     * @spring.property ref="syncProxy"
     * @param syncProxy
     *            The syncProxy to set.
     */
    //public void setSyncProxy(SyncProxy syncProxy) {
        //this.syncProxy = syncProxy;
    //}

    /**
     * @spring.property ref="regionManager"
     * @param regionManager
     *            the regionManager to set
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * @spring.property ref="orgRepository"
     * @param orgRepository
     *            the orgRepository to set
     */
    public void setOrgRepository(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>"+orgRepository);
    }

    /**
     * @spring.property ref="opSessionHandler"
     * @param operatorSessionHandler the operatorSessionHandler to set
     */
    public void setOperatorSessionHandler(
            OperatorSessionHandler operatorSessionHandler) {
        this.operatorSessionHandler = operatorSessionHandler;
    }
    
    
}
