package com.ft.web.sm.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.security.OrgRepository;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.commons.web.SpringWebUtils;

/**
 * 选择可访问组织标签
 * 
 * @version 1.0
 * 
 */
public class SelectAccessOrgTag extends TagSupport {

	private static final long serialVersionUID = 450663963371697351L;

	private String inputName;

	private String includeChildren = "false";

	private String orgId;

	private String size = "25";

	// 是否只显示公司类型的组织
	private String onlyCompany = "false";

	public int doStartTag() throws JspException {

		String orgName = "";
		String orgIdstr = "";

		if (orgId != null) {
			Evaluator aEvaluator = new Evaluator();
			Long aOrgId = (Long) aEvaluator.evaluate("orgId", this.orgId,
					Long.class, this, pageContext);
			if (aOrgId.longValue() != 0) {
				OrgRepository orgRepository = (OrgRepository) SpringWebUtils
						.getBean(pageContext.getServletContext(),
								"orgRepository");
				OrganizationDTO org = orgRepository.getOrgDTOById(aOrgId);
				if (org != null) {
					orgName = org.getName();
					orgIdstr = org.getOrgId().toString();
				}
			}
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("<input type=\"text\" ");
		buffer.append("size=\"").append(size).append("\" ");
		buffer.append("readonly=\"true\" ");
		buffer.append("id=\"").append("select_").append(this.inputName).append(
				"\" ");
		buffer.append("value=\"").append(orgName).append("\" />");

		HttpServletRequest httpRequest = (HttpServletRequest) this.pageContext
				.getRequest();
		buffer.append("<img src=\"").append(httpRequest.getContextPath())
				.append("/images/icon_sel.gif").append("\" ");
		buffer.append("onclick =\"").append("openSelect")
				.append(this.inputName).append("Tree()").append("\" />");
		buffer.append("<input type=\"hidden\" ");
		buffer.append("name=\"").append(this.inputName).append("\" ");
		buffer.append("id=\"").append(this.inputName).append("\" ");
		buffer.append("value=\"").append(orgIdstr).append("\" />");

		buffer.append("\r\n");

		buffer.append("<script>");
		buffer.append("function openSelect").append(this.inputName).append(
				"Tree(){");
		buffer.append(this.inputName).append("flag=window.open(\"");
		buffer.append(httpRequest.getContextPath()).append(
				"/sm/dialog.do?act=opAccessOrg");
		// added by libf,2006/12/18
		buffer.append("&onlyCompany=").append(this.onlyCompany);
		// end added
		buffer.append("&inputName=").append(this.inputName);
		buffer.append("&incChildren=").append(this.includeChildren);
		buffer.append("&orgId=\"+document.getElementById('").append(
				this.inputName).append("').value,");
		buffer.append("\"tree").append(this.inputName).append("\",");
		buffer
				.append("'height=300,width=400,top=100,left=400,toolbar=no,menubar=no,scrollbars=no,");
		buffer.append("resizable=no,location=no, status=yes');");
		buffer.append(this.inputName).append("flag.focus();}");
		buffer.append("</script>");

		TagUtils.getInstance().write(this.pageContext, buffer.toString());
		return SKIP_BODY;
	}

	/**
	 * @return Returns the inputName.
	 */
	public String getInputName() {
		return inputName;
	}

	/**
	 * @param inputName
	 *            The inputName to set.
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	/**
	 * @return the includeChildren
	 */
	public String getIncludeChildren() {
		return includeChildren;
	}

	/**
	 * @param includeChildren
	 *            the includeChildren to set
	 */
	public void setIncludeChildren(String includeChildren) {
		this.includeChildren = includeChildren;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the onlyCompany
	 */
	public String getOnlyCompany() {
		return onlyCompany;
	}

	/**
	 * @param onlyCompany
	 *            the onlyCompany to set
	 */
	public void setOnlyCompany(String onlyCompany) {
		this.onlyCompany = onlyCompany;
	}
}
