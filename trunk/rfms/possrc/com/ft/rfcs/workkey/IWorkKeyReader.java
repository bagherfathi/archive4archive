package com.ft.rfcs.workkey;


import com.ft.rfcs.FTPSException;

public interface IWorkKeyReader {

	public WorkKey read(String systemCode) throws FTPSException;
}
