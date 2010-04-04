package com.ft.struts;

import net.sf.navigator.menu.MenuComponent;


/**
 * 
 * Ê÷½áµã
 */
public class EntityMenuComponent extends MenuComponent{

	private static final long serialVersionUID = 3209456162837046403L;
	private Object entity;
	private String menuId;
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
