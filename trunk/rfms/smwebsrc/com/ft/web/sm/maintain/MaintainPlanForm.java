package com.ft.web.sm.maintain;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.MaintainPlanDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 系统维护表单类
 * 
 * @struts.form name="maintainPlanForm"
 * @version 1.0
 */
public class MaintainPlanForm extends BaseValidatorForm {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String planName;
    private String cycleUnit;
    
    private MaintainPlanDTO maintainPlan;

    private String act;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (null == maintainPlan) {
            maintainPlan = new MaintainPlanDTO();
        }
        super.reset(mapping, request);
    }

    /**
     * @struts.entity-field initial="id"
     * @return Returns the maintainPlan.
     */
    public MaintainPlanDTO getMaintainPlan() {
        return maintainPlan;
    }

    /**
     * @param maintainPlan
     *                The maintainPlan to set.
     */
    public void setMaintainPlan(MaintainPlanDTO maintainPlan) {
        this.maintainPlan = maintainPlan;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return the planName
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * @param planName the planName to set
     */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    /**
     * @return the cycleUnit
     */
    public String getCycleUnit() {
        return cycleUnit;
    }

    /**
     * @param cycleUnit the cycleUnit to set
     */
    public void setCycleUnit(String cycleUnit) {
        this.cycleUnit = cycleUnit;
    }
}
