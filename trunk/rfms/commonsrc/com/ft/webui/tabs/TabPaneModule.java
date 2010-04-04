
package com.ft.webui.tabs;

public class TabPaneModule {
	private String id;

	private String tabTitle;

	private String content ;
	
	private JspResponseWriter out = new JspResponseWriter();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JspResponseWriter getOut() {
		return out;
	}

	public void setOut(JspResponseWriter out) {
		this.out = out;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
