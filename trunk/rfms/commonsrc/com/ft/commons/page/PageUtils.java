package com.ft.commons.page;

import java.util.ArrayList;
import java.util.List;


/**
 * ��ҳ����
 *
 */
public class PageUtils {
    /**
     * �õ�ҳ�ļ���
     *
     * @param pageSize
     * @param arr
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List getArrayPage(int pageSize, Object[] arr) {
        List result = new ArrayList();
        PageBean aPageBean = new PageBean(pageSize);
        aPageBean.setRecordCount(arr.length);

        for (int index = 1; index <= aPageBean.getPageCount(); index++) {
            aPageBean.setCurrentPage(index);

            int begin = aPageBean.getCurrentPageFirstRecord();
            Object[] ids = new Object[aPageBean.getPageSize()];
            System.arraycopy(
                arr, begin, ids, 0, aPageBean.getCurrentPageSize());
            result.add(ids);
        }

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param pageSize DOCUMENT ME!
     * @param list DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List getArrayPage(int pageSize, List list) {
        return getArrayPage(pageSize, list.toArray());
    }
}
