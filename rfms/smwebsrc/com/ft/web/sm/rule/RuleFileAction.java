package com.ft.web.sm.rule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

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
import com.ft.common.session.OperatorSessionHelper;
import com.ft.commons.stream.StreamUtils;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.RuleFileDTO;
import com.ft.sm.dto.RuleInfoDTO;
import com.ft.struts.ActionMessagesHelper;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * 规则文件Action类
 * 
 * @version 1.0
 * 
 * @spring.bean id="ruleFileAction"
 * 
 * @struts.action name="ruleFileForm" path="/ruleFile" scope="request"
 *                input="sm.rule.file.list" parameter="act" validate="false"
 * 
 * @struts.action-forward name="add" path="sm.rule.file.add"
 * @struts.action-forward name="edit" path="sm.rule.file.edit"
 * 
 * @struts.tiles name="sm.rule.file.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/rule/listRuleFile.jsp"
 * 
 */
public class RuleFileAction extends BaseAction {
    private static Log logger = LogFactory.getLog(RuleFileAction.class);

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
     * 规则文件历史列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 发布模板文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward publish(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RuleFileForm fileForm = (RuleFileForm) form;
        String actionCode = ActionDefinition.SYS_UPDATE_RULE;
        // 保存受理记录
        AppRequest appRequest = fileForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        RuleInfoDTO rule = fileForm.getRule();
        try {
            //RuleInfoDTO rule = fileForm.getRule();
            RuleFileDTO ruleFile = fileForm.getRuleFile();
            OperatorDTO loginOp = OperatorSessionHelper.getLoginOp(request);
            ruleFile.setPublisher(loginOp.getContact().getName());
            this.ruleManager.publishRuleFile(rule, ruleFile, appRequest);
            logger.log(request, ActionDefinition.SYS_PUBLISH_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_PUBLISH_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        //return mapping.getInputForward();
        return getRedirectForwardAction("ruleFile.do?act=list&ruleId="
                + rule.getRuleId());
    }

    /**
     * 删除模板文件
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
        RuleFileForm fileForm = (RuleFileForm) form;
        String actionCode = ActionDefinition.SYS_UPDATE_RULE;
        // 保存受理记录
        AppRequest appRequest = fileForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        RuleFileDTO ruleFile = fileForm.getRuleFile();
        RuleInfoDTO rule = fileForm.getRule();
        try {
            this.ruleManager.deleteRuleFile(rule, ruleFile, appRequest);
            logger.log(request, ActionDefinition.SYS_DELETE_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        //return mapping.getInputForward();
        return getRedirectForwardAction("ruleFile.do?act=list&ruleId="
                + rule.getRuleId());
    }

    /**
     * 跳转到添加模板文件页面
     * 
     * @struts.tiles name="sm.rule.file.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/addRuleFile.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("add");
    }

    /**
     * 跳转到编辑模板文件页面
     * 
     * @struts.tiles name="sm.rule.file.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/rule/editRuleFile.jsp"
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
        RuleFileForm fileForm = (RuleFileForm) form;
        RuleFileDTO ruleFile = fileForm.getRuleFile();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(ruleFile
                .getFileContent());
        InputStreamReader reader = new InputStreamReader(inputStream);
        StringWriter writer = new StringWriter();
        StreamUtils.flow(reader, writer, new char[1024]);
        fileForm.setFileContent(writer.toString());

        if (reader != null) {
            reader.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        return mapping.findForward("edit");
    }

    /**
     * 上传模板文件
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
        RuleFileForm fileForm = (RuleFileForm) form;
        FormFile formFile = fileForm.getFile();
        if (formFile != null && formFile.getFileSize() > 2 * 1024 * 1024) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "maxLengthExceeded");
            return mapping.findForward("add");
        }

        RuleInfoDTO rule = fileForm.getRule();
        InputStream in = formFile.getInputStream();
        Reader reader = new InputStreamReader(in);
        try {
            // 对于droos规则，进行检验
            if (rule.getType() == RuleInfoDTO.DROOLS_TYPE) {
                RuleBaseLoader.getInstance().loadFromReader(reader);
            }
        } catch (Exception ex) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.rule.file.error");
            return mapping.findForward("add");
        } finally {
            reader.close();
            in.close();
        }
        
        String actionCode = ActionDefinition.SYS_UPLOAD_RULE_FILE;
        // 保存受理记录
        AppRequest appRequest = fileForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        try {
            RuleFileDTO ruleFile = new RuleFileDTO();
            ruleFile.setFileContent(formFile.getFileData());
            OperatorDTO loginOp = OperatorSessionHelper.getLoginOp(request);
            ruleFile.setPublisher(loginOp.getContact().getName());
            this.ruleManager.addRuleFile(rule, ruleFile, appRequest, fileForm
                    .isPublish());
            logger.log(request, ActionDefinition.SYS_UPLOAD_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPLOAD_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            throw ex;
        }

      //  return mapping.getInputForward();
        
        return getRedirectForwardAction("ruleFile.do?act=list&ruleId="
               + rule.getRuleId().toString());
    }

    /**
     * 更新模板文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RuleFileForm fileForm = (RuleFileForm) form;

        String actionCode = ActionDefinition.SYS_UPDATE_RULE;
        // 保存受理记录
        AppRequest appRequest = fileForm.getAppRequest(request, actionCode);
        AppDTO app = appService.saveApp(appRequest);
        appRequest.setAppId(app.getApp().getAppId());

        RuleInfoDTO rule = fileForm.getRule();

        try {
            RuleFileDTO ruleFile = new RuleFileDTO();
            ruleFile.setFileContent(fileForm.getFileContent().getBytes());
            OperatorDTO loginOp = OperatorSessionHelper.getLoginOp(request);
            ruleFile.setPublisher(loginOp.getContact().getName());
            this.ruleManager.addRuleFile(rule, ruleFile, appRequest, fileForm
                    .isPublish());
            logger.log(request, ActionDefinition.SYS_UPDATE_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_UPDATE_RULE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        return getRedirectForwardAction("ruleFile.do?act=list&ruleId="
                + rule.getRuleId());
    }

    /**
     * 下载文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward download(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        RuleFileForm fileForm = (RuleFileForm) form;
        RuleFileDTO ruleFile = fileForm.getRuleFile();
        RuleInfoDTO rule = fileForm.getRule();

        String fileName = rule.getCode() + "_" + ruleFile.getVersion();

        response.addHeader("Content-disposition", "attachment; filename="
                + fileName);

        response.setContentType("application/octet-stream");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(ruleFile
                .getFileContent());
        StreamUtils.flow(inputStream, response.getOutputStream(),
                new byte[1024]);
        response.getOutputStream().close();
        inputStream.close();
        return null;
    }
}
