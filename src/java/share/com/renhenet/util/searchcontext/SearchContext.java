package com.renhenet.util.searchcontext;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SearchContext {

	private ArrayList<SearchOption> searchOptions = new ArrayList<SearchOption>();

	private ArrayList<SearchContext> searchContexts = new ArrayList<SearchContext>();

	private Class searchClass;

	private SearchCondition.Condition condition;

	private String orderByField;

	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public SearchContext() {

	}

	public SearchContext(SearchCondition.Condition condition) {
		this.condition = condition;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public void addOption(SearchOption option) {
		searchOptions.add(option);
	}

	public SearchOption getSearchOption(int i) {
		return searchOptions.get(i);
	}

	public void addSearchContext(SearchContext context) {
		searchContexts.add(context);
	}

	public SearchContext getSearchContext(int i) {
		return searchContexts.get(i);
	}

	public int getSearchOptionSize() {
		return searchOptions.size();
	}

	public int getSearchContextSize() {
		return searchContexts.size();
	}

	public SearchCondition.Condition getCondition() {
		return condition;
	}

	public void setCondition(SearchCondition.Condition condition) {
		this.condition = condition;
	}

	public Class getSearchClass() {
		return searchClass;
	}

	public void setSearchClass(Class searchClass) {
		this.searchClass = searchClass;
	}
}
