package com.wipro.training.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import com.wipro.training.ecommerce.model.Product;

public class ProductDAO extends AbstractDAO<Product, Long> {

	public ProductDAO() {
		super(Product.class);
	}

	public Optional<List<Product>> findByName(String name) {
		TypedQuery<Product> typedQuery = getEntityManager().createQuery("FROM Product WHERE name LIKE :name", Product.class);
		typedQuery.setParameter("name", "%" + name + "%");

		return Optional.ofNullable(typedQuery.getResultList());
	}

}
