package com.renhenet.modules;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.modules.member.MemberService;

public class ServiceManager {
	private static MemberService memberService = (MemberService) ServiceLocator
			.getService("memberService");

	public static MemberService getMemberService() {
		return memberService;
	}

	public static void setMemberService(MemberService memberService) {
		ServiceManager.memberService = memberService;
	}

}
