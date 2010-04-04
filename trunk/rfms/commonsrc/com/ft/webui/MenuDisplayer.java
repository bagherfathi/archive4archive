package com.ft.webui;

import net.sf.navigator.menu.MenuComponent;

import org.apache.velocity.VelocityContext;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class MenuDisplayer extends TemplateDisplayer {
    public static final String FORWARD_REQUEST_URI_ATTR =
        "javax.servlet.forward.request_uri";
    public static final String FORWARD_PATH_INFO_ATTR = "xx";

    public static boolean matchURL(String url, String href) {
        return url.endsWith(href);
    }

    protected void getOther(VelocityContext context, MenuComponent menu) {
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        MenuComponent selectedMenu = this.findSelectedMenu(request, menu);

        if (selectedMenu != null) {
            context.put("selectedMenu", selectedMenu);
        }
    }

    protected MenuComponent findSelectedMenu(
        HttpServletRequest request, MenuComponent menu) {
        String forwardURI =
            (String) request.getAttribute(FORWARD_REQUEST_URI_ATTR);
        String contextPath = request.getContextPath();

        if (forwardURI.indexOf(contextPath) == 0) {
            forwardURI = forwardURI.substring(contextPath.length());
        }

        //String actionName = (String) request.getAttribute(FORWARD_PATH_INFO_ATTR);
        MenuComponent result = selectMenu(forwardURI, menu);

        if ((result == null) && (menu.getComponents().size() > 0)) {
            result = (MenuComponent) menu.getComponents().get(0);
        }

        return result;
    }

    protected MenuComponent selectMenu(String url, MenuComponent menu) {
        MenuComponent result = null;
        List list = menu.getComponents();

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            MenuComponent element = (MenuComponent) iter.next();

            //this.log.info("url " + element.getLocation());
            if (url.equals(element.getLocation())) {
                result = element;

                break;
            } else {
                result = selectMenu(url, element);
            }
        }

        return result;
    }
}
