/**
 * 
 */
package com.ft.rfcs;



import com.ft.rfcs.FTPSException;


/**
 * @author lch
 *
 */
public class FTPSException extends Exception {

	
	private String errcode;
	private String errInfo;
	/**
	 * 
	 */
	public FTPSException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param errcode
	 * @param errInfo
	 * @param throwable
	 */
	public FTPSException(String errcode,String errInfo,Throwable throwable){
		super(throwable);
		this.errcode = errcode;
		this.errInfo = errInfo;
	}
	
	/**
	 * 
	 * @param errcode
	 * @param errInfo
	 * @param throwable
	 */
	public FTPSException(String errcode,String errInfo){
		this.errcode = errcode;
		this.errInfo = errInfo;
	}
	/**
	 * @param arg0
	 */
	public FTPSException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public FTPSException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FTPSException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the errcode
	 */
	public String getErrcode() {
		return errcode;
	}

	/**
	 * @return the errInfo
	 */
	public String getErrInfo() {
		return errInfo;
	}
	
	

}
