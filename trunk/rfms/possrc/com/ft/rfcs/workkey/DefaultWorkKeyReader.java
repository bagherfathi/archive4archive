/**
 * 
 */
package com.ft.rfcs.workkey;


import com.ft.rfcs.FTPSException;

/**
 * @author lch
 *
 */
public class DefaultWorkKeyReader implements IWorkKeyReader {

	/* (non-Javadoc)
	 * @see com.ft.pgframework.workkey.IWorkKeyReader#read(com.ft.iso8583.ISO8583Message)
	 */
	public WorkKey read(String systemCode) throws FTPSException {
		WorkKey workkey = new WorkKey();
		workkey.setMacKey(new byte[]{0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38});
		workkey.setPinKey(new byte[]{0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38});
		return workkey;
	}

}
