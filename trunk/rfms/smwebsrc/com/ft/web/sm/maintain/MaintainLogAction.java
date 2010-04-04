package com.ft.web.sm.maintain;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ft.busi.sm.model.MaintainManager;
import com.ft.common.log.Log;
import com.ft.common.log.LogFactory;
import com.ft.sm.dto.MaintainLogDTO;
import com.ft.web.sm.ActionDefinition;
import com.ft.web.sm.BaseAction;

/**
 * ϵͳά����־Action��
 * 
 * @spring.bean id="maintainLogAction"
 * 
 * @struts.action name="maintainLogForm" path="/maintainLog" scope="request"
 *                input="sm.maintainLog.index" parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.maintainLog.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/maintain/indexLog.jsp"
 * 
 * @struts.action-forward name="create" path="sm.maintain.log.create"
 * 
 * @struts.action-forward name="edit" path="sm.maintain.log.edit"
 * 
 * @version 1.0
 */
public class MaintainLogAction extends BaseAction {

    private static Log logger = LogFactory.getLog(MaintainLogAction.class);

    private MaintainManager maintainManager;

    /**
     * @spring.property ref="maintainManager"
     * @param maintainManager
     *                The maintainManager to set.
     */
    public void setMaintainManager(MaintainManager maintainManager) {
        this.maintainManager = maintainManager;
    }

    /**
     * Ĭ�Ϸ�����ת��sm.maintainLog.indexҳ��
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * ��ѯ
     * <p>
     * ֻ��һ����ת������ҳ����datasource��ɲ�ѯ��
     * </p>
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toSearch(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.getInputForward();
    }

    /**
     * ���ϵͳά����־
     * 
     * @struts.tiles name="sm.maintain.log.create" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/maintain/addLog.jsp"
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
            return mapping.findForward("create");
        }
        // �������룺���ϵͳά����־
        String actionCode = ActionDefinition.SYS_ADD_MAINTAIN_LOG;
        // ��ҳ������־����
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        // ����ǰ����Ա��Ϊ��ϵͳά����־������
        maintainLog.setCreator(maintainLogForm.getCurrentUser().getContact()
                .getName());
        // ������־ʱ��
        maintainLog.setLogTime(new Date());
        try {
            // ����ϵͳά����־
            maintainManager.saveMaintainLog(maintainLog);
        } catch (Exception e) {
            // ��¼����ϵͳά����־ʧ�ܲ�����־
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ��ת���༭ҳ��
     * 
     * @struts.tiles name="sm.maintain.log.edit" extends="main.layout"
     * @struts.tiles-put name="body" value="/sm/maintain/editLog.jsp"
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("edit");
    }

    /**
     * �༭ϵͳά����־��Ϣ
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
        // �������룺����ϵͳά����־
        String actionCode = ActionDefinition.SYS_UPDATE_MAINTAIN_LOG;
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        try {
            // ����
            maintainManager.updateMaintainLog(maintainLog);
        } catch (Exception e) {
            // ��¼����ϵͳά����־ʧ�ܲ�����־
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * ɾ��ϵͳά����־��Ϣ
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
        // �������룺ɾ��ϵͳά����־
        String actionCode = ActionDefinition.SYS_DELETE_MAINTAIN_LOG;
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        try {
            // ɾ��
            maintainManager.deleteMaintainLog(maintainLog.getLogId());
        } catch (Exception e) {
            // ��¼ɾ��ϵͳά����־ʧ�ܲ�����־
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // ��¼������־
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }
}
