package com.wipro.training.ecommerce.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wipro.training.ecommerce.util.PersistenceUtil;

public abstract class AbstractDAO<T, I extends Serializable> {

	private EntityManager entityManager;
	private Class<T> entityClass;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;

		entityManager = PersistenceUtil.getEntityManager();
	}

	public Optional<T> find(I id) {
		return Optional.ofNullable(entityManager.find(entityClass, id));
	}

	public Optional<List<T>> findAll() {
		TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
		return Optional.ofNullable(typedQuery.getResultList());
	}

	public void delete(T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	public void save(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public Optional<T> merge(T entity) {
		entityManager.getTransaction().begin();
		T updated = entityManager.merge(entity);
		entityManager.getTransaction().commit();

		return Optional.ofNullable(updated);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
