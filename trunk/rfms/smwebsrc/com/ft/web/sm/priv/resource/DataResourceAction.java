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
 * 业务权限权限维护类
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
     * 权限信息维护类
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
     * 跳转到业务权限信息列表
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
     * 新增业务权限
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
        // 新建业务权限

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            Long resourceId = resourceManager.addDataResource(dataResource,
                    appRequest);
            dataResource.setResourceId(resourceId);
        } catch (Exception e) {
            // 记录新增业务权限失败日志
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }

        // 操作日志
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        // 创建完业务权限进入此业务权限的view页面
        return mapping.findForward("viewDataResource");
    }

    /**
     * 业务权限信息浏览页面
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
     * 删除业务权限
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
            // 记录删除失败日志
            logger.log(request, actionCode, code, ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + code, e);
            throw e;
        }

        // 操作日志
        logger.log(request, actionCode, code, ActionDefinition.ACTION_SUCCESS);
        // 跳转到业务权限列表
        return this.getRedirectForwardAction("dataResource.do");
    }

    /**
     * 编辑业务权限信息
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
        // 通过method判断是保存还是跳转
        if (request.getMethod().equals("GET")) {
            return mapping.findForward("edit");
        }

        // 保存业务权限信息

        AppRequest appRequest = drForm.getAppRequest(request, actionCode);
//        AppDTO app = appService.saveApp(appRequest);
//        appRequest.setAppId(app.getApp().getAppId());

        try {
            resourceManager.updateDataResource(dataResource, appRequest);
        } catch (Exception e) {
            // 记录更新失败日志
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * 跳转到新增条目信息页面
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
     * 保存条目信息
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

        // 判断此业务权限条目标题在此业务权限中是否已存在
        if (resourceManager.isExistEntryByDataResource(entry.getEntryId(),
                entry.getTitle(), dataResource.getResourceId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.data.resource.entry.exist");
            return mapping.findForward("addEntry");
        }

        // 判断单值型业务权限的条目是否重复
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
            // 记录新增业务权限条目失败日志
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }

        // 操作日志
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * 删除业务权限条目信息
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
            // 记录删除业务权限条目失败日志
            logger.log(request, actionCode, entryId,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + entryId, e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, entryId,
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * 跳转到条目信息编辑页面
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
     * 编辑业务权限条目信息
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
        // 判断此业务权限条目标题在此业务权限中是否已存在
        if (resourceManager.isExistEntryByDataResource(entry.getEntryId(),
                entry.getTitle(), dataResource.getResourceId())) {
            ActionMessagesHelper.saveRequestMessage(request,
                    "errors.data.resource.entry.exist");
            return mapping.findForward("editEntry");
        }

        // 判断单值型业务权限的条目是否重复
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
            // 记录更新业务权限条目失败日志
            logger.log(request, actionCode, appRequest.getAppId() + "",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId() + "",
                    e);
            throw e;
        }
        // 操作日志
        logger.log(request, actionCode, appRequest.getAppId() + "",
                ActionDefinition.ACTION_SUCCESS);
        return this.getRedirectForwardAction("dataResource.do?act=view&id="
                + dataResource.getResourceId());
        // return mapping.findForward("viewDataResource");
    }

    /**
     * 返回
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
            // 返回view
            return view(mapping, dataResourceForm, request, response);
        } else {
            // 返回list
            return mapping.findForward("list");
        }
    }
}
