package com.ft.web.sm.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.TemplateManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * 模板维护页面逻辑类。
 * 
 * @spring.bean id="templateAction"
 * 
 * @struts.action name="templateForm" path="/template" scope="request"
 *                input="sm.template.list" parameter="act" validate="false"
 * 
 * @struts.action-forward name="create" path="sm.template.create"
 * @struts.action-forward name="view" path="sm.template.view"
 * @struts.action-forward name="edit" path="sm.template.edit"
 * 
 * @struts.tiles name="sm.template.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/template/listTemplate.jsp"
 * 
 * @version 1.0
 */
public class TemplateAction extends BaseAction{
    private static Log logger = LogFactory.getLog(TemplateAction.class);
    
    private TemplateManager templateManager; 
    
    /**
     * @spring.property ref="templateManager"
     * @param templateManager
     *                The templateManager to set.
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }
    
    /* (non-Javadoc)
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
    
    /**
     * @struts.tiles name="sm.template.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/addTemplate.jsp"
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
     * 新建模板。
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
        TemplateForm templateForm = (TemplateForm)form;
        FormFile formFile = templateForm.getFile();
        if(formFile != null && formFile.getFileSize() > 2*1024*1024){
            ActionMessagesHelper.saveRequestMessage(request, "maxLengthExceeded");
            return mapping.findForward("create");
        }
        
        //增加模板
        String actionCode = ActionDefinition.SYS_ADD_TEMPLATE_INFO;
        
        TemplateDTO template = templateForm.getTemplate();
        
        //保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        
        //Long[] orgIds = this.getOrgIdFromParameter(request);
        
        
        try{
            if(formFile == null || formFile.getFileSize() <=0){
                this.templateManager.saveTempate(template,appRequest);
            }else{
                TemplateFileDTO templateFile = new TemplateFileDTO();
                templateFile.getTemplateFile().setFileContent(formFile.getFileData());
                this.templateManager.saveTemplate(template,appRequest, templateFile);
            }
            logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO,"" + appRequest.getAppId(),ActionDefinition.ACTION_SUCCESS);
        }catch(Exception ex){
            logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO,"" + appRequest.getAppId(),ActionDefinition.ACTION_FAIL);
            throw ex;
        }
        
        return mapping.getInputForward();
    }
    
    /**
     * 查看模板。
     * 
     * @struts.tiles name="sm.template.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/viewTemplate.jsp"
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
        return mapping.findForward("view");
    }
    
    /**
     * 从请求参数中获取适用组织ID。
     * @param request
     * @return
     */
    /*private Long[] getOrgIdFromParameter(HttpServletRequest request){
        String[] orgIds = request.getParameterValues("orgIds");
        List orgIdList = new ArrayList();
        for (int i = 0; i < orgIds.length; i++) {
            String orgId = orgIds[i];
            if (orgId != null && orgId.length() > 0) {
                orgIdList.add(new Long(orgId));
            }
        }
        
        return (Long[])orgIdList.toArray(new Long[0]);
    }
    */
    
    /**
     * 编辑模板。
     * 
     * @struts.tiles name="sm.template.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/editTemplate.jsp"
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
     * 更新模板。
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
        //增加模板
        String actionCode = ActionDefinition.SYS_UPDATE_TEMPLATE_INFO;
        TemplateForm templateForm = (TemplateForm)form;
        TemplateDTO template = templateForm.getTemplate();
        
        //保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        
        //Long[] orgIds = this.getOrgIdFromParameter(request);
        
        try{
            this.templateManager.updateTemplate(template,appRequest);
            logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO,"" + appRequest.getAppId(),ActionDefinition.ACTION_SUCCESS);
        }catch(Exception ex){
            logger.log(request, ActionDefinition.SYS_ADD_TEMPLATE_INFO,"" + appRequest.getAppId(),ActionDefinition.ACTION_FAIL);
            throw ex;
        }
        
        return mapping.getInputForward();
    }
}
