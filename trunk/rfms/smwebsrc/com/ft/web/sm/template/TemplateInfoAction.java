package com.ft.web.sm.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.TemplateManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.web.sm.BaseAction;

/**
 * 模板信息管理Action类
 * 
 * @version 1.0
 * 
 * @spring.bean id="templateInfoAction"
 * 
 * @struts.action name="templateForm" path="/template" scope="request"
 *                input="sm.template.info.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.template.info.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/template/listTemplateInfo.jsp"
 * 
 * @struts.action-forward name="create" path="sm.template.info.create"
 * @struts.action-forward name="edit" path="sm.template.info.edit"
 * @struts.action-forward name="view" path="sm.template.info.view"
 * 
 */
public class TemplateInfoAction extends BaseAction {

    @SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(TemplateInfoAction.class);

    @SuppressWarnings("unused")
	private TemplateManager templateManager;

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /*
        List categoryList = templateManager.findAllTemplateCategory();
        request.setAttribute("categoryList", categoryList);
        */
        return mapping.getInputForward();
    }

    /**
     * 查看模板信息
     * 
     * @struts.tiles name="sm.template.info.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/viewTemplateInfo.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /*
        TemplateForm templateForm = (TemplateForm) form;
        List fileInfoList = templateManager.findTemplateFileInfo(templateForm
                .getTemplateInfo().getTemplateId());
        request.setAttribute("fileInfoList", fileInfoList);
        */
        return mapping.findForward("view");
    }

    /**
     * 跳转到新建模板信息页面
     * 
     * @struts.tiles name="sm.template.info.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/createTemplateInfo.jsp"
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
        return mapping.findForward("create");
    }

    /**
     * 跳转到编辑模板信息页面
     * 
     * @struts.tiles name="sm.template.info.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/editTemplateInfo.jsp"
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
     * 保存模板信息
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
        /*
        TemplateForm templateForm = (TemplateForm) form;
        TemplateInfoDTO template = templateForm.getTemplateInfo();
        Long id;
        try {
            id = templateManager.saveOrUpdateTemplateInfo(template);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_ADD_TEMPLATE_INFO
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO, id
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("/sm/template.do?act=view"
                + "&templateId=" + id.toString());
                */
        return null;
    }

    /**
     * 更新模板信息
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
        /*
        TemplateForm templateForm = (TemplateForm) form;
        TemplateInfoDTO template = templateForm.getTemplateInfo();
        Long id;
        try {
            id = templateManager.saveOrUpdateTemplateInfo(template);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_TEMPLATE_INFO, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_UPDATE_TEMPLATE_INFO
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_UPDATE_TEMPLATE_INFO, id
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("/sm/template.do?act=view"
                + "&templateId=" + id.toString());
         */
        return null;
    }
    
    /**
     * 删除模板信息
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
        /*
        TemplateForm templateForm = (TemplateForm) form;
        TemplateInfoDTO template = templateForm.getTemplateInfo();
        Long id = template.getTemplateId();
        try {
            templateManager.deleteTemplateInfo(template);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_INFO, "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_DELETE_TEMPLATE_INFO
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_INFO, id
                .longValue()
                + "", ActionDefinition.ACTION_SUCCESS);
                */
        return this.getRedirectForwardAction("template.do");
    }

    /**
     * @spring.property ref="templateManager"
     * @param templateManager
     *                The templateManager to set.
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }

}
