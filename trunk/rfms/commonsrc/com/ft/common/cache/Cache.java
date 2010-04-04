package com.ft.common.cache;

/**
 * ª∫¥ÊΩ”ø⁄°£
 * 
 * @author <a href="mailto:liuliang2@126.com">¡ı¡¡</a>
 * @version 1.0
 */
public interface Cache {
    /**
     * Get an item from the cache
     * @param key
     * @return the cached object or <tt>null</tt>
     * @throws CacheException
     */
    public Object get(Object key);
    
    /**
     * Add an item to the cache
     * @param key
     * @param value
     * @throws CacheException
     */
    public void put(Object key, Object value);
    
    /**
     * Add an item to the cache
     * @param key
     * @param value
     * @param group
     */
    public void put(Object key,Object value,String group);
    
    /**
     * Remove an item from the cache
     */
    public void remove(Object key);
    
    /**
     * Clear the cache
     */
    public void clear();
    
    /**
     * Clear the cache
     * @param group
     */
    public void clear(String group);
    
    /**
     * Clean up
     */
    public void destroy();
    
    /**
     * Set cache size in number of items
     * @param capacity
     */
    public void setCacheCapacity(int capacity);
}
