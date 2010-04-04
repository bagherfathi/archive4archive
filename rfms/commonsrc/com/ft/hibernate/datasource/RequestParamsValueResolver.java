package com.ft.hibernate.datasource;

import com.ft.commons.datetime.TimeSegment;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestParamsValueResolver implements ParamsValueResolver {
    final static Log log =
        LogFactory.getLog(RequestParamsValueResolver.class);
    protected HttpServletRequest request;

    public RequestParamsValueResolver(HttpServletRequest request) {
        this.request = request;
    }

    protected RequestParamsValueResolver() {
        super();
    }

    /*
     *  (non-Javadoc)
     * @see com.ft.hibernate.datasource.ParamsValueResolver#getParamValues(java.util.List)
     */
    public List getParamValues(List Params) {
        List result = new ArrayList();
        
        for (Iterator iter = Params.iterator(); iter.hasNext();) {
            EntityParam element = (EntityParam) iter.next();
            String paramName=element.getParamName();
            //如果没有paramName 则去name
            if(paramName==null){
            	paramName=element.getName();
            }
            if (element.isFixed()) {
                String value = element.getDefaultValue();
                element.setValue(value);
                result.add(element);
            } else if (element.getType().equals(Date.class)) {
                SimpleDateFormat dateFormat =
                    new SimpleDateFormat("yyyy-MM-dd");
                String strTime = request.getParameter(paramName);
                String strBeginTime =
                    request.getParameter(paramName + ".beginTime");
                String strEndTime =
                    request.getParameter(paramName + ".endTime");

                if (StringUtils.isNotEmpty(strTime)) {
                    TimeSegment timeSegment =
                        TimeSegment.getInstance(strTime);
                    element.setValue(timeSegment);
                    result.add(element);

                    continue;
                }

                if (
                    StringUtils.isNotEmpty(strBeginTime)
                        && StringUtils.isNotEmpty(strEndTime)) {
                    TimeSegment timeSegment = new TimeSegment();

                    try {
                        timeSegment.setBeginDate(
                            dateFormat.parse(strBeginTime));
                        timeSegment.setEndDate(dateFormat.parse(strEndTime));
                        element.setValue(timeSegment);
                        result.add(element);
                    } catch (ParseException e) {
                        log.error(e.getMessage(), e);
                    }
                } else if (StringUtils.isNotEmpty(element.getDefaultValue())) {
                    TimeSegment timeSegment =
                        TimeSegment.getInstance(element.getDefaultValue());
                    element.setValue(timeSegment);
                    result.add(element);
                }
            } else {
                Object object = request.getAttribute(paramName);
                String value = null;

                if (object != null) {
                    value = object.toString();
                } else {
                    value = request.getParameter(paramName);
                }

                if (StringUtils.isEmpty(value)) {
                    value = element.getDefaultValue();
                }

                if (StringUtils.isNotEmpty(value)) {
                    Object objValue =
                        ConvertUtils.convert(value, element.getType());
                    element.setValue(objValue);
                    result.add(element);
                }
            }
        }

        return result;
    }
}
