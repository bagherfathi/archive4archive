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
 * ��Ϣ���������
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

    // ��¼��־��Ϣ
    private static Log logger = LogFactory.getLog(InfoCategoryAction.class);

    private InfoManager infoManager;

    /**
     * Ĭ�Ϸ���
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * ��������Ϣ����
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
            // ת������ҳ��
            return mapping.findForward("toAdd");
        }
        // �������룺������Ϣ����
        String actionCode = ActionDefinition.SYS_ADD_INFO_CATEGORY;
        // ������Ϣ����
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // ������Ϣ����
            this.infoManager.saveInfoGategory(category);
        } catch (Exception e) {
            // ��¼������Ϣ����ʧ����־
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // ��¼�����ɹ��Ĳ�����־
        logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("infoCategory.do");
       // return mapping.getInputForward();
    }

    /**
     * �༭��Ϣ����
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
     * �༭��Ϣ��������
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
        // �������룺������Ϣ����
        String actionCode = ActionDefinition.SYS_UPDATE_INFO_CATEGORY;
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // ����
            this.infoManager.updateInfoGategory(category);
        } catch (Exception e) {
            // ��¼�༭��Ϣ����ʧ����־
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // ��¼���³ɹ��Ĳ�����־
        logger.log(request, actionCode, name, ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("infoCategory.do");
        //   return mapping.getInputForward();
    }

    /**
     * ɾ����Ϣ����
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
        // �������룺ɾ����Ϣ����
        String actionCode = ActionDefinition.SYS_DELETE_INFO_CATEGORY;
        InfoCategoryForm infoForm = (InfoCategoryForm) form;
        InfoCategoryDTO category = infoForm.getCategory();
        String name = category.getName();
        try {
            // ɾ��
            this.infoManager.deleteInfoCategory(category.getCategoryId());
        } catch (Exception e) {
            // ��¼�༭��Ϣ����ʧ����־
            logger.log(request, actionCode, name, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + name, e);
            throw e;
        }
        // ��¼ɾ���ɹ��Ĳ�����־
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
