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
 * ʵ����֤
 * 
 */
public class EntityValidation {
    /**
     * ��֤���Ƿ����,����ʱ����true
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
     * ����ʵ������ԣ���֤�Ƿ��������ͬ����ֵ��ʵ��
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
     * �������Ψһ���Բ�ѯ�����ID
     * 
     * @param entityClass
     *            ��ѯ����
     * @param keyAttribute
     *            Id��������
     * @param attribue
     *            Ψһ������
     * @param obj
     *            Ψһ����ֵ
     * @param historyed
     *            �Ƿ��¼��ʷ
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
