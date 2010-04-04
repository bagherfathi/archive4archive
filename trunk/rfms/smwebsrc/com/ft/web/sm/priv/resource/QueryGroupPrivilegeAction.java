package com.ft.web.sm.priv.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.security.ResourceTreeBuilder;
import com.ft.common.session.PermissionChecker;
import com.ft.sm.dto.GroupDTO;
import com.ft.web.sm.BaseAction;

/**
 * 操作员组权限查询
 * 
 * @spring.bean id="queryGroupPrivilegeAction"
 * 
 * @struts.action path="/queryGroupPrivilege" name="queryGroupPrivilegeForm"
 *                scope="request" input="sm.query.group.privilege"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.query.group.privilege" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/privilege/queryGroupPrivilege.jsp"
 * 
 * @struts.action-forward name="list" path="sm.query.group.privilege.list"
 * 
 * @version 1.0
 */
public class QueryGroupPrivilegeAction extends BaseAction {

    /**
     * 功能权限维护类
     * 
     */
    private ResourceManager resourceManager;

    private ResourceTreeBuilder resourceTreeBuilder;

    /**
     * @spring.property ref="resourceManager"
     * @param resourceManager
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * 默认方法跳转到首页
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * @spring.property ref="resourceTreeBuilder"
     */
    public void setResourceTreeBuilder(ResourceTreeBuilder resourceTreeBuilder) {
        this.resourceTreeBuilder = resourceTreeBuilder;
    }

    /**
     * 查询
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.getInputForward();
    }

    /**
     * 操作员组权限查询
     * 
     * @struts.tiles name="sm.query.group.privilege.list" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/privilege/listGroupPrivilege.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward queryGroupPrivilege(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        QueryGroupPrivilegeForm gform = (QueryGroupPrivilegeForm) form;
        GroupDTO group = gform.getGroup();
        // 根据操作员组获得所有的功能权限
        List resourceList = this.resourceManager.findAllResourceOfGroup(group
                .getGroupId());
        // 创建功能权限树
        PermissionChecker checker = new PermissionChecker();
        checker.addPermission(resourceList);
        request.setAttribute("root", this.resourceTreeBuilder
                .buildTreeNode(checker));
        request.setAttribute("resources", resourceList);
        return mapping.findForward("list");
    }
}
