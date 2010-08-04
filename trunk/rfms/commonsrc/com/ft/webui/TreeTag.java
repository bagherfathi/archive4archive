package com.ft.webui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.commons.tree.BaseTreeVisitor;
import com.ft.commons.tree.TreeFilter;
import com.ft.commons.tree.TreeNode;
import com.ft.commons.tree.TreeTagNode;
import com.ft.commons.tree.TreeVisitor;
import com.ft.commons.tree.exception.FindNodeException;

public class TreeTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    public static final int COLLAPSE = 0;

    public static final int EXPAND = -2;

    private String closeFolderImg;// 关上时显示的图标

    private String openFolderImg;// 打开时显示的图标

    private String leafImg;// 页节点图标

    private boolean saveToCookie = false;

    private boolean useLine = true;

    private String root;// 要构建的树对象

    private int nodeId = 0;// 节点id

    private int indent = 3;// 缩进的格数

    private String id;// 遍历到的对象的变量名

    private String type;// 遍历到的对象的类型

    private String level;// 表示当前level的变量名

    private int extend = 0;// 展开到第几层

    private int createFromLevel = 0;// 从第几层开始构建树

    private int traverseLevel = -1;// 遍历的层数，-1表示所有

    private List nodeList = new ArrayList();

    private TreeFilter filter;// 过滤器

    private String cookieName = "ow_tree";// 使用的cookie名称

    private int index = 0;

    private int imgId = 0;

    private boolean expandOnServer = false;

    private String expandScript;// 通过server端展开的链接

    private String treeName = "";

    public int doStartTag() throws JspException {
		TreeNode root = (TreeNode) pageContext.findAttribute(this.root);
		
		
//    	Evaluator aEvaluator = new Evaluator();
//        TreeNode root = (TreeNode) aEvaluator.evaluate("root", this.root,
//                TreeNode.class, this, pageContext);
        if (root == null)
            throw new JspException("传入的tree为null，请检查！");
        if (expandOnServer) {
            this.setFilter(new TreeFilter() {

                public int check(TreeNode node) {
                    if (!node.isRoot()
                            && ((TreeNode) node.getParent()).getNodeStatus() == TreeTag.COLLAPSE) {
                        return TreeFilter.REMOVE;
                    } else {
                        return 0;
                    }
                }

            });
        }
        TreeVisitor v = new BaseTreeVisitor() {
            public void visit(TreeNode node) {
                if (currentLevel() < createFromLevel
                        || (traverseLevel != -1 && (currentLevel() - createFromLevel) > traverseLevel))
                    return;
                if (filter != null && filter.check(node) == TreeFilter.REMOVE) {
                    return;
                }
                TreeTagNode ttn = new TreeTagNode();
                ttn.setNode(node);
                ttn.setLevel(currentLevel() - createFromLevel);
                ttn.setLeaf(node.isLeaf());
                ttn.setExpand(node.getNodeStatus() == TreeTag.EXPAND);
                nodeList.add(ttn);
            }
        };
        try {
            root.traverseDepthFirst(v);
        } catch (FindNodeException e) {
            throw new JspException(e);
        }
        if (nodeList.size() > index) {
            TreeTagNode ttn = (TreeTagNode) nodeList.get(index++);
            TreeNode node = ttn.getNode();
            pageContext.setAttribute(id, node);
            if (level != null)
                pageContext.setAttribute(level, new Integer(ttn.getLevel()));

            // modified by libf,2006/9/5
            writeJavaScript(pageContext, treeControlScript());
            // writePrevious(pageContext, treeControlScript());
            // end
            writePrevious(pageContext, beforeBodyHtml(ttn));
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    public int doAfterBody() throws JspException {
        if (bodyContent != null) {
            writePrevious(pageContext, bodyContent.getString());
            bodyContent.clearBody();
        }
        writePrevious(pageContext, afterBodyHtml());

        if (nodeList.size() > index) {
            TreeTagNode ttn = (TreeTagNode) nodeList.get(index++);
            TreeNode node = ttn.getNode();
            pageContext.setAttribute(id, node);
            if (level != null)
                pageContext.setAttribute(level, new Integer(ttn.getLevel()));
            writePrevious(pageContext, beforeBodyHtml(ttn));
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    private boolean isMiddle(List nodes, int index) {
        if (index >= nodes.size() || index <= 1)
            return false;
        TreeTagNode ttn = (TreeTagNode) nodes.get(index - 1);
        for (int i = index; i < nodes.size(); i++) {
            TreeTagNode node = (TreeTagNode) nodes.get(i);
            if (!node.getNode().getKey().equals(ttn.getNode().getKey())) {
                if (ttn.getLevel() == node.getLevel()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean needLink(List nodeList, int level) {
        int temp = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            TreeTagNode node = (TreeTagNode) nodeList.get(i);
            if(node.getLevel() == level){
               temp = i +1; 
            }
        }
        if(this.index > temp){
            return false;
        }
        return true;
    }
    
    public int doEndTag() throws JspException {
        writePrevious(pageContext, endTagHtml());
        reset();
        return EVAL_PAGE;
    }

    public void release() {
        super.release();
        reset();
    }

    private void reset() {
        root = null;
        nodeId = 0;
        nodeList = new ArrayList();
        index = 0;
        imgId = 0;
        saveToCookie = true;
        level = null;
        extend = 0;
        traverseLevel = -1;
        createFromLevel = 0;
        filter = null;
    }

    private String beforeBodyHtml(TreeTagNode ttn) {
        StringBuffer html = new StringBuffer();
        int preLevel = 0;
        if (index > 1) {
            TreeTagNode preTTN = (TreeTagNode) nodeList.get(index - 2);
            preLevel = preTTN.getLevel();
        }
        for (int i = ttn.getLevel() - preLevel; i > 0; i--) {
            html.append("<tr ");
            if (ttn.getLevel() > extend && !expandOnServer)
                html.append("style = \"display:none\" ");
            html
                    .append("id = \"tr"
                            + getTreeName()
                            + +getNodeId()
                            + "\">\r\n<td nowrap class=\"td_node\">\r\n<table width = \"100%\" border = \"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
        }
        if (index == 1) {
            html
                    .append("<table width = \"100%\"  border = \"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
            html.append("<tr>\r\n<td nowrap class=\"td_node\">\r\n");
        } else {
            html.append("<tr>\r\n<td nowrap class=\"td_node\">\r\n");
            String contextPath = ((HttpServletRequest) pageContext.getRequest())
                    .getContextPath();

            for (int i = 1; i < ttn.getLevel(); i++) {
                if (this.needLink(this.nodeList, i)) {
                    html.append("<img align=\"absmiddle\" src = \"").append(contextPath);
                    html.append("/images/tree/line.gif\" />");
                }else{
                    for (int j = 0; j < 2; j++) {
                        html.append("&nbsp;&nbsp;");// 缩进
                    } 
                }
            }
            if (!this.isMiddle(this.nodeList, this.index)) {
                html.append("<img align=\"absmiddle\" src = \"").append(contextPath);
                html.append("/images/tree/joinbottom.gif\" />");
            } else {
                html.append("<img align=\"absmiddle\" src = \"").append(contextPath);
                html.append("/images/tree/join.gif\" />");
            }
        }

        if (!ttn.isLeaf()
                && (traverseLevel == -1 || ttn.getLevel() < traverseLevel)) {
            html.append("<img align=\"absmiddle\" src = \"");
            if (expandOnServer) {
                if (ttn.isExpand()) {
                    html.append(getOpenFolderImg());
                } else {
                    html.append(getCloseFolderImg());
                }
            } else {
                if (ttn.getLevel() >= extend)
                    html.append(getCloseFolderImg());
                else
                    html.append(getOpenFolderImg());
            }
            html.append("\" id = \"img" + getTreeName() + nextNodeId()
                    + "\" onclick = \"");
            if (expandOnServer) {
                if (ttn.isExpand()) {
                    html.append("javascript:onClick").append(getTreeName())
                            .append("Image(" + getNodeId() + ")");
                } else {
                    String script = expandScript.replaceAll("\\{nodeKey\\}",
                            ttn.getNode().getKey());
                    html.append("javascript:" + script + "");
                }
            } else {
                html.append("javascript:onClick").append(getTreeName()).append(
                        "Image(" + getNodeId() + ")");
            }
            html.append("\" nodeid=\"");
            if (expandOnServer) {
                html.append(ttn.getNode().getKey());
            } else {
                html.append(imgId++);
            }
            html.append("\">");
        } else {
            html.append("<img align=\"absmiddle\" src=\"").append(getLeafImg())
                    .append("\"/>");
        }
        return html.toString();
    }

    private String afterBodyHtml() {
        StringBuffer html = new StringBuffer();
        html.append("\r\n</td>\r\n</tr>\r\n");
        TreeTagNode nowTTN = new TreeTagNode();
        if (nodeList.size() > index)
            nowTTN = (TreeTagNode) nodeList.get(index);
        else
            nowTTN.setLevel(0);
        TreeTagNode preTTN = (TreeTagNode) nodeList.get(index - 1);
        for (int i = (preTTN.getLevel() - nowTTN.getLevel()); i > 0; i--) {
            html.append("</table>\r\n</td>\r\n</tr>\r\n");
        }
        return html.toString();
    }

    private String endTagHtml() {
        StringBuffer html = new StringBuffer();
        html.append("</table>\r\n");
        html
                .append("<input type = \"hidden\" name = \"maxNodeNo\" id = \"maxNodeNo\" value = \""
                        + getNodeId() + "\">\r\n");
        if (saveToCookie && !expandOnServer) {
            html.append("<script language = \"javascript\">\r\n");
            html.append("restoreFromCookie();\r\n");
            html.append("</script>\r\n");
        }
        if (expandOnServer) {
            html.append("<script language = \"javascript\">\r\n");
            html.append("saveToCookie();\r\n");
            html.append("</script>\r\n");
        }
        return html.toString();
    }

    private String treeControlScript() {
        StringBuffer html = new StringBuffer();
        // if (!expandOnServer) {
        html.append("<script language = \"javascript\">\r\n");
        html.append("<!--\r\n");
        html.append("function onClick").append(getTreeName()).append(
                "Image(id) {\r\n");
        // remove by zhouzhan 07/01/04 firefox和ie的获取dom对象的js一样 无需区分
        // html.append(" var browserName = navigator.appName;\r\n");
        // html.append(" var isIe = true;\r\n");
        html.append("	var targetStyle;\r\n");
        // html.append(" if (browserName == \"Netscape\") isIe = false;\r\n");
        html.append("	var trs, img, trDisplay;\r\n");
        // html.append(" if (isIe ) {\r\n");
        html.append("		trs	= eval(document.all[\"tr").append(getTreeName())
                .append("\"+id]);\r\n");
        html.append("		img	= eval(document.all[\"img").append(getTreeName())
                .append("\"+id]);\r\n");
        html.append("		trStyle	= \".style\";\r\n");
        // html.append(" } else {\r\n");
        // html.append(" trs =
        // eval(\"document.ids.tr").append(getTreeName()).append("\"+id);\r\n");
        // html.append(" img =
        // eval(\"document.ids.img").append(getTreeName()).append("\"+id);\r\n");
        // html.append(" trStyle = \"\";\r\n");
        // html.append(" }\r\n");
        html.append("	if (trs == null) return;\r\n");
        html.append("	if (trs.length == null) {\r\n");
        html.append("		targetStyle = eval(\"trs\"+trStyle);\r\n");
        html.append("		if (targetStyle.display == \"none\") {\r\n");
        html.append("			img.src = \"" + getOpenFolderImg() + "\";\r\n");
        html.append("			targetStyle.display = \"\";\r\n");
        html.append("		} else {\r\n");
        html.append("			img.src = \"" + getCloseFolderImg() + "\";\r\n");
        html.append("			targetStyle.display = \"none\";\r\n");
        html.append("		}\r\n");
        html.append("	} else {\r\n");
        html.append("		for (var i = 0; i < trs.length; i++) {\r\n");
        html.append("			targetStyle = eval(\"trs[i]\"+trStyle);\r\n");
        html.append("			if (targetStyle.display == \"none\") {\r\n");
        html.append("			    img.src = \"" + getOpenFolderImg() + "\";\r\n");
        html.append("				targetStyle.display = \"\";\r\n");
        html.append("			} else {\r\n");
        html.append("			    img.src = \"" + getCloseFolderImg() + "\";\r\n");
        html.append("				targetStyle.display	= \"none\";\r\n");
        html.append("			}\r\n");
        html.append("		}\r\n");
        html.append("	}\r\n");

        // modified by libf,2006/9/5
        if (this.saveToCookie) {
            html.append("	saveToCookie();\r\n");
        } else {
            html.append("\r\n");
        }
        // html.append(" saveToCookie();\r\n");
        // end modified
        html.append("}\r\n");
        html.append("var openFolderImg = \"" + getOpenFolderImg() + "\";\r\n");
        html.append("function saveToCookie() {\r\n");
        html.append("    if (!document.cookie) return;\r\n");
        html.append("    var cookie = \"").append(cookieName).append(
                "=a\";\r\n");
        html
                .append("    for (var i = 1; i <= parseInt(document.all.maxNodeNo.value); i++) {\r\n");
        html.append("        var tabNode = eval(\"document.all.img\"+i);\r\n");
        html
                .append("        if (tabNode.src.substring(tabNode.src.length - 13,tabNode.src.length) == \r\n");
        html
                .append("               openFolderImg.substring(openFolderImg.length - 13, openFolderImg.length)) {\r\n");
        html.append("           cookie = cookie + tabNode.nodeid +\"a\";\r\n");
        html.append("        }\r\n");
        html.append("    }\r\n");
        html.append("    document.cookie = cookie;\r\n");
        html.append("}\r\n");
        html.append("function restoreFromCookie() {\r\n");
        html.append("    var cookie = document.cookie;\r\n");
        html.append("    if (!cookie) return;\r\n");
        html
                .append("    var maxNodeNo = parseInt(document.all.maxNodeNo.value);\r\n");
        html
                .append("    for (var i = 1; i <= parseInt(document.all.maxNodeNo.value); i++) {\r\n");
        html.append("        var tabNode = eval(\"document.all.img\"+i);\r\n");
        html
                .append("        if (cookie.indexOf(\"a\" + tabNode.nodeid + \"a\") != -1) {\r\n");
        html.append("           onClick").append(getTreeName()).append(
                "Image(i);\r\n");
        html.append("        }\r\n");
        html.append("    }\r\n");
        html.append("}\r\n");
        html.append("-->\r\n");
        html.append("</script>\r\n");
        // }
        return html.toString();
    }

    private void writePrevious(PageContext pageContext, String text)
            throws JspException {
        JspWriter writer = pageContext.getOut();
        if (writer instanceof BodyContent)
            writer = ((BodyContent) writer).getEnclosingWriter();
        try {
            writer.print(text);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    // added by libf,2006/9/5
    private void writeJavaScript(PageContext pageContext, String text)
            throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(text);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    // end added

    public String getCloseFolderImg() {
        return closeFolderImg;
    }

    public void setCloseFolderImg(String closeFolderImg) {
        if (closeFolderImg.startsWith("/")) {
            closeFolderImg = ((HttpServletRequest) pageContext.getRequest())
                    .getContextPath()
                    + closeFolderImg;
        }
        this.closeFolderImg = closeFolderImg;
    }

    public String getOpenFolderImg() {
        return openFolderImg;
    }

    public void setOpenFolderImg(String openFolderImg) {
        if (openFolderImg.startsWith("/")) {
            openFolderImg = ((HttpServletRequest) pageContext.getRequest())
                    .getContextPath()
                    + openFolderImg;
        }
        this.openFolderImg = openFolderImg;
    }

    public String getLeafImg() {
        return leafImg;
    }

    public void setLeafImg(String leafImg) {
        if (leafImg.startsWith("/")) {
            leafImg = ((HttpServletRequest) pageContext.getRequest())
                    .getContextPath()
                    + leafImg;
        }
        this.leafImg = leafImg;
    }

    public boolean isUseLine() {
        return useLine;
    }

    public void setUseLine(boolean useLine) {
        this.useLine = useLine;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    private int nextNodeId() {
        return ++nodeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSaveToCookie() {
        return saveToCookie;
    }

    public void setSaveToCookie(boolean saveToCookie) {
        this.saveToCookie = saveToCookie;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getExtend() {
        return extend;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }

    public int getTraverseLevel() {
        return traverseLevel;
    }

    public void setTraverseLevel(int traverseLevel) {
        this.traverseLevel = traverseLevel;
    }

    public int getCreateFromLevel() {
        return createFromLevel;
    }

    public void setCreateFromLevel(int createFromLevel) {
        this.createFromLevel = createFromLevel;
    }

    public TreeFilter getFilter() {
        return filter;
    }

    public void setFilter(TreeFilter filter) {
        this.filter = filter;
    }

    public boolean isExpandOnServer() {
        return expandOnServer;
    }

    public void setExpandOnServer(boolean expandOnServer) {
        this.expandOnServer = expandOnServer;
    }

    public String getExpandScript() {
        return expandScript;
    }

    public void setExpandScript(String expandScript) {
        this.expandScript = expandScript;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

}
