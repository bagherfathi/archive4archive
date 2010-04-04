package com.ft.common.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ft.busi.sm.model.ConfigParamManager;
import com.ft.common.exception.CommonRuntimeException;
import com.ft.sm.dto.ConfigParamDTO;
import com.ft.sm.entity.ConfigParam;
import com.ft.commons.tree.BaseTree;
import com.ft.commons.tree.BaseTreeNode;
import com.ft.commons.tree.TreeBuilder;
import com.ft.commons.tree.TreeNode;

/**
 * 配置参数树创建类
 * @spring.bean id = "configParamTreeBuilder"
 * 
 * @version 1.0
 */
public class ConfigParamTreeBuilder implements TreeBuilder {

    private ConfigParamManager paramManager;

    public TreeNode buildTreeNode(Map initParams) {
        BaseTree tree = new BaseTree();
        List params = null;
        try {
            params = paramManager.findAllParams(ConfigParamDTO.PARAM_TYPE_NODE);
        } catch (Exception ex) {
            throw new CommonRuntimeException("", ex);
        }
        ConfigParam root = findRoot(params);
        BaseTreeNode node = new BaseTreeNode(root.getConfigId() + "", root
                .getConfigCode());
        tree.setRoot(node);
        buildParamNode(node, findChild(root, params), params);
        return node;
    }

    private void buildParamNode(BaseTreeNode rootNode, List allChild,
            List allNode) {
        if (!allChild.isEmpty()) {
            for (Iterator iter = allChild.iterator(); iter.hasNext();) {
                ConfigParam param = (ConfigParam) iter.next();
                BaseTreeNode node = new BaseTreeNode(param.getConfigId() + "",
                        param.getConfigCode());
                rootNode.addChildLast(node);
                List childs = findChild(param, allNode);
                buildParamNode(node, childs, allNode);
            }
        }
    }

    private List findChild(ConfigParam param, List allParam) {
        List<ConfigParam> childs = new ArrayList<ConfigParam>();
        long id = param.getConfigId();
        for (Iterator iter = allParam.iterator(); iter.hasNext();) {
            ConfigParam child = (ConfigParam) iter.next();
            long cpid = child.getParentId();
            long cid = child.getConfigId();
            if (id == cpid && id != cid) {
                childs.add(child);
            }
        }
        return childs;
    }

    private ConfigParam findRoot(List params) {
        for (Iterator iter = params.iterator(); iter.hasNext();) {
            ConfigParam element = (ConfigParam) iter.next();
            if (element.getConfigId() == element.getParentId()) {
                return element;
            }
        }
        return null;
    }

    /**
     * @spring.property ref="configParamManager"
     * @param paramManager
     *                The paramManager to set.
     */
    public void setParamManager(ConfigParamManager paramManager) {
        this.paramManager = paramManager;
    }

}
