package com.ft.rfcs.workkey;

public class WorkKey {

	private byte[] pinKey;
	private byte[] macKey;
	/**
	 * @return the pinKey
	 */
	public byte[] getPinKey() {
		return pinKey;
	}
	/**
	 * @param pinKey the pinKey to set
	 */
	public void setPinKey(byte[] pinKey) {
		this.pinKey = pinKey;
	}
	/**
	 * @return the macKey
	 */
	public byte[] getMacKey() {
		return macKey;
	}
	/**
	 * @param macKey the macKey to set
	 */
	public void setMacKey(byte[] macKey) {
		this.macKey = macKey;
	}
	
	
}
