package com.ft.web.sm.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.InfoManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.InfoCategoryDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 信息类别设置类
 * 
 * @spring.bean id="infoCategoryAction"
 * 
 * @struts.action name="infoCategoryForm" path="/infoCategory" scope="request"
 *                input="sm.info.category.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.info.category.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/info/listCategory.jsp"
 * 
 * @struts.action-forward name="toAdd" path="sm.info.category.create"
 * 
 * @struts.action-forward name="toEdit" path="sm.info.category.edit"
 * 
 * @struts.action-forward name="list" path="sm.info.category.list"
 * 
 * @version 1.0
 */
public class InfoCategoryAction extends BaseAction {

    // 记录日志信息
    private static Log logger = LogFactory.getLog(InfoCategoryAction.class);

    private InfoManager infoManager;

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 创建新信息种类
     * 
     * @struts.tiles name="sm.info.category.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/addCategory.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (request.getMethod().equals("GET")) {
            // 转到新增页面
            return mapping.findForward("toAdd");
        }
        // 操作编码：新增信息种类
        String actionCode = ActionDefinition.SYS_ADD_INFO_CATEGORY;
        // 设置信息种类
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // 保存信息种类
            this.infoManager.saveInfoGategory(category);
        } catch (Exception e) {
            // 记录新增信息种类失败日志
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // 记录新增成功的操作日志
        logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("infoCategory.do");
       // return mapping.getInputForward();
    }

    /**
     * 编辑信息种类
     * 
     * @struts.tiles name="sm.info.category.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/info/editCategory.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toEdit");
    }

    /**
     * 编辑信息种类数据
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
        // 操作编码：更新信息种类
        String actionCode = ActionDefinition.SYS_UPDATE_INFO_CATEGORY;
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // 更新
            this.infoManager.updateInfoGategory(category);
        } catch (Exception e) {
            // 记录编辑信息种类失败日志
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // 记录更新成功的操作日志
        logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("infoCategory.do");
        //   return mapping.getInputForward();
    }

    /**
     * 删除信息分类
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
        // 操作编码：删除信息分类
        String actionCode = ActionDefinition.SYS_DELETE_INFO_CATEGORY;
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // 删除
            this.infoManager.deleteInfoCategory(category.getCategoryId());
        } catch (Exception e) {
            // 记录编辑信息种类失败日志
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // 记录删除成功的操作日志
        logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
        return mapping.findForward("list");
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.getInputForward();
    }

    /**
     * @spring.property ref="infoManager"
     * @param infoManager
     */
    public void setInfoManager(InfoManager infoManager) {
        this.infoManager = infoManager;
    }

}
