package com.ft.webui;

import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;
import javax.servlet.jsp.tagext.TagData;

public class TreeTei extends TagExtraInfo {

    public VariableInfo[] getVariableInfo(TagData data) {
        VariableInfo[] variables = new VariableInfo[2];
        String id = data.getAttributeString("id");
        String type = data.getAttributeString("type");
        String level = data.getAttributeString("level");
        int counter = 0;

        if (id != null) {
            if (type == null) {
                type = "com.onewaveinc.framework.tree.TreeNode";
            }
            variables[counter++] = new VariableInfo(data.getAttributeString("id"), type, true, VariableInfo.NESTED);
        }

        if(level != null) {
            variables[counter++] = new VariableInfo(data.getAttributeString("level"), "java.lang.Integer", true, VariableInfo.NESTED);
        }

        /* create returning array, and copy results */
        VariableInfo[] result;
        if (counter > 0) {
            result = new VariableInfo[counter];
            System.arraycopy(variables, 0, result, 0, counter);
        } else {
            result = new VariableInfo[0];
        }
        return result;
    }
}

