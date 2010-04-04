package com.ft.web.sm.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

/**
 * 公告标题换行
 * @version 1.0
 */
public class AfficheTitleTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String title;
    
    private int splitNum = 5;

    public int doStartTag() throws JspException {

        Evaluator aEvaluator = new Evaluator();
        String aTitle = (String) aEvaluator.evaluate("title", this.title,
                String.class, this, pageContext);
        StringBuffer buffer = new StringBuffer();
        if (aTitle.length() > this.getSplitNum()) {
            for (int i = 0; i < aTitle.length(); i = i + this.getSplitNum()) {
                if (i + this.getSplitNum() < aTitle.length()) {
                    if(i==0){
                        buffer.append(aTitle.substring(i, i + this.getSplitNum())).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");  
                    }else{
                        buffer.append(aTitle.substring(i, i + this.getSplitNum()+2)).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
                    }
                }else{
                    buffer.append(aTitle.substring(i,aTitle.length()));
                }
            }
        }else{
            buffer.append(aTitle);
        }
        try {
            this.pageContext.getOut().write(buffer.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the splitNum
     */
    public int getSplitNum() {
        return splitNum;
    }

    /**
     * @param splitNum the splitNum to set
     */
    public void setSplitNum(int splitNum) {
        this.splitNum = splitNum;
    }
    
    

}
