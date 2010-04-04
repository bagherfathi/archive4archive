package com.ft.spring;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.ft.struts.ActionFormAttributeFilter;
import com.ft.struts.ActionFormAttributeLookuper;


/**
 * 配置Struts 的Application上下文
 *
 */
public class StrutsApplicationContext {
    final static Log log = LogFactory.getLog(StrutsApplicationContext.class);
    public static final Class DEFAULT_CONTEXT_CLASS =
        XmlWebApplicationContext.class;
    private String prefix = "struts.springcontext.path.prefix";
    private String formAttributeLookuperName = "formAttributeLookuper";
    private String formAttributeFilterName ="formAttributeFilter";
    private WebApplicationContext webApplicationContext;

    public StrutsApplicationContext(
        ActionServlet servlet, ModuleConfig moduleConfig) {
        initApplicationContext(servlet, moduleConfig);
    }

    /**
    * 初始化ApplicationContext
    * @param servlet
    * @param moduleConfig
     */
    public void initApplicationContext(
        ActionServlet servlet, ModuleConfig moduleConfig) {
        String name =
            servlet.getServletContext()
                   .getInitParameter("struts.springcontext.path.prefix");
        String prefix = moduleConfig.getPrefix();

        if (prefix.length() > 1) {
            prefix = prefix.substring(1);
        }
        if(name == null){
        	name = "/WEB-INF/struts";
        }
        String fileName = name + "/"+prefix+"/"+ prefix + "-action-context.xml";
        String realName = servlet.getServletContext().getRealPath(fileName);
        File aFile = new File(realName);
        log.info("初始化strust的spring配置文件,文件名称："+fileName);
        if (aFile.exists()) {
            log.debug(
                "load form Attribute lookup file " + aFile.getAbsolutePath());
            this.webApplicationContext = createWebApplicationContext(
                    servlet, moduleConfig, fileName);
        } else {
            this.webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servlet.getServletContext());
        }
    }

    public WebApplicationContext getWebApplicationContext(
        ActionServlet servlet, ModuleConfig moduleConfig) {
        return (WebApplicationContext) servlet.getServletContext()
                                              .getAttribute(
            prefix + moduleConfig.getPrefix());
    }

    /**
    * 创建WebApplicationContext
    * @param servlet
    * @param moduleConfig
    * @param config
    * @return
    * @throws BeansException
     */
    protected WebApplicationContext createWebApplicationContext(
        ActionServlet servlet, ModuleConfig moduleConfig, String config)
        throws BeansException {
        WebApplicationContext parent =
            WebApplicationContextUtils.getWebApplicationContext(
                servlet.getServletContext());

        if (log.isDebugEnabled()) {
            log.debug(
                "Servlet with name '" + moduleConfig.getPrefix()
                + "' will try to create custom WebApplicationContext context of class '"
                + moduleConfig.getPrefix() + "'" + ", using parent context ["
                + parent + "]");
        }

        ConfigurableWebApplicationContext wac =
            (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(
                DEFAULT_CONTEXT_CLASS);
        wac.setParent(parent);
        wac.setServletContext(servlet.getServletContext());
        wac.setNamespace(this.prefix + moduleConfig.getPrefix());
        wac.setConfigLocations(new String[] { config });
        wac.refresh();

        return wac;
    }

    public ActionFormAttributeLookuper getAttributeLookuper() {
        if (webApplicationContext.containsBean(formAttributeLookuperName)) {
            return (ActionFormAttributeLookuper) webApplicationContext.getBean(
                formAttributeLookuperName);
        } else {
            return null;
        }
    }
    
    public ActionFormAttributeFilter getFormAttributeFilter() {
        if (webApplicationContext.containsBean(formAttributeFilterName)) {
            return (ActionFormAttributeFilter) webApplicationContext.getBean(
            		formAttributeFilterName);
        } else {
            return null;
        }
    }

    /**
     * 根据bean 得到类名
     * @param type
     * @return
     */
    public Action getBeanByClassName(String type) {
        try {
            Class clazz =
                Thread.currentThread().getContextClassLoader().loadClass(
                    type);
            String[] names =
                this.webApplicationContext.getBeanNamesForType(clazz);

            if (names.length > 0) {
                return (Action) this.webApplicationContext.getBean(names[0]);
            }
        } catch (ClassNotFoundException e) {
            return null;
        }

        return null;
    }

    public Action getBeanByMapping(ActionMapping mapping) {
        if (this.webApplicationContext.containsBean(mapping.getPath())) {
            Object obj =
                this.webApplicationContext.getBean(mapping.getPath());

            if (obj == null) {
                String[] names =
                    this.webApplicationContext.getBeanNamesForType(
                        mapping.getClass());

                if (names.length > 0) {
                    obj = this.webApplicationContext.getBean(names[0]);
                }
            }

            if (obj instanceof Action) {
                if (log.isDebugEnabled()) {
                    log.debug("load bean from mapping " + mapping.getPath());
                }

                return (Action) obj;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug(" mapping bean not found " + mapping.getPath());
                }

                return null;
            }
        } else {
            return getBeanByClassName(mapping.getType());
        }
    }
}
