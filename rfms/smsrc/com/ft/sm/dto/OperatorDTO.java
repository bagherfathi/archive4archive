package com.ft.sm.dto;

import java.io.Serializable;

import com.ft.sm.entity.Contact;
import com.ft.sm.entity.CycleDate;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;

/**
 * ����Աʵ���װ�ࡣ
 * 
 */
public class OperatorDTO implements Serializable {
    private static final long serialVersionUID = -6173047502861232862L;

    // ����Ա״̬
    public static final long STATUS_DISABLE = 1; // ��ֹ״̬

    public static final long STATUS_NORMAL = 0; // ����״̬

    private Organization org;

    private Contact contact;

    private CycleDate cycleDate;

    private Operator operator;

    public OperatorDTO() {
        this.cycleDate = new CycleDate();
        this.contact = new Contact();
        operator = new Operator();
        operator.setSsoStatus(STATUS_NORMAL);
        operator.setSsoAccessed(STATUS_NORMAL);
    }

    public OperatorDTO(Operator op) {
        this.operator = copyFrom(op);
        
        this.cycleDate = new CycleDate();
        this.contact = new Contact();

        this.contact.setAddress(operator.getAddress());
        this.contact.setMobilePhone(operator.getMobilePhone());
        this.contact.setName(operator.getOpName());
        this.contact.setPostCode(operator.getPostcode());
        this.contact.setTelephone(operator.getTelePhone());

        this.cycleDate.setCreateDate(operator.getCreateTime());
        this.cycleDate.setExpireDate(operator.getExpireTime());
        this.cycleDate.setModifyDate(operator.getModifyTime());
        this.cycleDate.setValidDate(operator.getValidTime());
    }

    public OperatorDTO(Operator op, Organization org) {
        
        this.operator = copyFrom(op);
        this.org = OrganizationDTO.copyFrom(org);
        this.operator.setOrgId(org.getOrgId());

        this.cycleDate = new CycleDate();
        this.contact = new Contact();

        this.contact.setAddress(operator.getAddress());
        this.contact.setMobilePhone(operator.getMobilePhone());
        this.contact.setName(operator.getOpName());
        this.contact.setPostCode(operator.getPostcode());
        this.contact.setTelephone(operator.getTelePhone());

        this.cycleDate.setCreateDate(operator.getCreateTime());
        this.cycleDate.setExpireDate(operator.getExpireTime());
        this.cycleDate.setModifyDate(operator.getModifyTime());
        this.cycleDate.setValidDate(operator.getValidTime());
    }

    /**
	 * @return the merchantCode
	 */
	public java.lang.String getMerchantCode() {
		return this.operator.getMerchantCode();
	}

	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(java.lang.String merchantCode) {
		this.operator.setMerchantCode(merchantCode);
	}
	
    /**
     * ����Ա��½���ơ�
     * 
     * @return
     */
    public String getLoginName() {
        return this.operator.getLoginName();
    }

    public void setLoginName(String loginName) {
        this.operator.setLoginName(loginName);
    }

    /**
     * ����Ա����
     * 
     * @return
     */
    public String getName() {
        return this.operator.getOpName();
    }

    public void setName(String name) {
        this.operator.setOpName(name);
    }

    /**
     * ����Ա��½���
     * 
     * @return
     */
    public String getPassword() {
        return this.operator.getPassword();
    }

    public void setPassword(String password) {
        this.operator.setPassword(password);
    }

    /**
     * ����ԱID��
     * 
     * @return
     */
    public Long getOperatorId() {
        return new Long(this.operator.getOperatorId());
    }

    public void setOperatorId(Long operatorId) {
        this.operator.setOperatorId(operatorId.longValue());
    }

    /**
     * ����Ա������֯������
     * 
     * @return
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
        if (org != null)
            this.operator.setOrgId(org.getOrgId());
    }
    
    /**
     * ��֯Id
     * @return
     */
    public Long getOrgId() {
        return new Long(this.operator.getOrgId());
    }

    public void setOrgId(Long orgId) {
        this.operator.setOrgId(orgId.longValue());
    }

    /**
     * ����Ա���������ַ��
     * 
     * @return Returns the email.
     */
    public String getEmail() {
        return this.operator.getEmail();
    }

    /**
     * @param email
     *                The email to set.
     */
    public void setEmail(String email) {
        this.operator.setEmail(email);
    }

    /**
     * ����ԱMSN�ʺš�
     * 
     * @return Returns the msn.
     */
    public String getMsn() {
        return this.operator.getMsn();
    }

    /**
     * @param msn
     *                The msn to set.
     */
    public void setMsn(String msn) {
        this.operator.setMsn(msn);
    }

    /**
     * ����Ա״̬��
     * 
     * @return Returns the status.
     */
    public long getStatus() {
        return this.operator.getStatus();
    }

    /**
     * @param status
     *                The status to set.
     */
    public void setStatus(long status) {
        this.operator.setStatus(status);
    }

