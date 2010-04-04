package com.ft.web.sm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.common.security.ResourceRepository;
import com.ft.sm.dto.ResourceDTO;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 将功能权限的Path转换为由title组成的字符串
 * 
 * @version 1.0
 */
public class ResourcePathTag extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3107142509774863237L;
	private String resourcePath;

    public int doStartTag() throws JspException {

        StringBuffer path = new StringBuffer();

        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);
        ResourceRepository resourceRepository = (ResourceRepository) app
                .getBean(ResourceRepository.getBeanName());

        Evaluator aEvaluator = new Evaluator();
        String rpath = (String) aEvaluator.evaluate("resourcePath",
                this.resourcePath, String.class, this, pageContext);

        String[] ids = rpath.split("#");
        for (int i = 0; i < ids.length; i++) {
            String resourceId = ids[i];
            if(resourceId == null || resourceId.length() <=0) continue;
            Long id = Long.valueOf(resourceId);
            ResourceDTO resource = resourceRepository.getResource(id);
            path.append("/");
            path.append(resource.getTitle());
        }
        TagUtils.getInstance().write(this.pageContext, path.toString());
        return super.doStartTag();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

}
