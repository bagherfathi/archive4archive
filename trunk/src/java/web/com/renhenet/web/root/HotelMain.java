package com.renhenet.web.root;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.BaseAction;
import com.renhenet.fw.waf.WebContext;

/**
 * ���˵�½��ҳ
 * 
 * @author luoxn
 */
public class HotelMain extends BaseAction {
	@Override
	public String process(WebContext context) throws ServletException {
		return "show";
	}
}
