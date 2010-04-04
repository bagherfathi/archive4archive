package com.ft.web.sm.template;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.TemplateManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.TemplateDTO;
import com.ft.sm.dto.TemplateFileDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.commons.stream.StreamUtils;
import com.ft.struts.ActionMessagesHelper;

/**
 * 模版文件管理Action类
 * 
 * @version 1.0
 * 
 * @spring.bean id="templateFileAction"
 * 
 * @struts.action name="templateForm" path="/templateFile" scope="request"
 *                input="sm.template.file.list" parameter="act" validate="false"
 * 
 * @struts.action-forward name="add" path="sm.template.file.add"
 * @struts.action-forward name="edit" path="sm.template.file.edit"
 * @struts.action-forward name="history" path="sm.template.file.history"
 * @struts.action-forward name="preview" path="sm.template.file.preview"
 * @struts.action-forward name="toPreview" path="sm.template.file.toPreview"
 * 
 * @struts.tiles name="sm.template.file.list" extends="main.layout"
 * @struts.tiles-put name="body"
 *                   value="/sm/template/listTemplateFileHistory.jsp"
 * 
 */
public class TemplateFileAction extends BaseAction {

    private static Log logger = LogFactory.getLog(TemplateFileAction.class);

    private TemplateManager templateManager;

    private VelocityEngine velocityEngine;

    /**
     * 默认方法
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 模板文件版本历史列表
     * 
     * @struts.tiles name="sm.template.file.history" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/template/listTemplateFileHistory.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listHistory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("history");
    }

    /**
     * 跳转到预览模版文件页面
     * 
     * @struts.tiles name="sm.template.file.toPreview"
     *               page="/WEB-INF/jsp/sm/template/toPreviewTemplateFile.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toPreview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("toPreview");
    }

    /**
     * 预览模版文件
     * 
     * @struts.tiles name="sm.template.file.preview"
     *               page="/WEB-INF/jsp/sm/template/previewTemplateFile.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward preview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TemplateForm templateForm = (TemplateForm) form;
        TemplateFileDTO templateFile = templateForm.getTemplateFile();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                templateFile.getTemplateFile().getFileContent());
        InputStreamReader reader = new InputStreamReader(inputStream);
        StringWriter writer = new StringWriter();
        templateForm.setFileContent(writer.toString());
        try {
            velocityEngine.evaluate(new VelocityContext(), writer, "template",
                    reader);
            request.setAttribute("fileContent", writer.toString());
        } catch (Exception e) {
            request.setAttribute("fileContent",
                    "<font color='red'>文件解析出错,不能预览</font>");
        }
        return mapping.findForward("preview");
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
        TemplateForm templateForm = (TemplateForm) form;
        String actionCode = ActionDefinition.SYS_UPDATE_TEMPLATE_INFO;
        // 保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            TemplateDTO templateDto = templateForm.getTemplate();
            TemplateFileDTO templateFile = templateForm.getTemplateFile();
            this.templateManager.publisTemplateFile(templateDto, templateFile,
                    appRequest);
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        return getRedirectForwardAction("templateFile.do?act=listHistory&templateId="
                + templateForm.getTemplate().getTemplateId());
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
        TemplateForm templateForm = (TemplateForm) form;
        String actionCode = ActionDefinition.SYS_UPDATE_TEMPLATE_INFO;
        // 保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        TemplateFileDTO templateFile = templateForm.getTemplateFile();
        TemplateDTO templateDto = templateForm.getTemplate();
        try {
            this.templateManager.deleteTemplateFile(templateDto, templateFile,
                    appRequest);
            logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, ActionDefinition.SYS_DELETE_TEMPLATE_FILE, ""
                    + appRequest.getAppId(), ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        return getRedirectForwardAction("templateFile.do?act=listHistory&templateId="
                + templateForm.getTemplate().getTemplateId());
    }

    /**
     * 跳转到添加模板文件页面
     * 
     * @struts.tiles name="sm.template.file.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/addTemplateFile.jsp"
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
     * @struts.tiles name="sm.template.file.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/template/editTemplateFile.jsp"
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
        TemplateForm templateForm = (TemplateForm) form;
        TemplateFileDTO templateFile = templateForm.getTemplateFile();
        InputStream inputStream = null;
        InputStreamReader reader = null;
        try {
            inputStream = new ByteArrayInputStream(templateFile
                    .getTemplateFile().getFileContent());
            reader = new InputStreamReader(inputStream);
            StringWriter writer = new StringWriter();
            StreamUtils.flow(reader, writer, new char[1024]);
            templateForm.setFileContent(writer.toString());
        } catch (Exception ex) {
            ActionMessagesHelper.saveRequestMessage(request, "msg.canot.edit.template.file");
            return mapping.findForward("history");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return mapping.findForward("edit");
    }

    /**
     * 保存模板文件
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
        TemplateForm templateForm = (TemplateForm) form;

        FormFile formFile = templateForm.getFile();
        if (formFile != null && formFile.getFileSize() > 2 * 1024 * 1024) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "maxLengthExceeded");
            return mapping.findForward("add");
        }
        String actionCode = ActionDefinition.SYS_UPDATE_TEMPLATE_INFO;
        // 保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {

            TemplateFileDTO templateFile = new TemplateFileDTO();
            templateFile.getTemplateFile().setFileContent(
                    formFile.getFileData());
            TemplateDTO templateDto = templateForm.getTemplate();
            this.templateManager.addTemplateFile(templateDto, templateFile,
                    appRequest, templateForm.isPublish());
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        return getRedirectForwardAction("templateFile.do?act=listHistory&templateId="
                + templateForm.getTemplate().getTemplateId());
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
        TemplateForm templateForm = (TemplateForm) form;

        String actionCode = ActionDefinition.SYS_UPDATE_TEMPLATE_INFO;
        // 保存受理记录
        AppRequest appRequest = templateForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());

        TemplateDTO templateDto = templateForm.getTemplate();

        try {
            TemplateFileDTO templateFile = new TemplateFileDTO();
            templateFile.getTemplateFile().setFileContent(
                    templateForm.getFileContent().getBytes());
            this.templateManager.addTemplateFile(templateDto, templateFile,
                    appRequest, templateForm.isPublish());
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_SUCCESS);
        } catch (Exception ex) {
            logger.log(request, actionCode, "" + appRequest.getAppId(),
                    ActionDefinition.ACTION_FAIL);
            throw ex;
        }

        return getRedirectForwardAction("templateFile.do?act=listHistory&templateId="
                + templateDto.getTemplateId());
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
        TemplateForm templateForm = (TemplateForm) form;
        TemplateFileDTO file = templateForm.getTemplateFile();
        TemplateDTO template = templateForm.getTemplate();

        String fileName = template.getTemplate().getTemplateCode() + "_"
                + file.getTemplateFile().getFileVersion();
        response.addHeader("Content-disposition", "attachment; filename="
                + fileName);
        response.setContentType("application/octet-stream");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(file
                .getTemplateFile().getFileContent());
        StreamUtils.flow(inputStream, response.getOutputStream(),
                new byte[1024]);
        response.getOutputStream().close();
        inputStream.close();
        return null;
    }

    /**
     * @spring.property ref="templateManager"
     * @param templateManager
     *            The templateManager to set.
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }

    /**
     * @spring.property ref="velocityEngine"
     * @param velocityEngine
     *            the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

}
