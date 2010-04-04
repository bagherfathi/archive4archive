package com.ft.busi.dto;

import com.ft.sm.entity.App;
import com.ft.sm.entity.AppType;

/**
 * @version 1.0
 */
public class AppAndAppTypeDTO extends AppDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730433641796626592L;
	/**
	 * 受理类型
	 */
	private AppType appType = null;

	public AppAndAppTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppAndAppTypeDTO(App app,AppType appType) {
		super(app);
		this.appType=appType;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the appType.
	 */
	public AppType getAppType() {
		return appType;
	}

	/**
	 * @param appType The appType to set.
	 */
	public void setAppType(AppType appType) {
		this.appType = appType;
	}

}
