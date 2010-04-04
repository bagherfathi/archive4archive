package com.ft.struts.message;

public class ActionMethodLoggerDesc {
    private String moduleName;
    private String act;
    private String attribute;

    public String getAct() {
        return act;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
