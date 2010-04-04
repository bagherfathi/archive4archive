package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.Contact;
import com.ft.sm.entity.Organization;

/**
 * 组织机构实体封装类。
 * 
 * @version 1.0
 */
public class OrganizationDTO extends XmlTreeNode implements DTO ,Comparable{
    private static final long serialVersionUID = 6254509842626886526L;

    // 组织状态
    public static final long STATUS_DISABLE = 1; // 禁止状态

    public static final long STATUS_NORMAL = 0; // 正常状态

    // 独立财务标志
    public static final long NOT_SELF_BALANCE = 0; // 非独立财务

    public static final long SELF_BALANCE = 1; // 独立财务

    // 组织类型
    public static final int ORG_TYPE_DEP = 0; // 部门

    public static final int ORG_TYPE_COMPANY = 1; // 分公司

    public static final int ORG_TYPE_BUSIHALL = 2; // 营业厅

    public static final int ORG_TYPE_AGENT = 3; // 代理营业厅

    public static final int ORG_TYPE_REGION = 4; // 数据区域

    public static final String PATH_SEPARATOR = "#";

    private Organization parent; // 父组织机构

    private Organization org; // 组织机构实体

    private List children;

    private Contact contact;

    public OrganizationDTO() {
        this.org = new Organization();
        this.org.setCreateTime(new Date());
        this.org.setModifyTime(this.org.getCreateTime());

        contact = new Contact();
    }

    public OrganizationDTO(Organization org) {
        this.org = copyFrom(org);
        contact = new Contact();
        this.contact.setAddress(org.getAddress());
        this.contact.setMobilePhone(org.getMobilePhone());
        this.contact.setName(org.getLinkName());
        this.contact.setPostCode(org.getPostcode());
        this.contact.setTelephone(org.getTelePhone());
    }

    public OrganizationDTO(Organization parent, Organization org) {
        this.parent = parent;
        this.org = org;
        this.org.setParentId(parent.getOrgId());

        contact = new Contact();
        this.contact.setAddress(org.getAddress());
        this.contact.setMobilePhone(org.getMobilePhone());
        this.contact.setName(org.getLinkName());
        this.contact.setPostCode(org.getPostcode());
        this.contact.setTelephone(org.getTelePhone());
    }

    /**
     * 组织机构标识。
     * 
     * @return
     */
    public Long getOrgId() {
        return new Long(this.org.getOrgId());
    }

    public void setOrgId(Long orgId) {
        this.org.setOrgId(orgId.longValue());
    }

    /**
     * 组织机构名称。
     * 
     * @return
     */
    public String getName() {
        return this.org.getOrgName();
    }

    public void setName(String name) {
        this.org.setOrgName(name);
    }

