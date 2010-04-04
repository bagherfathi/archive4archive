package com.ft.web.sm.priv.group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.OrgTreeBuilder;
import com.ft.sm.dto.GroupDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.ActionDefinition;

/**
 * 操作员组的组织维护页面控制类
 * 
 * @spring.bean id="groupOrgAction"
 * 
 * @struts.action path="/groupOrg" name="groupOrgForm" scope="request"
 *                validate="false" parameter="act" input="sm.group.org.list"
 * 
 * @struts.tiles name="sm.group.org.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/group/listGroupOrg.jsp"
 * 
 * @struts.action-forward path="sm.group.org.config" name="configOrg"
 * 
 * @version 1.0
 */
public class GroupOrgAction extends GroupBaseAction {

    private static Log logger = LogFactory.getLog(GroupOrgAction.class);

    @SuppressWarnings("unused")
	private OrgTreeBuilder orgTreeBuilder;

    /**
     * 操作员组的可访问组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GroupOrgForm groupForm = (GroupOrgForm) form;
        List accessOrg = this.orgManager.findAcessOrgByGroupId(groupForm
                .getGroup().getGroupId());
        request.setAttribute("accessOrg", accessOrg);
        return mapping.getInputForward();
    }

    /**
     * @struts.tiles name="sm.group.org.config" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/group/groupOrgConfig.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward configOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GroupOrgForm groupForm = (GroupOrgForm) form;
        List accessOrgs = this.orgManager.findAcessOrgByGroupId(groupForm
                .getGroup().getGroupId());

//        TreeNode treenode = this.orgTreeBuilder.buildOrgNode(
//				OperatorSessionHelper.getAccessOrgsOfLoginOp(request), "可选组织");

        request.setAttribute("accessOrgs", accessOrgs);
//        request.setAttribute("root", treenode);

        return mapping.findForward("configOrg");
    }

    /**
     * 保存可访问组织
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GroupOrgForm groupForm = (GroupOrgForm) form;
        GroupDTO group = groupForm.getGroup();
        try {
            this.groupManager.saveGroupAccessOrg(groupForm.getCurrentUser(),
                    filterOrgs(groupForm.getOrgs()), group);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_SAVE_GROUP_ORG, group
                    .getGroupId().longValue()
                    + "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + ActionDefinition.SYS_SAVE_GROUP_ORG
                    + " failed,action sequence ="
                    + group.getGroupId().longValue(), ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_SAVE_GROUP_ORG, group
                .getGroupId().longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("group.do?act=view&selectedPane=org&id="
                        + group.getGroupId().longValue());
        // return getRedirectForwardAction("groupOrg.do?id="
        // + group.getGroupId().longValue());
    }

    /**
     * 过滤组织信息
     * 
     * @param orgDTOs
     * @return
     */
    private OrganizationDTO[] filterOrgs(OrganizationDTO[] orgDTOs) {
        List<OrganizationDTO> result = new ArrayList<OrganizationDTO>();

        for (int i = 0; i < orgDTOs.length; i++) {
            boolean save = true;
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                OrganizationDTO element = (OrganizationDTO) iter.next();
                if (orgDTOs[i].getPath().startsWith(element.getPath())) {
                    save = false;
                    break;
                }
            }
            if (save) {
                result.add(orgDTOs[i]);
            }
        }
        return (OrganizationDTO[]) result.toArray(new OrganizationDTO[result
                .size()]);

    }

    /**
     * @spring.property ref="orgTreeBuilder"
     * @param orgTreeBuilder
     */
    public void setOrgTreeBuilder(OrgTreeBuilder orgTreeBuilder) {
        this.orgTreeBuilder = orgTreeBuilder;
    }
}
