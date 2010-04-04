package com.ft.struts;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.struts.message.ActionFormFilterDefine;

public class ActionFormAttributeFilter {
	private Map map = new HashMap();
	
	public List getFilterAttribute(ActionMapping actionMapping,HttpServletRequest request){
		   
		String path = actionMapping.getPath()+"/"+ request.getParameter(actionMapping.getParameter());
		ActionFormFilterDefine define = (ActionFormFilterDefine) map.get(path);
		if(define !=null){
			return define.getAttributes();
		}else{
			return Collections.EMPTY_LIST;
		}
		
	}
}
