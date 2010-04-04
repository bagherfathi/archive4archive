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
 * 规则分类功能维护类
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

    // 记录日志信息
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
     * 默认方法
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
     * 创建规则种类
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
        // 操作编码：新建规则种类
        String actionCode = ActionDefinition.SYS_SAVE_RULE_CATEGORY;

        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // 保存规则种类
            this.ruleManager.saveCategory(category);
        } catch (Exception e) {
            // 记录新增规则种类失败日志
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // 记录创建成功的操作日志
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 转向到编辑页面
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
     * 编辑规则种类信息
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
        // 操作编码：更新规则种类信息
        String actionCode = ActionDefinition.SYS_UPDATE_RULE_CATEGORY;
        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // 更新
            this.ruleManager.updateCategory(category);
        } catch (Exception e) {
            // 记录编辑规则种类失败日志
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // 记录更新成功的操作日志
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return this
                .getRedirectForwardAction("ruleCategory.do?act=toEdit&id="
                        + category.getCategoryId().longValue());
    }

    /**
     * 删除规则种类
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
        // 操作编码：删除规则种类
        String actionCode = ActionDefinition.SYS_DELETE_RULE_CATEGORY;
        RuleCategoryForm categoryForm = (RuleCategoryForm) form;
        RuleCategoryDTO category = categoryForm.getCategory();
        try {
            // 删除
            this.ruleManager.deleteCategory(category);
        } catch (Exception e) {
            // 记录删除规则种类失败日志
            logger.log(request, actionCode, category.getName(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + category.getName(), e);
            throw e;
        }
        // 记录删除成功的操作日志
        logger.log(request, actionCode, category.getName(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();

    }
}
