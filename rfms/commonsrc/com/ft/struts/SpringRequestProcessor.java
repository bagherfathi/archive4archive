package com.ft.struts;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.action.InvalidCancelException;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.apache.struts.util.RequestUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.ft.spring.StrutsApplicationContext;
import com.ft.struts.message.ActionMessagePlugin;
import com.ft.struts.message.ActionMethodDesc;

/**
 * 扩展Struts 的框架，Spring中加载Action对象
 * 
 * @version 1.0
 */
public class SpringRequestProcessor extends TilesRequestProcessor {
    public static final Class DEFAULT_CONTEXT_CLASS = XmlWebApplicationContext.class;

    final static Log log = LogFactory.getLog(SpringRequestProcessor.class);

    private String contextPrefix = "struts.module.spring.applicatContext.";

    private Class contextClass;

    public void init(ActionServlet servlet, ModuleConfig moduleConfig)
	    throws ServletException {
	super.init(servlet, moduleConfig);

	StrutsApplicationContext applicationContext = new StrutsApplicationContext(
		servlet, moduleConfig);
	servlet.getServletContext().setAttribute(
		contextPrefix + moduleConfig.getPrefix(), applicationContext);
    }

    /**
     * 得到StrutsApplicationContext上文件
     * 
     * @return
     */
    public StrutsApplicationContext getStrutsApplicationContext() {
	return (StrutsApplicationContext) servlet.getServletContext()
		.getAttribute(contextPrefix + this.moduleConfig.getPrefix());
    }

    /**
     * 执行Action 对象
     * 
     * @param request
     *                Web请求对象
     * @param response
     *                Web 响应对象
     * @param action
     *                当前的Action对象
     * @param form
     *                对应的Form 对象
     * @param ActionMapping
     * @see org.apache.struts.action.ActionMapping
     * 
     */
    protected ActionForward processActionPerform(HttpServletRequest request,
	    HttpServletResponse response, Action action, ActionForm form,
	    ActionMapping mapping) throws IOException, ServletException {
	try {
	    ActionForward result = action.execute(mapping, form, request,
		    response);

	    if (result instanceof ExpressActionForward) {
		ExpressActionForward aFoward = (ExpressActionForward) result;
		String path = aFoward.getPathRequest(request);
		result = new ActionForward(result);
		result.setPath(path);
	    }
	    if (request.getAttribute("exceptionStack") == null) {
		doActionMessageProcess(request, action, mapping);
	    }

	    return result;
	} catch (Exception ex) {
	    return (processException(request, response, ex, form, mapping));
	}
    }

    /**
     * <p>
     * Ask our exception handler to handle the exception. Return the
     * <code>ActionForward</code> instance (if any) returned by the called
     * <code>ExceptionHandler</code>.
     * </p>
     * 
     * @param request
     *                The servlet request we are processing
     * @param response
     *                The servlet response we are processing
     * @param exception
     *                The exception being handled
     * @param form
     *                The ActionForm we are processing
     * @param mapping
     *                The ActionMapping we are using
     * 
     * @exception IOException
     *                    if an input/output error occurs
     * @exception ServletException
     *                    if a servlet exception occurs
     */
    protected ActionForward processException(HttpServletRequest request,
	    HttpServletResponse response, Exception exception, ActionForm form,
	    ActionMapping mapping) throws IOException, ServletException {

	// Is there a defined handler for this exception?
	ExceptionConfig config = mapping.findException(exception.getClass());
	if (config == null) {
	    //当在子模块中无法找到异常处理配置时，在全局配置中查找
	    ModuleConfig moduleConfig = (ModuleConfig) request.getSession()
		    .getServletContext().getAttribute(Globals.MODULE_KEY);
	    config = moduleConfig.findExceptionConfig(exception.getClass()
		    .getName());

	    if (config == null) {
		log.warn(getInternal().getMessage("unhandledException",
			exception.getClass()));
		if (exception instanceof IOException) {
		    throw (IOException) exception;
		} else if (exception instanceof ServletException) {
		    throw (ServletException) exception;
		} else {
		    throw new ServletException(exception);
		}
	    }
	}

	// Use the configured exception handling
	try {
	    ExceptionHandler handler = (ExceptionHandler) RequestUtils
		    .applicationInstance(config.getHandler());
	    return (handler.execute(exception, config, mapping, form, request,
		    response));
	} catch (Exception e) {
	    throw new ServletException(e);
	}

    }

    /**
     * 从Spring中加载Action对象
     * 
     * @param HttpServletRequest
     *                Web请求对象
     * @param HttpServletResponse
     *                Web 响应对象
     * @param ActionMapping
     * @see org.apache.struts.action.ActionMapping
     */
    protected Action processActionCreate(HttpServletRequest request,
	    HttpServletResponse response, ActionMapping mapping)
	    throws IOException {
	Action instance = null;

	synchronized (actions) {
	    instance = (Action) actions.get(mapping.getPath());

	    if (instance != null) {
		return (instance);
	    }

	    try {
		instance = (Action) this.getStrutsApplicationContext()
			.getBeanByMapping(mapping);

		if (instance == null) {
		    instance = super.processActionCreate(request, response,
			    mapping);
		}

		instance.setServlet(this.servlet);
		actions.put(mapping.getPath(), instance);

		return instance;
	    } catch (Exception e) {
		log.error(mapping, e);

		return super.processActionCreate(request, response, mapping);
	    }
	}
    }

