package com.ft.commons.page;

import java.util.Collection;


/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface PageSet {
    /**
     * �Ƿ�����һ��ҳ.
     *
     * @return
     */
    public boolean hasNext();

    /**
     * �Ƿ�����һҳ.
     *
     * @return
     */
    public boolean hasPrevious();

    /**
     * ����һҳ.
     *
     * @return
     */
    public Collection next();

    /**
     * ����һҳ.
     *
     * @return
     */
    public Collection previous();

    /**
     * ��ҳ��.
     *
     * @return
     */
    public int getCount();

    /**
     * ÿҳ��¼��.
     *
     * @return
     */
    public int getPageSize();

    /**
     * ��ת����nҳ ,���PageNo��������ҳ�����׳�.
     *
     * @param pageNo
     *
     * @return
     *
     * @throws java.lang.IllegalArgumentException
     */
    public Collection gotoPage(int pageNo);

    /**
     * ת����һҳ.
     *
     * @return
     */
    public Collection first();

    /**
     * ת�����һҳ.
     *
     * @return
     */
    public Collection last();

    /**
     * ҳ��
     *
     * @return
     */
    public int getPageNo();
}
