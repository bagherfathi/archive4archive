package com.renhenet.web.quanzong;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.QuanzongService;
import com.renhenet.po.Quanzong;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.form.QuanzongForm;

public class QuanzongAction extends DispatchActions {
	private static QuanzongService service = (QuanzongService) ServiceLocator
			.getService("quanzongService");

	@Override
	protected Class getActionClass() {
		return Quanzong.class;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		return searchContext;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int type = context.getIntParameter("type");
		context.put("type", type);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			QuanzongForm form = (QuanzongForm) context.getForm();
			List<Quanzong> quanzongList = service.getQuanzong();
			if (quanzongList != null && quanzongList.size() > 0) {
				if (form.getType() == 0) {
					for (int i = 0; i < quanzongList.size(); i++) {
						service.delObject(Quanzong.class, quanzongList.get(i)
								.getId());
						return "insertsuccess";
					}
				} else if (form.getType() == 1) {
					for (int i = 0; i < quanzongList.size(); i++) {
						service.delObject(Quanzong.class, quanzongList.get(i)
								.getId());
					}
				} else if (form.getType() == 2) {
					for (int i = 0; i < quanzongList.size(); i++) {
						if (quanzongList.get(i).getType() != 2) {
							service.delObject(Quanzong.class, quanzongList.get(
									i).getId());
						}
					}
					
					
				}
			}
		}
		
		if(type==2){
			List<Quanzong> quanzongList = service.getQuanzongByType(type);
			context.put("quanzongList", quanzongList);
		}

		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		return super.deleteProcess(context);
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "quanzong";
	}
}
