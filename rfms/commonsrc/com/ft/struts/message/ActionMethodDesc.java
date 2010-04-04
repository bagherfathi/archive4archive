package com.ft.struts.message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;

public class ActionMethodDesc {
    private String name;
    private String input;
    private boolean validate;
    private String key;
    private String scope;
    private boolean showMessage = true;
    private List params = new ArrayList(2);
    private ActionMessageDesc messageDesc;
    private ActionMethodLoggerDesc loggerDesc;
    private String messageScope;
    private String httpMethods = "post";

    public void addParam(String param) {
        this.params.add(param);
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Object[] getMsgParams(final HttpServletRequest request) {
        Object[] objs = new Object[params.size()];
        int i = 0;

//        for (Iterator iter = params.iterator(); iter.hasNext(); i++) {
//            String element = (String) iter.next();
//            element = "${" + element + "}";
//            objs[i] =
//                ExpressionEvaluator.eval(
//                    element,
//                    new VariableResolverImpl() {
//                        public Object resolveVariable(String arg0)
//                            throws ELException {
//                            return request.getAttribute(arg0);
//                        }
//                    });
//        }

        return objs;
    }

    public ActionMessage getActionMessage(final HttpServletRequest request) {
        ActionMessage message = null;

        if (this.key != null) {
            Object[] msgParams = getMsgParams(request);
            message = new ActionMessage(key, msgParams);
        }

        return message;
    }

    public ActionMessageDesc getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(ActionMessageDesc messageDesc) {
        this.messageDesc = messageDesc;
    }

    public String getMessageScope() {
        return messageScope;
    }

    public void setMessageScope(String messageScope) {
        this.messageScope = messageScope;
    }

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public String getHttpMethods() {
        return httpMethods;
    }

    public void setHttpMethods(String httpMethods) {
        this.httpMethods = httpMethods;
    }

    public ActionMethodLoggerDesc getLoggerDesc() {
        return loggerDesc;
    }

    public void setLoggerDesc(ActionMethodLoggerDesc loggerDesc) {
        this.loggerDesc = loggerDesc;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
