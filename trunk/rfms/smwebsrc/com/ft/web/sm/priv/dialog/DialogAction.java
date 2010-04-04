package com.ft.web.sm.priv.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ft.busi.sm.model.OperatorManager;
import com.ft.busi.sm.model.RegionManager;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.security.DataResourceTreeBuilder;
import com.ft.common.security.OrgRegionTreeBuilder;
import com.ft.common.security.OrgTreeBuilder;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.RegionDTO;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeNode;

/**
 * 弹出页面控制类
 * 
 * @version 1.0
 * 
 * @spring.bean id="dialogAction"
 * 
 * @struts.action name="dialogForm" path="/dialog" scope="request"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="dialog.select.org"
 *               page="/WEB-INF/jsp/sm/dialog/opOrgTree.jsp"
 * 
 * @struts.action-forward name="opOrg" path="dialog.select.org"
 * @struts.action-forward name="parentOrg" path="dialog.select.parent"
 * @struts.action-forward name="selLinkValue" path="dialog.select.link"
 * @struts.action-forward name="regionTree" path="dialog.select.region"
 * @struts.action-forward name="templateBind" path="sm.dialog.template.bind"
 * @struts.action-forward name="opAccessOrg"
 *                        path="sm.dialog.select.op.access.org"
 * @struts.action-forward name="resourceTree" path="dialog.select.resource"
 * @struts.action-forward name="dataResourceTree" path="dialog.select.dataresource"
 * @struts.action-forward name="orgRegionTree" path="dialog.select.org.region"
 * @struts.action-forward name="orgsTree" path="dialog.select.orgs.tree"
 * @struts.action-forward name="selLoginOrg" path="dialog.select.login.org"
 * @struts.action-forward name="selOp" path="dialog.select.op"
 * @struts.action-forward name="resourceOfRoleTree" path="dialog.select.role"
 * 
 */
public class DialogAction extends DispatchAction {

    private RegionManager regionManager;

    private OrgTreeBuilder orgTreeBuilder;
    
    private ResourceTreeBuilder resourceTreeBuilder;
    
    private DataResourceTreeBuilder dataResourceTreeBuilder;
    
    private OrgRegionTreeBuilder orgRegionTreeBuilder;
    
    private OperatorManager opManager;
    
    private ResourceManager resourceManager;
    
    

    /**
     * @spring.property ref="regionManager"
     * @param regionManager
     *                The regionManager to set.
     */
    public void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    /**
     * @spring.property ref="orgTreeBuilder"
     * @param orgTreeBuilder
     *                the orgTreeBuilder to set
     */
    public void setOrgTreeBuilder(OrgTreeBuilder orgTreeBuilder) {
        this.orgTreeBuilder = orgTreeBuilder;
    }
    
    /**
     * @spring.property ref="resourceTreeBuilder"
     * @param resourceTreeBuilder the resourceTreeBuilder to set
     */
    public void setResourceTreeBuilder(ResourceTreeBuilder resourceTreeBuilder) {
        this.resourceTreeBuilder = resourceTreeBuilder;
    }
    
    /**
     * @spring.property ref="dataResourceTreeBuilder"
     * @param dataResourceTreeBuilder the dataResourceTreeBuilder to set
     */
    public void setDataResourceTreeBuilder(
            DataResourceTreeBuilder dataResourceTreeBuilder) {
        this.dataResourceTreeBuilder = dataResourceTreeBuilder;
    }
    
    /**
     * @spring.property ref="orgRegionTreeBuilder"
     * @param orgRegionTreeBuilder the orgRegionTreeBuilder to set
     */
    public void setOrgRegionTreeBuilder(OrgRegionTreeBuilder orgRegionTreeBuilder) {
        this.orgRegionTreeBuilder = orgRegionTreeBuilder;
    }

