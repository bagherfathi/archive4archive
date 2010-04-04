package com.ft.webui.context;

import com.ft.commons.lookup.Lookuper;
import com.ft.commons.lookup.LookuperRepository;
import com.ft.commons.template.TemplateEngine;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.PageContext;


public class WebUIContextImpl implements WebUIContext {
    private static WebUIContextImpl webUIContext;
    TemplateEngine templateEngine;
    Configuration configuration;

    public WebUIContextImpl(ServletContext context) {
        URL url;
        webUIContext = this;

        try {
            WebApplicationContext appContext =
                WebApplicationContextUtils
                .getWebApplicationContext(context);
            url = context.getResource("/WEB-INF/webui.properties");

            String[] names = appContext.getBeanNamesForType(Lookuper.class);

            for (int i = 0; i < names.length; i++) {
                context.setAttribute(names[i], appContext.getBean(names[i]));
            }

            if (appContext.containsBean("systemInfo")) {
                context.setAttribute(
                    "systemInfo", appContext.getBean("systemInfo"));
            }

            names = appContext.getBeanNamesForType(LookuperRepository.class);

            for (int i = 0; i < names.length; i++) {
                LookuperRepository lookuper =
                    (LookuperRepository) appContext.getBean(names[i]);
                Map map = lookuper.loadLookuper();

                for (Iterator iter = map.entrySet().iterator();
                        iter.hasNext();) {
                    Map.Entry element = (Map.Entry) iter.next();
                    context.setAttribute(
                        (String) element.getKey(), element.getValue());
                }
            }

            PropertiesConfiguration config = new PropertiesConfiguration(url);
            configuration = config;
            templateEngine = (TemplateEngine) appContext.getBean(
                    config.getString("template.engine.name"));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    }

    protected void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    protected void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public TemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public static WebUIContext getWebUIContext() {
        return webUIContext;
    }

    public String getMessage(
        PageContext pageContext, String bundle, String locale, String key) {
        return key;
    }
}