    /**
     * 得到StrutsContext 的类，主要于扩展自定义的Action 处理上下文
     */
    public Class getContextClass() {
	return contextClass;
    }

    /**
     * 处理Form的赋值,去空格，过滤字段
     * 
     * @param request
     *                Web请求对象
     * @param response
     *                Web 响应对象
     * @param form
     *                Action中对应form对象
     * @param mapping
     * @see org.apache.struts.action.ActionMapping
     */
    protected void processPopulate(HttpServletRequest request,
	    HttpServletResponse response, ActionForm form, ActionMapping mapping)
	    throws ServletException {
	if (form != null) {
	    if (form instanceof InitActionForm) {
		((InitActionForm) form).init(mapping, request);
	    }
	    log.debug("lookup from " + mapping.toString());
	    ActionFormAttributeLookuper lookuper = getStrutsApplicationContext()
		    .getAttributeLookuper();

	    if (lookuper != null) {
		lookuper.lookup(request, response, form, mapping);
	    }

	    log.debug("lookup fromed " + mapping.toString());
	    // add by coffee
	    ActionFormAttributeFilter filter = this
		    .getStrutsApplicationContext().getFormAttributeFilter();
	    HttpServletRequest requestProxy = request;

	    if (!isMultipart(requestProxy)) {
		if (filter != null) {
		    List attrs = filter.getFilterAttribute(mapping, request);
		    requestProxy = new ParameterFilterHttpServletRequestProxy(
			    requestProxy, attrs);
		} else {
		    requestProxy = new ParameterFilterHttpServletRequestProxy(
			    requestProxy, Collections.EMPTY_LIST);
		}
	    }
	    super.processPopulate(requestProxy, response, form, mapping);
	}

    }

    private boolean isMultipart(HttpServletRequest request) {
	String contextType = request.getContentType();
	if (contextType != null) {
	    return contextType.startsWith("multipart/form-data");
	}
	return false;
    }

    /**
     * 权限处理,目前不使用
     * 
     * @param HttpServletRequest
     *                Web请求对象
     * @param HttpServletResponse
     *                Web 响应对象
     * @param ActionMapping
     * @see org.apache.struts.action.ActionMapping
     */
    protected boolean processRoles(HttpServletRequest request,
	    HttpServletResponse response, ActionMapping mapping)
	    throws IOException, ServletException {
	return true;
    }

    /**
     * 处理指定出错校验的重向页面，一般用于DispatchAction
     * 
     * @param HttpServletRequest
     *                Web请求对象
     * @param HttpServletResponse
     *                Web 响应对象
     * @param ActionForm
     *                form 对象进行校验
     * @param ActionMapping
     * @see org.apache.struts.action.ActionMapping
     */
    protected boolean processValidate(HttpServletRequest request,
	    HttpServletResponse response, ActionForm form, ActionMapping mapping)
	    throws IOException, ServletException, InvalidCancelException {
	ActionMessagePlugin plugin = ActionMessagePlugin
		.getActionMessagePlugin(request.getSession()
			.getServletContext(), mapping);

	if (plugin != null) {
	    ActionMethodDesc desc = plugin.getMethodMessage(mapping, request);

	    if (desc == null) {
		return super.processValidate(request, response, form, mapping);
	    }

	    ActionMapping actionMapping = mapping;

	    try {
		actionMapping = (ActionMapping) BeanUtils.cloneBean(mapping);

		if (desc.isValidate()) {
		    actionMapping.setInput((desc.getInput() != null) ? desc
			    .getInput() : mapping.getInput());
		    actionMapping.setValidate(true);

		    return super.processValidate(request, response, form,
			    actionMapping);
		} else {
		    return true;
		}
	    } catch (Exception e) {
		log.error(e);

		return true;
	    }
	} else {
	    return super.processValidate(request, response, form, mapping);
	}
    }

    /**
     * 自动生成应用消息
     * 
     * @param HttpServletRequest
     *                Web请求对象
     * @param action
     *                当前请求的Action
     * @param mapping
     * @see org.apache.struts.action.ActionMapping
     */
    private void doActionMessageProcess(HttpServletRequest request,
	    Action action, ActionMapping mapping) {
	ActionMessagePlugin plugin = ActionMessagePlugin
		.getActionMessagePlugin(
			action.getServlet().getServletContext(), mapping);
	if (plugin != null) {
	    plugin.saveActionMessage(request, action, mapping);

	}
    }

    // end added
}
