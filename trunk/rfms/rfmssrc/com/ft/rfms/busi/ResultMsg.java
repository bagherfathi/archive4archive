/**
 * 
 */
package com.ft.rfms.busi;

import java.io.Serializable;

/**
 * @author solar
 *
 */
public class ResultMsg implements Serializable {

	public static final String REG_EXISTS="1002";
	public static final String SUCCESS="1001";
	public static final String UNKONW_ERROR="9999";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4481992024322273608L;
	
	private String returnCode;
	private String returnMsg;
	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the returnMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public ResultMsg(String returnCode, String returnMsg) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}
	

}
