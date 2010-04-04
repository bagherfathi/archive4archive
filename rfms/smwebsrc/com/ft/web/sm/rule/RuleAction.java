
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
 * �������ݹ�����
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
    // ��¼��־��Ϣ
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
     * Ĭ�Ϸ���
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * ��ѯ����
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
     * ��ת����ӹ���ҳ��
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
     * �������򣬴���������Ϣ�����й����ļ����ϴ������ļ���������ļ���Ϣ��
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
        // ���������¼
        AppRequest appRequest = ruleForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        // ��ù���
        RuleInfoDTO rule = ruleForm.getRule();
        Long categoryId = ruleForm.getCategoryId();

        try {
            // ���ϴ��ļ�
            if (file == null || file.getFileSize() <= 0) {
                // ���������Ϣ
                this.ruleManager.saveRule(rule, categoryId, appRequest);
            } else {
                InputStream in = file.getInputStream();
                Reader reader = new InputStreamReader(in);

                try {
                    // ����droos���򣬽��м���
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

                // ������򼰹����ļ�
                RuleFileDTO ruleFile = new RuleFileDTO();
                ruleFile.setFileContent(file.getFileData());
                this.ruleManager.saveRule(rule, categoryId, ruleForm
                        .getCurrentUser(), ruleFile, appRequest);
            }
        } catch (Exception e) {
            // ��¼��������ʧ����־
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);

        return mapping.getInputForward();
    }

    /**
     * ��ת���༭ҳ��
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
     * �༭������Ϣ
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

        // ��������-���¹���
        String actionCode = ActionDefinition.SYS_UPDATE_RULE;

        // ���������¼
        AppRequest appRequest = ruleForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        Long categoryId = ruleForm.getCategoryId();
        RuleInfoDTO rule = ruleForm.getRule();
        rule.setCategoryId(categoryId);
        try {
            // ���¹�����Ϣ
            this.ruleManager.updateRule(rule,appRequest);
        } catch (Exception e) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, "" + appRequest.getAppId(),
                ActionDefinition.ACTION_SUCCESS);
        ruleForm.setRule(rule);
        request.setAttribute("rule", rule);
        return toView(mapping, ruleForm, request, response);
    }

    /**
     * ��ת��������Ϣ�鿴ҳ��
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
