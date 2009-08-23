package com.renhenet.po;

import com.renhenet.fw.orm.IdPersistent;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class Structure extends IdPersistent implements java.io.Serializable {
	private String serialNumber;
	private String znName;
	private int dictionaryName;
	private int ifUpdate;
	private int infoSortId;
	private int taxis;
	private int type;
	private int length;
	private int decimalDigits;
	private int inputFashion;
	private int isNull;
	private int isOverlap;
	private int isIndex;
	private int isQuery;
	private int isTypeQuery;
	private int isPurview;
	private int isSelfMotion;
	private int isExtend;
	private int isBalance;
	private int isSerialNumber;
	private int isDelete;
	private int isList;
	// µÚ¼¸²ã
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsList() {
		return isList;
	}

	public void setIsList(int isList) {
		this.isList = isList;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getTaxis() {
		return taxis;
	}

	public void setTaxis(int taxis) {
		this.taxis = taxis;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getIsNull() {
		return isNull;
	}

	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}

	public String getZnName() {
		return znName;
	}

	public void setZnName(String znName) {
		this.znName = znName;
	}

	public int getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(int dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public int getIfUpdate() {
		return ifUpdate;
	}

	public void setIfUpdate(int ifUpdate) {
		this.ifUpdate = ifUpdate;
	}

	public int getInfoSortId() {
		return infoSortId;
	}

	public void setInfoSortId(int infoSortId) {
		this.infoSortId = infoSortId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public int getInputFashion() {
		return inputFashion;
	}

	public void setInputFashion(int inputFashion) {
		this.inputFashion = inputFashion;
	}

	public int getIsOverlap() {
		return isOverlap;
	}

	public void setIsOverlap(int isOverlap) {
		this.isOverlap = isOverlap;
	}

	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	public int getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(int isQuery) {
		this.isQuery = isQuery;
	}

	public int getIsTypeQuery() {
		return isTypeQuery;
	}

	public void setIsTypeQuery(int isTypeQuery) {
		this.isTypeQuery = isTypeQuery;
	}

	public int getIsPurview() {
		return isPurview;
	}

	public void setIsPurview(int isPurview) {
		this.isPurview = isPurview;
	}

	public int getIsSelfMotion() {
		return isSelfMotion;
	}

	public void setIsSelfMotion(int isSelfMotion) {
		this.isSelfMotion = isSelfMotion;
	}

	public int getIsExtend() {
		return isExtend;
	}

	public void setIsExtend(int isExtend) {
		this.isExtend = isExtend;
	}

	public int getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(int isBalance) {
		this.isBalance = isBalance;
	}

	public int getIsSerialNumber() {
		return isSerialNumber;
	}

	public void setIsSerialNumber(int isSerialNumber) {
		this.isSerialNumber = isSerialNumber;
	}

}
