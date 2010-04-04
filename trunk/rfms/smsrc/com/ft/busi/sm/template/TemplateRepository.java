package com.ft.busi.sm.template;

import java.io.IOException;
import java.io.Reader;

import com.ft.sm.dto.OrganizationDTO;

/**
 * 模板文件仓库类。
 * 
 * @version 1.0
 */
public interface TemplateRepository {
    /**
     *  获取指定组织在指定类别中绑定的模板字节流。
     * 
     * @param org
     *                组织机构。
     * @param categoryCode
     *                模板类别编码。
     * @return
     * @throws IOException
     */
    public Reader getTemplateReader(OrganizationDTO org, String categoryCode)
            throws IOException, TemplateException;
    
    /**
     * 获取指定模板的字节流。
     * @param templateCode
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public Reader getTemplateReader(String templateCode) throws IOException, TemplateException;
}
