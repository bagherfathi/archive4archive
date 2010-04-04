package com.ft.web.sm.template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.TemplateManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.TemplateDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * 模版绑定Action类。
 * 
 * @version 1.0
 * 
 * @spring.bean id="templateBindAction"
 * 
 * @struts.action name="templateBindForm" path="/templateBind" scope="request"
 *                input="sm.template.bind.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.template.bind.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/template/listTemplateBind.jsp"
 * 
 */
public class TemplateBindAction extends BaseAction {
    private static Log logger = LogFactory.getLog(TemplateBindAction.class);
    private TemplateManager templateManager;

    /**
     * 默认方法。
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TemplateBindForm bindForm = (TemplateBindForm) form;
        Long orgId = bindForm.getOrgId();
        if(orgId == null) {
            OrganizationDTO loginOrg = OperatorSessionHelper.getLoginOrg(request);
            orgId = loginOrg.getOrgId();
            
            //根组织不是分公司时，显示错误信息。
            if(loginOrg.getType() != OrganizationDTO.ORG_TYPE_COMPANY){
                ActionMessagesHelper.saveRequestMessage(request,"errors.not.company");
                bindForm.setOrgId(new Long(-1));
            }else{
                bindForm.setOrgId(orgId);
            }
        }
        bindForm.setNotBindList(new ArrayList());
        return mapping.getInputForward();
    }

    /**
     * 更改模板类别
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward changeCategory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TemplateBindForm bindForm = (TemplateBindForm) form;
        Long orgId = bindForm.getOrgId();
        String categoryCode = bindForm.getCategoryCode();

        // 查找模板类别中所有模板
        List templateList = this.templateManager
                .findTemplateOfCategory(categoryCode);
        List bindList = this.templateManager.findBindTemplateOfOrg(
                categoryCode, orgId);
        List<Object> notBindList = new ArrayList<Object>();

        for (Iterator iterator = templateList.iterator(); iterator.hasNext();) {
            TemplateDTO template = (TemplateDTO) iterator.next();
            boolean flag = false;
            for (Iterator iterator2 = bindList.iterator(); iterator2.hasNext();) {
                TemplateDTO bindTemplate = (TemplateDTO) iterator2.next();
                if (bindTemplate.getTemplateId().equals(
                        template.getTemplateId())) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                LabelValueBean valueBean = new LabelValueBean(template
                        .getTemplate().getTemplateName(), ""
                        + template.getTemplate().getTemplateId());
                notBindList.add(valueBean);
            }
        }

        bindForm.setNotBindList(notBindList);
        request.setAttribute("templateDtoList", bindList);
        return mapping.getInputForward();
    }

    /**
     *　添加绑定关系。
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addBind(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TemplateBindForm bindForm = (TemplateBindForm)form;
        String actionCode = ActionDefinition.SYS_ADD_TEMPLATE_BIND;
        //保存受理记录
        AppRequest appRequest = bindForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        
        Long orgId = bindForm.getOrgId();
        Long templateId = bindForm.getTemplateId();
        
        try{
            this.templateManager.addTemplateBind(orgId, templateId, appRequest);
            logger.log(request, actionCode,"" + appRequest.getAppId(),ActionDefinition.ACTION_SUCCESS);
        }catch(Exception ex){
            logger.log(request, actionCode,"" + appRequest.getAppId(),ActionDefinition.ACTION_FAIL);
            throw ex;
        }
        
        bindForm.setTemplateId(new Long(0));
        return this.changeCategory(mapping, form, request, response);
    }
    
    /**
     * 删除绑定关系
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteBind(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TemplateBindForm bindForm = (TemplateBindForm)form;
        String actionCode = ActionDefinition.SYS_DEL_TEMPLATE_BIND;
        //保存受理记录
        AppRequest appRequest = bindForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        
        Long orgId = bindForm.getOrgId();
        Long templateId = bindForm.getDelTemplateId();
        
        try{
            this.templateManager.deleteTemplateBind(orgId, templateId, appRequest);
            logger.log(request, actionCode,"" + appRequest.getAppId(),ActionDefinition.ACTION_SUCCESS);
        }catch(Exception ex){
            logger.log(request, actionCode,"" + appRequest.getAppId(),ActionDefinition.ACTION_FAIL);
            throw ex;
        }
        
        bindForm.setTemplateId(new Long(0));
        return this.changeCategory(mapping, form, request, response);
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
