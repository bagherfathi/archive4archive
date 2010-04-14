/**
 * 
 */
package com.ft.rfms.web.merchant;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class BindTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3558482896139425223L;
	
	private String nodeId;
	private String name;
	private String code;
	private String parentId;
	
	
	public BindTreeNode(String nodeId, String name, String code, String parentId) {
		super();
		this.nodeId = nodeId;
		this.name = name;
		this.code = code;
		this.parentId = parentId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

}
