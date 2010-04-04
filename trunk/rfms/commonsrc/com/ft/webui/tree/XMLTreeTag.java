package com.ft.webui.tree;

import javax.servlet.jsp.JspException;

import com.ft.webui.TemplateTagSupport;

public class XMLTreeTag extends TemplateTagSupport {

	private static final long serialVersionUID = 1L;

	private String imagePath;

	private String treeId;

	private String xmldoc;

	private String xmlURL;

	private String enableDragAndDrop;

	private String onRightClickHandler;

	private String onClickHandler;

	private String images;

	private String script;

	private String treeVar;
	
	private String enableCheckBox;
	
	private String dragHandler;
	public void reset(){
		this.imagePath = "/images";
		this.images=null;
		this.treeId= null;
		this.xmldoc = null;
		this.xmlURL = null;
		this.enableDragAndDrop = null;
		this.onRightClickHandler = null;
		this.onClickHandler = null;
		this.treeVar = null;
	}

	public int doStartTag() throws JspException {
		super.doStartTag();
		return SKIP_BODY;

	}

	public String getVarName() {
		return "xmlTree";
	}

	public String getEnableDragAndDrop() {
		return enableDragAndDrop;
	}

	public void setEnableDragAndDrop(String enableDragAndDrop) {
		this.enableDragAndDrop = enableDragAndDrop;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getOnClickHandler() {
		return onClickHandler;
	}

	public void setOnClickHandler(String onClickHandler) {
		this.onClickHandler = onClickHandler;
	}

	public String getOnRightClickHandler() {
		return onRightClickHandler;
	}

	public void setOnRightClickHandler(String onRightClickHandler) {
		this.onRightClickHandler = onRightClickHandler;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeVar() {
		return treeVar;
	}

	public void setTreeVar(String treeVar) {
		this.treeVar = treeVar;
	}

	public String getXmldoc() {
		return xmldoc;
	}

	public void setXmldoc(String xmldoc) {
		this.xmldoc = xmldoc;
	}

	public String getXmlURL() {
		return xmlURL;
	}

	public void setXmlURL(String xmlURL) {
		this.xmlURL = xmlURL;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getEnableCheckBox() {
		return enableCheckBox;
	}

	public void setEnableCheckBox(String enableCheckBox) {
		this.enableCheckBox = enableCheckBox;
	}

	public String getDragHandler() {
		return dragHandler;
	}

	public void setDragHandler(String dragHandler) {
		this.dragHandler = dragHandler;
	}
}
