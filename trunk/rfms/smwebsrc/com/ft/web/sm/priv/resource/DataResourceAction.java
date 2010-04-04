package com.ft.web.sm.priv.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.struts.ActionMessagesHelper;

/**
 * ҵ��Ȩ��Ȩ��ά����
 * 
 * 
 * @spring.bean id="dataResourceAction"
 * 
 * @struts.action path="/dataResource" name="dataResourceForm" scope="request"
 *                input="sm.dataResource.list" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.dataResource.list" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/resource/listDataResource.jsp"
 * 
 * @struts.action-forward name="add" path="sm.dataResource.add"
 * 
 * @struts.action-forward name="edit" path="sm.dataResource.edit"
 * 
 * @struts.action-forward name="viewDataResource" path="sm.dataResource.view"
 * 
 * @struts.action-forward name="list" path="sm.dataResource.list"
 * 
 * @struts.action-forward name="addEntry" path="sm.dataResource.add.entry"
 * 
 * @struts.action-forward name="editEntry" path="sm.dataResource.edit.entry"
 * 
 * @version 1.0
 */
public class DataResourceAction extends BaseAction {

    private static Log logger = LogFactory.getLog(DataResourceAction.class);

    /**
     * Ȩ����Ϣά����
     */
    private ResourceManager resourceManager;

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List dataResources = resourceManager.findAllDataResource();
        request.setAttribute("dataResources", dataResources);
        return mapping.getInputForward();
    }

    /**
     * ��ת��ҵ��Ȩ����Ϣ�б�
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
        return mapping.findForward("list");
    }

    /**
     * ����ҵ��Ȩ��
     * 
     * @struts.tiles name="sm.dataResource.add" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/resource/addDataResource.jsp"
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

        String actionCode = ActionDefinition.SYS_ADD_DATA_RESOURCE;

        DataResourceForm drForm = (DataResourceForm) form;
        if (request.getMethod().equals("GET")) {
            return mapping.findForward("add");
        }

        DataResourceDTO dataResource = drForm.getDataResource();
        // �½�ҵ��Ȩ��

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            Long resourceId = resourceManager.addDataResource(dataResource,
                    appRequest);
            dataResource.setResourceId(resourceId);
        } catch (Exception e) {
            // ��¼����ҵ��Ȩ��ʧ����־
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        // ������ҵ��Ȩ�޽����ҵ��Ȩ�޵�viewҳ��
        return mapping.findForward("viewDataResource");
    }

    /**
     * ҵ��Ȩ����Ϣ���ҳ��
     * 
     * @struts.tiles name="sm.dataResource.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/resource/viewDataResource.jsp"
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
        return mapping.findForward("viewDataResource");
    }

    /**
     * ɾ��ҵ��Ȩ��
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
        String actionCode = ActionDefinition.SYS_DELETE_DATA_RESOURCE;
        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceDTO dr = drForm.getDataResource();
        String code = dr.getCode();

        try {
            resourceManager.deleteDataResource(dr, drForm.getAppRequest(
                    request, actionCode));
        } catch (Exception e) {
            // ��¼ɾ��ʧ����־
            logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + code, e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
        // ��ת��ҵ��Ȩ���б�
        return this.getRedirectForwardAction("dataResource.do");
    }

    /**
     * �༭ҵ��Ȩ����Ϣ
     * 
     * @struts.tiles name="sm.dataResource.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/resource/editDataResource.jsp"
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

        String actionCode = ActionDefinition.SYS_UPDATE_DATA_RESOURCE;

        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceDTO dataResource = drForm.getDataResource();
        // ͨ��method�ж��Ǳ��滹����ת
        if (request.getMethod().equals("GET")) {
            return mapping.findForward("edit");
        }

        // ����ҵ��Ȩ����Ϣ

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            resourceManager.updateDataResource(dataResource, appRequest);
        } catch (Exception e) {
            // ��¼����ʧ����־
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * ��ת��������Ŀ��Ϣҳ��
     * 
     * @struts.tiles name="sm.dataResource.add.entry" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/resource/addDataResourceEntry.jsp"
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toAddEntry(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("addEntry");
    }

    /**
     * ������Ŀ��Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addEntry(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_ADD_DATA_RESOURCE_ENTRY;

        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceDTO dataResource = drForm.getDataResource();
        DataResourceEntryDTO entry = drForm.getEntry();
        entry.setMaxValue(drForm.getMaxValue());
        entry.setMinValue(drForm.getMinValue());

        // �жϴ�ҵ��Ȩ����Ŀ�����ڴ�ҵ��Ȩ�����Ƿ��Ѵ���
        if (resourceManager.isExistEntryByDataResource(entry.getEntryId(),
                entry.getTitle(), dataResource.getResourceId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.data.resource.entry.exist");
            return mapping.findForward("addEntry");
        }

        // �жϵ�ֵ��ҵ��Ȩ�޵���Ŀ�Ƿ��ظ�
        if (dataResource.getDataType() == DataResourceDTO.ASSIGN_TYPE_SINGLE) {
            if (resourceManager.isEntryValueDuplicatedOfSingleDataResource(
                    null, dataResource.getResourceId(), drForm.getMaxValue())) {
                ActionMessagesHelper.saveRequestMessage(request,
                        "errors.data.resource.entry.duplicated");
                return mapping.findForward("addEntry");
            }
        }

        entry.setDataResource(dataResource.getDataResource());

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            resourceManager.addDataResourceEntry(entry, appRequest);
        } catch (Exception e) {
            // ��¼����ҵ��Ȩ����Ŀʧ����־
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * ɾ��ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteEntry(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_DELETE_DATA_RESOURCE_ENTRY;

        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceEntryDTO entry = drForm.getEntry();
        DataResourceDTO dataResource = drForm.getDataResource();
        String entryId = entry.getEntryId().toString();

        try {
            resourceManager.deleteDataResourceEntry(entry, drForm
                    .getAppRequest(request, actionCode));
        } catch (Exception e) {
            // ��¼ɾ��ҵ��Ȩ����Ŀʧ����־
            logger.log(request, actionCode, entryId,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + entryId, e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, entryId,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * ��ת����Ŀ��Ϣ�༭ҳ��
     * 
     * @struts.tiles name="sm.dataResource.edit.entry" extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/resource/editDataResourceEntry.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toEditEntry(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceEntryDTO entry = drForm.getEntry();
        drForm.setMaxValue(entry.getMaxValue());
        drForm.setMinValue(entry.getMinValue());
        return mapping.findForward("editEntry");
    }

    /**
     * �༭ҵ��Ȩ����Ŀ��Ϣ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editEntry(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String actionCode = ActionDefinition.SYS_UPDATE_DATA_RESOURCE_ENTRY;

        DataResourceForm drForm = (DataResourceForm) form;
        DataResourceEntryDTO entry = drForm.getEntry();
        entry.setMaxValue(drForm.getMaxValue());
        entry.setMinValue(drForm.getMinValue());

        DataResourceDTO dataResource = drForm.getDataResource();
        entry.setDataResource(dataResource.getDataResource());
        // �жϴ�ҵ��Ȩ����Ŀ�����ڴ�ҵ��Ȩ�����Ƿ��Ѵ���
        if (resourceManager.isExistEntryByDataResource(entry.getEntryId(),
                entry.getTitle(), dataResource.getResourceId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.data.resource.entry.exist");
            return mapping.findForward("editEntry");
        }

        // �жϵ�ֵ��ҵ��Ȩ�޵���Ŀ�Ƿ��ظ�
        if (dataResource.getDataType() == DataResourceDTO.ASSIGN_TYPE_SINGLE) {
            if (resourceManager.isEntryValueDuplicatedOfSingleDataResource(
                    entry.getEntryId(), dataResource.getResourceId(), drForm
                            .getMaxValue())) {
                ActionMessagesHelper.saveRequestMessage(request,
                        "errors.data.resource.entry.duplicated");
                return mapping.findForward("editEntry");
            }
        }

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());

        try {
            resourceManager.updateDataResourceEntry(entry, appRequest);
        } catch (Exception e) {
            // ��¼����ҵ��Ȩ����Ŀʧ����־
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }
        // ������־
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * ����
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DataResourceForm dataResourceForm = (DataResourceForm) form;
        DataResourceDTO dr = dataResourceForm.getDataResource();
        if (null != dr && null != dr.getResourceId()) {
            // ����view
            return view(mapping, dataResourceForm, request, response);
        } else {
            // ����list
            return mapping.findForward("list");
        }
    }
}
