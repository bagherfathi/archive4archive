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
 * 系统维护日志Action类
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
     * 默认方法跳转到sm.maintainLog.index页面
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 查询
     * <p>
     * 只是一个跳转方法，页面由datasource完成查询。
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
     * 添加系统维护日志
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
        // 操作编码：添加系统维护日志
        String actionCode = ActionDefinition.SYS_ADD_MAINTAIN_LOG;
        // 从页面获得日志数据
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        // 将当前操作员设为该系统维护日志的作者
        maintainLog.setCreator(maintainLogForm.getCurrentUser().getContact()
                .getName());
        // 设置日志时间
        maintainLog.setLogTime(new Date());
        try {
            // 保存系统维护日志
            maintainManager.saveMaintainLog(maintainLog);
        } catch (Exception e) {
            // 记录新增系统维护日志失败操作日志
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 跳转到编辑页面
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
     * 编辑系统维护日志信息
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
        // 操作编码：更新系统维护日志
        String actionCode = ActionDefinition.SYS_UPDATE_MAINTAIN_LOG;
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        try {
            // 更新
            maintainManager.updateMaintainLog(maintainLog);
        } catch (Exception e) {
            // 记录更新系统维护日志失败操作日志
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }

    /**
     * 删除系统维护日志信息
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
        // 操作编码：删除系统维护日志
        String actionCode = ActionDefinition.SYS_DELETE_MAINTAIN_LOG;
        MaintainLogForm maintainLogForm = (MaintainLogForm) form;
        MaintainLogDTO maintainLog = maintainLogForm.getMaintainLog();
        try {
            // 删除
            maintainManager.deleteMaintainLog(maintainLog.getLogId());
        } catch (Exception e) {
            // 记录删除系统维护日志失败操作日志
            logger.log(request, actionCode, maintainLog.getTitle(),
                    ActionDefinition.ACTION_FAIL);
            logger.error("Excute action " + actionCode
                    + " failed,action sequence =" + maintainLog.getTitle(), e);
            throw e;
        }
        // 记录操作日志
        logger.log(request, actionCode, maintainLog.getTitle(),
                ActionDefinition.ACTION_SUCCESS);
        return mapping.getInputForward();
    }
}
