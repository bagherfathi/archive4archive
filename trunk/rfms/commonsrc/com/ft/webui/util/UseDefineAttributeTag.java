/*package com.ft.webui.util;

import org.apache.struts.taglib.tiles.util.TagUtils;
import org.apache.struts.tiles.ComponentDefinition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


*//**
 * @jsp.tag name="decode" display-name="ButtonTag" body-content="empty"
 *//*
public class UseDefineAttributeTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String name;
    private String attribute;
    private String scope;
    private String var;
    private String type;

     (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     
    public int doStartTag() throws JspException {
        if ((name != null) && !name.equals("")) {
            ComponentDefinition parentDef =
                TagUtils.getComponentDefinition(name, pageContext);
            Object obj = parentDef.getAttribute(attribute);

            if (obj != null) {
                this.pageContext.setAttribute(var, obj);
            }
        }

        return SKIP_BODY;
    }

    *//**
     * @return Returns the attribute.
     *//*
    public String getAttribute() {
        return attribute;
    }

    *//**
     * @jsp.attribute description="attribute"
     *                required="false" rtexprvalue="true"
     * @param attribute The attribute to set.
     *//*
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    *//**
     * @jsp.attribute description="name"
     *                required="false" rtexprvalue="true"
     * @return Returns the name.
     *//*
    public String getName() {
        return name;
    }

    *//**
     * @jsp.attribute description="name"
     *                required="false" rtexprvalue="true"
     * @param name The name to set.
     *//*
    public void setName(String name) {
        this.name = name;
    }

    *//**
     * @return Returns the scope.
     *//*
    public String getScope() {
        return scope;
    }

    *//**
     * @jsp.attribute description="scope"
     *                required="false" rtexprvalue="true"
     * @param scope The scope to set.
     *//*
    public void setScope(String scope) {
        this.scope = scope;
    }

    *//**
     * @return Returns the type.
     *//*
    public String getType() {
        return type;
    }

    *//**
     * @jsp.attribute description="type"
     *                required="false" rtexprvalue="true"
     * @param type The type to set.
     *//*
    public void setType(String type) {
        this.type = type;
    }

    *//**
     * @return Returns the var.
     *//*
    public String getVar() {
        return var;
    }

    *//**
     * @jsp.attribute description="var"
     *                required="false" rtexprvalue="true"
     * @param var The var to set.
     *//*
    public void setVar(String var) {
        this.var = var;
    }
}
*/