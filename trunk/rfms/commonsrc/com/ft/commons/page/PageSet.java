package com.ft.commons.page;

import java.util.Collection;


/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface PageSet {
    /**
     * 是否有下一个页.
     *
     * @return
     */
    public boolean hasNext();

    /**
     * 是否有上一页.
     *
     * @return
     */
    public boolean hasPrevious();

    /**
     * 下走一页.
     *
     * @return
     */
    public Collection next();

    /**
     * 回走一页.
     *
     * @return
     */
    public Collection previous();

    /**
     * 总页数.
     *
     * @return
     */
    public int getCount();

    /**
     * 每页记录数.
     *
     * @return
     */
    public int getPageSize();

    /**
     * 跳转到第n页 ,如果PageNo数大于总页数会抛出.
     *
     * @param pageNo
     *
     * @return
     *
     * @throws java.lang.IllegalArgumentException
     */
    public Collection gotoPage(int pageNo);

    /**
     * 转到第一页.
     *
     * @return
     */
    public Collection first();

    /**
     * 转到最后一页.
     *
     * @return
     */
    public Collection last();

    /**
     * 页数
     *
     * @return
     */
    public int getPageNo();
}
