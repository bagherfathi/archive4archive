package com.ft.web.sm.maintain;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.MaintainLogDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 系统维护日志表单类
 * 
 * @struts.form name="maintainLogForm"
 * @version 1.0
 */
public class MaintainLogForm extends BaseValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MaintainLogDTO maintainLog;

    private int logType;

    private String logTitle;
    
    private Long planId;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (null == maintainLog) {
            maintainLog = new MaintainLogDTO();
        }
        super.reset(mapping, request);
    }

    /**
     * @struts.entity-field initial="id"
     * @return Returns the maintainLog.
     */
    public MaintainLogDTO getMaintainLog() {
        return maintainLog;
    }

    /**
     * @param maintainLog
     *                The maintainLog to set.
     */
    public void setMaintainLog(MaintainLogDTO maintainLog) {
        this.maintainLog = maintainLog;
    }

    /**
     * @return the logType
     */
    public int getLogType() {
        return logType;
    }

    /**
     * @param logType
     *                the logType to set
     */
    public void setLogType(int logType) {
        this.logType = logType;
    }

    /**
     * @return the logTitle
     */
    public String getLogTitle() {
        return logTitle;
    }

    /**
     * @param logTitle
     *                the logTitle to set
     */
    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    /**
     * @return the planId
     */
    public Long getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
