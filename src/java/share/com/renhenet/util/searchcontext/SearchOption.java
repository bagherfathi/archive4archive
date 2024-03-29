package com.renhenet.util.searchcontext;

public class SearchOption {

	private String fieldName;

	private String fieldName2;

	private Object value;

	private Option option;

	private SearchCondition.Condition condition;

	public static enum Option {
		eqaul, noteqaul, in, like, bigthan, smallthan, bigthanandequal, smallthanandeqaul, isnull, isnotnull
	}

	public SearchOption(String fieldName, Object value, Option option,
			SearchCondition.Condition condition) {
		this.fieldName = fieldName;
		this.value = value;
		this.option = option;
		this.condition = condition;
	}

	public SearchOption(String fieldName, Object value, Option option) {
		this(fieldName, value, option, SearchCondition.Condition.and);
	}

	public SearchOption(String fieldName, String value) {
		this(fieldName, value, Option.eqaul, SearchCondition.Condition.and);
	}

	public static SearchOption getTwoFieldsSearchOption(String fieldName,
			String fieldName2, Option option,
			SearchCondition.Condition condition) {
		SearchOption o = new SearchOption();
		o.setFieldName(fieldName);
		o.setFieldName2(fieldName2);
		o.setOption(option);
		o.setCondition(condition);
		return o;
	}

	public static SearchOption getTwoFieldsSearchOption(String fieldName,
			String fieldName2, Option option) {
		return getTwoFieldsSearchOption(fieldName, fieldName2, option,
				SearchCondition.Condition.and);
	}

	private SearchOption() {
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public SearchCondition.Condition getCondition() {
		return condition;
	}

	public void setCondition(SearchCondition.Condition condition) {
		this.condition = condition;
	}

	public static void main(String[] args) {
		System.err.println("aa " + Option.eqaul);
	}

	public String getFieldName2() {
		return fieldName2;
	}

	public void setFieldName2(String fieldName2) {
		this.fieldName2 = fieldName2;
	}

}
