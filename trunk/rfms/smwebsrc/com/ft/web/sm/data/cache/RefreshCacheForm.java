package com.ft.web.sm.data.cache;

import org.apache.struts.util.LabelValueBean;

import com.ft.web.sm.BaseForm;

/**
 * 手动更新WEB端缓存页面逻辑Form Bean
 * 
 * @struts.form name="refreshCacheForm"
 * 
 * @version 1.0
 */
public class RefreshCacheForm extends BaseForm{
    private static final long serialVersionUID = 2432911545565878776L;
    
    private String cacheCode;
    private LabelValueBean[] dataType;

    /**
     * @return the cacheCode
     */
    public String getCacheCode() {
        return cacheCode;
    }

    /**
     * @param cacheCode the cacheCode to set
     */
    public void setCacheCode(String cacheCode) {
        this.cacheCode = cacheCode;
    }

    /**
     * @return the dataType
     */
    public LabelValueBean[] getDataType() {
        this.dataType = new LabelValueBean[5];
        for (int i = 0; i < 5; i++) {
            dataType[i] = new LabelValueBean();
        }
        dataType[0].setLabel("枚举数据");
        dataType[0].setValue("enum");
        
        dataType[1].setLabel("功能权限");
        dataType[1].setValue("resource");
        
        dataType[2].setLabel("组织机构");
        dataType[2].setValue("org");
        
        dataType[3].setLabel("模板文件");
        dataType[3].setValue("template");
        
        dataType[4].setLabel("规则文件");
        dataType[4].setValue("rule");
        
        return dataType;
    }
}