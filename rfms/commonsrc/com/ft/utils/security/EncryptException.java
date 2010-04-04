package com.ft.utils.security;


public class EncryptException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6799290908963738631L;
	public String errCode;
	public String errMessage;
	
	public EncryptException(String errMessage){
		super(errMessage);
		this.errMessage=errMessage;
	}


}