package com.ft.webui;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

/**
 * @jsp.tag name="parseParam" display-name="ParseValueParamTag" body-content="empty"
 */
public class ParseValueParamTag extends TagSupport {
    private static final long serialVersionUID = -3244385208736494626L;
    private String name = null;
    private String type = null;
    private Object value = null;

    public ParseValueParamTag()
    {
    }

    public void release()
    {
        super.release();
        name = null;
        type = null;
        value = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public int doEndTag()
        throws JspException
    {
        ParseValueTag tag = (ParseValueTag)findAncestorWithClass(this, com.ft.webui.ParseValueTag.class);
        if(tag == null)
            return 6;
        Evaluator Evalutator = new Evaluator();
        try
        {
            value = (String)Evalutator.evaluate("value", value.toString(), java.lang.String.class, this, pageContext);
        }
        catch(JspException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            if(getType().endsWith("java.lang.Class"))
            {
                Class aClass = Class.forName(value.toString());
                if(tag.params == null)
                    tag.setParams(new ArrayList());
                tag.params.add(aClass);
            } else
            {
                Object objValue = ConvertUtils.convert(value.toString(), Class.forName(getType()));
                if(tag.params == null)
                    tag.setParams(new ArrayList());
                tag.params.add(objValue);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        release();
        return 6;
    }
}
