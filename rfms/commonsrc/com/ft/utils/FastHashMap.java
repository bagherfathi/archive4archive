
package com.ft.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public final class FastHashMap implements Map, Serializable {

	// ----------------------------------------------------------- Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = -4685761626584668475L;

	/**
	 * Construct a an empty map.
	 */
	public FastHashMap() {

		super();
		this.map = new HashMap();

	}

	/**
	 * Construct an empty map with the specified capacity.
	 *
	 * @param capacity The initial capacity of the empty map
	 */
	public FastHashMap(int capacity) {

		super();
		this.map = new HashMap(capacity);

	}

	/**
	 * Construct an empty map with the specified capacity and load factor.
	 *
	 * @param capacity The initial capacity of the empty map
	 * @param factor The load factor of the new map
	 */
	public FastHashMap(int capacity, float factor) {

		super();
		this.map = new HashMap(capacity, factor);

	}

	/**
	 * Construct a new map with the same mappings as the specified map.
	 *
	 * @param map The map whose mappings are to be copied
	 */
	public FastHashMap(Map<Object,Object> map) {

		super();
		this.map = new HashMap<Object,Object>(map);

	}

	// ----------------------------------------------------- Instance Variables

	/**
	 * The underlying map we are managing.
	 */
	private HashMap<Object,Object> map = null;

	// --------------------------------------------------------- Public Methods

	/**
	 * Remove all mappings from this map.
	 */
	public void clear() {

		synchronized (this) {
			HashMap<Object,Object> temp = (HashMap) map.clone();
			temp.clear();
			map = temp;
		}

	}

	/**
	 * Return a shallow copy of this <code>FastHashMap</code> instance.
	 * The keys and values themselves are not copied.
	 */
	public Object clone() {

		return new FastHashMap(map);

	}

	/**
	 * Return <code>true</code> if this map contains a mapping for the
	 * specified key.
	 *
	 * @param key Key to be searched for
	 */
	public boolean containsKey(Object key) {

		return map.containsKey(key);

	}

	/**
	 * Return <code>true</code> if this map contains one or more keys mapping
	 * to the specified value.
	 *
	 * @param value Value to be searched for
	 */
	public boolean containsValue(Object value) {

		return map.containsValue(value);

	}

	/**
	 * Return a collection view of the mappings contained in this map.  Each
	 * element in the returned collection is a <code>Map.Entry</code>.
	 */
	public Set entrySet() {

		return map.entrySet();

	}

	/**
	 * Compare the specified object with this list for equality.  This
	 * implementation uses exactly the code that is used to define the
	 * list equals function in the documentation for the
	 * <code>Map.equals</code> method.
	 *
	 * @param o Object to be compared to this list
	 */
	public boolean equals(Object o) {

		// Simple tests that require no synchronization
		if (o == this)
			return true;
		else if ( !(o instanceof Map) )
			return false;
		Map mo = (Map) o;

		// Compare the two maps for equality

		if ( mo.size() != map.size() )
			return false;
		java.util.Iterator i = map.entrySet().iterator();
		while ( i.hasNext() ) {
			Map.Entry e = (Map.Entry) i.next();
			Object key = e.getKey();
			Object value = e.getValue();
			if (value == null) {
				if ( !( mo.get(key) == null && mo.containsKey(key) ) )
					return false;
			}
			else {
				if ( !value.equals( mo.get(key) ) )
					return false;
			}
		}
		return true;

	}

	/**
	 * Return the value to which this map maps the specified key.  Returns
	 * <code>null</code> if the map contains no mapping for this key, or if
	 * there is a mapping with a value of <code>null</code>.  Use the
	 * <code>containsKey()</code> method to disambiguate these cases.
	 *
	 * @param key Key whose value is to be returned
	 */
	public Object get(Object key) {

		return map.get(key);

	}

	/**
	 * Return the hash code value for this map.  This implementation uses
	 * exactly the code that is used to define the list hash function in the
	 * documentation for the <code>Map.hashCode</code> method.
	 */
	public int hashCode() {

		int h = 0;
		java.util.Iterator i = map.entrySet().iterator();
		while ( i.hasNext() )
			h += i.next().hashCode();
		return h;

	}

	/**
	 * Return <code>true</code> if this map contains no mappings.
	 */
	public boolean isEmpty() {

		return map.isEmpty();

	}

	/**
	 * Return a set view of the keys contained in this map.
	 */
	public Set keySet() {

		return map.keySet();

	}

	/**
	 * Associate the specified value with the specified key in this map.
	 * If the map previously contained a mapping for this key, the old
	 * value is replaced and returned.
	 *
	 * @param key The key with which the value is to be associated
	 * @param value The value to be associated with this key
	 */
	public Object put(Object key, Object value) {

		synchronized (this) {
			HashMap temp = (HashMap) map.clone();
			Object result = temp.put(key, value);
			map = temp;
			return (result);
		}

	}

	/**
	 * Copy all of the mappings from the specified map to this one, replacing
	 * any mappings with the same keys.
	 *
	 * @param in Map whose mappings are to be copied
	 */
	public void putAll(Map in) {

		synchronized (this) {
			HashMap temp = (HashMap) map.clone();
			temp.putAll(in);
			map = temp;
		}

	}

	/**
	 * Remove any mapping for this key, and return any previously
	 * mapped value.
	 *
	 * @param key Key whose mapping is to be removed
	 */
	public Object remove(Object key) {

		synchronized (this) {
			HashMap temp = (HashMap) map.clone();
			Object result = temp.remove(key);
			map = temp;
			return (result);
		}

	}

	/**
	 * Return the number of key-value mappings in this map.
	 */
	public int size() {

		return map.size();

	}

	/**
	 * Return a collection view of the values contained in this map.
	 */
	public Collection values() {

		return map.values();

	}

	public String toString() { return map.toString(); }

}





