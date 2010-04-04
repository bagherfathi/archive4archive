package com.ft.webui.table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.callback.NullSafeBeanComparator;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;

import com.ft.commons.page.PageBean;
import com.ft.commons.web.SpringWebUtils;
import com.ft.hibernate.datasource.EntityDataSource;
import com.ft.hibernate.datasource.RequestParamsValueResolver;

/**
 * 使用Hibernate 得到表格中显示的数据
 */
public class HbiernateTableCallback implements TableCallback {
	/**
	 * 从Hibernate中查询出当前要显示的记录
	 * 
	 */
	public Collection retrieveRows(TableModel model) throws Exception {
        if (!(model.getContext().getContextObject() instanceof PageContext)) {
            throw new UnsupportedOperationException(
                "There is no context path associated with the request.");
        }

        Table table = model.getTableHandler().getTable();
        PageContext pageContext =
            (PageContext) model.getContext().getContextObject();
        String dataSource = (String) table.getItems();

        EntityDataSource aDataSource =
            (EntityDataSource) SpringWebUtils.getBean(
                pageContext, dataSource);
        PageBean aPageBean = this.limit2PageBean(model.getLimit());
        aPageBean.setPageSize(table.getRowsDisplayed());
      
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        RequestParamsValueResolver paramResolver =
            new RequestParamsValueResolver(request);
        List result = aDataSource.getResultSet(aPageBean, paramResolver);
        aPageBean.setRecordCount(aDataSource.count(paramResolver));
        model.getLimit()
             .setRowAttributes(
            aPageBean.getIntegerRecordCount(), aPageBean.getPageSize());
        model.getTableHandler()
             .setTotalRows(new Integer(aPageBean.getIntegerRecordCount()));
      //  model.setCollectionOfPageBeans(result);
      //  model.setCollectionOfBeans(result);
        //for (Iterator iter = result.iterator(); iter.hasNext();) {
        //    Object bean = (Object) iter.next();
        //    model.setCurrentRowBean(bean);
        //}
        
     
        return result;
    }
	/**
	 * 创建一个PageBean 对象用于翻页
	 * @param limit 
	 * @return 翻页对象
	 */
    private PageBean limit2PageBean(Limit limit) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(limit.getPage());

        return pageBean;
    }

    /**
     * 过滤记录，当前功没使用
     */
    public Collection filterRows(TableModel model, Collection rows)
        throws Exception {
        return rows;
    }
    /**
     * 对记录行进行排序
     * 
     */
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
                    Collections.sort((List) rows, new NullSafeBeanComparator(property, new NullComparator()));
                } else if (sortOrder.equals(TableConstants.SORT_DESC)) {
                    NullSafeBeanComparator reversedNaturalOrderBeanComparator = new NullSafeBeanComparator(property,
                            new ReverseComparator(new NullComparator()));
                    Collections.sort((List) rows, reversedNaturalOrderBeanComparator);
                }
            } catch (NoClassDefFoundError e) {
                String msg = "The column property [" + property + "] is nested and requires BeanUtils 1.7 or greater for proper sorting.";
                //logger.error(msg);
                throw new NoClassDefFoundError(msg); //just rethrow so it is not hidden
            }
        } else {
            if (sortOrder.equals(TableConstants.SORT_ASC)) {
                BeanComparator comparator = new BeanComparator(property, new NullComparator());
                Collections.sort((List) rows, comparator);
            } else if (sortOrder.equals(TableConstants.SORT_DESC)) {
                BeanComparator reversedNaturalOrderBeanComparator = new BeanComparator(property, new ReverseComparator(new NullComparator()));
                Collections.sort((List) rows, reversedNaturalOrderBeanComparator);
            }
        }
        return rows;
    }
}
