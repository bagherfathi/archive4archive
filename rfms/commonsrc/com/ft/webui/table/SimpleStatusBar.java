package com.ft.webui.table;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.table.view.html.TwoColumnRowLayout;

import org.extremecomponents.util.HtmlBuilder;


public class SimpleStatusBar extends TwoColumnRowLayout {
    public SimpleStatusBar(HtmlBuilder html, TableModel model) {
        super(html, model);
    }

    protected boolean showLayout(TableModel model) {
        boolean showStatusBar = BuilderUtils.showStatusBar(model);
        boolean filterable = BuilderUtils.filterable(model);

        if (!showStatusBar && !filterable) {
            return false;
        }

        return true;
    }

    protected void columnLeft(HtmlBuilder html, TableModel model) {
        boolean showStatusBar = BuilderUtils.showStatusBar(model);

        if (!showStatusBar) {
            return;
        }

        html.td(4).styleClass(BuilderConstants.STATUS_BAR_CSS).close();
        new PageNavBarBuilder(html, model).statusMessage();
        html.tdEnd();
    }

    protected void columnRight(HtmlBuilder html, TableModel model) {
    }
}
