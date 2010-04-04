package com.ft.busi.sm.template;

import java.io.IOException;
import java.io.Writer;

import com.ft.sm.dto.OrganizationDTO;

/**
 * 模板引擎。
 * 
 * @version 1.0
 */
public interface TemplateEngine {
    /**
     * 对指定模板进行实例化，并将实例化结果输出。
     * 
     * @param context
     *                模板引擎执行上下文。
     * @param org
     *                指定的组织机构。
     * @param categoryCode
     *                模板类别代码。
     * @param writer
     *                用于输出模板实例化结果。
     */
    public void execute(TemplateContext context, OrganizationDTO org,
            String categoryCode, Writer writer) throws IOException,
            TemplateException;
    
    /**
     * 对指定模板进行实例化，并将实例化结果输出。
     * 
     * @param context
     *                模板引擎执行上下文。
     * @param templateCode
     *                模板代码。
     * @param writer
     *                用于输出模板实例化结果。
     */
    public void execute(TemplateContext context,String templateCode, Writer writer) throws IOException,
            TemplateException;
}
