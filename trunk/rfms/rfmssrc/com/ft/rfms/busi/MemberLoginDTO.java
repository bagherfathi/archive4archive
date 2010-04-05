/**
 * 
 */
package com.ft.rfms.busi;

import java.io.Serializable;

import com.ft.rfms.entity.RfmsMember;

/**
 * @author solar
 * 
 */
public class MemberLoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9150443907068075618L;

	private RfmsMember member;

	private ResultMsg resultMsg;

	/**
	 * @return the resultMsg
	 */
	public ResultMsg getResultMsg() {
		return resultMsg;
	}

	/**
	 * @param resultMsg
	 *            the resultMsg to set
	 */
	public void setResultMsg(ResultMsg resultMsg) {
		this.resultMsg = resultMsg;
	}

	public RfmsMember getMember() {
		return member;
	}

	public void setMember(RfmsMember member) {
		this.member = member;
	}

}
