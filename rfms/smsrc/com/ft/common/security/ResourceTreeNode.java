package com.ft.common.security;

import com.ft.commons.tree.BaseTreeNode;

/**
 * Ȩ����Ľڵ� ˵������������Ȩ�޺�ҵ��Ȩ��
 * 
 */
public class ResourceTreeNode extends BaseTreeNode {
    // ����Resource��ResourcePath
    private String path;

    // �ýڵ��Ƿ��ѡ
    private boolean checked = true;

    // �ýڵ��Ƿ�Ϊ��ѡ
    private int single;

    private String group;

    /**
     * ����Ψһ��ʽ�����ƣ���ַ�Ĺ�����
     * 
     * @param key
     * @param name
     * @param path
     */
    public ResourceTreeNode(String key, String name, String path) {
        super.setKey(key);
        super.setNodeName(name);
        this.path = path;
    }

    public ResourceTreeNode(String key, String name, int single, String group) {
        super.setKey(key);
        super.setNodeName(name);
        this.setSingle(single);
        this.setGroup(group);
    }

    /**
     * ����Ψһ��ʽ�����ƵĹ�����
     * 
     * @param key
     * @param name
     */
    public ResourceTreeNode(String key, String name) {
        super.setKey(key);
        super.setNodeName(name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return Returns the checked.
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked
     *                The checked to set.
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
