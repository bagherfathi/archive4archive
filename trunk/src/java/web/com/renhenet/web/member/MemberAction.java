package com.renhenet.web.member;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.ServiceManager;
import com.renhenet.modules.member.MemberService;
import com.renhenet.po.Member;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.form.MemberForm;
@SuppressWarnings("unchecked")
public class MemberAction extends DispatchActions {
	private static final MemberService memberService = ServiceManager
			.getMemberService();

	@Override
	protected Class getActionClass() {
		return Member.class;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();
		int role = context.getIntParameter("role");

		if (role >= 0) {
			searchContext.addOption(new SearchOption("role", role,
					SearchOption.Option.eqaul));
		}

		return searchContext;
	}

	public String insertProcess(WebContext context) throws ServletException {
		MemberForm form = (MemberForm) context.getForm();
		if (form != null) {
			Member meber = memberService.getMemberByName(form.getLoginName());
			if (meber != null) {
				context.put("nameExits", true);
				return "insert";
			}
		}
		return super.insertProcess(context);
	}

	@Override
	protected CommonService getService() {
		return memberService;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "member";
	}

}
