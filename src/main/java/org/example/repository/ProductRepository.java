package org.example.repository;

import org.example.domain.Product;
import org.example.exceptions.IDNotUniqueException;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

private Map<Integer, Product> products;

public ProductRepository() {
	this.products = new HashMap<>();
}

public Product save(Product product) throws IDNotUniqueException {
	if (products.containsKey(product.getId())) {
		throw new IDNotUniqueException("The id is not unique");
	}
	products.put(product.getId(), product);
	return product;
}

public Product update(Product product) {
	if (products.containsKey(product.getId())) {
		products.put(product.getId(), product);
	}
	return product;
}

public void delete(int productId) {
	products.remove(productId);
}

public Iterable<Product> findAll() {
	return products.values();
}

public Product findById(int id) {
	return products.get(id);
}
}
