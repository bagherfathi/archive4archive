package com.renhenet.web.root;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.BaseAction;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.ServiceManager;
import com.renhenet.modules.member.MemberService;
import com.renhenet.po.Member;
import com.renhenet.web.form.LoginForm;

/**
 * 前台个人用户登陆
 * 
 * @author luoxn
 * 
 */
public class WebLogin extends BaseAction {

	private static final MemberService memberService = ServiceManager
			.getMemberService();

	@Override
	public String process(WebContext context) throws ServletException {
		String re = DEFAULT_FORWARD;
		Member member = null;
		if (context.getParameter("login") != null) {
			LoginForm form = (LoginForm) context.getForm();
			member = memberService.getMemberByNameAndPwd(form.getLoginName(),
					form.getPassword(), "y");

			if (member != null) {
				context.setTempClientValue("member", member);
				context.setTempClientValue("memberId", member.getId());
				re = "/index.html";
			} else {
				re = "/login.html";
			}
		} else if (context.getParameter("logout") != null) {
			context.setTempClientValue("memberId", "");
			context.setTempClientValue("member", "");
			re = "/login.html";
		}
		return re;
	}
}
