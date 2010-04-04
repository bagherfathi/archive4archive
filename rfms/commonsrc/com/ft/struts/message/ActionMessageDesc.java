package com.ft.struts.message;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Coffee
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionMessageDesc {
    private String path;
    private String scope = "request";
    private Map methods = new HashMap();
    private String text;
 

    public void addActionMethodDesc(ActionMethodDesc actionMethodDesc) {
        methods.put(actionMethodDesc.getName(), actionMethodDesc);
        actionMethodDesc.setMessageDesc(this);
    }

    public ActionMethodDesc getActionMethodDesc(String name) {
        return (ActionMethodDesc) methods.get(name);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
