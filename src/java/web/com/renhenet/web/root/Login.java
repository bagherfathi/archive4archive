package com.renhenet.web.root;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.BaseAction;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.ServiceManager;
import com.renhenet.modules.member.MemberService;
import com.renhenet.po.Member;
import com.renhenet.web.form.LoginForm;

/**
 * ��̨�û���½
 * 
 * @author luoxn
 */
public class Login extends BaseAction {
	
	private static final MemberService memberService = ServiceManager
			.getMemberService();

	@Override
	public String process(WebContext context) throws ServletException {
		String password = context.getParameter("password");
		context.put("password", password);
		String re = DEFAULT_FORWARD;
		Member member = null;
		if (context.getParameter("login") != null) {
			LoginForm form = (LoginForm) context.getForm();
			member = memberService.getMemberByNameAndPwd(form.getLoginName(),
					form.getPassword(), "y");

			if (member != null) {
				context.setTempClientValue("member", member);
				context.setTempClientValue("memberId", member.getId());
				re = "/login.html";
			} else {
				re = "/index.html";
			}
		} else if (context.getParameter("logout") != null) {
			context.setTempClientValue("memberId", "");
			context.setTempClientValue("member", "");
			re = "/index.html";
		}
		return re;
	}
}
