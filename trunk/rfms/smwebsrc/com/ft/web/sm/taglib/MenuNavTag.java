package com.ft.web.sm.taglib;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.common.security.ResourceRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * µº∫Ω–≈œ¢
 * 
 * @version 1.0
 * 
 */
public class MenuNavTag extends TagSupport {

	private static final long serialVersionUID = -2869604846619969761L;

	private String menuId;

	private String split = "-";

	public int doStartTag() throws JspException {

		Evaluator aEvaluator = new Evaluator();
		menuId = (String) aEvaluator.evaluate("menuId", this.menuId,
				String.class, this, pageContext);
		String navString = "";
		if (StringUtils.isNotEmpty(menuId)) {
		ApplicationContext app = PageContextUtils
				.getApplicationContext(this.pageContext);
		ResourceRepository resourceRepository = (ResourceRepository) app
				.getBean(ResourceRepository.getBeanName());
		List resources = resourceRepository.iterator();
		
		ResourceDTO resource = resourceRepository.getResource(new Long(this
				.getMenuId()));
		resource = findLeaf(resource, resources, resourceRepository);
		String path = resource.getResourcePath();
		String[] resourceIds = path.split(ResourceDTO.PATH_SEPARATOR);
		for (int i = 0; i < resourceIds.length; i++) {
			if (StringUtils.isNotEmpty(resourceIds[i])) {
				resource = resourceRepository.getResource(new Long(
						resourceIds[i]));
				if (i != (resourceIds.length - 1)) {
					navString += resource.getTitle();
					navString += this.getSplit();
				} else {
					navString += resource.getTitle();
				}
			}
		}
		}
		TagUtils.getInstance().write(this.pageContext, navString);
		return super.doStartTag();
	}

	private ResourceDTO findLeaf(ResourceDTO resource, List resources,
			ResourceRepository resourceRepository) {
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			ResourceDTO element = (ResourceDTO) iter.next();
			if (element.getParent() == null) {
				ResourceDTO resDTO = resourceRepository.getResource(element
						.getParentId());
                                if(resDTO != null){
				     element.setParent(resDTO.getResource());
                                }else{
                                    System.out.println ("************" + element.getResourceId());
                                }
			}
			if (resource.getParent() == null) {
				ResourceDTO resDTO = resourceRepository.getResource(resource
						.getParentId());
				resource.setParent(resDTO.getResource());
			}
			if (element.isMenu() && element.isVisible()
					&& element.getParent().getMenuVisible() > 0) {
				if (resource.getResourceId().longValue() != resource
						.getParent().getResourceId()) {
					if (element.getParent().getResourceId() == resource
							.getResourceId().longValue()) {
						resource = element;
						findLeaf(resource, resources, resourceRepository);
						break;
					}
				}
			}
		}
		return resource;
	}

	/**
	 * @return Returns the id.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setMenuId(String id) {
		this.menuId = id;
	}

	/**
	 * @return Returns the split.
	 */
	public String getSplit() {
		return split;
	}

	/**
	 * @param split
	 *            The split to set.
	 */
	public void setSplit(String split) {
		this.split = split;
	}

}
