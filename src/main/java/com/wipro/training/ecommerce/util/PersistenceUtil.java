package com.wipro.training.ecommerce.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
	private static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory != null ? entityManagerFactory : Persistence.createEntityManagerFactory("eCommerce");
	}

	public static EntityManager getEntityManager() {
		EntityManager entityManager = threadLocal.get();

		if (entityManager != null)
			return entityManager;

		entityManager = getEntityManagerFactory().createEntityManager();
		threadLocal.set(entityManager);

		return entityManager;
	}

	public static final void dispose() {
		EntityManager entityManager = threadLocal.get();

		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

}
