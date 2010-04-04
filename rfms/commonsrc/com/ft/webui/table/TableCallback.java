 
package com.ft.webui.table;

import org.extremecomponents.table.callback.FilterRowsCallback;
import org.extremecomponents.table.callback.RetrieveRowsCallback;
import org.extremecomponents.table.callback.SortRowsCallback;
 
public interface TableCallback extends RetrieveRowsCallback,
	FilterRowsCallback, SortRowsCallback {

}
