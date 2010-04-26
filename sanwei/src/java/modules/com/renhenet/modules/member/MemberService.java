package com.renhenet.modules.member;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Member;

public class MemberService extends CommonService {
	public Member getMemberById(int id) {
		return (Member) dao.load(Member.class, id);
	}

	public Member getMemberByNameAndPwd(String loginName, String pwd, int state) {
		String hql = "from Member where loginName=? and password=? and state = ?";
		return (Member) dao.findSingle(hql, new Object[] { loginName, pwd,
				state });
	}

	public Member getMemberByName(String loginName) {
		String hql = "from Member where loginName=?";
		return (Member) dao.findSingle(hql, new Object[] { loginName });
	}
}