    /**
     * ����Ա��ϵ��Ϣ��
     * 
     * @return
     */
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        if (contact != null) {
            this.operator.setAddress(contact.getAddress());
            this.operator.setMobilePhone(contact.getMobilePhone());
            this.operator.setOpName(contact.getName());
            this.operator.setPostcode(contact.getPostCode());
            this.operator.setTelePhone(contact.getTelephone());
        }
    }

    /**
     * ʱ����Ϣ����������ʱ�䡢��Чʱ�䡢��ֹʱ����޸�ʱ�䡣
     * 
     * @return
     */
    public CycleDate getCycleDate() {
        return cycleDate;
    }

    public void setCycleDate(CycleDate cycleDate) {
        this.cycleDate = cycleDate;
        if (cycleDate != null) {
            this.operator.setCreateTime(cycleDate.getCreateDate());
            this.operator.setValidTime(cycleDate.getValidDate());
            this.operator.setExpireTime(cycleDate.getExpireDate());
            this.operator.setModifyTime(cycleDate.getModifyDate());
        }
    }

    /**
     * ����״̬��
     * 
     * @return
     */
    public long getLockStatus() {
        return this.operator.getLockStatus();
    }

    public void setLockStatus(long lockStatus) {
        this.operator.setLockStatus(lockStatus);
    }

    /**
     * ��ע��Ϣ��
     * 
     * @return
     */
    public String getMemo() {
        return this.operator.getMemo();
    }

    public void setMemo(String memo) {
        this.operator.setMemo(memo);
    }

    /**
     * ֤�����롣
     * 
     * @return
     */
    public String getRegNumber() {
        return this.operator.getRegNumber();
    }

    public void setRegNumber(String regNumber) {
        this.operator.setRegNumber(regNumber);
    }

    /**
     * ֤�����͡�
     * 
     * @return
     */
    public long getRegType() {
        return this.operator.getRegType();
    }

    public void setRegType(long regType) {
        this.operator.setRegType(regType);
    }

    /**
     * ����Ա���͡�
     * 
     * @return
     */
    public long getType() {
        return this.operator.getType();
    }

    public void setType(int type) {
        this.operator.setType(type);
    }

    /**
     * ����Ա�������ţ�����Ա������֯����
     */
    public Organization getDepartment() {
        return this.org;
    }

    /**
     * SSOϵͳ�в���Ա������֯��Ωһ��ʶ��
     * 
     * @return Returns the orgSSOCode.
     */
    public String getOrgSSOCode() {
        return this.operator.getOrgSsoCode();
    }

    /**
     * @param orgSSOCode
     *                The orgSSOCode to set.
     */
    public void setOrgSSOCode(String orgSSOCode) {
        this.operator.setOrgSsoCode(orgSSOCode);
    }

    /**
     * SSOϵͳ��Ωһ��ʶ��
     * 
     * @return Returns the ssoCode.
     */
    public String getSsoCode() {
        return this.operator.getSsoCode();
    }

    /**
     * @param ssoCode
     *                The ssoCode to set.
     */
    public void setSsoCode(String ssoCode) {
        this.operator.setSsoCode(ssoCode);
    }

    /**
     * ����Ա���š�
     * 
     * @return Returns the jobNumber.
     */
    public String getJobNumber() {
        return this.operator.getJobNumber();
    }

    /**
     * @param jobNumber
     *                The jobNumber to set.
     */
    public void setJobNumber(String jobNumber) {
        this.operator.setJobNumber(jobNumber);
    }

    /**
     * SSOϵͳ�в���Ա�Ƿ�ɷ��ʱ�Ӧ�á�
     * 
     * @return the ssoAccessed
     */
    public long getSsoAccessed() {
        return this.operator.getSsoAccessed();
    }

    /**
     * @param ssoAccessed
     *                the ssoAccessed to set
     */
    public void setSsoAccessed(long ssoAccessed) {
        this.operator.setSsoAccessed(ssoAccessed);
    }

    /**
     * SSOϵͳ�в���Ա״̬��
     * 
     * @return the ssoStatus
     */
    public long getSsoStatus() {
        return this.operator.getSsoStatus();
    }

    /**
     * @param ssoStatus
     *                the ssoStatus to set
     */
    public void setSsoStatus(long ssoStatus) {
        this.operator.setSsoStatus(ssoStatus);
    }

    /**
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }
    
    public static Operator copyFrom(Operator op) {
        Operator operator = new Operator();
        
        operator.setOperatorId(op.getOperatorId()) ;
        operator.setOrgId(op.getOrgId());
        operator.setOpName(op.getOpName());
        operator.setLoginName(op.getLoginName());
        operator.setType(op.getType());
        operator.setLockStatus(op.getLockStatus());
        operator.setMemo(op.getMemo());
        operator.setRegionId(op.getRegionId());
        operator.setEmail(op.getEmail());
        operator.setMsn(op.getMsn());
        operator.setPassword(op.getPassword());
        operator.setStatus(op.getStatus());
        operator.setAddress(op.getAddress());
        operator.setMobilePhone(op.getMobilePhone());
        operator.setTelePhone(op.getTelePhone());
        operator.setPostcode(op.getPostcode());
        operator.setRegType(op.getRegType());
        operator.setRegNumber(op.getRegNumber());
        operator.setCreateTime(op.getCreateTime());
        operator.setModifyTime(op.getModifyTime());
        operator.setValidTime(op.getValidTime());
        operator.setExpireTime(op.getExpireTime());
        operator.setSsoCode(op.getSsoCode());
        operator.setJobNumber(op.getJobNumber());
        operator.setOrgSsoCode(op.getOrgSsoCode());
        operator.setSsoStatus(op.getSsoStatus());
        operator.setSsoAccessed(op.getSsoAccessed());
        operator.setMerchantCode(op.getMerchantCode());
        return operator;
    }
}
