package com.ft.webui;


/**
 * @jsp.tag name="processBar" display-name="ProcessBarTag" body-content="empty"
 *
 */
public class ProcessBarTag extends TemplateTagSupport {
    private static final long serialVersionUID = 1L;
    private long current;
    private long total;
    private String classId;

    public String getClassId() {
        return classId;
    }

    /**
    * @jsp.attribute description="classId"
    *   required="false" rtexprvalue="true"
    */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getVarName() {
        return "process";
    }

    public long getCurrent() {
        return current;
    }

    /**
    * @jsp.attribute description="current"
    *  required="false" rtexprvalue="true"
    */
    public void setCurrent(long current) {
        this.current = current;
    }

    public long getTotal() {
        return total;
    }

    /**
    * @jsp.attribute description="total"
    *  required="false" rtexprvalue="true"
    */
    public void setTotal(long total) {
        this.total = total;
    }

    public String getValue() {
        if (total != 0) {
            return "" + ((current * 100) / total) + "%";
        } else {
            return "0";
        }
    }
}
