package com.ft.sm.dto;

import java.util.List;

import com.ft.sm.entity.Resource;

/**
 * Class comments.
 * 
 */
public class ResourceDTO extends XmlTreeNode implements DTO,Comparable{
    private static final long serialVersionUID = 5468933483658376492L;
    
    //·���ָ���
    public static final String PATH_SEPARATOR = "#";
    
    //����Ȩ������
    public static final int MENU_TYPE = 1;        //�˵�����
    public static final int BUTTON_TYPE = 2;      //��ť����

    private Resource parent;
    private Resource resource;
    
    private List<Resource> children;
    
    public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public ResourceDTO(){
        this.resource = new Resource();
    }
    
    public ResourceDTO(Resource resource){
        this.resource = resource;
    }
    
    public ResourceDTO(Resource parent,long resourceType){
        this.parent = parent;
        this.resource = new Resource();
        resource.setResourceType(resourceType);
        resource.setParentId(parent.getResourceId());
    }
    
    public ResourceDTO(Resource parent,Resource resource){
        this.parent = parent;
        this.resource = resource;
    }
    
    /**
     * �˵�Ȩ�޵�ͼƬ�ļ�·����
     * 
     * @return
     */
    public String getImage() {
        return this.resource.getMenuImage();
    }

    public void setImage(String image) {
        this.resource.setMenuImage(image);
    }

    /**
     * �˵�Ȩ���ڸ��˵�����ʾ˳��
     * 
     * @return
     */
    public long getOrder() {
        return this.resource.getMenuOrder();
    }

    public void setOrder(int order) {
        this.resource.setMenuOrder(order);
    }

    /**
     * Ȩ�����ڸ�Ȩ�ޱ�ʶ��
     * 
     * @return
     */
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
        this.resource.setParentId(parent.getResourceId());
    }

    /**
     * Ȩ��ID��
     * 
     * @return Returns the id.
     */
    public Long getResourceId() {
        return new Long(this.resource.getResourceId());
    }
    
    public void setResourceId(Long resId) {
        this.resource.setResourceId(resId.longValue());
    }

    /**
     * Ȩ�޵�Ωһ��ʶ��
     * 
     * @return
     */
    public String getResourceKey() {
        return this.resource.getResourceCode();
    }

    public void setResourceKey(String resourceKey) {
        this.resource.setResourceCode(resourceKey);
    }

    /**
     * Ȩ�޵�·��. Ȩ�޵�·��ָ��Ȩ�����дӸ��ڵ㵽���ڵ��ȫ·����
     * 
     * @return
     */
    public String getResourcePath() {
        return this.resource.getResourcePath();
    }

    public void setResourcePath(String resourcePath) {
        this.resource.setResourcePath(resourcePath);
    }

    /**
     * Ȩ�޵���ʾ���⡣
     * 
     * @return
     */
    public String getTitle() {
        return this.resource.getResourceTitle();
    }

    public void setTitle(String title) {
        this.resource.setResourceTitle(title);
    }

    /**
     * Ȩ�޵����� Ȩ�����Ͱ�����1:�˵�Ȩ�ޣ�2����ťȨ�ޡ�
     * 
     * @return
     */
    public long getType() {
        return this.resource.getResourceType();
    }

    public void setType(long type) {
        this.resource.setResourceType(type);
    }

    /**
     * �˵������ӡ�
     * 
     * @return
     */
    public String getUrl() {
        return this.resource.getMenuUrl();
    }

    public void setUrl(String url) {
        this.resource.setMenuUrl(url);
    }

    /**
     * �Ƿ�Ϊ�˵�Ȩ�ޡ�
     * 
     * @return
     */
    public boolean isMenu() {
        return this.resource.getResourceType() == MENU_TYPE ? true : false;
    }

    /**
     * �Ƿ�Ϊ��ťȨ�ޡ�
     * 
     * @return
     */
    public boolean isButton() {
        return this.resource.getResourceType() == BUTTON_TYPE ? true : false;
    }

    /**
     * �˵��Ƿ�ɼ���
     * 
     * @hibernate.property column="menu_visible"
     * @return Returns the visible.
     */
    public boolean isVisible() {
        if(this.resource.getMenuVisible() == 1)
            return true;
        else
            return false;
    }

    /**
     * @param visible
     *            The visible to set.
     */
    public void setVisible(boolean visible) {
        if(visible){
            this.resource.setMenuVisible(1);
        }else{
            this.resource.setMenuVisible(0);
        }
    }

    /**
     * ����Ȩ�޵ȼ���
     * @return
     */
    public int getLevel() {
        if (this.getResourcePath() == null || this.getResourcePath().length() <= 0) {
            return 0;
        } else {
            if(this.getResourcePath().startsWith(PATH_SEPARATOR)){
                return this.getResourcePath().split(PATH_SEPARATOR).length -1;
            }else{
                return this.getResourcePath().split(PATH_SEPARATOR).length;
            }
        }
    }

    /**
     * ����Ȩ��ʵ�塣
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.resource;
    }

    /* (non-Javadoc)
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.resource = (Resource)target;
    }
    
    public Long getParentId(){
        return new Long(this.resource.getParentId());
    }
    
    public void setParentId(Long parentId){
        this.resource.setParentId(parentId.longValue());
    }
    
    /**
     * ����·������Ž�������
     * 
     * @param arg0
     * @param arg1
     * @return
     */
    public int compareTo(Object o1) {
        ResourceDTO res2 = (ResourceDTO) o1;
        int res1Length = this.getResourcePath().split(PATH_SEPARATOR).length;
        int res2Length = res2.getResourcePath().split(PATH_SEPARATOR).length;
        int ret = res1Length - res2Length;
        if (ret == 0) {
            ret = new Long(this.getOrder() - res2.getOrder()).intValue();

            if (ret == 0) {
                ret = this.getResourceId().intValue()
                        - res2.getResourceId().intValue();
            }
        }

        return ret;
    }

    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeId()
     */
    public Long getNodeId() {
	return this.getResourceId();
    }
    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeName()
     */
    public String getNodeName() {
	return this.getTitle();
    }
    /*
     * (non-Javadoc)
     * @see com.ft.sm.dto.XmlTreeNode#getNodeStatus()
     */
    public Long getNodeStatus() {
	return new Long(0);
    }
}
