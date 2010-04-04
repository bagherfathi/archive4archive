package com.ft.sm.dto;
import com.ft.sm.entity.Region;


/**
 * ����ʵ���װ�ࡣ
 * 
 */
public class RegionDTO extends XmlTreeNode implements DTO,Comparable{
    private static final long serialVersionUID = -8293143656043393251L;
    public static final String PATH_SEPARATOR = "#";
    
    //����״̬
    public static final long STATUS_DISABLE = 1;    //��ֹ״̬
    public static final long STATUS_NORMAL = 0;     //����״̬
    
    //��������
    public static final long REGION_TYPE_GUOJIA = 0;    //����
    public static final long REGION_TYPE_SHNEG = 1;     //ʡ
    public static final long REGION_TYPE_SHI = 2;       //��
    public static final long REGION_TYPE_QUXIAN = 3;    //����
    public static final long REGION_TYPE_QU = 4;        //��
    public static final long REGION_TYPE_JIEDAO = 5;    //�ֵ�
    public static final long REGION_TYPE_SHEQU = 6;     //����
    public static final long REGION_TYPE_XIAOQU = 7;    //С��

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
     * �������. ϵͳ�У�����ı�����Ωһ�ġ�
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
     * ����ID��
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
     * �������ơ�
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
     * ����·��. ����·��ָ���������дӸ�����ڵ㵽������ڵ��ȫ·����·����ʽΪid#id...#id��
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
     * �������ͣ���ʡ���С����ȵȡ�
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
     * ������
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
     * ����״̬��
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
