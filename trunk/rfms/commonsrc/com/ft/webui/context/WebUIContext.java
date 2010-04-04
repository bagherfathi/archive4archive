package com.ft.webui.context;

import com.ft.commons.template.TemplateEngine;

import org.apache.commons.configuration.Configuration;

import javax.servlet.jsp.PageContext;

public interface WebUIContext {
    public TemplateEngine getTemplateEngine();

    public Configuration getConfiguration();

    public String getMessage(
        PageContext pageContext, String bundle, String locale, String key);
}
