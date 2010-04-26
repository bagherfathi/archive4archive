package com.renhenet.web.member;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.ServiceManager;
import com.renhenet.modules.member.LogService;
import com.renhenet.modules.member.MemberService;
import com.renhenet.po.Log;
import com.renhenet.po.Member;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.form.MemberForm;

@SuppressWarnings("unchecked")
public class MemberAction extends DispatchActions {

	private static final MemberService memberService = ServiceManager
			.getMemberService();

	private static LogService logservice = (LogService) ServiceLocator
			.getService("logService");

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
		String forword = super.insertProcess(context);
		 
		if (context.getParameter("insert") != null || context.getParameter("insert2") != null) {
			Log log = new Log();
			int userId = (Integer.parseInt(context.getTempClientValue("memberId")));
			log.setUserId(userId);
			log.setSubject("添加管理员 Id："+form.getId());
			logservice.insertObject(log);
		}
		return forword;
	}

	public String updateProcess(WebContext context) throws ServletException {
		if (context.getParameter("insert") != null || context.getParameter("insert2") != null) {
			MemberForm form = (MemberForm) context.getForm();
			Log log = new Log();
			log.setSubject("修改管理员 Id:"+form.getId());
			int userId = (Integer.parseInt(context.getTempClientValue("memberId")));
			log.setUserId(userId);
			logservice.insertObject(log);
		}
		return super.updateProcess(context);
	}

	public String detailProcess(WebContext context) throws ServletException {
		MemberForm form = (MemberForm) context.getForm();
		Log log = new Log();
		log.setSubject("删除管理员 Id:"+form.getId());
		int userId = (Integer.parseInt(context.getTempClientValue("memberId")));
		log.setUserId(userId);
		logservice.insertObject(log);
		return super.deleteProcess(context);
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
