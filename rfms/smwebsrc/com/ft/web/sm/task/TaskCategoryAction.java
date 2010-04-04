package com.ft.web.sm.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.TaskManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.TaskCategoryDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * ������๦��ά����
 * 
 * @spring.bean id="taskCategoryAction"
 * 
 * @struts.action name="taskCategoryForm" path="/taskCategory" scope="request"
 *                input="sm.task.category.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.task.category.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/task/listTaskCategory.jsp"
 * 
 * @struts.action-forward name="toCreate" path="sm.task.category.add"
 * 
 * @struts.action-forward name="edit" path="sm.task.category.edit"
 * 
 * @version 1.0
 */
public class TaskCategoryAction extends BaseAction {

    private static Log logger = LogFactory.getLog(TaskCategoryAction.class);

    private TaskManager taskManager;

    /**
     * @spring.property ref="taskManager"
     * @param taskManager
     */
    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Ĭ�Ϸ���
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
     * ������������
     * 
     * @struts.tiles name="sm.task.category.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/addTaskCategory.jsp"
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

        if (request.getMethod().equals("GET")) {
            return mapping.findForward("toCreate");
        }
        String actionCode = ActionDefinition.SYS_SAVE_TASK_CATEGORY;

        TaskCategoryForm categoryForm = (TaskCategoryForm) form;
        TaskCategoryDTO category = categoryForm.getCategory();
        try {
            // �����������
            this.taskManager.saveOrUpdateCategory(category);
        } catch (Exception e) {
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ת�򵽱༭ҳ��
     * 
     * @struts.tiles name="sm.task.category.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/task/editTaskCategory.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * �༭����������Ϣ
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
        String actionCode = ActionDefinition.SYS_UPDATE_TASK_CATEGORY;
        TaskCategoryForm categoryForm = (TaskCategoryForm) form;
        TaskCategoryDTO category = categoryForm.getCategory();
        try {
            // ����
            this.taskManager.saveOrUpdateCategory(category);
        } catch (Exception e) {
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // ��¼���³ɹ��Ĳ�����־
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("taskCategory.do?act=toEdit&id="
                        + category.getCategoryId().longValue());
    }

    /**
     * ɾ����������
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
        String actionCode = ActionDefinition.SYS_DELETE_TASK_CATEGORY;
        TaskCategoryForm categoryForm = (TaskCategoryForm) form;
        TaskCategoryDTO category = categoryForm.getCategory();
        try {
            // ɾ��
            this.taskManager.deleteCategory(category);
        } catch (Exception e) {
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // ��¼ɾ���ɹ��Ĳ�����־
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }
}
