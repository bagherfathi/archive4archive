package com.ft.commons.lookup;

import org.apache.struts.util.LabelValueBean;

import java.util.Iterator;
import java.util.List;


/**
 * 查询文本中的Bean
 *
 */
public class TextLookuper extends AbstractLookuper {
    private String source;

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSource() {
        return source;
    }

    /**
     * DOCUMENT ME!
     *
     * @param source DOCUMENT ME!
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public List getResult() {
        return LookupUtil.list(source);
    }

    /**
     * 根据value 值，在已有的集合中查找
     */
    public LabelValueBean lookup(Object value) {
        for (Iterator iter = this.getResult().iterator(); iter.hasNext();) {
            LabelValueBean bean = (LabelValueBean) iter.next();

            if (bean.getValue().equals(value.toString())) {
                return bean;
            }
        }

        return null;
    }
}
