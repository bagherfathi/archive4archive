package com.ft.webui;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.navigator.displayer.MenuDisplayer;
import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.taglib.UseMenuDisplayerTag;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.taglib.TagUtils;

/**
 * 重构Struts Menu DisplayMenuTag，实现从PageContext中获取到MenuRepository进行展现.
 * 
 * 和Struts Menu结合使用，用法如：
 * <menu:useMenuDisplayer name="Velocity" config="/templates/menu/tabMenu.html" >
 *     <webui:displayMenu name="SSOMenu"/>
 * </menu:useMenuDisplayer>
 * 
 * @jsp.tag name="displayMenu" display-name="DisplayMenuTag" body-content="empty"
 * 
 */
public class DisplayMenuTag extends TagSupport{
    protected final static Log log = LogFactory.getLog(DisplayMenuTag.class);
	
	private String attribute;
	private String scope;
	private String name;
    private String target;
    
    
    public String getAttribute() {
		return attribute;
	}

    /**
     * @jsp.attribute description="Arribute in context"
     * required="true" rtexprvalue="false"
     */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getName() {
		return name;
	}

	/**
     * @jsp.attribute description="Name of root menu"
     * required="true" rtexprvalue="true"
     */
	public void setName(String name) {
        if(name.startsWith("${") && name.endsWith("}")){
            name = StringUtils.removeStart(name,"${");
            name = StringUtils.removeEnd(name,"}");
            try {
                this.name = (String)TagUtils.getInstance().lookup(pageContext,name,null);
            } catch (JspException e) {
                e.printStackTrace();
            }
            
        }else{
            this.name = name;    
        }		
	}

	public String getScope() {
		return scope;
	}

	/**
     * @jsp.attribute description=""
     * required="false" rtexprvalue="false"
     */
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTarget() {
		return target;
	}

	/**
     * @jsp.attribute description=""
     * required="false" rtexprvalue="false"
     */
	public void setTarget(String target) {
		this.target = target;
	}

	public int doStartTag() throws JspException {
    	MenuDisplayer displayer = (MenuDisplayer) pageContext.getAttribute(UseMenuDisplayerTag.DISPLAYER_KEY);

        if (displayer == null) {
            throw new JspException("Could not retrieve the menu displayer.");
        }
        
        MenuRepository repository = (MenuRepository)TagUtils.getInstance().lookup(this.pageContext,attribute,scope);
        if(repository == null){
        	throw new JspException("Not found " + attribute + " in any scope.");
        }

        MenuComponent menu = repository.getMenu(this.name);

        if (menu != null) {
            try {
                if (target != null) {
                    displayer.setTarget(this.target);
                }

                try {
                    setPageLocation(menu);
                } catch (MalformedURLException m) {
                    log.error("Incorrect action or forward: " + m.getMessage());
                    log.warn("Menu '" + menu.getName() + "' location set to #");
                    menu.setLocation("#");
                } 

                displayer.display(menu);
                displayer.setTarget(null);
            } catch (Exception e) {
                e.printStackTrace();
                throw new JspException(e);
            }
        } else {
            String error = "No Menu was found which name is " + this.name;
            log.warn(error);
            try {
                pageContext.getOut().write(error);
            } catch (IOException io) {
                throw new JspException(error);
            }
        }

        return SKIP_BODY;
    }

    /**
     * Sets the value for the menu location to the
     * appropriate value if location is null.  If location
     * is null, and the page attribute exists, it's value
     * will be set to the the value for page prepended with
     * the context path of the application.
     *
     * If the page is null, and the forward attribute exists,
     * it's value will be looked up in struts-config.xml.
     *
     *                                     FIXME - ssayles - 121102
     * Ideally, this should happen at menu initialization but
     * I was unable to find a reliable way to get the context path
     * outside of a request.  The performance impact is probably
     * negligable, but it would be better to check for this only once.
     *
     * @param menu The menu component to set the location for.
     */
    protected void setPageLocation(MenuComponent menu) throws MalformedURLException, JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    	setLocation(menu);
        String url = menu.getLocation();

        // Check if there are parameters on the value
        if ((url != null) && (url.indexOf("${") > -1)) {
            String queryString = null;

            if (url.indexOf("?") > -1) {
                queryString = url.substring(url.indexOf("?") + 1);
                url = url.substring(0, url.indexOf(queryString));
            }

            // variable is in the URL
            if (queryString != null) {
                menu.setUrl(url + parseString(queryString, request));
            } else {
                // parse the URL, rather than the queryString
                menu.setUrl(parseString(queryString, request).toString());
            }
        } else {
            menu.setUrl(url);
        }

        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        if (menu.getUrl() != null) {
            menu.setUrl(response.encodeURL(menu.getUrl()));
        }

        // do all contained menus
        MenuComponent[] subMenus = menu.getMenuComponents();

        if (subMenus.length > 0) {
            for (int i = 0; i < subMenus.length; i++) {
                this.setPageLocation(subMenus[i]);
            }
        }
    }

    protected void setLocation(MenuComponent menu) throws MalformedURLException {
        // if the location attribute is null, then set it with a context relative page
        // attribute if it exists
        if (menu.getLocation() == null) {
            try {
                if (menu.getPage() != null) {
                    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                    if(menu.getPage().startsWith("http")){
                	menu.setLocation(menu.getPage());
                    }else{
                        menu.setLocation(request.getContextPath() + getPage(menu.getPage()));
                    }
                } else if (menu.getForward() != null) {
                    menu.setLocation(TagUtils.getInstance().computeURL(pageContext, menu.getForward(),
                            null, null, null, null, null, null, false));
//                    menu.setLocation(TagUtils.getInstance().computeURL(pageContext, menu.getForward(),
//                                        null, null, null, menu.getModule(), null, null, false));
                } else if (menu.getAction() != null) {
                    // generate Struts Action URL,
                    // this will append Context Path (if any),
                    // Servlet Mapping (path mapping or extension mapping)
                    // Module Prefix (if any) & Session ID (if any)
                    menu.setLocation(TagUtils.getInstance().computeURL(pageContext, null, null, null,
                                        menu.getAction(), null, null, null, false));
//                    menu.setLocation(TagUtils.getInstance().computeURL(pageContext, null, null, null,
//                            menu.getAction(), menu.getModule(), null, null, false));
                }
            } catch (NoClassDefFoundError e) {
                if (menu.getForward() != null) {
                    throw new MalformedURLException("forward '" + menu.getForward() + "' invalid - no struts.jar");
                } else if (menu.getAction() != null) {
                    throw new MalformedURLException("action '" + menu.getAction() + "' invalid - no struts.jar");
                }
            }
        }
    }

    /**
     * Returns the value with page prepended with a "/"
     * if it is not already.
     *
     * @param page The value for the page.
     */
    protected String getPage(String page) {
        if (page.startsWith("/")) {
            return page;
        } else {
            page = "/" + page;
        }

        return page;
    }
    
    private StringBuffer parseString(String str, HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        while (str.indexOf("${") >= 0) {
            sb.append(str.substring(0, str.indexOf("${")));

            String variable = str.substring(str.indexOf("${") + 2, str.indexOf("}"));
            String value = (String) pageContext.findAttribute(variable);

            if (value == null) {
                // look for it as a request parameter
                value = request.getParameter(variable);
            }

            // is value still null?!
            if (value == null) {
                log.warn("Value for '" + variable +
                        "' not found in pageContext or as a request parameter");
            }

            sb.append(value);
            str = str.substring(str.indexOf("}") + 1, str.length());
        }

        return sb.append(str);
    }

    public void release() {
        this.name = null;
        this.target = null;
    }
}
