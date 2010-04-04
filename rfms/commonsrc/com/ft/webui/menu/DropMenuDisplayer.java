package com.ft.webui.menu;

import net.sf.navigator.displayer.MessageResourcesMenuDisplayer;
import net.sf.navigator.menu.MenuComponent;

import java.io.IOException;

import javax.servlet.jsp.JspException;


public class DropMenuDisplayer extends MessageResourcesMenuDisplayer {
    public void display(MenuComponent menu) throws JspException, IOException {
        displayComponents(menu, 0);
    }

    protected void displayComponents(MenuComponent menu, int level)
        throws JspException, IOException {
        String menuName = this.getMenuIndex(menu, level);
        String page = "";

        if (menu.getPage() != null) {
            page = menu.getPage();
        }

        MenuComponent[] components = menu.getMenuComponents();

        if (level != 0) {
            out.println(
                "eosmenu" + menuName + "=new Array(\"" + menu.getTitle()
                + "\",\"" + page + "\"" + ",\"" + components.length + "\""
                + ",\"\",\"\");");
        }

        for (int i = 0; i < components.length; i++) {
            displayComponents(components[i], level + 1);
        }
    }

    protected String getMenuIndex(MenuComponent menu, int level) {
        if (menu.getParent() == null) {
            return "";
        } else {
            int index = menu.getParent().getComponents().indexOf(menu) + 1;

            if (level == 1) {
                return this.getMenuIndex(menu.getParent(), level - 1) + index;
            } else {
                return this.getMenuIndex(menu.getParent(), level - 1) + "_"
                + index;
            }
        }
    }
}
