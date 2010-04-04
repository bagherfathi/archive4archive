package com.ft.hibernate.commons;


/**
 * @author 
 * 使用Count Group 的查询时返回的对象
 */
public class EntityGroupCount {
    private int count;
    private Object value;
    private String desc;

    public EntityGroupCount(int count, int value) {
        this.count = count;
        this.value = new Integer(value);
    }

    
    public EntityGroupCount(int count, String value) {
        this.count = count;
        this.value = value;
    }

    /**
     *
     * @return Returns the count.
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return Returns the desc.
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc The desc to set.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return Returns the value.
     */
    public Object getValue() {
        return value;
    }

    /**
     *
     * @param value The value to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