    /**
     * 父组织机构。
     * 
     * @return
     */
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization org) {
        this.parent = org;
        this.org.setParentId(org.getOrgId());
    }

    /**
     * 组织机构路径 组织机构路径指在组织机构树中从根节点到该节点的全路径。
     * 
     * @return
     */
    public String getPath() {
        return this.org.getOrgPath();
    }

    public void setPath(String path) {
        this.org.setOrgPath(path);
    }

    /**
     * 组织机构类型。
     * 
     * @return
     */
    public long getType() {
        return this.org.getOrgType();
    }

    public void setType(long type) {
        this.org.setOrgType(type);
    }

    /**
     * 组织机构最后修改时间。
     * 
     * @return
     */
    public Date getModifyDate() {
        return this.org.getModifyTime();
    }

    public void setModifyDate(Date modifyDate) {
        this.org.setModifyTime(modifyDate);
    }

    /**
     * 组织机构创建时间。
     * 
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return this.org.getCreateTime();
    }

    /**
     * @param createTime
     *                The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.org.setCreateTime(createTime);
    }

    /**
     * 组织机构状态。
     * 
     * @return Returns the status.
     */
    public long getStatus() {
        return this.org.getStatus();
    }

    /**
     * @param status
     *                The status to set.
     */
    public void setStatus(long status) {
        this.org.setStatus(status);
    }

    /**
     * 联系人信息。
     * 
     * @hibernate.component
     * @return
     */
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;

        if (contact != null) {
            this.org.setAddress(contact.getAddress());
            this.org.setMobilePhone(contact.getMobilePhone());
            this.org.setLinkName(contact.getName());
            this.org.setPostcode(contact.getPostCode());
            this.org.setTelePhone(contact.getTelephone());
        }
    }

    /**
     * 管理员ID。
     * 
     * @return
     */
    public long getManagerOpId() {
        return this.org.getManagerId();
    }

    public void setManagerOpId(long managerId) {
        this.org.setManagerId(managerId);
    }

    /**
     * 组织机构代码。
     * 
     * @return
     */
    public String getCode() {
        return this.org.getOrgCode();
    }

    public void setCode(String code) {
        this.org.setOrgCode(code);
    }

    /**
     * 描述信息。
     * 
     * @return
     */
    public String getDesc() {
        return this.org.getOrgDesc();
    }

    public void setDesc(String desc) {
        this.org.setOrgDesc(desc);
    }

   
    /**
     * 是否独立结算
     */
    public long getSelfBalance() {
        return this.org.getSelfBalance() ;
    }

    public void setSelfBalance(long selfBalance) {
        this.org.setSelfBalance(selfBalance);
    }

    /**
     * 组织机构的父组织机构在SSO系统中的惟一编码。
     * 
     * @return Returns the parentSSOCode.
     */
    public String getParentSSOCode() {
        return this.org.getParentSsoCode();
    }

    /**
     * @param parentSSOCode
     *                The parentSSOCode to set.
     */
    public void setParentSSOCode(String parentSSOCode) {
        this.org.setParentSsoCode(parentSSOCode);
    }

    /**
     * 组织机构在SSO系统中的惟一编码。
     * 
     * @return Returns the ssoCode.
     */
    public String getSsoCode() {
        return this.org.getSsoCode();
    }

    /**
     * @param ssoCode
     *                The ssoCode to set.
     */
    public void setSsoCode(String ssoCode) {
        this.org.setSsoCode(ssoCode);
    }

    /**
     * @return the org
     */
    public Organization getOrg() {
        return org;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.org;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.org = (Organization) target;
    }

    public List getChildren() {
        if (children == null) {
            children = new ArrayList();
        }
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public Long getParentOrgId() {
        return new Long(this.org.getParentId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.TreeNode#getNodeId()
     */
    public Long getNodeId() {
        return this.getOrgId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.TreeNode#getNodeName()
     */
    public String getNodeName() {
        return this.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.TreeNode#getNodeStatus()
     */
    public Long getNodeStatus() {
        return new Long(this.getStatus());
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeType()
     */
    //public String getNodeType() {
    //    return String.valueOf(this.getType());
    //}

    /**
     * 判断是否是根组织。
     * 
     * @return
     */
    public boolean isRoot() {
        if (this.org.getOrgId() == this.org.getParentId()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是营业厅类型的组织，包括营业厅和代理营业厅。
     * 
     * @return
     */
    public boolean isBusiHall() {
        if (this.org.getOrgType() == OrganizationDTO.ORG_TYPE_AGENT
                || this.org.getOrgType() == OrganizationDTO.ORG_TYPE_BUSIHALL) {
            return true;
        } else {
            return false;
        }
    }
    
    public Long[] getLocationIds(){
        String orgPath = this.getPath();
        
        if(orgPath == null || orgPath.length() <=0){
            return new Long[]{this.getOrgId()};
        }
        
        String[] ids = orgPath.split(OrganizationDTO.PATH_SEPARATOR);
        List<Long> orgIds = new ArrayList<Long>();
        for (int i = 0; i < ids.length; i++) {
            if(ids[i].length() >0){
                orgIds.add(new Long(ids[i]));
            }
        }
        
        return (Long[])orgIds.toArray(new Long[0]);
    }
    
    
    public int compareTo(Object o1) {
        OrganizationDTO res2 = (OrganizationDTO) o1;
        int res1Length = this.getPath().split(PATH_SEPARATOR).length;
        int res2Length = res2.getPath().split(PATH_SEPARATOR).length;
        int ret = res1Length - res2Length;
        //removed by libf,2007/03/22
        //if (ret == 0) {
        //    ret = this.getName().hashCode()- res2.getName().hashCode();
        //    //ret = this.getOrgId().intValue() - res2.getOrgId().intValue();
        //}
        //end removed

        return ret;
    }
    
    public static Organization copyFrom(Organization org){
        Organization o = new Organization();
        
        o.setOrgId(org.getOrgId());
        o.setOrgType(org.getOrgType());
        o.setOrgName(org.getOrgName());
        o.setOrgCode(org.getOrgCode());
        o.setParentId(org.getParentId());
        o.setRegionId(org.getRegionId());
        o.setOrgPath(org.getOrgPath());
        o.setModifyTime(org.getModifyTime());
        o.setCreateTime(org.getCreateTime());
        o.setOrgDesc(org.getOrgDesc());
        o.setStatus(org.getStatus());
        o.setLinkName(org.getLinkName());
        o.setAddress(org.getAddress());
        o.setMobilePhone(org.getMobilePhone());
        o.setTelePhone(org.getTelePhone());
        o.setPostcode(org.getPostcode());
        o.setManagerId(org.getManagerId());
        o.setSelfBalance(org.getSelfBalance());
        o.setSsoCode(org.getSsoCode());
        o.setParentSsoCode(org.getParentSsoCode());

        return o;
    }
}
