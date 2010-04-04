
package com.ft.web.sm.rule;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.drools.compiler.RuleBaseLoader;

import com.ft.busi.dto.AppDTO;
import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.RuleManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;
import com.ft.struts.ActionMessagesHelper;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 规则数据管理类
 * 
 * @spring.bean id="ruleAction"
 * 
 * @struts.action name="ruleForm" path="/rule" scope="request"
 *                input="sm.rule.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.rule.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/rule/listRule.jsp"
 * 
 * @struts.action-forward name="create" path="sm.rule.create"
 * 
 * @struts.action-forward name="edit" path="sm.rule.edit"
 * 
 * @struts.action-forward name="view" path="sm.rule.view"
 * 
 * 
 * @version 1.0
 */
public class RuleAction extends BaseAction {
    // 记录日志信息
    private static Log logger = LogFactory.getLog(RuleAction.class);

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

    /**
     * 查询规则
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 跳转到添加规则页面
     * 
     * @struts.tiles name="sm.rule.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/addRule.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("create");
    }

    /**
     * 创建规则，创建规则信息，若有规则文件则上传规则文件保存规则文件信息。
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
        RuleForm ruleForm = (RuleForm) form;
        FormFile file = ruleForm.getFile();
        if(file != null && file.getFileSize() > 2 * 1024 * 1024){
            ActionMessagesHelper.saveRequestMessage(request, "maxLengthExceeded");
            return mapping.findForward("create");
        }
        String actionCode = ActionDefinition.SYS_SAVE_RULE;
        // 保存受理记录
        AppRequest appRequest = ruleForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        // 获得规则
        RuleInfoDTO rule = ruleForm.getRule();
        Long categoryId = ruleForm.getCategoryId();

        try {
            // 无上传文件
            if (file == null || file.getFileSize() <= 0) {
                // 保存规则信息
                this.ruleManager.saveRule(rule, categoryId, appRequest);
            } else {
                InputStream in = file.getInputStream();
                Reader reader = new InputStreamReader(in);

                try {
                    // 对于droos规则，进行检验
                    if (rule.getType() == RuleInfoDTO.DROOLS_TYPE) {
                        RuleBaseLoader.getInstance().loadFromReader(reader);
                    }
                } catch (Exception ex) {
                    ActionMessagesHelper.saveRequestMessage(request,
                            "errors.rule.file.error");
                    return this.toCreate(mapping, ruleForm, request, response);
                } finally {
                    reader.close();
                    in.close();
                }

                // 保存规则及规则文件
                RuleFileDTO ruleFile = new RuleFileDTO();
                ruleFile.setFileContent(file.getFileData());
                this.ruleManager.saveRule(rule, categoryId, ruleForm
                        .getCurrentUser(), ruleFile, appRequest);
            }
        } catch (Exception e) {
            // 记录新增规则失败日志
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return mapping.getInputForward();
    }

    /**
     * 跳转到编辑页面
     * 
     * @struts.tiles name="sm.rule.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/editRule.jsp"
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
     * 编辑规则信息
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
        RuleForm ruleForm = (RuleForm) form;

        // 操作编码-更新规则
        String actionCode = ActionDefinition.SYS_UPDATE_RULE;

        // 保存受理记录
        AppRequest appRequest = ruleForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        Long categoryId = ruleForm.getCategoryId();
        RuleInfoDTO rule = ruleForm.getRule();
        rule.setCategoryId(categoryId);
        try {
            // 更新规则信息
            this.ruleManager.updateRule(rule,appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        ruleForm.setRule(rule);
        request.setAttribute("rule", rule);
        return toView(mapping, ruleForm, request, response);
    }

    /**
     * 跳转到规则信息查看页面
     * 
     * @struts.tiles name="sm.rule.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/viewRule.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RuleForm ruleForm = (RuleForm) form;
        List ruleFiles = this.ruleManager.findRuleFileByRuleId(ruleForm
                .getRule().getRuleId());
        request.setAttribute("ruleFiles", ruleFiles);
        return mapping.findForward("view");
    }
}
