package com.ft.commons.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;


public class StringValidator {

    public static boolean validateMaxLength(Object bean, ValidatorAction va,
            Field field, ActionMessages errors, Validator validator,
            HttpServletRequest request) {

        String value = null;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }

        if (value != null) {
            try {
                int max = Integer.parseInt(field.getVarValue("maxlength"));

                if (value.getBytes().length > max) {
                    errors.add(field.getKey(), Resources.getActionMessage(
                            request, va, field));

                    return false;
                }
            } catch (Exception e) {
                errors.add(field.getKey(), Resources.getActionMessage(request,
                        va, field));
                return false;
            }
        }

        return true;
    }

    public static boolean validateMinLength(Object bean, ValidatorAction va,
            Field field, ActionMessages errors, Validator validator,
            HttpServletRequest request) {

        String value = null;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                int min = Integer.parseInt(field.getVarValue("minlength"));

                if (value.getBytes().length < min) {
                    errors.add(field.getKey(), Resources.getActionMessage(
                            request, va, field));

                    return false;
                }
            } catch (Exception e) {
                errors.add(field.getKey(), Resources.getActionMessage(request,
                        va, field));
                return false;
            }
        }

        return true;
    }

    /**
     * 验证字符串中是否包含中文
     * 
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param validator
     * @param request
     * @return
     */
    public static boolean includeChinese(Object bean, ValidatorAction va,
            Field field, ActionMessages errors, Validator validator,
            HttpServletRequest request) {
        String value = null;
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());
        }
        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                if (!value.matches("[^\u4e00-\u9fa5]+")) {
                    errors.add(field.getKey(), Resources.getActionMessage(
                            request, va, field));

                    return false;
                }
            } catch (Exception e) {
                errors.add(field.getKey(), Resources.getActionMessage(request,
                        va, field));
                return false;
            }
        }
        return true;
    }

    protected static boolean isString(Object o) {
        return (o == null) ? true : String.class.isInstance(o);
    }

}
