package com.ft.web.sm.data.enumdata;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.EnumManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.EnumGroupDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * Enum������ά��ҳ�������
 * 
 * @struts.action path="/enumGroup" name="enumGroupForm" scope="request"
 *                validate="false" parameter="act" input="sm.enum.group.list"
 * 
 * @struts.action-forward name="edit" path="sm.enum.group.edit"
 * 
 * @struts.action-forward name="import" path="sm.enum.group.import"
 * 
 * @struts.action-forward name="report" path="sm.enum.group.import.report"
 * 
 * @struts.tiles name="sm.enum.group.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/enum/listEnumGroup.jsp"
 * 
 * @spring.bean id="enumGroupAction"
 * 
 * @version 1.0
 */
public class EnumGroupAction extends BaseAction {

    /* ��¼��־��Ϣ */
    private static Log logger = LogFactory.getLog(EnumGroupAction.class);

    private EnumManager enumManager;

    /**
     * @spring.property ref="enumManager"
     * 
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }

    /**
     * Ĭ�Ϸ���
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * ��ת������ҳ��
     * 
     * @struts.tiles name="sm.enum.group.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/enum/editEnumGroup.jsp"
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * ����EnumDataGroup
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
        EnumGroupForm groupForm = (EnumGroupForm) form;
        
        EnumGroupDTO group = groupForm.getEnumGroup();

        String actionCode = ActionDefinition.SYS_UPDATE_ENUMGROUP;
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;

        if (group.getGroupId() == null || group.getGroupId().longValue() <= 0) {
            actionCode = ActionDefinition.SYS_ADD_ENUMGROUP;
            flag = XmlTreeNode.ADD_NODE_FLAG;
        }

        // //���������¼
        AppRequest appRequest = groupForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        Long groupId = null;
        try {
            if (group.getGroupId() == null
                    || group.getGroupId().longValue() <= 0) {
                group.setCreateDate(new Date());
                group.setOperatorId(groupForm.getCurrentUser().getOperatorId()
                        .longValue());
                group.setOrgId(groupForm.getCurrentUser().getOrg().getOrgId());
                group.setLoginOrgId(OperatorSessionHelper
                        .getLoginOrg(request).getOrgId().longValue());
                group.setAppId(appRequest.getAppId());
                groupId = this.enumManager.saveEnumDataGroup(group);
            } else {
                this.enumManager.updateEnumDataGroup(group, appRequest);
                groupId = group.getGroupId();
            }
        } catch (Exception ex) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), ex);

            throw ex;
        }

        // ������־
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return this
                .getRedirectForwardAction("enumGroup.do?act=edit&groupId="
                        + groupId + "&flag=" + flag);
    }

    /**
     * ɾ��EnumDataGroup
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EnumGroupForm groupForm = (EnumGroupForm) form;
        EnumGroupDTO group = groupForm.getEnumGroup();
        Long groupId = group.getGroupId();

        String actionCode = ActionDefinition.SYS_DELETE_ENUMGROUP;
        ////���������¼
        AppRequest appRequest = groupForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        if (null != group) {

            try {
                this.enumManager.deleteEnumDateGroup(groupId, appRequest);
            } catch (Exception ex) {
                logger.log(request, actionCode, "" + appRequest.getAppId(),
                        ActionDefinition.ACTION_FAIL);
                logger.error("Excute action " + actionCode
                        + " failed,action sequence =" + appRequest.getAppId(),
                        ex);
                throw ex;
            }

            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);

        }
        // return mapping.getInputForward();
        return this
                .getRedirectForwardAction("enumGroup.do?flag=delete&deletedId="
                        + groupId.toString());
    }

    /**
     * ��ת���༭ҳ��
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }
}
