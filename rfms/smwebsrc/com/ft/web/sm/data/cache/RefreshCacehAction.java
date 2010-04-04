package com.ft.web.sm.data.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.common.enumdata.EnumRepository;
import com.ft.common.security.OrgRepository;
import com.ft.common.security.ResourceRepository;
import com.ft.sm.rules.impl.RuleRepositoryImpl;
import com.ft.sm.template.impl.TemplateRepositoryImpl;
import com.ft.web.sm.BaseAction;
import com.ft.commons.web.SpringWebUtils;
import com.ft.struts.ActionMessagesHelper;

/**
 * 用于页面更新缓存。
 * 
 * 
 * @struts.action path="/refreshCache" name="refreshCacheForm" scope="request" validate="false"
 *                parameter="act" input="sm.refresh.cache.index"
 * 
 * @struts.tiles name="sm.refresh.cache.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/cache/refreshCache.jsp"
 * 
 * @version 1.0
 */
public class RefreshCacehAction extends BaseAction{ 

    /* (non-Javadoc)
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
    
    /**
     * 用于页面手动更新缓存数据。
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refresh(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RefreshCacheForm cacheForm = (RefreshCacheForm)form;
        
        String loginName = cacheForm.getCurrentUser().getLoginName();
        if(!"admin".equals(loginName)){
            ActionMessagesHelper.saveRequestMessage(request,
            "errors.nopermission");
            return mapping.getInputForward();
        }
        
        String cacheCode = cacheForm.getCacheCode();
        
        if("enum".equals(cacheCode)){
            refreshEnumCaceh(request);
        }else if("org".equals(cacheCode)){
            refreshOrgCaceh(request);
        }else if("resource".equals(cacheCode)){
            refreshResourceCaceh(request);
        }else if("rule".equals(cacheCode)){
            refreshRuleCaceh(request);
        }else if("template".equals(cacheCode)){
            refreshTemplateCaceh(request);
        }
        
        return mapping.getInputForward();
    }
    
    /**
     * 更新枚举数据缓存。
     * @param request
     * @throws Exception
     */
    private void refreshEnumCaceh(HttpServletRequest request) throws Exception{
        EnumRepository enumRepository = (EnumRepository)SpringWebUtils.getBean(request,EnumRepository.getBeanName());
        if(enumRepository != null){
            enumRepository.loadEnumDatas();
        }
    }
    
    /**
     * 更新组织数据缓存。
     * @param request
     * @throws Exception
     */
    private void refreshOrgCaceh(HttpServletRequest request) throws Exception{
        OrgRepository orgRepository = (OrgRepository)SpringWebUtils.getBean(request,OrgRepository.getBeanName());
        if(orgRepository != null){
            orgRepository.init();
        }
    }
    
    /**
     * 更新功能权限数据缓存。
     * @param request
     * @throws Exception
     */
    private void refreshResourceCaceh(HttpServletRequest request) throws Exception{
        ResourceRepository resourceRepository = (ResourceRepository)SpringWebUtils.getBean(request,ResourceRepository.getBeanName());
        if(resourceRepository != null){
            resourceRepository.init();
        }
    }
    
    /**
     * 更新规则缓存。
     * @param request
     * @throws Exception
     */
    private void refreshRuleCaceh(HttpServletRequest request) throws Exception{
        RuleRepositoryImpl ruleRepository = (RuleRepositoryImpl)SpringWebUtils.getBean(request,"ruleRepository");
        if(ruleRepository != null){
            ruleRepository.initialize();
        }
    }
    
    /**
     * 更新模板缓存。
     * @param request
     * @throws Exception
     */
    private void refreshTemplateCaceh(HttpServletRequest request) throws Exception{
        TemplateRepositoryImpl templateRepository = (TemplateRepositoryImpl)SpringWebUtils.getBean(request,"templateRepository");
        if(templateRepository != null){
            templateRepository.initialize();
        }
    }
    
}
