package com.ft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Array;

/**
 * 数组帮助类
 * 
 * @author adminxp
 * 
 */
public final class ArrayHelper {

	/**
	 * 转化成字符串数组
	 * 
	 * @param objects
	 * @return
	 */
	public static String[] toStringArray(Object[] objects) {
		int length = objects.length;
		String[] result = new String[length];
		for (int i = 0; i < length; i++) {
			result[i] = objects[i].toString();
		}
		return result;
	}

	/**
	 * 填充字符串数组,用value值填充长度为length的数组
	 * 
	 * @param value
	 *            数组值
	 * @param length
	 *            数组长度
	 * @return
	 */
	public static String[] fillArray(String value, int length) {
		String[] result = new String[length];
		Arrays.fill(result, value);
		return result;
	}

	/**
	 * 填充整数数组
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static int[] fillArray(int value, int length) {
		int[] result = new int[length];
		Arrays.fill(result, value);
		return result;
	}

	/**
	 * 集合对象转化为字符串数组
	 * 
	 * @param coll
	 *            集合对想
	 * @return
	 */
	public static String[] toStringArray(Collection<String> coll) {
		return (String[]) coll.toArray(EMPTY_STRING_ARRAY);
	}

	/**
	 * 
	 * @param coll
	 * @return
	 */
	public static String[][] to2DStringArray(Collection<String> coll) {
		return (String[][]) coll.toArray(new String[coll.size()][]);
	}

	public static int[][] to2DIntArray(Collection<Integer> coll) {
		return (int[][]) coll.toArray(new int[coll.size()][]);
	}

	/**
	 * 集合对象转化为整数数组
	 * 
	 * @param coll
	 *            集合对象
	 * @return
	 */
	public static int[] toIntArray(Collection<Integer> coll) {
		Iterator iter = coll.iterator();
		int[] arr = new int[coll.size()];
		int i = 0;
		while (iter.hasNext()) {
			arr[i++] = ((Integer) iter.next()).intValue();
		}
		return arr;
	}

	/**
	 * 集合对象转化为boolean数组
	 * 
	 * @param coll
	 * @return
	 */
	public static boolean[] toBooleanArray(Collection coll) {
		Iterator iter = coll.iterator();
		boolean[] arr = new boolean[coll.size()];
		int i = 0;
		while (iter.hasNext()) {
			arr[i++] = ((Boolean) iter.next()).booleanValue();
		}
		return arr;
	}

	public static Object[] typecast(Object[] array, Object[] to) {
		return java.util.Arrays.asList(array).toArray(to);
	}

	// Arrays.asList doesn't do primitive arrays
	public static List<Object> toList(Object array) {
		if (array instanceof Object[])
			return Arrays.asList((Object[]) array); // faster?
		int size = Array.getLength(array);
		List<Object> list = new ArrayList<Object>(size);
		for (int i = 0; i < size; i++) {
			list.add(Array.get(array, i));
		}
		return list;
	}

	public static String[] slice(String[] strings, int begin, int length) {
		String[] result = new String[length];
		for (int i = 0; i < length; i++) {
			result[i] = strings[begin + i];
		}
		return result;
	}

	public static Object[] slice(Object[] objects, int begin, int length) {
		Object[] result = new Object[length];
		for (int i = 0; i < length; i++) {
			result[i] = objects[begin + i];
		}
		return result;
	}

	public static List<Object> toList(Iterator iter) {
		List<Object> list = new ArrayList<Object>();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	public static String[] join(String[] x, String[] y) {
		String[] result = new String[x.length + y.length];
		for (int i = 0; i < x.length; i++)
			result[i] = x[i];
		for (int i = 0; i < y.length; i++)
			result[i + x.length] = y[i];
		return result;
	}

	public static int[] join(int[] x, int[] y) {
		int[] result = new int[x.length + y.length];
		for (int i = 0; i < x.length; i++)
			result[i] = x[i];
		for (int i = 0; i < y.length; i++)
			result[i + x.length] = y[i];
		return result;
	}

	private ArrayHelper() {
	}

	public static String toString(Object[] array) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}

	public static boolean isAllNegative(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= 0)
				return false;
		}
		return true;
	}

	public static void addAll(Collection<Object> collection, Object[] array) {
		for (int i = 0; i < array.length; i++) {
			collection.add(array[i]);
		}
	}

	public static final String[] EMPTY_STRING_ARRAY = {};

	public static final int[] EMPTY_INT_ARRAY = {};

	public static final Class[] EMPTY_CLASS_ARRAY = {};

	public static final Object[] EMPTY_OBJECT_ARRAY = {};

	public static int[] getBatchSizes(int maxBatchSize) {
		int batchSize = maxBatchSize;
		int n = 1;
		while (batchSize > 1) {
			if (batchSize == 3)
				batchSize = 4; // allow 3,2,1
			batchSize = batchSize / 2;
			n++;
		}
		int[] result = new int[n];
		batchSize = maxBatchSize;
		for (int i = 0; i < n; i++) {
			result[i] = batchSize;
			if (batchSize == 3)
				batchSize = 4; // allow 3,2,1
			batchSize = batchSize / 2;
		}
		return result;
	}
	
	 /**
     * 把long数组转为指定字符分隔的字符串
     * @param array 要转换的数组
     * @param regex 分隔符
     * @return
     */
    public static String getArrayAsString(long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i]);
                if (i != (array.length - 1)) // 如果不是最后一个，加上中间
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }
    
    /**
     * 把Long数组转为指定字符分隔的字符串
     * @param array 要转换的数组
     * @param regex 分隔符
     * @return
     */
    public static String getArrayAsString(Long[] array, String regex) {
        StringBuffer tempStrBuf = new StringBuffer();
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                tempStrBuf.append(array[i].longValue());
                if (i != (array.length - 1)) // 如果不是最后一个，加上中间
                    tempStrBuf.append(regex);
            }
        }
        return tempStrBuf.toString();
    }

}
