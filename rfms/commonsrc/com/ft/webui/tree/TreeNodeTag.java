package com.ft.webui.tree;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 *
 */
public class TreeNodeTag extends TagSupport{

	private static final long serialVersionUID = -7595167931528000952L;
	TreeNodeModel model= new TreeNodeModel();

	public String getHref() {
		return model.getHref();
	}

	public String getImg() {
		return model.getImg();
	}

	public String getNodeId() {
		return model.getNodeId();
	}

	public String getOnclick() {
		return model.getOnclick();
	}

	public String getOpenImg() {
		return model.getOpenImg();
	}

	public String getTarget() {
		return model.getTarget();
	}

	public boolean isChecked() {
		return model.isChecked();
	}

	public void setChecked(boolean checked) {
		model.setChecked(checked);
	}

	public void setHref(String href) {
		model.setHref(href);
	}

	public void setImg(String img) {
		model.setImg(img);
	}

	public void setNodeId(String nodeId) {
		model.setNodeId(nodeId);
	}

	public void setOnclick(String onclick) {
		model.setOnclick(onclick);
	}

	public void setOpenImg(String openImg) {
		model.setOpenImg(openImg);
	}

	public void setTarget(String target) {
		model.setTarget(target);
	}

	public int doEndTag() throws JspException {
		TreeTag treeTag = (TreeTag) findAncestorWithClass(this,TreeTag.class);
		if(treeTag==null){
			treeTag.addNodeModel(this.model);
		}
		return EVAL_PAGE;
	}

	public int getLevel() {
		return model.getLevel();
	}

	public void setLevel(int level) {
		model.setLevel(level);
	}
	

}
