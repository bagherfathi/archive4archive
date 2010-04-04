package com.ft.webui.tabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.webui.tabs.xml.Xhtml;

/**
 * 
 * JSP Tag that renders a collection of tabs.
 */
public final class TabContainerTag extends BodyTagSupport {
	private static final long serialVersionUID = 7884746752048343647L;
	public static final String COOKIE_PREFIX = "webui.tabs";
	private String id;
	private String skin;
	private List children;
	private String selectedTabPaneId;
	private String urlSelectedTabPaneId;
	private int selectedIndex = 0;
	private int cookieSelectedIndex = -1;
	private String jsTabListener;
	private String tabLocation = "top";

	public void setId(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSkin(final String skin) {
		this.skin = skin.toLowerCase();
	}

	public String getSkin() {
		if ((null == skin) || (0 == skin.length())) {
			skin = "default";
		}

		return skin;
	}

	public void setSelectedTabPaneId(final String selectedTabPaneId) {
		Evaluator Evalutator = new Evaluator();
		try {
			this.selectedTabPaneId = (String) Evalutator.evaluate(
					"selectedTabPaneId", selectedTabPaneId, String.class, this,
					pageContext);
		} catch (JspException e) {
			throw new RuntimeException(e);
		}
	}

	public String getSelectedTabPaneId() {
		return selectedTabPaneId;
	}

	void setSelectedIndex(final int selectedIndex) {
		this.selectedIndex = selectedIndex - 1;
	}

	int getSelectedIndex() {
		return selectedIndex;
	}

	private void setCookieSelectedIndex(final int selectedIndex) {
		this.cookieSelectedIndex = selectedIndex;
	}

	private int getCookieSelectedIndex() {
		return cookieSelectedIndex;
	}

	private void setUrlSelectedTabPaneId(final String selectedId) {
		this.urlSelectedTabPaneId = selectedId;
	}

	private String getUrlSelectedTabPaneId() {
		return urlSelectedTabPaneId;
	}

	public void setJsTabListener(final String jsTabListener) {
		this.jsTabListener = jsTabListener;
	}

	public String getJsTabListener() {
		return jsTabListener;
	}

	List getChildren() {
		if (null == children) {
			children = new ArrayList();
		}

		return children;
	}

	int getChildCount() {
		return getChildren().size();
	}

	void addChild(final TabPaneModule child) {
		getChildren().add(child);
		// if(this.selectedTabPaneId==null){
		// this.selectedIndex = 0;
		// }else{
		// int idx = this.getChildren().indexOf(child);
		// if(this.selectedTabPaneId.equals(child.getId())){
		// this.selectedIndex = idx;
		// }else{
		// this.selectedIndex = -1;
		// }
		// }
	}

	public int doEndTag() throws JspException {
		JspResponseWriter out = new JspResponseWriter();

		// added by libf,2007/01/29，解决tab导航链接在页面下方时显示内容不正确的问题
		determineSelectedIndex();
		// end added

		out.startElement(Xhtml.Tag.DIV);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_SKIN_CLASS_NAME
				+ getSkin());
		out.startElement(Xhtml.Tag.DIV);
		out.attribute(Xhtml.Attr.ID, id);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_CONTAINER_CLASS_NAME);

		if (tabLocation.endsWith("top")) {
			buildNavigator(out);
		}

