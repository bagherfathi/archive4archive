package com.ft.commons.page;

import java.util.Collection;
import java.util.List;

public class ListPageSet extends AbstractPageSet {
    private List list;

    /** DOCUMENT ME! */
    protected PageBean pageable;

    /** DOCUMENT ME! */
    protected int pageNo;
    private Collection current;

    /**
     * Creates a new ListPageSet object.
     *
     * @param list DOCUMENT ME!
     * @param pageSize DOCUMENT ME!
     */
    public ListPageSet(List list, int pageSize) {
        this.list = list;
        this.reset(pageSize);
    }

    /**
     * DOCUMENT ME!
     *
     * @param pageSize DOCUMENT ME!
     */
    public void reset(int pageSize) {
        this.pageable = PageBean.createPageBean(this.list.size(), pageSize);
        pageNo = 0;
    }

    private int getIndex() {
        if (pageNo == 0) {
            return 0;
        } else {
            return (pageNo - 1) * pageable.getPageSize();
        }
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#next()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Collection next() {
        if (!this.hasNext()) {
            throw new IllegalStateException();
        }

        return this.gotoPage(this.pageNo + 1);
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#previous()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Collection previous() {
        if (!this.hasPrevious()) {
            throw new IllegalStateException();
        }

        int begin = this.getIndex();

        return this.list.subList(begin, begin + pageable.getPageSize());
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#pageCount()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getCount() {
        return this.pageable.getPageCount();
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#pageSize()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getPageSize() {
        return this.pageable.getPageSize();
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#gotoPage(int)
     */

    /**
     * DOCUMENT ME!
     *
     * @param pageNo DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Collection gotoPage(int pageNo) {
        int retCount = pageable.getPageSize();

        if (pageNo == pageable.getPageCount()) {
            retCount = pageable.getLastPageSize();
        }

        int begin = this.getIndex();
        this.current = this.list.subList(begin, begin + retCount);
        this.pageNo = pageNo;

        return this.current;
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#first()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Collection first() {
        return this.next();
    }

    /* (non-Javadoc)
     * @see cn.cafesoft.commons.page.PageSet#last()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Collection last() {
        return this.gotoPage(this.pageable.getPageCount());
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * DOCUMENT ME!
     *
     * @param src DOCUMENT ME!
     * @param begin DOCUMENT ME!
     * @param size DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List getPage(List src, int begin, int size) {
        return src.subList(begin, size);
    }
}
