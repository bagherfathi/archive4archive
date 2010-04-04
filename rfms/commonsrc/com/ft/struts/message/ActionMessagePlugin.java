package com.ft.struts.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.digester.Digester;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.config.ModuleConfig;
import org.xml.sax.SAXException;

 
/**
 * action 中消息处理的插件
 */
public class ActionMessagePlugin implements PlugIn {
    final static String KEY = ActionMessagePlugin.class.getName();
    public final static String RESOURCE_DELIM = ",";

    /*
    private static Log log = LogFactory.getLog(EntityLookupPlugin.class);
    */
    private ActionServlet servlet = null;
    HashMap properties = new HashMap();
    private String fileName;

    /*
    private OperatorLogger operatorLogger;
    */
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
     *      org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException {
        String key = KEY;

        if (config.getPrefix() != null) {
            key = key + "." + config.getPrefix();
        }

        this.servlet = servlet;

        /*
        this.operatorLogger = (OperatorLogger)
                        AppWebUtils.getBean(this.servlet.getServletContext(),"operatorLogger");
                        */
        try {
            initResources();
            this.servlet.getServletContext().setAttribute(key, this);
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    protected void initResources() throws IOException, ServletException {
        if ((this.fileName == null) || (this.fileName.length() <= 0)) {
            return;
        }

        InputStream input =
            servlet.getServletContext().getResourceAsStream(fileName);
        Digester digester = new Digester();
        digester.push(this);
        digester.addObjectCreate(
            "ActionMessages/action", ActionMessageDesc.class);
        digester.addSetProperties("ActionMessages/action");
        digester.addSetNext("ActionMessages/action", "addActionMessageDesc");
        digester.addObjectCreate(
            "ActionMessages/action/method", ActionMethodDesc.class);
        digester.addSetProperties("ActionMessages/action/method");
        digester.addSetNext(
            "ActionMessages/action/method", "addActionMethodDesc");
        digester.addCallMethod(
            "ActionMessages/action/method/param", "addParam", 1);
        digester.addCallParam("ActionMessages/action/method/param", 0);

        /*
        digester.addObjectCreate("ActionMessages/action/method/logger",
                        ActionMethodLoggerDesc.class);
        digester.addSetProperties("ActionMessages/action/method/logger");
        digester.addSetNext("ActionMessages/action/method/logger",
                        "setLoggerDesc");
                        */
        try {
            digester.parse(input);
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
    }

    public void addActionMessageDesc(ActionMessageDesc desc) {
        this.properties.put(desc.getPath(), desc);
    }

    public ActionMessageDesc getActionMessageDesc(String key) {
        return (ActionMessageDesc) this.properties.get(key);
    }

    public ActionMethodDesc getMethodMessage(
        ActionMapping mapping, HttpServletRequest request) {
        String path = mapping.getPath();
        ActionMessageDesc messageDesc = getActionMessageDesc(path);

        if (messageDesc == null) {
            return null;
        }

        String parameter = null;
        parameter = request.getParameter(mapping.getParameter());

        if (parameter == null) {
            parameter = "execute";
        }

        return messageDesc.getActionMethodDesc(parameter);
    }

    public ActionMethodDesc getMethodMessage(
        ActionMapping mapping, HttpServletRequest request, Action action) {
        String path = mapping.getPath();
        ActionMessageDesc messageDesc = getActionMessageDesc(path);

        if (messageDesc == null) {
            return null;
        }

        String parameter = null;

        if (action instanceof DispatchAction) {
            parameter = request.getParameter(mapping.getParameter());
        } else {
            parameter = "execute";
        }

        return messageDesc.getActionMethodDesc(parameter);
    }

    /**
     * @param mapping
     * @return
     */
    public static ActionMessagePlugin getActionMessagePlugin(
        ServletContext context, ActionMapping mapping) {
        String key =
            KEY
            + ((mapping.getModuleConfig().getPrefix() == null) ? ""
                                                               : ("."
            + mapping.getModuleConfig().getPrefix()));

        return (ActionMessagePlugin) context.getAttribute(key);
    }



    /**
     * 在执行之后，根据配置生成消息
     * @param request  HttpServletRequest
     * @param action 执行的Action类
     * @param mapping  Struts 中 Action的定义类
     */
    public void saveActionMessage(
        HttpServletRequest request, Action action, ActionMapping mapping) {
        ActionMethodDesc desc = getMethodMessage(mapping, request, action);

        if (desc == null) {
            return;
        }

        if (
            desc.getHttpMethods().indexOf(request.getMethod().toLowerCase()) == -1) {
            return;
        }

        ActionMessages messages =
            (ActionMessages) request.getAttribute(Globals.MESSAGE_KEY);

        if (desc.isShowMessage()) {
            if ((messages == null) || messages.isEmpty()) {
                messages = new ActionMessages();

                ActionMessage actionMessage = desc.getActionMessage(request);
                messages.add(desc.getMessageDesc().getPath(), actionMessage);

                if ("session".equals(desc.getScope())) {
                    request.getSession()
                           .setAttribute(Globals.MESSAGE_KEY, messages);
                } else {
                    request.setAttribute(Globals.MESSAGE_KEY, messages);
                }

                //TagUtils.getInstance().message()
            }
        }

        /*
        if (desc.getLoggerDesc() != null) {
                loggerAction(request, mapping, desc);
        }*/
    }

    /*
    private void loggerAction(HttpServletRequest request,
                    ActionMapping mapping, ActionMethodDesc desc) {
            ActionMethodLoggerDesc loggerDesc = desc.getLoggerDesc();
            loggerDesc.getAct();
            if (mapping.getAttribute() != null) {
                    Object obj = request.getAttribute(mapping.getAttribute());
                    try {
                            User user = AppWebUtils.getUser(request);
                            Object loggerObject = PropertyUtils.getProperty(obj, loggerDesc
                                            .getAttribute());
                            if (loggerObject instanceof Collection) {
                                    this.operatorLogger.log(loggerDesc.getModuleName(), user,
                                                    loggerDesc.getAct(), (Collection) loggerObject, "");
                            } else {
                                    this.operatorLogger.log(loggerDesc.getModuleName(), user,
                                                    loggerDesc.getAct(), loggerObject, "");
                            }
                    } catch (Exception e) {
                            log.debug(e.getMessage(), e);
                    }
            }
    }*/
}
