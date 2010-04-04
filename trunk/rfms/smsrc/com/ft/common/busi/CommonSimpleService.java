package com.ft.common.busi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author soler
 *
 *@spring.bean id="commonSimpleService"
 */
public class CommonSimpleService extends BaseServiceImpl implements BaseService {

	 

	public List getBizObjList(SearchContext searchContext,
			String orderBy,String hqlTableName) {
		return this.getBizObjList(searchContext, orderBy, 0, 0,hqlTableName);

	}

	public List getBizObjList(SearchContext searchContext,
			String orderBy, int startRows, int rows,String hqlTableName) {
		StringBuffer query = new StringBuffer();
		query.append("select t from ").append(hqlTableName).append(
				" as t ");
		List args = new ArrayList();
		StringBuffer where = new StringBuffer();
		getWhereHql(searchContext, args, where);
		query.append(where);

		if (StringUtils.isEmpty(orderBy)) {
			orderBy = searchContext.getOrderBy();
		}

		if (StringUtils.isEmpty(orderBy)) {
			orderBy = "desc";
		}

		String orderByField = "id";
		if (!StringUtils.isEmpty(searchContext.getOrderByField())) {
			orderByField = searchContext.getOrderByField();
		}
		query.append(" order by t.");
		query.append(orderByField);
		query.append(" ");
		query.append(orderBy);
		List fList = baseDao.query(query.toString(), args.toArray(),
				startRows, rows);
		return fList;

	}

	public int getBizObjCount(SearchContext searchContext,String hqlTableName) {
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from ").append(hqlTableName).append(
				" as t ");
		List args = new ArrayList();
		StringBuffer where = new StringBuffer();
		getWhereHql(searchContext, args, where);
		query.append(where);
		return baseDao.getCount(query.toString(), args.toArray());
	}

	/**
	 * 有SearchContext build search的hql
	 * 
	 * @param searchContext
	 * @param args
	 * @return
	 */
	private void getWhereHql(SearchContext searchContext, List args,
			StringBuffer where) {

		int ll = searchContext.getSearchContextSize();
		int l = searchContext.getSearchOptionSize();
		if ((l > 0 || ll > 0)) {
			if (where.length() <= 0) {
				where.append(" where");
			} else {
				if (!where.toString().endsWith("where")) {
					where.append(getAndOrBySearchConditon(searchContext
							.getCondition()));
				}
			}
		}

		if (l > 0) {

			// 处理不在searchcontext里的searchoption
			if (where.toString().trim().endsWith(")")) {
				where.append(" and ");
			}
			where.append("(");
			StringBuffer where1 = new StringBuffer();

			for (int i = 0; i < l; i++) {
				SearchOption searchOption = searchContext.getSearchOption(i);
				getHqlBySearchOption(args, where1, searchOption);
			}
			where.append(where1);
			where.append(")");
		}

		if (ll > 0) {
			for (int i = 0; i < ll; i++) {
				SearchContext context = searchContext.getSearchContext(i);
				getWhereHql(context, args, where);
			}
		}
	}

	protected String getAndOrBySearchConditon(
			SearchCondition.Condition condition) {
		String str = "";
		if (condition != null) {

			switch (condition) {
			case and:
				str = " and ";
				break;
			case or:
				str = " or ";
				break;
			}
		}

		return str;
	}

	@SuppressWarnings("unchecked")
	private void getHqlBySearchOption(List args, StringBuffer where,
			SearchOption searchOption) {
		// andOrWhere(where, searchOption.getCondition());
		if (where.length() > 0) {
			where.append(getAndOrBySearchConditon(searchOption.getCondition()));
		}
		where.append(" t.").append(searchOption.getFieldName());

		if (StringUtils.isEmpty(searchOption.getFieldName2())) {
			switch (searchOption.getOption()) {
			case eqaul:
				where.append(" = ? ");
				break;
			case noteqaul:
				where.append(" != ? ");
				break;
			case in:
				where.append(" in(" + searchOption.getValue() + ") ");
				break;
			case like:
				where.append(" like ? ");
				break;
			case bigthan:
				where.append(" > ? ");
				break;
			case smallthan:
				where.append(" < ? ");
				break;
			case bigthanandequal:
				where.append(" >= ? ");
				break;
			case smallthanandeqaul:
				where.append(" <= ? ");
				break;
			case isnull:
				where.append(" is null ");
				break;
			case isnotnull:
				where.append(" is not null ");
				break;
			}

			if (searchOption.getOption() != SearchOption.Option.in
					&& searchOption.getOption() != SearchOption.Option.isnotnull
					&& searchOption.getOption() != SearchOption.Option.isnull) {
				args.add(searchOption.getValue());
			}
		} else {
			switch (searchOption.getOption()) {
			case eqaul:
				where.append(" = ");
				break;
			case noteqaul:
				where.append(" != ");
				break;
			case bigthan:
				where.append(" >  ");
				break;
			case smallthan:
				where.append(" < ");
				break;
			case bigthanandequal:
				where.append(" >= ");
				break;
			case smallthanandeqaul:
				where.append(" <= ");
				break;
			}
			where.append(" t.").append(searchOption.getFieldName2());
		}

	}

 
	/**
	 * 从poform做update
	 * 
	 * @param form
	 * @return
	 */
	public void updateObject(BaseEntity obj) {
		baseDao.update(obj);
	}

 

	public Object insertObject(Object p) {
		baseDao.save(p);
		return p;
	}

	protected void AndOrWhere(StringBuffer where) {
		if (!StringUtils.isEmpty(where.toString())) {
			where.append(" and ");
		} else {
			where.append(" where ");
		}
	}

	protected class Friends {
		private int friendCount;

		private String friendIds;

		public int getFriendCount() {
			return friendCount;
		}

		public void setFriendCount(int friendCount) {
			this.friendCount = friendCount;
		}

		public String getFriendIds() {
			return friendIds;
		}

		public void setFriendIds(String friendIds) {
			this.friendIds = friendIds;
		}
	}


}
