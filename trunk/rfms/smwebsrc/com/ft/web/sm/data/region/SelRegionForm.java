package com.ft.web.sm.data.region;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.web.sm.BaseForm;

/**
 * —°‘Ò«¯”Ú“≥√ÊForm Bean°£
 * 
 * @struts.form name="selRegionForm"
 * 
 * @version 1.0
 * 
 */
public class SelRegionForm extends BaseForm{
    private static final long serialVersionUID = 4838700784392440745L;
    
    private List shengList;
    private List shiList;
    private List quxianList;
    private List quList;
    private List accessRegionList;
    
    private long selShengId;
    private long selShiId;
    private long selQuxianId;
    private long selQuId;
    private String regionName;
    private int searchType;
    private long orgId;
    /**
     * @return the quxianList
     */
    public List getQuxianList() {
        return quxianList;
    }
    /**
     * @param quxianList the quxianList to set
     */
    public void setQuxianList(List quxianList) {
        this.quxianList = quxianList;
    }
    /**
     * @return the shengList
     */
    public List getShengList() {
        return shengList;
    }
    /**
     * @param shengList the shengList to set
     */
    public void setShengList(List shengList) {
        this.shengList = shengList;
    }
    /**
     * @return the shiList
     */
    public List getShiList() {
        return shiList;
    }
    /**
     * @param shiList the shiList to set
     */
    public void setShiList(List shiList) {
        this.shiList = shiList;
    }
    /**
     * @return the quList
     */
    public List getQuList() {
        return quList;
    }
    /**
     * @param quList the quList to set
     */
    public void setQuList(List quList) {
        this.quList = quList;
    }
    /**
     * @return the selQuId
     */
    public long getSelQuId() {
        return selQuId;
    }
    /**
     * @param selQuId the selQuId to set
     */
    public void setSelQuId(long selQuId) {
        this.selQuId = selQuId;
    }
    /**
     * @return the selQuxianId
     */
    public long getSelQuxianId() {
        return selQuxianId;
    }
    /**
     * @param selQuxianId the selQuxianId to set
     */
    public void setSelQuxianId(long selQuxianId) {
        this.selQuxianId = selQuxianId;
    }
    /**
     * @return the selShengId
     */
    public long getSelShengId() {
        return selShengId;
    }
    /**
     * @param selShengId the selShengId to set
     */
    public void setSelShengId(long selShengId) {
        this.selShengId = selShengId;
    }
    /**
     * @return the selShiId
     */
    public long getSelShiId() {
        return selShiId;
    }
    /**
     * @param selShiId the selShiId to set
     */
    public void setSelShiId(long selShiId) {
        this.selShiId = selShiId;
    }
    
    
    /**
     * @return the accessRegionList
     */
    public List getAccessRegionList() {
        return accessRegionList;
    }
    /**
     * @param accessRegionList the accessRegionList to set
     */
    public void setAccessRegionList(List accessRegionList) {
        this.accessRegionList = accessRegionList;
    }
    
    public String getRegionName() {
        return regionName;
    }
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public int getSearchType() {
        return searchType;
    }
    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
    
    public long getOrgId() {
        return orgId;
    }
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
    /*
     * (non-Javadoc)
     * @see com.ft.web.sm.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if(this.shengList == null){
            this.shengList = new ArrayList();
        }
        
        if(this.shiList == null){
            this.shiList = new ArrayList();   
        }
        
        if(this.quxianList == null){
            this.quxianList = new ArrayList();
        }
        
        if(this.quList == null){
            this.quList = new ArrayList();
        }
        
        if(this.accessRegionList == null){
            this.accessRegionList = new ArrayList();
        }
    }
    
    public void init(){
        this.getShengList().clear();
        this.getShiList().clear();
        this.getQuxianList().clear();
        this.getQuList().clear();
        
        this.selShengId = 0;
        this.selShiId = 0;
        this.selQuxianId = 0;
        this.selQuId = 0;
        this.orgId = 0;
        this.searchType = 1;
        this.regionName = "";
    }
}
