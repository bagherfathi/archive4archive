package com.ft.web.sm.rule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.RuleManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.RuleCategoryDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * ������๦��ά����
 * 
 * @spring.bean id="ruleCategoryAction"
 * 
 * @struts.action name="ruleCategoryForm" path="/ruleCategory" scope="request"
 *                input="sm.rule.category.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.rule.category.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/rule/listRuleCategory.jsp"
 * 
 * @struts.action-forward name="toCreate" path="sm.rule.category.add"
 * 
 * @struts.action-forward name="edit" path="sm.rule.category.edit"
 * 
 * @author <a href="mailto:zlkn2006@163.com">zhouliang</a>
 * @version 1.0
 */
public class RuleCategoryAction extends BaseAction {

    // ��¼��־��Ϣ
    private static Log logger = LogFactory.getLog(RuleCategoryAction.class);

    private RuleManager ruleManager;

    /**
     * @spring.property ref="ruleManager"
     * @param ruleManager
     */
    public void setRuleManager(RuleManager ruleManager) {
        this.ruleManager = ruleManager;
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
     * @struts.tiles name="sm.rule.category.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/addRuleCategory.jsp"
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
        // �������룺�½���������
        String actionCode = ActionDefinition.SYS_SAVE_RULE_CATEGORY;

        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // �����������
            this.ruleManager.saveCategory(category);
        } catch (Exception e) {
            // ��¼������������ʧ����־
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // ��¼�����ɹ��Ĳ�����־
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ת�򵽱༭ҳ��
     * 
     * @struts.tiles name="sm.rule.category.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/editRuleCategory.jsp"
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
        // �������룺���¹���������Ϣ
        String actionCode = ActionDefinition.SYS_UPDATE_RULE_CATEGORY;
        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // ����
            this.ruleManager.updateCategory(category);
        } catch (Exception e) {
            // ��¼�༭��������ʧ����־
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
                .getRedirectForwardAction("ruleCategory.do?act=toEdit&id="
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
        // �������룺ɾ����������
        String actionCode = ActionDefinition.SYS_DELETE_RULE_CATEGORY;
        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // ɾ��
            this.ruleManager.deleteCategory(category);
        } catch (Exception e) {
            // ��¼ɾ����������ʧ����־
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
