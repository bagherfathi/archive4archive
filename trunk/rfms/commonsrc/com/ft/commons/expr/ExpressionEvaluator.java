package com.ft.commons.expr;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.springframework.beans.factory.BeanDefinitionStoreException;

/**
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 *
 */
public class ExpressionEvaluator {

	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	public static String evaluate(String param, Map map) {
		return parseStringValue(param, map);

	}

	protected static String parseStringValue(String strVal, Map props) {
		StringBuffer buf = new StringBuffer(strVal);
		int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
		while (startIndex != -1) {
			int endIndex = buf.toString().indexOf(DEFAULT_PLACEHOLDER_SUFFIX,
					startIndex + DEFAULT_PLACEHOLDER_PREFIX.length());
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex
						+ DEFAULT_PLACEHOLDER_PREFIX.length(), endIndex);
				String propVal = resolvePlaceholder(placeholder, props);
				if (propVal != null) {
					buf.replace(startIndex, endIndex
							+ DEFAULT_PLACEHOLDER_SUFFIX.length(), propVal);

					startIndex = buf.toString().indexOf(
							DEFAULT_PLACEHOLDER_PREFIX,
							startIndex + propVal.length());
				}

				else {
					throw new BeanDefinitionStoreException(
							"Could not resolve placeholder '" + placeholder
									+ "'");
				}
			} else {
				startIndex = -1;
			}
		}

		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	protected static String resolvePlaceholder(String param, Map props) {
		Expression aExpression;
		try {
			aExpression = ExpressionFactory.createExpression(param);
			JexlContext jc = JexlHelper.createContext();
			jc.getVars().putAll(props);
			Object o = aExpression.evaluate(jc);
			return o.toString();
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public static String eval(String path, VariableResolver resolver) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static String evaluate(String paramString, String name,
			String realPath) {
		Map map = new HashMap();
		map.put(name, realPath);
		return resolvePlaceholder(paramString, map);

	}

}
