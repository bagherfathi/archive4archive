package com.ft.web.sm.taglib;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.ft.busi.sm.model.RegionManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.commons.web.SpringWebUtils;
import com.ft.webui.context.WebUIContextImpl;

/**
 * 将区域的数据加载到regionData.vm中
 * 
 * @version 1.0
 */
public class RegionDataTag extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4035605018461844348L;

	public int doStartTag() throws JspException {
        try {
            // 获取Region
            RegionManager regionManager = (RegionManager) SpringWebUtils
                    .getBean(pageContext.getServletContext(), "regionManager");
            List regionList = regionManager.findAllRegionOrderByParent();
            HashMap<Object,Object> params = new HashMap<Object,Object>();
            params.put("regions", regionList);
            StringWriter write = new StringWriter();
            WebUIContextImpl.getWebUIContext().getTemplateEngine().execute(
                    params, "regionData.vm", write);
            TagUtils.getInstance().write(pageContext, write.toString());
            return SKIP_BODY;
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
    }
}
