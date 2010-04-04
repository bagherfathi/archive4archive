package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.Enum;
import com.ft.sm.entity.EnumCategory;

/**
 * 枚举类别实体封装类。
 * 
 */
public class EnumCategoryDTO extends XmlTreeNode implements HisDTO  {
    private static final long serialVersionUID = 4328813153114711076L;
    
    private EnumCategory enumCategory;
    
    // 类别中所有的枚举数据
    private List<Enum> enumDatas;
    
    public EnumCategoryDTO(){
        this.enumCategory = new EnumCategory();
    }
    
    public EnumCategoryDTO(EnumCategory enumCatogiry){
        this.enumCategory = enumCatogiry;
    } 
   
    /**
     * 枚举数据类代码。
     * @return the categoryCode
     */
    public String getCategoryCode() {
        return this.enumCategory.getCategoryCode();
    }
    /**
     * @param categoryCode the categoryCode to set
     */
    public void setCategoryCode(String categoryCode) {
        this.enumCategory.setCategoryCode(categoryCode);
    }
    
    /**
     * 枚举数据类别ID。
     * @return the categoryId
     */
    public Long getCategoryId() {
        return new Long(this.enumCategory.getCategoryId());
    }
    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.enumCategory.setCategoryId(categoryId.longValue());
    }
    
    /**
     * 分类名称。
     * @return the categoryName
     */
    public String getCategoryName() {
        return this.enumCategory.getCategoryName();
    }
    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.enumCategory.setCategoryName(categoryName);
    }
    
    /**
     * 分类所在组ID。
     * @return the groupId
     */
    public Long getGroupId() {
        return new Long(this.enumCategory.getGroupId());
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.enumCategory.setGroupId(groupId.longValue());
    }
    
    /**
     * 系统数据列表。
     * 
     * @return the enumDatas
     */
    public List<Enum> getEnumDatas() {
        if (this.enumDatas == null) {
            this.enumDatas = new ArrayList<Enum>();
        }
        return enumDatas;
    }

    /**
     * 添加系统数据。
     * 
     * @param enumData
     */
    public void addEnumData(Enum enumData) {
        this.getEnumDatas().add(enumData);
    }
    
    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.enumCategory;
    }
    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.enumCategory = (EnumCategory)target;
    }

    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeId()
     */
    public Long getNodeId() {
	return this.getCategoryId();
    }

    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeName()
     */
    public String getNodeName() {
	return this.getCategoryName();
    }
    /* (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeType()
     */
    public String getNodeType() {
	return "category";
    }
    
    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setAppId(long)
     */
    public void setAppId(long appId) {
        this.enumCategory.setAppId(appId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setCreateDate(java.util.Date)
     */
    public void setCreateDate(Date createDate) {
        this.enumCategory.setCreateDate(createDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setExpireDate(java.util.Date)
     */
    public void setExpireDate(Date expireDate) {
        this.enumCategory.setExpireDate(expireDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setLoginOrgId(long)
     */
    public void setLoginOrgId(long loginOrgId) {
        this.enumCategory.setLoginOrgId(loginOrgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOperatorId(long)
     */
    public void setOperatorId(long operatorId) {
        this.enumCategory.setOperatorId(operatorId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setOrgId(long)
     */
    public void setOrgId(long orgId) {
        this.enumCategory.setOrgId(orgId);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setUpdateDate(java.util.Date)
     */
    public void setUpdateDate(Date updateDate) {
        this.enumCategory.setUpdateDate(updateDate);
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.HisDTO#setValidDate(java.util.Date)
     */
    public void setValidDate(Date validDate) {
        this.enumCategory.setValidDate(validDate);
    }
}
