package com.ft.rfms.entity;

import java.io.Serializable;

import com.ft.hibernate.support.IBaseEntity;

/**
  * RfmsFlowCtrl.java Create on 2008-12-16 15:38:49
  * <p> 
  * 实体类:RfmsFlowCtrl
  * <p>
  * 对应表名:RFMS_FLOW_CTRL
  */

public class RfmsFlowCtrl implements Serializable,IBaseEntity {
        private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	// constructors
	public RfmsFlowCtrl () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public RfmsFlowCtrl (Long flowCtrlId) {
		this.setFlowCtrlId(flowCtrlId);
		initialize();
	}

	protected void initialize () {}

/*[CONSTRUCTOR MARKER END]*/


	public static String REF = "RfmsFlowCtrl";
	public static String PROP_FLOW_REMARK = "flowRemark";
	public static String PROP_STATUS_AFTER = "statusAfter";
	public static String PROP_STATUS_BEFORE = "statusBefore";
	public static String PROP_NEXT_FLOW = "nextFlow";
	public static String PROP_FLOW_STEP = "flowStep";
	public static String PROP_FLOW_CTRL_ID = "flowCtrlId";
	public static String PROP_ROLE_ID = "roleId";



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long flowCtrlId;

	// fields
	private java.lang.Long roleId;
	private java.lang.Long statusBefore;
	private java.lang.Long statusAfter;
	private java.lang.Long flowStep;
	private java.lang.Long nextFlow;
	private java.lang.String flowRemark;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="FLOW_CTRL_ID"
     */
	public java.lang.Long getFlowCtrlId () {
		return flowCtrlId;
	}
    public java.lang.Long getId () {
		return flowCtrlId;
	}
	/**
	 * Set the unique identifier of this class
	 * @param flowCtrlId the new ID
	 */
	public void setFlowCtrlId (java.lang.Long flowCtrlId) {
		this.flowCtrlId = flowCtrlId;
		this.hashCode = Integer.MIN_VALUE;
	}
	public void setId (java.lang.Long flowCtrlId) {
		this.flowCtrlId = flowCtrlId;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: ROLE_ID
	 */
	public java.lang.Long getRoleId () {
		return roleId;
	}

	/**
	 * Set the value related to the column: ROLE_ID
	 * @param roleId the ROLE_ID value
	 */
	public void setRoleId (java.lang.Long roleId) {
		this.roleId = roleId;
	}



	/**
	 * Return the value associated with the column: STATUS_BEFORE
	 */
	public java.lang.Long getStatusBefore () {
		return statusBefore;
	}

	/**
	 * Set the value related to the column: STATUS_BEFORE
	 * @param statusBefore the STATUS_BEFORE value
	 */
	public void setStatusBefore (java.lang.Long statusBefore) {
		this.statusBefore = statusBefore;
	}



	/**
	 * Return the value associated with the column: STATUS_AFTER
	 */
	public java.lang.Long getStatusAfter () {
		return statusAfter;
	}

	/**
	 * Set the value related to the column: STATUS_AFTER
	 * @param statusAfter the STATUS_AFTER value
	 */
	public void setStatusAfter (java.lang.Long statusAfter) {
		this.statusAfter = statusAfter;
	}



	/**
	 * Return the value associated with the column: FLOW_STEP
	 */
	public java.lang.Long getFlowStep () {
		return flowStep;
	}

	/**
	 * Set the value related to the column: FLOW_STEP
	 * @param flowStep the FLOW_STEP value
	 */
	public void setFlowStep (java.lang.Long flowStep) {
		this.flowStep = flowStep;
	}



	/**
	 * Return the value associated with the column: NEXT_FLOW
	 */
	public java.lang.Long getNextFlow () {
		return nextFlow;
	}

	/**
	 * Set the value related to the column: NEXT_FLOW
	 * @param nextFlow the NEXT_FLOW value
	 */
	public void setNextFlow (java.lang.Long nextFlow) {
		this.nextFlow = nextFlow;
	}



	/**
	 * Return the value associated with the column: FLOW_REMARK
	 */
	public java.lang.String getFlowRemark () {
		return flowRemark;
	}

	/**
	 * Set the value related to the column: FLOW_REMARK
	 * @param flowRemark the FLOW_REMARK value
	 */
	public void setFlowRemark (java.lang.String flowRemark) {
		this.flowRemark = flowRemark;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof RfmsFlowCtrl)) return false;
		else {
			RfmsFlowCtrl rfmsFlowCtrl = (RfmsFlowCtrl) obj;
			if (null == this.getFlowCtrlId() || null == rfmsFlowCtrl.getFlowCtrlId()) return false;
			else return (this.getFlowCtrlId().equals(rfmsFlowCtrl.getFlowCtrlId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getFlowCtrlId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getFlowCtrlId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}




}