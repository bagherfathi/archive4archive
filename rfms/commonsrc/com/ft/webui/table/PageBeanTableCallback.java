package com.ft.webui.table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.callback.NullSafeBeanComparator;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.limit.Sort;

import com.ft.commons.page.PageBean;

/**
 * 程序翻页Callback对象
 * 
 */
public class PageBeanTableCallback implements TableCallback {
	/**
	 * 得到显示的列表对象
	 */
	public Collection retrieveRows(TableModel model) throws Exception {

		PageBean aPageBean = (PageBean) model.getContext().getPageAttribute(
				"current_table_page_bean");
		Table table = model.getTableHandler().getTable();
		Collection result = RetrievalUtils.retrieveCollection(model.getContext(), table
				.getItems(), table.getScope());
		model.getLimit().setRowAttributes(aPageBean.getIntegerRecordCount(),
				aPageBean.getPageSize());
	    model.getTableHandler()
        .setTotalRows(new Integer(aPageBean.getIntegerRecordCount()));
		return result;
	}

	public Collection filterRows(TableModel model, Collection rows)
			throws Exception {
		return rows;
	}

	public Collection sortRows(TableModel model, Collection rows)
			throws Exception {
		boolean sorted = model.getLimit().isSorted();

		if (!sorted) {
			return rows;
		}

		Sort sort = model.getLimit().getSort();
		String property = sort.getProperty();
		String sortOrder = sort.getSortOrder();

		if (StringUtils.contains(property, ".")) {
			try {
				if (sortOrder.equals(TableConstants.SORT_ASC)) {
					Collections.sort((List) rows, new NullSafeBeanComparator(
							property, new NullComparator()));
				} else if (sortOrder.equals(TableConstants.SORT_DESC)) {
					NullSafeBeanComparator reversedNaturalOrderBeanComparator = new NullSafeBeanComparator(
							property, new ReverseComparator(
									new NullComparator()));
					Collections.sort((List) rows,
							reversedNaturalOrderBeanComparator);
				}
			} catch (NoClassDefFoundError e) {
				String msg = "The column property ["
						+ property
						+ "] is nested and requires BeanUtils 1.7 or greater for proper sorting.";
				// logger.error(msg);
				throw new NoClassDefFoundError(msg); // just rethrow so it is
				// not hidden
			}
		} else {
			if (sortOrder.equals(TableConstants.SORT_ASC)) {
				BeanComparator comparator = new BeanComparator(property,
						new NullComparator());
				Collections.sort((List) rows, comparator);
			} else if (sortOrder.equals(TableConstants.SORT_DESC)) {
				BeanComparator reversedNaturalOrderBeanComparator = new BeanComparator(
						property, new ReverseComparator(new NullComparator()));
				Collections.sort((List) rows,
						reversedNaturalOrderBeanComparator);
			}
		}
		return rows;
	}

}
