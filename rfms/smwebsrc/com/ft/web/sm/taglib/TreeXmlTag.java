package com.ft.web.sm.taglib;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import com.ft.busi.sm.model.XmlTreeManager;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.commons.web.SpringWebUtils;
import com.ft.sm.dto.XmlTreeNode;
import com.ft.webui.context.WebUIContextImpl;

/**
 * 生成树的XML数据
 * 
 * @version 1.0
 * 
 */
public class TreeXmlTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String nodeId;

    private String beanName;

    private String childType; // 获取子节点类型

    public int doStartTag() throws JspException {
        boolean child = false;
        Evaluator aEvaluator = new Evaluator();
        String type_id = (String) aEvaluator.evaluate("type_id", this.nodeId,
                String.class, this, pageContext);
        String bean = (String) aEvaluator.evaluate("beanName", this.beanName,
                String.class, this, pageContext);

        try {
            childType = (String) aEvaluator.evaluate("childType",
                    this.childType, String.class, this, pageContext);
        } catch (Exception ex) {
            this.childType = "";
        }
        
        Long aId = new Long(0);
        String aType = "";
        if (type_id != "") {
            String[] temp = type_id.split("_");
            if (temp.length > 1) {
                aType = temp[0];
                aId = Long.valueOf(temp[1]);
            } else {
                aId = Long.valueOf(type_id);
            }

        }
        XmlTreeManager treeManager = (XmlTreeManager) SpringWebUtils.getBean(
                pageContext.getServletContext(), bean);
        HashMap<String,Object> params = new HashMap<String,Object>();
        XmlTreeNode root;

        if (aId.longValue() <= 0) { // 虚根节点小于零
            if (bean.equals("orgManager")) { // 组织特殊处理 根组织为当前登陆的组织
                root = OperatorSessionHelper
                        .getLoginOrg((HttpServletRequest) pageContext
                                .getRequest());

            } else {
                root = treeManager.findRootNode();
            }
            params.put("root", root);
            aId = root.getNodeId();

        } else {
            // root = treeManager.findNodeById(id);
            child = true;
        }
        List children;

        if (childType != null && childType.length() > 0) {
            children = treeManager.findNodeChildren(aId, childType);
        } else {
            children = treeManager.findNodeChildren(aId, aType);
        }

        params.put("type_id", type_id);
        params.put("children", children);
        params.put("child", new Boolean(child));

        StringWriter write = new StringWriter();
        WebUIContextImpl.getWebUIContext().getTemplateEngine().execute(params,
                "treeXml.vm", write);
        TagUtils.getInstance().write(pageContext, write.toString());
        return SKIP_BODY;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId
     *                the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the beanName
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * @param beanName
     *                the beanName to set
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * @return the childType
     */
    public String getChildType() {
        return childType;
    }

    /**
     * @param childType
     *                the childType to set
     */
    public void setChildType(String childType) {
        /*
         * try { Evaluator aEvaluator = new Evaluator(); childType = (String)
         * aEvaluator.evaluate("childType", this.childType, String.class, this,
         * pageContext); } catch (Exception ex) { this.childType = ""; }
         */
        this.childType = childType;
    }
}
