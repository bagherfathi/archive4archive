package com.ft.web.sm.priv.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.dto.AppRequest;
import com.ft.busi.sm.model.ResourceManager;
import com.ft.busi.sm.model.RoleManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.common.security.DataResourceTreeBuilder;
import com.ft.sm.dto.DataResourceDTO;
import com.ft.sm.dto.DataResourceEntryDTO;
import com.ft.sm.dto.RoleDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;
import com.ft.commons.tree.TreeNode;
import com.ft.struts.ActionMessagesHelper;

/**
 * ҵ���ɫ������
 * 
 * 
 * @spring.bean name="dataRoleAction"
 * 
 * @struts.action name="dataRoleForm" path="/dataRole" scope="request"
 *                input="sm.data.role.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.data.role.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/role/listDataRole.jsp"
 * 
 * @struts.action-forward name="create" path="sm.data.role.create"
 * 
 * @struts.action-forward name="preview" path="sm.data.role.preview"
 * 
 * @struts.action-forward name="view" path="sm.data.role.view"
 * 
 * @struts.action-forward name="update" path="sm.data.role.update"
 * 
 * @version 1.0
 */
public class DataRoleAction extends BaseAction {

    private static Log logger = LogFactory.getLog(DataRoleAction.class);

    private DataResourceTreeBuilder dataResourceTreeBuilder;

    private RoleManager roleManager;

    private ResourceManager resourceManager;

    /**
     * ��תĿ�곣��
     */
    private static final String FORWARD = "dataRole.do";

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
	    //List dataRoles = roleManager.findRoleByType(new Long(RoleDTO.ROLE_TYPE_DATA));
    	DataRoleForm dataRoleForm = (DataRoleForm) form;
    	List dataRoles = roleManager.findRoleByRoleName(dataRoleForm.getRole().getRoleName(),RoleDTO.ROLE_TYPE_DATA);
    	request.setAttribute("dataRoles", dataRoles);
    	
