package com.wipro.training.ecommerce;

import java.util.List;
import java.util.Optional;

import com.wipro.training.ecommerce.dao.ProductDAO;
import com.wipro.training.ecommerce.model.Product;

public class ProductImpl {

	public static void main(String[] args) {
		Product product = new Product();
		product.setName("Motorola One");
		product.setPrice(1499.00);
		product.setQuantity(1);

		ProductDAO productDAO = new ProductDAO();
		productDAO.save(product);

		Optional<Product> testProduct = productDAO.find(1L);
		if (testProduct.isPresent()) {
			System.out.println(testProduct.get());
		}

		Optional<List<Product>> listOfProducts = productDAO.findAll();
		if (listOfProducts.isPresent()) {
			listOfProducts.get().forEach(p -> System.out.println(p.getName()));
		}

		Optional<List<Product>> productCollection = productDAO.findByName("Moto");
		if (productCollection.isPresent()) {
			productCollection.get().forEach(System.out::println);
		}

		System.exit(0);
	}

}
