package com.ft.commons.tree;


public abstract class BaseTreeVisitor implements TreeVisitor {
    private int level = 0;

    public void visit(TreeNode node){
    }

    public void onAscent() {
        level--;
    }

    public void onDescent() {
        level++;
    }

    public final int currentLevel() {
        return level;
    }
}
