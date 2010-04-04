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
 * 模版类别管理Action类
 * 
 * @version 1.0
 * 
 * @spring.bean id="templateCategoryAction"
 * 
 * @struts.action name="templateForm" path="/templateCategory" scope="request"
 *                input="sm.template.category.list" parameter="act"
 *                validate="false"
 * 
 * @struts.action-forward name="create" path="sm.template.category.create"
 * @struts.action-forward name="view" path="sm.template.category.view"
 * 
 * @struts.tiles name="sm.template.category.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/template/listTemplateCategory.jsp"
 * 
 */
public class TemplateCategoryAction extends BaseAction {

    @SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(TemplateCategoryAction.class);

    @SuppressWarnings("unused")
	private TemplateManager templateManager;

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 预览模板类别
     * 
     * @struts.tiles name="sm.template.category.view" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/template/viewTemplateCategory.jsp"
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
     * 跳转到新建模板分类页面
     * 
     * @struts.tiles name="sm.template.category.create" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/template/createTemplateCategory.jsp"
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
     * 跳转到编辑模板分类页面
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
        return mapping.findForward("create");
    }

    /**
     * 保存模板分类
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
        TemplateForm categoryForm = (TemplateForm) form;
        TemplateCategoryDTO category = categoryForm.getCategory();
        Long id;
        String logCode;
        if (category.getCategoryId().longValue() == 0) {
            logCode = ActionDefinition.SYS_ADD_TEMPLATE_CATRGORY;
        } else {
            logCode = ActionDefinition.SYS_UPDATE_TEMPLATE_CATRGORY;
        }
        try {
            id = templateManager.saveOrUpdateTemplateCategory(category);
        } catch (Exception ex) {
            logger.log(request, logCode, "", ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + logCode
                    + " failed,action sequence = ", ex);
            throw ex;
        }
        logger.log(request, logCode, id.toString(),
                ActionDefinition.ACTION_SUCCESS);

        return this.getRedirectForwardAction("/sm/templateCategory.do?act=view"
                + "&categoryId=" + id.toString());
               */
        return null;
    }

    /**
     * 删除模板类别
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
        TemplateForm categoryForm = (TemplateForm) form;
        TemplateCategoryDTO[] categorys = categoryForm.getCategorys();
        String ids = "";
        for (int i = 0; i < categorys.length; i++) {
            ids = ids + categorys[i].getCategoryId().toString() + ",";
        }
        try {
            templateManager.deleteTemplateCategory(categorys);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_CATRGORY,
                    ids, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action "
                    + ActionDefinition.SYS_DELETE_TEMPLATE_CATRGORY
                    + " failed,action sequence = " + ids, ex);
            throw ex;
        }
        logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_CATRGORY, ids,
                ActionDefinition.ACTION_SUCCESS);
                
        */
        return getRedirectForwardAction("templateCategory.do");

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
