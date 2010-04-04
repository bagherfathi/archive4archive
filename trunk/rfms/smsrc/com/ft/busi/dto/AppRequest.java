package com.ft.busi.dto;

/**
 * �����¼�ӿ���������ࡣ
 * 
 * @version 1.0
 */
public class AppRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * �����¼��ʶ
	 */
	private long appId;

	/** ������ */
	private String appCode;
    /**��������ʶ*/
	private long relatAppId;
	/**
	 * ����Ա��ʶ��
	 */
	private long operatorId;

	/**
	 * Ӫҵ����֯��ʶ��
	 */
	private long orgId;

	/**
	 * ��½��֯��ʶ��
	 */
	private long loginOrgId;

	/**
	 * �ֹ�˾��֯��ʶ��
	 */
	private long corpOrgId;

	/**
	 * ��¼������֯��ʶ��
	 */
	private long recOrgId;

	/**
	 * ����Ӫҵ����ʶ
	 */
	private long officeOrgId;

	/**
	 * �������ͱ�ʶ��
	 */
	private String appAction;

	/**
	 * ����ע��
	 */
	private String notes;

	/**
	 * ֱ��ͨ��busi�����ݿ����: ---��Ҫ��Ϊ����������������ϸʹ��
	 * <li>true:ֱ�ӱ������¶���</li>
	 * <li>false:�����������¶���</li>
	 */
	private boolean directSaveUpdate;

	/** �����¼������ϸ.��1;ɾ:0;��2 */
	private Long operatorType;

	/**
	 * 
	 * ���캯����
	 */
	public AppRequest() {

	}

	/**
	 * 
	 * @return �����������͡�
	 */
	public String getAppAction() {
		return appAction;
	}

	/**
	 * �����������͡�
	 * 
	 * @param appAction
	 *            �������͡�
	 */
	public void setAppAction(String appAction) {
		this.appAction = appAction;
	}

	/**
	 * @return ���ر�ע��
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * ���ñ�ע��Ϣ��
	 * 
	 * @param notes
	 *            ��ע��Ϣ��
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * ���ز���Ա��ʶ��
	 * 
	 * @return ���ز���Ա��ʶ��
	 */
	public long getOperatorId() {
		return operatorId;
	}

	/**
	 * ���ò���Ա��ʶ��
	 * 
	 * @param operatorId
	 *            ����Ա��ʶ��
	 */
	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * ���ص�½��֯��ʶ��
	 * 
	 * @return ���ص�½��֯��ʶ��
	 */
	public long getLoginOrgId() {
		return loginOrgId;
	}

	/**
	 * ���õ�½��֯��ʶ��
	 * 
	 * @param loginOrgId
	 *            ��½��֯��ʶ��
	 */
	public void setLoginOrgId(long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}

	/**
	 * ������֯��ʶ��
	 * 
	 * @return ��֯��ʶ��
	 */
	public long getOrgId() {
		return orgId;
	}

	/**
	 * ������֯��ʶ��
	 * 
	 * @param orgId
	 *            ��֯��ʶ��
	 */
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return ���������¼��ʶ��
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * ���������¼��ʶ
	 * 
	 * @param appId
	 *            �����¼��ʶ��
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}

	/**
	 * @return Returns the corpOrgId.
	 */
	public long getCorpOrgId() {
		return corpOrgId;
	}

	/**
	 * @param corpOrgId
	 *            The corpOrgId to set.
	 */
	public void setCorpOrgId(long corpOrgId) {
		this.corpOrgId = corpOrgId;
	}

	/**
	 * @return Returns the recOrgId.
	 */
	public long getRecOrgId() {
		return recOrgId;
	}

	/**
	 * @param recOrgId
	 *            The recOrgId to set.
	 */
	public void setRecOrgId(long recOrgId) {
		this.recOrgId = recOrgId;
	}

	/**
	 * ����directSaveUpdate�� ֱ��ͨ��busi�����ݿ����: ---��Ҫ��Ϊ����������������ϸʹ��
	 * <li>true:ֱ�ӱ������¶���</li>
	 * <li>false:�����������¶���</li>
	 * 
	 * @return Returns the directSaveUpdate.
	 */
	public boolean isDirectSaveUpdate() {
		return directSaveUpdate;
	}

	/**
	 * ����directSaveUpdate �� directSaveUpdate ֱ��ͨ��busi�����ݿ����: --��Ҫ��Ϊ����������������ϸʹ��
	 * <li>true:ֱ�ӱ������¶���</li>
	 * <li>false:�����������¶���</li>
	 * 
	 * @param directSaveUpdate
	 *            The directSaveUpdate to set.
	 */
	public void setDirectSaveUpdate(boolean directSaveUpdate) {
		this.directSaveUpdate = directSaveUpdate;
	}

	/**
	 * ����operatorType�� �����¼������ϸ.��1;ɾ:0;��2
	 * 
	 * @return Returns the operatorType.
	 */

	public Long getOperatorType() {
		return operatorType;
	}

	/**
	 * ����operatorType �� operatorType �����¼������ϸ.��1;ɾ:0;��2
	 * 
	 * @param operatorType
	 *            The operatorType to set.
	 */

	public void setOperatorType(Long operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * @return Returns the officeOrgId.
	 */
	public long getOfficeOrgId() {
		return officeOrgId;
	}

	/**
	 * @param officeOrgId The officeOrgId to set.
	 */
	public void setOfficeOrgId(long officeOrgId) {
		this.officeOrgId = officeOrgId;
	}

	/**
	 * ����appCode��
	 * @return Returns the appCode.
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * ����appCode �� appCode
	 * @param appCode The appCode to set.
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * ����relatAppId��
	 * @return Returns the relatAppId.
	 */
	public long getRelatAppId() {
		return relatAppId;
	}

	/**
	 * ����relatAppId �� relatAppId
	 * @param relatAppId The relatAppId to set.
	 */
	public void setRelatAppId(long relatAppId) {
		this.relatAppId = relatAppId;
	}

}
