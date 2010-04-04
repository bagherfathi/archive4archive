package com.ft.hibernate.datasource;

import com.ft.commons.datetime.TimeSegment;

import com.ft.entity.EntityQuery;

import org.apache.commons.beanutils.ConstructorUtils;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * @author Coffee
 * 抽象实体数据源类
 */
public abstract class EntityAbstractDataSource implements EntityDataSource {
    protected EntityQuery queryHelper;
    protected List params = new ArrayList();
    protected List fetchModes = new ArrayList();

    /**
     * 得到批量查询的属性
     * @return
     */
    public List getFetchModes() {
        return fetchModes;
    }
    /**
     * 设置批量查询的属性
     * @param fetchModes
     */
    public void setFetchModes(List fetchModes) {
        this.fetchModes = fetchModes;
    }

    /**
     * 返回数据的参数列表,列表中的元素为 EntityParam对象
     * @return 参数列表
     */
    public List getParams() {
        return params;
    }

    /**
     * 设置参数列表
     * @return 
     */
    public void setParams(List params) {
        this.params = params;
    }
    /**
     * 查询帮忙对象
     * @return
     */
    public EntityQuery getQueryHelper() {
        return queryHelper;
    }

    public void setQueryHelper(EntityQuery queryHelper) {
        this.queryHelper = queryHelper;
    }

    /**
     * 根据参数列表，获取参数值的对象数组
     *
     * @param params
     *
     * @return
     */
    public Object[] getParamValues(List params) {
        Object[] result = new Object[this.getParamSize(params)];
        int i = 0;
        List<Object> list=new java.util.LinkedList<Object>();
        for (Iterator iter = params.iterator(); iter.hasNext(); i++) {
            EntityParam element = (EntityParam) iter.next();
            if(element.getType()==java.lang.Long.class && new Long(String.valueOf(element.getValue())).longValue()==-1){
        		continue;
        	}
            if (Date.class.equals(element.getType())) {
                TimeSegment timeSegment = (TimeSegment) element.getValue();
                result[i] = timeSegment.getBeginDate();
                result[++i] = timeSegment.getEndDate();
                list.add(timeSegment.getBeginDate());
                list.add(timeSegment.getEndDate());
                
            } else if ("like".equals(element.getExpr())) {
                result[i] = "%" + element.getValue() + "%";
                list.add(result[i]);
            } else {
            	
                try {
                    result[i] = ConstructorUtils.invokeConstructor(
                            element.getType(), element.getValue());
                    list.add(result[i]);
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        return list.toArray();
    }

    /**
     * 根据参数列表，获取有多少个的参数
     *
     * @param params
     *
     * @return
     */
    public int getParamSize(List params) {
        int result = 0;
        Iterator iter = params.iterator();

        for (int i = 0; iter.hasNext(); i++) {
            EntityParam element = (EntityParam) iter.next();

            if (Date.class.equals(element.getType())) {
                result = result + 2;
            } else {
                result++;
            }
        }

        return result;
    }
}
