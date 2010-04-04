package com.ft.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ConfigRuleSet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.springframework.core.io.Resource;
import org.xml.sax.InputSource;


/**
 *
 * @author Coffee
 *
 */
public abstract class AbstractActionFormAttributeLookup
    implements ActionFormAttributeLookuper {
    final static Log log =
        LogFactory.getLog(AbstractActionFormAttributeLookup.class);
    private File file;
    private ModuleConfigImpl config = new ModuleConfigImpl("");

    public void setFile(File file) {
        this.file = file;
    }

    public void setResource(Resource res) throws IOException {
        this.file = res.getFile();
    }

    public void init() {
        if (this.file != null) {
            parseConfigFile();

            if (log.isDebugEnabled()) {
                FormBeanConfig[] configs = this.config.findFormBeanConfigs();

                for (int i = 0; i < configs.length; i++) {
                    log.debug(configs[i]);
                }
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("this.file is null " + this.file.getAbsolutePath());
            }
        }
    }

    public void lookup(
        HttpServletRequest request, HttpServletResponse response,
        ActionForm form, ActionMapping mapping) {
        if (form == null) {
            log.debug(" form is null ");

            return;
        }

        FormBeanConfig formBeanConfig =
            config.findFormBeanConfig(mapping.getName());

        if (formBeanConfig == null) {
            log.debug(" mapping " + mapping + "not found");

            return;
        }

        FormPropertyConfig[] props = formBeanConfig.findFormPropertyConfigs();
        log.debug("find form " + formBeanConfig + " props" + props.length);

        for (int i = 0; i < props.length; i++) {
            FormPropertyConfig propconfig = props[i];
            log.debug(propconfig);

            if (propconfig.getTypeClass().isArray()) {
                Class entityType =
                    propconfig.getTypeClass().getComponentType();
                String[] values =
                    request.getParameterValues(propconfig.getInitial());
                List result = this.loadByIds(entityType, values);

                try {
                    Object arrResult =
                        Array.newInstance(entityType, result.size());
                    System.arraycopy(
                        result.toArray(), 0, arrResult, 0, result.size());
                    PropertyUtils.setSimpleProperty(
                        form, propconfig.getName(), arrResult);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            } else {
            	String param=request.getParameter(propconfig.getInitial());
                if(param==null || param.equalsIgnoreCase("")){
                	param="0";
                }
            	Long id =new Long(param);
                
                log.debug("get id" + id);

                if (id.longValue() > 0) {
                    log.debug(
                        "load entity " + propconfig.getTypeClass() + "=" + id);

                    Object entity =
                        this.getEntity(propconfig.getTypeClass(), id);

                    try {
                        PropertyUtils.setSimpleProperty(
                            form, propconfig.getName(), entity);
                        request.setAttribute(propconfig.getName(), entity);
                        log.debug(
                            "load entity " + propconfig.getName()
                            + propconfig.getTypeClass() + "=" + id + " ok");
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

    protected abstract Object getEntity(Class typeClass, Serializable id);

    protected abstract List loadByIds(
        Class entityType, Serializable[] values);

    protected void parseConfigFile() {
        if (log.isDebugEnabled()) {
            log.debug("pasere " + this.file.getAbsolutePath());
        }

        Digester digester = new Digester();
        digester.setValidating(false);
        digester.push(config);
        this.addRules(digester);

        InputStream input = null;

        try {
            InputSource is = new InputSource();
            input = new FileInputStream(this.file);
            is.setByteStream(input);
            digester.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void addRules(Digester digester) {
        digester.addRuleSet(new ConfigRuleSet());
    }

    public void handleMessage(
        Action action, ActionMapping mapping, HttpServletRequest request) {
        if (mapping.getPath() == null) {
            return;
        }

        ActionConfig actionConfig = null;

        try {
            actionConfig = this.config.findActionConfig(mapping.getPath());
        } catch (Exception e) {
            return;
        }

        String msgKey = null;
        String scope = null;

        if (action instanceof DispatchAction) {
            String act = request.getParameter("");

            if (StringUtils.isNotEmpty(act)) {
                ForwardConfig aForwardConfig =
                    actionConfig.findForwardConfig(act);

                if (aForwardConfig != null) {
                    msgKey = aForwardConfig.getPath();
                    scope = aForwardConfig.getModule();
                }
            }
        } else {
            msgKey = actionConfig.getInput();
        }

        if (StringUtils.isNotEmpty(msgKey)) {
            ActionMessagesHelper.saveMessage(request, msgKey, scope);
        }
    }
}
