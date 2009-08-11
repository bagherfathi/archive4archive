package com.renhenet.web;

import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.Config;
import com.renhenet.fw.orm.Persistent;
import com.renhenet.fw.waf.BasePOForm;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.po.Member;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
@SuppressWarnings("unchecked")
public abstract class DispatchActions extends DispatchAdminAction {

	public static final String LIST_FORWARD = "list";

	public static final String OWN_LIST_FORWARD = "ownList";

	public static final String UPDATE_FORWARD = "update";

	public static final String DETAIL_FORWARD = "detail";

	public static final String INSERT_FORWARD = "insert";

	public static final String RECYCLE_FORWARD = "recycle";

	public static final String DELETE_FORWARD = "delete";

	public static final String APPROVE_FORWARD = "approve";

	public static final String CUSTOM_FORWARD = null;

	public static final String INSERT_SUCCESS_FORWARD = "insertsuccess";

	public static final String FIELD_MEMBER_ID = "members.id";

	@Override
	public String listProcess(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);
		if (member == null) {
			return "/index.html";
		}
		SearchContext searchContext = getListSearchContext(context);
		if (searchContext == null) {
			searchContext = new SearchContext();
		}
		searchContext.setSearchClass(getActionClass());

		doSearch(context, searchContext);
		return LIST_FORWARD;
	}

	/**
	 * 做search
	 * 
	 * @param context
	 */
	protected abstract SearchContext getListSearchContext(WebContext context);

	@SuppressWarnings("unused")
	private SearchOption getPersonalSearchOption(WebContext context) {
		int memberId = WebHelper.getMemberIdContext(context);

		SearchOption option = new SearchOption(FIELD_MEMBER_ID, memberId,
				SearchOption.Option.eqaul);

		return option;
	}

	/**
	 * 得到action操作得class(PO)
	 * 
	 * @return
	 */
	protected abstract Class getActionClass();

	/**
	 * 做搜索
	 * 
	 * @param context
	 * @param orderBy
	 * @param searchContext
	 */
	private void doSearch(WebContext context, SearchContext searchContext) {
		int totalCount = getService().getBizObjCount(searchContext);
		if (totalCount > 0) {
			int startRow = WebHelper.getStartRow(context, totalCount);

			int ppc = getPerPageCount(context);
			String orderBy = WebHelper.delSearchOption(context, "orderBy");
			List list = getService().getBizObjList(searchContext, orderBy,
					startRow, ppc);

			Pagination pagination = new Pagination(totalCount, startRow, ppc);
			context.put("bizObjList", list);
			context.put("pagination", pagination);
		} else {
			context.put("searchNoResult", true);
		}
	}

	public String delSearchOption(WebContext context, String optionName) {
		String optionValue = context.getParameter(optionName);

		context.put(optionName, optionValue);
		return optionValue;
	}

	protected int getGetAllListMaxCount() {
		return Config.getInt("getall.maxcount", 500);
	}

	@SuppressWarnings("static-access")
	protected int getPerPageCount(WebContext context) {
		int ppc;
		if (context.getParameter("pageSize") != null
				&& StringUtils.isNumeric(context.getParameter("pageSize"))) {
			ppc = Integer.parseInt(context.getParameter("pageSize"));
			context.setClientValue("pageSize", ppc);
			context.put("pageSize", ppc);
		} else if (!StringUtils.isEmpty(context.getClientValue("pageSize"))
				&& StringUtils.isNumeric(context.getClientValue("pageSize"))) {
			ppc = Integer.parseInt(context.getClientValue("pageSize"));
		} else {
			ppc = this.perPage;
		}

		return ppc;
	}

	@Override
	public String updateProcess(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);
		if (member == null) {
			return "/index.html";
		}
		int id = context.getSIntParameter("sid");
		BasePOForm form = (BasePOForm) context.getForm();

		if (id <= 0) {
			id = form.getId();
		}
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {

			getService().updateObject(form, getActionClass());
			context.put("updated", true);
		}

		if (context.getParameter("insert") != null) {
			String fromUrl = context.getParameter("fromUrl");
			if (!StringUtils.isEmpty(fromUrl)) {
				return fromUrl;
			}
		}

		Persistent f = (Persistent) getService().getObjectById(
				getActionClass(), id);
		context.put("bizObj", f);
		return UPDATE_FORWARD;

	}

	@Override
	public String detailProcess(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);
		if (member == null) {
			return "/index.html";
		}
		int id = context.getSIntParameter("sid");
		Persistent f = this.getService()
				.getObjectByIdFull(getActionClass(), id);
		context.put("bizObj", f);
		return DETAIL_FORWARD;
	}

	@Override
	public String insertProcess(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);
		if (member == null) {
			return "/index.html";
		}
		String forword = INSERT_FORWARD;
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			BasePOForm form = (BasePOForm) context.getForm();

			int id = (Integer) getService()
					.insertObject(form, getActionClass());
			form.setId(id);
			context.put("inserted", true);

			context.put("insert", null);
			context.put("insert2", null);
		}
		if (context.getParameter("insert") != null) {
			String fromUrl = context.getParameter("fromUrl");
			if (!StringUtils.isEmpty(fromUrl)) {
				forword = fromUrl;
			} else {
				forword = INSERT_SUCCESS_FORWARD;
			}
		}

		return forword;
	}

	@Override
	public String deleteProcess(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);
		if (member == null) {
			return "/index.html";
		}
		int id = context.getSIntParameter("sid");
		CommonService commonService = getService();
		String re = DELETE_FORWARD;

		commonService.delObject(this.getActionClass(), id);
		context.put("delete", true);

		String fromUrl = context.getParameter("fromUrl");
		if (!StringUtils.isEmpty(fromUrl)) {
			return fromUrl;
		} else {
			return re;
		}

	}

	public String customActionsProcess(WebContext context)
			throws ServletException {
		return CUSTOM_FORWARD;
	}

	/**
	 * 得到service
	 * 
	 * @return
	 */
	protected abstract CommonService getService();
}
