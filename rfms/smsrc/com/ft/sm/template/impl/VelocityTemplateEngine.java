package com.ft.sm.template.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.ft.busi.sm.template.TemplateContext;
import com.ft.busi.sm.template.TemplateEngine;
import com.ft.busi.sm.template.TemplateException;
import com.ft.busi.sm.template.TemplateRepository;
import com.ft.sm.dto.OrganizationDTO;

/**
 * 模板引擎实现类.
 * 
 * @spring.bean id="vmTemplateEngine"
 */
public class VelocityTemplateEngine implements TemplateEngine {
    private static final Log logger = LogFactory
            .getLog(VelocityTemplateEngine.class);

    private VelocityEngine velocityEngine;

    private TemplateRepository templateRepository;

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.template.TemplateEngine#execute(com.ft.sm.template.TemplateContext,
     *      java.lang.Long, java.lang.String, java.io.Writer)
     */
    public void execute(TemplateContext templateContext, OrganizationDTO org,
            String categoryCode, Writer writer) throws IOException,
            TemplateException {
        logger.info("Begin execute template,category code " + categoryCode + ",organization id " + org.getOrgId());
        Reader reader = templateRepository.getTemplateReader(org, categoryCode);
        VelocityContext context = new VelocityContext();

        // 设置参数
        for (Iterator iter = templateContext.getAttributes().entrySet()
                .iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            context.put((String) element.getKey(), element.getValue());
        }

        try {
            velocityEngine.evaluate(context, writer, "template", reader);
        } catch (Exception e) {
            logger.error("Execute template failed,category code " + categoryCode + ",organization id " + org.getOrgId());
            throw new TemplateException(e);
        }
        
        logger.info("Execute template success,category code " + categoryCode + ",organization id " + org.getOrgId());
    }

    /* (non-Javadoc)
     * @see com.ft.busi.sm.template.TemplateEngine#execute(com.ft.busi.sm.template.TemplateContext, java.lang.String, java.io.Writer)
     */
    public void execute(TemplateContext templateContext, String templateCode,
            Writer writer) throws IOException, TemplateException {
        logger.info("Begin excute template,template code " + templateCode);
        Reader reader = templateRepository.getTemplateReader(templateCode);
        VelocityContext context = new VelocityContext();

        // 设置参数
        for (Iterator iter = templateContext.getAttributes().entrySet()
                .iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            context.put((String) element.getKey(), element.getValue());
        }

        try {
            velocityEngine.evaluate(context, writer, "template", reader);
        } catch (Exception e) {
            logger.error("Excute template failed,template code " + templateCode);
            throw new TemplateException(e);
        }
        
        logger.info("Excute template success,template code " + templateCode);
    }



    /**
     * @spring.property ref="templateRepository"
     * @param templateRepository
     *                the templateRepository to set
     */
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * @spring.property ref="velocityEngine"
     * @param velocityEngine
     *                the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
