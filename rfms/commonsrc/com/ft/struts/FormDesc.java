package com.ft.struts;

import java.util.ArrayList;
import java.util.List;

public class FormDesc {
    private String formName;
    private String scope;
    private List attrDescs = new ArrayList();

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void addAttrDesc(FormAttributeDesc obj) {
        this.attrDescs.add(obj);
    }

    public List getAttrDescs() {
        return this.attrDescs;
    }
}
