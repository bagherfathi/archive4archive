package com.ft.hibernate.helper;


public class EntityManager {
       
	protected EntityQueryHelper entityQueryHelper;
	protected EntityPersistentHelper entityPersistentHelper;

	
	 public void saveOrUpdate(Object obj){
    	 this.getEntityPersistentHelper().saveOrUpdate(obj);
     }
	
	
	public EntityPersistentHelper getEntityPersistentHelper() {
		return entityPersistentHelper;
	}
    /**
     * @spring.property
     * ref="entityPersistentHelper"
     * @param entityPersistentHelper
     */
	public void setEntityPersistentHelper(
			EntityPersistentHelper entityPersistentHelper) {
		this.entityPersistentHelper = entityPersistentHelper;
	}

	public EntityQueryHelper getEntityQueryHelper() {
		return entityQueryHelper;
	}

	/**
	 * @spring.property
	 * ref="entityQueryHelper"
	 * @param entityQueryHelper
	 */
	public void setEntityQueryHelper(EntityQueryHelper entityQueryHelper) {
		this.entityQueryHelper = entityQueryHelper;
	}
	
	
}
