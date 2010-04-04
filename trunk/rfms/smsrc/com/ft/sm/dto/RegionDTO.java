package com.ft.sm.dto;
import com.ft.sm.entity.Region;


/**
 * 区域实体封装类。
 * 
 */
public class RegionDTO extends XmlTreeNode implements DTO,Comparable{
    private static final long serialVersionUID = -8293143656043393251L;
    public static final String PATH_SEPARATOR = "#";
    
    //区域状态
    public static final long STATUS_DISABLE = 1;    //禁止状态
    public static final long STATUS_NORMAL = 0;     //正常状态
    
    //区域类型
    public static final long REGION_TYPE_GUOJIA = 0;    //国家
    public static final long REGION_TYPE_SHNEG = 1;     //省
    public static final long REGION_TYPE_SHI = 2;       //市
    public static final long REGION_TYPE_QUXIAN = 3;    //区县
    public static final long REGION_TYPE_QU = 4;        //区
    public static final long REGION_TYPE_JIEDAO = 5;    //街道
    public static final long REGION_TYPE_SHEQU = 6;     //社区
    public static final long REGION_TYPE_XIAOQU = 7;    //小区

    private Region parent;
    private Region region;
    
    public RegionDTO(){
        this.region = new Region();
    }
    
    public RegionDTO(Region region){
        this.region = region;
    }
    
    public RegionDTO(Region parent,Region region){
        this.region = region;
        this.parent = parent;
    }
    
    /**
     * 区域编码. 系统中，区域的编码是惟一的。
     *
     * @return
     */
    public String getRegionCode() {
        return this.region.getRegionCode();
    }

    public void setRegionCode(String regionCode) {
       this.region.setRegionCode(regionCode);
    }

    public String getYscode() {
        return this.region.getYscode();
    }

    public void setYscode(String yscode) {
       this.region.setYscode(yscode);
    }
    /**
     * 区域ID。
     * 
     * @return Returns the id.
     */
    public Long getRegionId() {
        return new Long(region.getRegionId());
    }

    public void setRegionId(Long regionId) {
        this.region.setRegionId(regionId.longValue());
    }

    /**
     * 区域名称。
     * 
     * @return
     */
    public String getRegionName() {
        return this.region.getRegionName();
    }

    public void setRegionName(String regionName) {
        this.region.setRegionName(regionName);
    }
    public String getYsname() {
        return this.region.getYsname();
    }

    public void setYsname(String ysname) {
        this.region.setYsname(ysname);
    }

    /**
     * 区域路径. 区域路径指在区域树中从根区域节点到本区域节点的全路径，路径格式为id#id...#id。
     * 
     * @return
     */
    public String getRegionPath() {
        return this.region.getRegionPath();
    }

    public void setRegionPath(String regionPath) {
        this.region.setRegionPath(regionPath);
    }

    /**
     * 区域类型，如省、市、区等等。
     * 
     * @return
     */
    public long getRegionType() {
        return this.region.getRegionType();
    }

    public void setRegionType(long regionType) {
        this.region.setRegionType(regionType);
    }

    /**
     * 父区域。
     * 
     * @return
     */
    public Region getParent() {
        return parent;
    }

    public void setParent(Region parent) {
        this.parent = parent;
        if(parent != null){
            this.region.setParentId(parent.getRegionId());
        }
    }

    /**
     * 区域状态。
     * 
     */
    public long getStatus() {
        return this.region.getStatus();
    }

    public void setStatus(long status) {
        this.region.setStatus(status);
    }
    
    public Long getParentId(){
        return new Long(this.region.getParentId());
    }
    
    public void setParentId(Long parentId){
        this.region.setParentId(parentId.longValue());
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.region;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.region = (Region)target;
    }
    
    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.TreeNode#getNodeId()
     */
    public Long getNodeId() {
	return getRegionId();
    }
    
    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.TreeNode#getNodeName()
     */
    public String getNodeName() {
	return getRegionName();
    }
    
    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.TreeNode#getNodeStatus()
     */
    public Long getNodeStatus() {
	return new Long(getStatus());
    }
    
    public int compareTo(Object o1) {
        RegionDTO res2 = (RegionDTO) o1;
        int res1Length = this.getRegionPath().split(PATH_SEPARATOR).length;
        int res2Length = res2.getRegionPath().split(PATH_SEPARATOR).length;
        int ret = res1Length - res2Length;
        if (ret == 0) {
            ret = this.getRegionId().intValue() - res2.getRegionId().intValue();
        }

        return ret;
    }
}