    /**
     * @spring.property ref="operatorManager"
     * @param opManager the opManager to set
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }
    
    /**
     * @spring.property ref="resourceManager"
     * @param resourceManager the resourceManager to set
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * 弹出为操作员选择组织的页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward opOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("opOrg");
    }

    /**
     * 弹出当前操作员的可访问组织页面
     * 
     * @struts.tiles name="sm.dialog.select.op.access.org"
     *               page="/WEB-INF/jsp/sm/dialog/opAccessOrg.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward opAccessOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Boolean incChildren = Boolean.valueOf(request
                .getParameter("incChildren"));
        Boolean onlyCompany = Boolean.valueOf(request
                .getParameter("onlyCompany"));
        List orgs = OperatorSessionHelper.getAccessOrgsOfLoginOp(request);
        TreeNode root = orgTreeBuilder.buildAccessOrgTreeNode(incChildren
                .booleanValue(), onlyCompany.booleanValue(), orgs);
        request.setAttribute("root", root);
        return mapping.findForward("opAccessOrg");

    }

    /**
     * 弹出为组织选择父节点的页面
     * 
     * @struts.tiles name="dialog.select.parent"
     *               page="/WEB-INF/jsp/sm/dialog/orgTree.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward parentOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("parentOrg");
    }

    /**
     * 弹出为系统数据选择关联值的页面
     * 
     * @struts.tiles name="dialog.select.link"
     *               page="/WEB-INF/jsp/sm/dialog/enumTree.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selLinkValue(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("selLinkValue");
    }

    /**
     * 模板绑定页
     * 
     * @struts.tiles name="sm.dialog.template.bind"
     *               page="/WEB-INF/jsp/sm/dialog/templateBind.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward templateBind(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /*
        DialogForm dialogForm = (DialogForm) form;
        List fileInfos = templateManager.findTemplateFileInfo(dialogForm
                .getTemplateId());
        request.setAttribute("fileInfos", fileInfos);
        */
        return mapping.findForward("templateBind");
    }
    
    /**
     * 模板绑定页
     * 
     * @struts.tiles name="dialog.select.role"
     *               page="/WEB-INF/jsp/sm/dialog/resourceTree.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String roleId = request.getParameter("roleId");
        List resourceList = resourceManager.findResourceOfRole(new Long(roleId));
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(resourceList);
        TreeNode node = this.resourceTreeBuilder.buildTreeNode(checker);
        request.setAttribute("root", node);
        return mapping.findForward("resourceOfRoleTree");
    }

    /**
     * 弹出区域信息树
     * 
     * @struts.tiles name="dialog.select.region"
     *               page="/WEB-INF/jsp/sm/dialog/regionTree.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward regionTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Long regionId = new Long(1);
        Long level = new Long(0);
        Long style = new Long(1);
        String regionIdStr = request.getParameter("regionId");
        String levelStr = request.getParameter("level");
        String styleStr = request.getParameter("style");
        String inputName = request.getParameter("inputName");
        if (!regionIdStr.equals("null")) {
            regionId = Long.valueOf(request.getParameter("regionId"));
        }
        if (!levelStr.equals("null")) {
            level = Long.valueOf(request.getParameter("level"));
        }
        if (!styleStr.equals("null")) {
            style = Long.valueOf(request.getParameter("style"));
        }

        // RegionDTO region = this.regionManager.findRegionById(regionId);
        List regionList = this.regionManager.findRegionByParentId(regionId,
                true);
        TreeNode root = buildTreeNode(regionList, level);
        request.setAttribute("root", root);
        request.setAttribute("style", style);
        request.setAttribute("inputName", inputName);
        return mapping.findForward("regionTree");
    }

    /**
     * 构建区域数据树
     */
    private TreeNode buildTreeNode(List regionList, Long level) {
        BaseTree tree = new BaseTree();
        int loop = 1;
        int min = 0;
        RegionDTO region = new RegionDTO();

        List<RegionDTO> allRegion = new ArrayList<RegionDTO>();

        for (Iterator iter = regionList.iterator(); iter.hasNext();) {
            RegionDTO temp = (RegionDTO) iter.next();
            String[] str = temp.getRegionPath().split("#");
            int len = str.length;
            if (loop == 1) {
                min = len;
                region = temp;
                loop++;
            } else {
                if (len < min) {
                    min = len;
                    region = temp;
                }
            }
            // 判断显示级数
            if (level.longValue() == 0) {
                allRegion.add(temp);
            } else {
                if (len <= level.longValue()) {
                    allRegion.add(temp);
                }
            }
        }
        // 设置首节点
        BaseTreeNode root = new BaseTreeNode(region.getRegionId().toString(),
                region.getRegionName());
        root.setStatus(new Long(region.getStatus()).intValue());
        root.bindObject(region);
        tree.setRoot(root);
        List childs = findChildForAll(region, allRegion);
        buildRegionNode(root, childs, allRegion);
        return root;
    }

    /**
     * 根据region和所有的Region对象的列表，从中找到region的子项
     * 
     * @param region
     * @param allRegion
     * @return
     */
    private List findChildForAll(RegionDTO region, List allRegion) {
        List<RegionDTO> chidlren = new ArrayList<RegionDTO>();
        for (Iterator iter = allRegion.iterator(); iter.hasNext();) {
            RegionDTO child = (RegionDTO) iter.next();
            if (child.getParentId().equals(region.getRegionId())) {
                chidlren.add(child);
            }
        }
        return chidlren;
    }

    /**
     * 构建区域树节点,一个递归过程
     * 
     * @param rootNode
     * @param childList
     * @param allNode
     */
    private void buildRegionNode(BaseTreeNode rootNode, List childList,
            List allNode) {
        if (!childList.isEmpty()) {
            for (Iterator iter = childList.iterator(); iter.hasNext();) {
                RegionDTO region = (RegionDTO) iter.next();
                BaseTreeNode node = new BaseTreeNode(region.getRegionId()
                        .toString(), region.getRegionName());
                node.setStatus(new Long(region.getStatus()).intValue());
                node.bindObject(region);
                rootNode.addChildLast(node);
                List childs = findChildForAll(region, allNode);
                buildRegionNode(node, childs, allNode);
            }
        }

    }
    
    /**
     * @struts.tiles name="dialog.select.resource"
     *               page="/WEB-INF/jsp/sm/dialog/selectResource.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward resourceTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TreeNode root = this.resourceTreeBuilder.buildTreeNode(new HashMap());
        request.setAttribute("root", root);
        return mapping.findForward("resourceTree");
    }
    
    /**
     * @struts.tiles name="dialog.select.dataresource"
     *               page="/WEB-INF/jsp/sm/dialog/selectDataResource.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dataResourceTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TreeNode root = this.dataResourceTreeBuilder.buildTreeNode(new HashMap());
        request.setAttribute("root", root);
        return mapping.findForward("dataResourceTree");
    }
    
    /**
     * @struts.tiles name="dialog.select.org.region"
     *               page="/WEB-INF/jsp/sm/dialog/selectOrgRegion.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectOrgRegion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String orgIdStr = request.getParameter("orgId");
        
        if(orgIdStr == null || orgIdStr.length() <=0) throw new java.lang.IllegalArgumentException();
        
        Long orgId = new Long(orgIdStr);
        Map<String,Long> initParam = new HashMap<String,Long>();
        initParam.put("orgId", orgId);
        
        TreeNode root = this.orgRegionTreeBuilder.buildTreeNode(initParam);
        request.setAttribute("root", root);
        return mapping.findForward("orgRegionTree");
    }
    
    /**
     * 
     *  @struts.tiles name="dialog.select.orgs.tree"
     *               page="/WEB-INF/jsp/sm/dialog/orgsTree.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward orgsTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("orgsTree");
    }
    
    /**
     * 
     *  @struts.tiles name="dialog.select.login.org"
     *               page="/WEB-INF/jsp/sm/dialog/selLoginOrg.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selLoginOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("loginOrg", OperatorSessionHelper.getLoginOrg(request));
        return mapping.findForward("selLoginOrg");
    }
    
    /**
     * @struts.tiles name="dialog.select.op"
     *              page="/WEB-INF/jsp/sm/dialog/selectOp.jsp"
     * 
     */
    
    public ActionForward selOp(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String orgIdStr = request.getParameter("orgIds");
        if(orgIdStr.endsWith(",")){
            orgIdStr = orgIdStr.substring(0, orgIdStr.length()-1);
        }
        List ops = this.opManager.searchOperator(orgIdStr);
        request.setAttribute("ops", ops);
        return mapping.findForward("selOp");
    }
}
