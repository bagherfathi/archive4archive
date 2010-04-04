package com.ft.common.cache;

/**
 * ʵ��ֿ�����ࡣ
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public abstract class AbstractRepository {
	protected Cache cache;

	/**
	 * @param cache
	 *            the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * ��ʼ��ʵ��ֿ⡣
	 * 
	 */
	public abstract void initialize();
}
