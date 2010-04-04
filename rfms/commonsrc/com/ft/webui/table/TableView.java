package com.ft.webui.table;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.AbstractHtmlView;
import org.extremecomponents.table.view.DefaultToolbar;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.util.HtmlBuilder;

public class TableView extends AbstractHtmlView {
    protected void beforeBodyInternal(TableModel model) {
        // toolbar(getHtmlBuilder(), getTableModel());
        getTableBuilder().tableStart();

        getTableBuilder().theadStart();

        if (BuilderUtils.showTitle(model)) {
            getTableBuilder().titleRowSpanColumns();
        }

        // statusBar(getHtmlBuilder(), getTableModel());
        getTableBuilder().filterRow();

        getTableBuilder().headerRow();

        getTableBuilder().theadEnd();

        getTableBuilder().tbodyStart();
    }

    protected void afterBodyInternal(TableModel model) {
        getCalcBuilder().defaultCalcLayout();

        getTableBuilder().tbodyEnd();
        getTableBuilder().theadStart();

        statusBar(getHtmlBuilder(), getTableModel());
        // toolbar(getHtmlBuilder(), getTableModel());
        getTableBuilder().theadEnd();
        getTableBuilder().tableEnd();
    }

    protected void toolbar(HtmlBuilder html, TableModel model) {
        new DefaultToolbar(html, model).layout();
    }

    protected void statusBar(HtmlBuilder html, TableModel model) {
        new SimpleStatusBar(html, model).layout();
    }
}
