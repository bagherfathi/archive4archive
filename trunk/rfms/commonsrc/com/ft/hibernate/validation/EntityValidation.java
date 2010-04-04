package com.ft.hibernate.validation;

import java.util.List;

import com.ft.commons.web.SpringWebUtils;

import com.ft.entity.EntityQuery;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;

/**
 * 实体验证
 * 
 */
public class EntityValidation {
    /**
     * 验证类是否存在,存在时返回true
     * 
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param request
     * @return
     */
    public static boolean validateClassExist(Object bean, ValidatorAction va,
            Field field, ActionMessages errors, HttpServletRequest request) {
        try {
            Object entity = PropertyUtils.getProperty(bean, field.getKey());
            Class.forName((String) entity);
        } catch (Exception e) {
            errors.add(field.getKey(), Resources.getActionMessage(request, va,
                    field));

            return false;
        }

        return true;
    }

    /**
     * 根据实体的属性，验证是否存在有相同属性值的实体
     * 
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param validator
     * @param request
     * @return
     */
    public static boolean validationEntityExsit(Object bean,
            ValidatorAction va, Field field, ActionMessages errors,
            Validator validator, HttpServletRequest request) {
        String className = field.getVarValue("entityName");
        String attribute = field.getVarValue("attribute");
        String keyAttribute = field.getVarValue("keyName");
        Boolean historyed = Boolean.valueOf(field.getVarValue("historyed"));

        try {
            Class entityClass = Class.forName(className);
            Object obj = PropertyUtils.getProperty(bean, field.getProperty());
            Object otherId = getEntityIdByIdentityAttribute(request,
                    entityClass, keyAttribute, attribute, obj, historyed
                            .booleanValue());
            if (otherId == null) {
                return true;
            } else {
                String[] names = field.getProperty().split("\\.");
                Object dbKey = otherId;
                Object entityId = PropertyUtils.getProperty(bean, names[0]
                        + "." + keyAttribute);

                if (dbKey.equals(entityId)) {
                    return true;
                } else {
                    errors.add(field.getKey(), Resources.getActionMessage(
                            validator, request, va, field));

                    return false;
                }
            }
        } catch (Exception e) {
            errors.add(field.getKey(), Resources.getActionMessage(request, va,
                    field));

            return false;
        }
    }

    /**
     * 根据类的唯一属性查询对象的ID
     * 
     * @param entityClass
     *            查询对象
     * @param keyAttribute
     *            Id属性名称
     * @param attribue
     *            唯一属性名
     * @param obj
     *            唯一属性值
     * @param historyed
     *            是否记录历史
     * @return
     */
    public static Object getEntityIdByIdentityAttribute(
            HttpServletRequest request, Class entityClass, String keyAttribute,
            String attribute, Object obj, boolean historyed) {
        EntityQuery query = (EntityQuery) SpringWebUtils.getBean(request,
                "entityQueryHelper");
        StringBuffer sql = new StringBuffer("select ");
        sql.append(" entity.").append(keyAttribute);
        sql.append(" from ").append(entityClass.getName());
        sql.append(" entity where entity.").append(attribute).append("=?");
        if (historyed) {
            sql.append(" and entity.expireDate is null ");
        }
        List list = query.query(sql.toString(), new Object[] { obj });

        if (list.size() > 0) {
            return list.iterator().next();
        } else {
            return null;
        }
    }
}