        return mapping.getInputForward();
    }

    /**
     * ��ת��������ɫҳ��
     * 
     * @struts.tiles name="sm.data.role.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/addDataRole.jsp"
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
        return mapping.findForward("create");
    }

    /**
     * ����request�е�entryId
     * 
     * @param request
     * @return
     * @throws Exception
     */
    private List findEntryIdOfRequest(HttpServletRequest request)
            throws Exception {

        // �Ȼ�����е�ҵ��Ȩ������
        List allDataResource = new ArrayList();
        List<DataResourceEntryDTO> entryList = new ArrayList<DataResourceEntryDTO>();
        allDataResource = this.resourceManager.findAllDataResource();
        for (Iterator iter = allDataResource.iterator(); iter.hasNext();) {
            DataResourceDTO dataR = (DataResourceDTO) iter.next();
            String name = dataR.getTitle();
            String[] entryIds = request.getParameterValues(name);
            if (null != entryIds) {
                for (int i = 0; i < entryIds.length; i++) {
                    String idStr = entryIds[i];
                    Long id = Long.valueOf(idStr);
                    DataResourceEntryDTO entry = this.resourceManager
                            .findDataResourceEntryById(id);
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    /**
     * ����Ԥ��ҳ��
     * 
     * @struts.tiles name="sm.data.role.preview" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/previewDataRole.jsp"
     * 
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

        // ��request�л����ѡ��ҵ����Ŀ
        List entryList = new ArrayList();
        entryList = findEntryIdOfRequest(request);

        DataRoleForm roleForm = (DataRoleForm) form;
        RoleDTO role = roleForm.getRole();

        List dataResourceList = new ArrayList();
        dataResourceList = this.getDataResourceByEntryList(entryList);

        TreeNode tree = dataResourceTreeBuilder.buildTreeNode(dataResourceList,
                entryList);
        HttpSession hs = request.getSession();
        hs.setAttribute("entryList", entryList);
        request.setAttribute("role", role);
        request.setAttribute("root", tree);
        return mapping.findForward("preview");
    }

    /**
     * ִ��ҵ���ɫ�������
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

        String actionCode = ActionDefinition.SYS_ADD_DATA_ROLE;

        DataRoleForm roleForm = (DataRoleForm) form;
        List entryList = findEntryIdOfRequest(request);
        RoleDTO role = roleForm.getRole();
        
        //����
        RoleDTO existRole = this.roleManager.findRoleByName(role.getRoleName(), RoleDTO.ROLE_TYPE_DATA);
        if(existRole != null && !existRole.getRoleId().equals(role.getRoleId())){
            ActionMessagesHelper.saveRequestMessage(request,
            "errors.datarole.name.exist",new Object[]{role.getRoleName()});
            return mapping.findForward("create");
        }
//        HttpSession hs = request.getSession();
//        List entryList = new ArrayList();
//        entryList = (List) hs.getAttribute("entryList");
//        RoleDTO role = roleForm.getRole();
//        hs.removeAttribute("entryList");
      
        AppRequest appRequest = roleForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());
        
        try {
            roleManager.addDataRole(roleForm.getCurrentUser(), role, entryList,appRequest);
        } catch (Exception e) {
            // ��¼����ʧ����־
            logger.log(request, actionCode, appRequest.getAppId()+"",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId()+"", e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, appRequest.getAppId()+"",
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction(FORWARD);
    }

    /**
     * ҵ���ɫɾ������
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

        String actionCode = ActionDefinition.SYS_DELETE_DATA_ROLE;

        DataRoleForm roleForm = (DataRoleForm) form;
        String roleName = roleForm.getRole().getRoleName();

        try {
            roleManager.deleteRole(roleForm.getCurrentUser(), roleForm
                    .getRole(), roleForm.getAppRequest(request, actionCode));
        } catch (Exception e) {
            // ��¼ɾ��ʧ����־
            logger.log(request, actionCode, roleName,
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + roleName, e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, roleName,
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction(FORWARD);
    }

    /**
     * ���ҳ��
     * 
     * @struts.tiles name="sm.data.role.view" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/viewDataRoleIndex.jsp"
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
        DataRoleForm roleForm = (DataRoleForm) form;
        RoleDTO role = roleForm.getRole();

        List dataResourceList = new ArrayList();
        // ��õ�ǰ��ɫ��ӵ�е�ҵ��Ȩ����Ŀ
        List dataResourceEntryList = this.resourceManager
                .findDataResourcesOfRole(role.getRoleId());

        dataResourceList = getDataResourceByEntryList(dataResourceEntryList);

        TreeNode tree = dataResourceTreeBuilder.buildTreeNode(dataResourceList,
                dataResourceEntryList);
        request.setAttribute("role", role);
        request.setAttribute("root", tree);
        return mapping.findForward("view");
    }

    /**
     * ͨ����Ŀ���ҵ��Ȩ��
     * 
     * @param entryList
     * @return
     */
    private List getDataResourceByEntryList(List entryList) {
        List<DataResourceDTO> dataResourceList = new ArrayList<DataResourceDTO>();

        if (null != entryList && entryList.size() > 0) {
            Map<Long,DataResourceDTO> dataResourceMap = new HashMap<Long,DataResourceDTO>();
            for (Iterator iter = entryList.iterator(); iter.hasNext();) {
                DataResourceEntryDTO entry = (DataResourceEntryDTO) iter.next();
                DataResourceDTO dRes = new DataResourceDTO(entry
                        .getDataResource());
                if (!dataResourceMap.containsKey(dRes.getResourceId())) {
                    dataResourceMap.put(dRes.getResourceId(), dRes);
                }
            }

            Set keys = dataResourceMap.keySet();
            for (Iterator iter = keys.iterator(); iter.hasNext();) {
                Long keyId = (Long) iter.next();
                dataResourceList.add((DataResourceDTO) dataResourceMap
                        .get(keyId));
            }
        }
        return dataResourceList;
    }

    /**
     * ��ת���༭ҳ��
     * 
     * @struts.tiles name="sm.data.role.update" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/role/editDataRole.jsp"
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
        DataRoleForm roleForm = (DataRoleForm) form;
        RoleDTO role = roleForm.getRole();
        request.setAttribute("role", role);
        return mapping.findForward("update");
    }

    /**
     * �༭��ɫ
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

        String actionCode = ActionDefinition.SYS_UPDATE_DATA_ROLE;

        DataRoleForm roleForm = (DataRoleForm) form;
        List entryList = new ArrayList();
        entryList = findEntryIdOfRequest(request);
        
        AppRequest appRequest = roleForm.getAppRequest(request, actionCode);
		// AppDTO app = appService.saveApp(appRequest);
		// appRequest.setAppId(app.getApp().getAppId());

        try {
            roleManager.updateDataRole(roleForm.getCurrentUser(), roleForm
                    .getRole(), entryList, appRequest);
        } catch (Exception e) {
            // ��¼����ʧ����־
            logger.log(request, actionCode, appRequest.getAppId()+"",
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + appRequest.getAppId()+"", e);
            throw e;
        }

        // ������־
        logger.log(request, actionCode, appRequest.getAppId()+"",
                ActionDefinition.ACTION_SUCCESS);

        return getRedirectForwardAction(FORWARD);
    }

    /**
     * @spring.property ref="dataResourceTreeBuilder"
     */
    public void setDataResourceTreeBuilder(
            DataResourceTreeBuilder dataResourceTreeBuilder) {
        this.dataResourceTreeBuilder = dataResourceTreeBuilder;
    }

    /**
     * @spring.property ref="roleManager"
     */
    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * @spring.property ref="resourceManager"
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
}
