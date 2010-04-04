package com.ft.webui.table;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.TableActions;
import org.extremecomponents.util.HtmlBuilder;

import com.ft.commons.page.PageBean;
import com.ft.webui.context.WebUIContextImpl;


public class PageNavBarBuilder {
    private HtmlBuilder html;
    private TableModel model;

    public PageNavBarBuilder(TableModel model) {
        this(new HtmlBuilder(), model);
    }

    public PageNavBarBuilder(HtmlBuilder html, TableModel model) {
        this.html = html;
        this.model = model;
    }

    public HtmlBuilder getHtmlBuilder() {
        return html;
    }

    protected TableModel getTableModel() {
        return model;
    }

    public PageBean getPageBean() {
        Table table = model.getTableHandler().getTable();
        PageBean pageBean = new PageBean(table.getRowsDisplayed());
        pageBean.setRecordCount(model.getLimit().getTotalRows());
        pageBean.setCurrentPage(model.getLimit().getPage());

        return pageBean;
    }

    public void statusMessage() {
        
        boolean showPagination = BuilderUtils.showPagination(model);
        boolean showExports = BuilderUtils.showExports(model);
        if (model.getLimit().getTotalRows() == 0) {
            html.append(
                model.getMessages()
                     .getMessage(BuilderConstants.STATUSBAR_NO_RESULTS_FOUND));
        } else {
            StringWriter writer = new StringWriter();
            TableActions tableAction = new TableActions(model);
            String exportLink ="";
            if (showExports) {
                Iterator iterator =
                    model.getExportHandler().getExports().iterator();
                for (Iterator iter = iterator; iter.hasNext();) {
                    Export export = (Export) iter.next();
                    exportLink=tableAction.getExportAction(export.getView(),export.getFileName());
                }
            }
          
            HashMap params = new HashMap();
            params.put("pageBean",this.getPageBean());
            params.put("pageAction",tableAction);
            params.put("showPagination",new Boolean(showPagination));
            params.put("exportLink",exportLink);
            params.put("formName",model.getTableHandler().getTable().getTableId());
            WebUIContextImpl.getWebUIContext().getTemplateEngine()
                            .execute(
                params, "pageNav.vm", writer);
            html.append(writer.toString());
            Table table = model.getTableHandler().getTable();
            this.model.getContext().setSessionAttribute("currentPage_" + table.getTableId(), ""+this.getPageBean().getCurrentPage());
        }
    }

    public String toString() {
        return html.toString();
    }
}
