package com.ft.common.cache;

/**
 * 实体仓库抽象类。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
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
	 * 初始化实体仓库。
	 * 
	 */
	public abstract void initialize();
}
