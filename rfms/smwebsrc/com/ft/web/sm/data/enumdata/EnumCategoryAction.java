package com.ft.web.sm.data.enumdata;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.ft.busi.dto.AppDTO;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.EnumManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.EnumCategoryDTO;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 枚举数据类别页面控制类.
 * 
 * @version 1.0
 * 
 * @struts.action path="/enumCategory" name="enumCategoryForm" scope="request"
 *                validate="false" parameter="act" input="sm.enum.category.edit"
 * 
 * @struts.action-forward name="edit" path="sm.enum.category.edit"
 * 
 * @struts.tiles name="sm.enum.category.edit" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/enum/editEnumCategory.jsp"
 * 
 * @spring.bean id="enumCategoryAction"
 * 
 */
public class EnumCategoryAction extends BaseAction {
    private static Log logger = LogFactory.getLog(EnumCategoryAction.class);

    private EnumManager enumManager;

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 跳转到创建EnumCategory页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * 保存EnumCategory数据
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
        EnumCategoryForm categoryForm = (EnumCategoryForm) form;
        EnumCategoryDTO category = categoryForm.getEnumCategory();

        Long groupId = categoryForm.getGroupId();
        String actionCode = ActionDefinition.SYS_UPDATE_ENUMCATEGORY;
        String flag = XmlTreeNode.UPDATE_NODE_FLAG;
        if (category.getCategoryId() == null
                || category.getCategoryId().longValue() <= 0) {
            actionCode = ActionDefinition.SYS_ADD_ENUMCATEGORY;
            flag = XmlTreeNode.ADD_NODE_FLAG;
        }

        // //保存受理记录
        AppRequest appRequest = categoryForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        Long categoryId = null;
        try {
            if (category.getCategoryId() == null
                    || category.getCategoryId().longValue() <= 0) {
                category.setGroupId(groupId);
                category.setCreateDate(new Date());
                category.setOperatorId(categoryForm.getCurrentUser()
                        .getOperatorId().longValue());
                category.setOrgId(categoryForm.getCurrentUser().getOrg()
                        .getOrgId());
                category.setLoginOrgId(OperatorSessionHelper.getLoginOrg(
                        request).getOrgId().longValue());
                category.setAppId(appRequest.getAppId());
                categoryId = this.enumManager.saveEnumCategory(category);
            } else {
                this.enumManager.updatenumCategory(category, appRequest);
                categoryId = category.getCategoryId();
            }
        } catch (Exception ex) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), ex);

            throw ex;
        }

        // 操作日志
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return this
                .getRedirectForwardAction("enumCategory.do?act=edit&categoryId="
                        + categoryId + "&flag=" + flag);
    }

    /**
     * 跳转到编辑EnumCategory页面
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

    /**
     * 删除EnumCategory数据
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
        EnumCategoryForm categoryForm = (EnumCategoryForm) form;
        EnumCategoryDTO category = categoryForm.getEnumCategory();
        Long groupId = category.getGroupId();
        Long categoryId = category.getCategoryId();
        String flag = XmlTreeNode.DELETE_NODE_FLAG;

        String actionCode = ActionDefinition.SYS_DELETE_ENUMCATEGORY;
        ////保存受理记录
        AppRequest appRequest = categoryForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        if (null != category) {

            try {
                this.enumManager.deleteEnumCategory(categoryId, appRequest);
            } catch (Exception ex) {
                logger.log(request, actionCode, "" + appRequest.getAppId(),
                        ActionDefinition.ACTION_FAIL);
                logger.error("Excute action "
                        + ActionDefinition.SYS_DELETE_ENUMCATEGORY
                        + " failed,action sequence =" + appRequest.getAppId(),
                        ex);
                throw ex;
            }

            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);

        }

        return this
                .getRedirectForwardAction("enumGroup.do?act=edit&groupId="
                        + groupId + "&flag=" + flag + "&deletedId="
                        + categoryId);
    }

    /**
     * @spring.property ref="enumManager"
     * 
     */
    public void setEnumManager(EnumManager enumManager) {
        this.enumManager = enumManager;
    }
}
