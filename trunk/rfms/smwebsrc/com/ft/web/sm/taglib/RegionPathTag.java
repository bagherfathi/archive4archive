package com.ft.web.sm.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.springframework.context.ApplicationContext;

import com.ft.busi.sm.model.RegionManager;
import com.ft.sm.dto.RegionDTO;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 用于获取指定区域的全路径。
 * 
 * @version 1.0
 */
public class RegionPathTag extends TagSupport{
    private static final long serialVersionUID = -5404853681611726828L;
    private String regionId;
    private String separator = "/";
    
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public String getSeparator() {
        return separator;
    }
    public void setSeparator(String separator) {
        this.separator = separator;
    }
    
    public int doStartTag() throws JspException {
        ApplicationContext app = PageContextUtils
                .getApplicationContext(this.pageContext);
        RegionManager regionManager = (RegionManager) app
                .getBean("regionManager");

        Evaluator aEvaluator = new Evaluator();
        
        Long rId = (Long) aEvaluator.evaluate("regionId",
                this.regionId, Long.class, this, pageContext);

        
        try{
            List result = regionManager.findRegionLocation(rId);
            
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < result.size(); i++) {
                RegionDTO region = (RegionDTO) result.get(i);
                if(i==0){
                    buf.append(region.getRegionName());
                }else{
                    buf.append(this.separator);
                    buf.append(region.getRegionName());
                }
            }
            
            TagUtils.getInstance().write(this.pageContext, buf.toString());
        }catch(Exception ex){
            throw new JspException(ex);
        }
        
        return super.doStartTag();
    }
}
