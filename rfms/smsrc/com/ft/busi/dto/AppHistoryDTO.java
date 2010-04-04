package com.ft.busi.dto;

import java.util.Hashtable;



/**
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AppHistoryDTO extends BaseDTO{

    /**
     * 需要记录历史的对象
     */
    Hashtable hashObj = new Hashtable();
    
    @SuppressWarnings("unchecked")
	public AppHistoryDTO appendObj(String id, Object obj)
    {
        this.hashObj.put(id,obj);
        return this;
    }
    
    public Object getObj(String id)
    {
        return hashObj.get(id);
    }
    
    public Hashtable getAllObject()
    {
        return this.hashObj;
    }

   
  

   

}
