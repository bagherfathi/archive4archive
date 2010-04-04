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
 * ����ʵ������Դ��
 */
public abstract class EntityAbstractDataSource implements EntityDataSource {
    protected EntityQuery queryHelper;
    protected List params = new ArrayList();
    protected List fetchModes = new ArrayList();

    /**
     * �õ�������ѯ������
     * @return
     */
    public List getFetchModes() {
        return fetchModes;
    }
    /**
     * ����������ѯ������
     * @param fetchModes
     */
    public void setFetchModes(List fetchModes) {
        this.fetchModes = fetchModes;
    }

    /**
     * �������ݵĲ����б�,�б��е�Ԫ��Ϊ EntityParam����
     * @return �����б�
     */
    public List getParams() {
        return params;
    }

    /**
     * ���ò����б�
     * @return 
     */
    public void setParams(List params) {
        this.params = params;
    }
    /**
     * ��ѯ��æ����
     * @return
     */
    public EntityQuery getQueryHelper() {
        return queryHelper;
    }

    public void setQueryHelper(EntityQuery queryHelper) {
        this.queryHelper = queryHelper;
    }

    /**
     * ���ݲ����б���ȡ����ֵ�Ķ�������
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
     * ���ݲ����б���ȡ�ж��ٸ��Ĳ���
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
