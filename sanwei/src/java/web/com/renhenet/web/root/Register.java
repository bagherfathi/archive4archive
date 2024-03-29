package com.renhenet.web.root;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.member.MemberService;
import com.renhenet.po.Member;
import com.renhenet.web.WebAction;
import com.renhenet.web.form.MemberForm;

public class Register extends WebAction {
	private static MemberService service = (MemberService) ServiceLocator
			.getService("memberService");

	@Override
	public String webProcess(WebContext context, Member member)
			throws ServletException {
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			MemberForm form = (MemberForm) context.getForm();
			Member member1 = new Member();
			member1.setLoginName(form.getLoginName());
			member1.setPassword(form.getPassword());
			member1.setState(0);

			service.insertObject(member1);
			return "/index.html";

		}

		return "show";
	}
}
