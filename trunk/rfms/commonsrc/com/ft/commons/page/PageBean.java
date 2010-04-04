package com.ft.commons.page;

import java.io.Serializable;


/**
 * ��ҳ����.
 */
public class PageBean implements Serializable {
    /** Comment for <code>serialVersionUID</code> .*/
    private static final long serialVersionUID = -8132783218617126174L;
    private int pageCount;
    private int pageSize = 30;
    private int lastPageSize;
    private long recordCount;
    private int currentPage = 1;

    /**
     * Creates a new PageBean object.
     */
    public PageBean() {
        super();
    }

    /**
     * Creates a new PageBean object.
     *
     * @param pageSize
     */
    public PageBean(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @param pageCount
     */
    protected void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @param recordCount
     */
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;

        if (this.pageSize != 0) {
            this.calculate();
        }
    }

    /**
     *
     * @return
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     *
     * @return
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * �����Ҫ����ҳ.
     */
    public void calculate() {
        this.pageCount = (int) recordCount / pageSize;

        this.lastPageSize = (int) this.recordCount % this.pageSize;

        if (this.lastPageSize == 0) {
            this.lastPageSize = this.pageSize;
        } else {
            this.pageCount++;
        }
    }

    /**
     * ����ҳ.
     *
     * @param recordCount
     * @param pageSize
     */
    public void setPage(long recordCount, int pageSize) {
        this.recordCount = recordCount;
        this.pageSize = pageSize;
        calculate();
    }

    /**
     * �����µ�ҳ.
     *
     * @param recordCount
     * @param pageSize
     *
     * @return
     */
    public static PageBean createPageBean(long recordCount, int pageSize) {
        PageBean bean = new PageBean();
        bean.setPage(recordCount, pageSize);

        return bean;
    }

    /**
     * �õ��ܹ��ж�������¼.
     *
     * @return
     */
    public long getRecordCount() {
        return ((pageCount - 1) * pageSize) + lastPageSize;
    }

    /**
     * ���һҳ�Ĵ�С.
     */
    public int getLastPageSize() {
        return this.lastPageSize;
    }

    /**
     *
     * @param lastPageSize
     */
    public void setLastPageSize(int lastPageSize) {
        this.lastPageSize = lastPageSize;
    }

    /**
     * �ܵ�ҳ��.
     *
     * @return
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * ��ǰ��ҳ��.
     *
     * @return
     */
    public int getCurrentPageSize() {
        if (this.pageCount == this.currentPage) {
            return this.getLastPageSize();
        } else {
            return this.pageSize;
        }
    }

    /**
     * �õ���ǰҳ�ĵ�һ����¼�ļ�¼��.
     *
     * @return
     */
    public int getCurrentPageFirstRecord() {
        return (this.currentPage - 1) * this.pageSize;
    }

    /**
     * ��һҳ.
     *
     * @return
     */
    public int getNextPageNo() {
        int result = this.currentPage + 1;

        if (result > this.pageCount) {
            result = this.pageCount;
        }

        return result;
    }

    /**
     * ��һҳ.
     *
     * @return
     */
    public int getPrivPageNo() {
        int result = this.currentPage - 1;

        if (result < 1) {
            result = 1;
        }

        return result;
    }

    /**
     * �Ƿ�����һҳ.
     *
     * @return
     */
    public boolean isNext() {
        return this.pageCount > this.currentPage;
    }

    /**
     * �Ƿ�����һҳ.
     *
     * @return
     */
    public boolean isPriv() {
        return this.currentPage > 1;
    }

    /**
     * �Ƿ������һҳ.
     *
     * @return
     */
    public boolean isLast() {
        return this.pageCount <= this.currentPage;
    }

    /**
     * �Ƿ��ǵ�һҳ.
     *
     * @return
     */
    public boolean isFirst() {
        return this.currentPage == 1;
    }

    /**
     * ���õ�ǰҳ.
     *
     * @return
     */
    public int[] getCurrentScope() {
        if (pageCount == 0) {
            return new int[] { 0, 0 };
        }

        int[] result = new int[2];

        if (this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }

        result[0] = (this.currentPage - 1) * pageSize;
        result[1] = result[0] + pageSize;

        if (this.currentPage == this.pageCount) {
            result[1] = result[0] + this.lastPageSize;
        }

        return result;
    }

    /**
     * �õ���¼��.
     *
     * @return PageBean.recordCount
     */
    public int getIntegerRecordCount() {
        return (int) this.recordCount;
    }
}
