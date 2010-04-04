package com.ft.common.security;

import com.ft.commons.tree.BaseTreeNode;

/**
 * 权限项的节点 说明：包括功能权限和业务权限
 * 
 */
public class ResourceTreeNode extends BaseTreeNode {
    // 用于Resource的ResourcePath
    private String path;

    // 该节点是否可选
    private boolean checked = true;

    // 该节点是否为单选
    private int single;

    private String group;

    /**
     * 带有唯一标式，名称，地址的构造器
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
     * 带有唯一标式，名称的构造器
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
