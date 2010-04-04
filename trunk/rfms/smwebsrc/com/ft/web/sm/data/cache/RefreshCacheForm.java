package com.ft.web.sm.data.cache;

import org.apache.struts.util.LabelValueBean;

import com.ft.web.sm.BaseForm;

/**
 * �ֶ�����WEB�˻���ҳ���߼�Form Bean
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
        dataType[0].setLabel("ö������");
        dataType[0].setValue("enum");
        
        dataType[1].setLabel("����Ȩ��");
        dataType[1].setValue("resource");
        
        dataType[2].setLabel("��֯����");
        dataType[2].setValue("org");
        
        dataType[3].setLabel("ģ���ļ�");
        dataType[3].setValue("template");
        
        dataType[4].setLabel("�����ļ�");
        dataType[4].setValue("rule");
        
        return dataType;
    }
}