package com.ft.commons.page;


public abstract class AbstractPageSet implements PageSet {
    /**
     * (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#hasNext().
     * @return DOCUMENT ME!
     */
    public boolean hasNext() {
        return this.getPageNo() < this.getCount();
    }

    
    /**
     * (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#hasPrevious().
     * @return DOCUMENT ME!
     */
    public boolean hasPrevious() {
        return this.getPageNo() > 1;
    }
}