		out.startElement(Xhtml.Tag.DIV);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_PANE_WRAP_CLASS_NAME);

		for (Iterator iter = children.iterator(); iter.hasNext();) {
			TabPaneModule tabPane = (TabPaneModule) iter.next();
			this.renderPane(tabPane);
			out.text(tabPane.getOut().getBuffer().toString());
		}
		out.endElement(Xhtml.Tag.DIV);
		out.comment(TabConstants.TAB_PANE_WRAP_CLASS_NAME);

		if (!tabLocation.endsWith("top")) {
			buildNavigator(out);
		}

		out.endElement(Xhtml.Tag.DIV);
		out.comment(TabConstants.TAB_CONTAINER_CLASS_NAME);
		out.endElement(Xhtml.Tag.DIV);
		out.comment(TabConstants.TAB_SKIN_CLASS_NAME);
		try {
			pageContext.getOut().print(out.getBuffer());
		} catch (IOException e) {
			throw new JspException(e);
		} finally {
			this.children.clear();
		}
		return EVAL_PAGE;
	}

	/**
	 * @param out
	 */
	private void buildNavigator(JspResponseWriter out) {
		out.startElement(Xhtml.Tag.DIV);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_WRAP_CLASS_NAME);

		// removed by libf,2007/01/29，解决tab导航链接在页面下方时显示内容不正确的问题
		// determineSelectedIndex();
		// end removed
		int i = 0;

		for (Iterator iter = children.iterator(); iter.hasNext();) {
			TabPaneModule tabPane = (TabPaneModule) iter.next();
			out.startElement(Xhtml.Tag.DIV);
			out.attribute(Xhtml.Attr.ID, tabPane.getId()
					+ TabConstants.TAB_ID_SUFFIX);

			if ((null != getJsTabListener())
					&& (getJsTabListener().length() > 0)) {
				out.attribute(Xhtml.Attr.ONCLICK,
						"org.ditchnet.jsp.TabUtils.tabClicked(event);"
								+ getJsTabListener().trim()
								+ "(new org.ditchnet.jsp.TabEvent(this));");
			} else {
				out.attribute(Xhtml.Attr.ONCLICK,
						"org.ditchnet.jsp.TabUtils.tabClicked(event);");
			}

			if (i == getSelectedIndex()
					|| ((i + this.getSelectedIndex()) == -1)) {
				out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_CLASS_NAME
						+ " " + TabConstants.FOCUSED_CLASS_NAME);
			} else {
				out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_CLASS_NAME
						+ " " + TabConstants.UNFOCUSED_CLASS_NAME);
			}

			out.startElement(Xhtml.Tag.SPAN);
			out
					.attribute(Xhtml.Attr.CLASS,
							TabConstants.TAB_BG_LEFT_CLASS_NAME);
			out.text(" ");
			out.endElement(Xhtml.Tag.SPAN);
			out.startElement(Xhtml.Tag.A);
			out.attribute(Xhtml.Attr.HREF, getTabUrl(tabPane)
					+ TabLinkTag.QUESTION_MARK
					+ TabLinkTag.PARAM_NAME_TAB_PANE_ID + TabLinkTag.EQUALS
					+ tabPane.getId());
			out.attribute(Xhtml.Attr.ONCLICK, "return false;");

			if ((null != tabPane.getTabTitle())
					&& (0 < tabPane.getTabTitle().length())) {
				out.text(tabPane.getTabTitle());
			}

			out.text(" ");
			out.endElement(Xhtml.Tag.A);
			out.endElement(Xhtml.Tag.DIV);
			i++;
		}

		out.startElement(Xhtml.Tag.BR);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.CLEAR_CLASS_NAME);
		out.endElement(Xhtml.Tag.BR);
		out.endElement(Xhtml.Tag.DIV);
		out.comment(TabConstants.TAB_WRAP_CLASS_NAME);
	}

	private void determineSelectedIndex() {
		// check url first
		TabPaneModule child;
		for (int i = 0; i < getChildCount(); i++) {
			child = (TabPaneModule) getChildren().get(i);
			if (child.getId().equals(getUrlSelectedTabPaneId())) {
				setSelectedIndex(i + 1);

				return;
			}
		}
		// then check cookie
		if (getCookieSelectedIndex() > -1) {
			setSelectedIndex(cookieSelectedIndex + 1);
			return;
		}

		// then check jsp tag attr
		for (int i = 0; i < getChildCount(); i++) {
			child = (TabPaneModule) getChildren().get(i);

			if (child.getId().equals(getSelectedTabPaneId())) {
				setSelectedIndex(i + 1);

				return;
			}
		}
	}

	private String getTabUrl(final TabPaneModule tabPane) {
		HttpServletRequest request = getRequest();

		return request.getRequestURL().toString();
	}

	private void consumeQueryStringParam() {
		String tabPaneIdParamValue = getRequest().getParameter(
				TabLinkTag.PARAM_NAME_TAB_PANE_ID);

		if ((null == tabPaneIdParamValue)
				|| (0 == tabPaneIdParamValue.length())) {
			return;
		}

		setUrlSelectedTabPaneId(tabPaneIdParamValue);
	}

	private void consumeCookie() {
		Cookie[] cookies = getPageCookies();
		Cookie cookie;
		// String prefix;
		// String value;

		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];

			if (isDitchnetTabCookie(cookie)) {
				int index = cookie.getName().indexOf(":") + 1;

				if (isCookieForThisContainer(cookie, index)) {
					try {
						setCookieSelectedIndex(Integer.parseInt(cookie
								.getValue()));
					} catch (NumberFormatException e) {
						setCookieSelectedIndex(0);
					}
				}
			}
		}
	}

	private HttpServletRequest getRequest() {

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		return request;
	}

	private Cookie[] getPageCookies() {
		Cookie[] cookies = getRequest().getCookies();

		if (null == cookies) {
			cookies = new Cookie[0];
		}

		return cookies;
	}

	private boolean isDitchnetTabCookie(final Cookie cookie) {
		return 0 == cookie.getName().indexOf(COOKIE_PREFIX);
	}

	private boolean isCookieForThisContainer(final Cookie cookie,
			final int index) {
		return cookie.getName().substring(index).equals(getId());
	}

	public int doStartTag() throws JspException {
		consumeCookie();
		consumeQueryStringParam();
		return EVAL_BODY_BUFFERED;
	}

	private boolean isSelectedTab(TabPaneModule module) {
		int idx = this.children.indexOf(module);
		return (idx == this.selectedIndex);

	}

	private void renderPane(TabPaneModule module) throws JspException {
		JspResponseWriter out = module.getOut();
		out.lineBreak();
		out.startElement(Xhtml.Tag.DIV);
		out.attribute(Xhtml.Attr.ID, id);
		// modified by libf
		// out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_PANE_CLASS_NAME);
		out.attribute(Xhtml.Attr.CLASS, TabConstants.TAB_PANE_CLASS_NAME + " "
				+ TabConstants.TAB_PANE_CLASS_NAME_NEW);
		// end modified

		if (isSelectedTab(module)) {
			out.attribute(Xhtml.Attr.STYLE, "display:block;");
		} else {
			out.attribute(Xhtml.Attr.STYLE, "display:none;");
		}
		out.text(" ");
		out.text(module.getContent());
		out.lineBreak();
		out.endElement(Xhtml.Tag.DIV);

		// try {
		// pageContext.getOut().print(out.getBuffer());
		// } catch (IOException e) {
		// throw new JspException(e);
		// }
	}

	/**
	 * @return the tabLocation
	 */
	public String getTabLocation() {
		return tabLocation;
	}

	/**
	 * @param tabLocation
	 *            the tabLocation to set
	 */
	public void setTabLocation(String tabLocation) {
		this.tabLocation = tabLocation;
	}
}
