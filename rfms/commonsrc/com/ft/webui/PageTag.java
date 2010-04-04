package com.ft.webui;

import com.ft.commons.page.PageUtils;

import org.apache.struts.taglib.TagUtils;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * @jsp.tag name="page" display-name="pageTag" body-content="JSP"
 *
 */
public class PageTag extends BodyTagSupport {
    private static final long serialVersionUID = 5579731135984949212L;
    private Object items;
    private String var;
    private int pageSize;

    public int doStartTag() throws JspException {
        List list = new ArrayList();

        if (items == null) {
            JspException e = new JspException("iterate.items");
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        // Construct an iterator for this items
        if (items.getClass().isArray()) {
            try {
                list = Arrays.asList((Object[]) items);
            } catch (ClassCastException e) {
                int length = Array.getLength(items);
                list = new ArrayList(length);

                for (int i = 0; i < length; i++) {
                    list.add(Array.get(items, i));
                }
            }
        } else if (items instanceof Collection) {
            list.addAll((Collection) items);
        } else {
            JspException e = new JspException("NOT ITEMS");
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        List value = PageUtils.getArrayPage(this.pageSize, list);
        pageContext.setAttribute(var, value);

        return SKIP_BODY;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
