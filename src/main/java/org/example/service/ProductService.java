package org.example.service;

import org.example.domain.Product;
import org.example.domain.Supplier;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.ProductRepository;
import org.example.validators.ProductValidator;

public class ProductService {
private ProductRepository productRepository;
private ProductValidator productValidator;
private SupplierService supplierService;

public ProductService(ProductRepository productRepository, ProductValidator productValidator, SupplierService supplierService) {
	this.productRepository = productRepository;
	this.productValidator = productValidator;
	this.supplierService = supplierService;
}

public Product saveProduct(int Id, String name, String Description, double price, int quantity, int supplierId) throws ValidationException {
	Supplier supplier = supplierService.findById(supplierId);
	Product product = new Product(Id, name, Description, price, quantity, supplier);
	productValidator.validateProduct(product);
	Product savedProduct = productRepository.save(product);
	return savedProduct;
}

public void removeProduct(int Id) throws IDNotUniqueException, ValidationException {
	productRepository.delete(Id);
}

public Product updateProduct(int id, String newName, String newDescription, double newPrice, int newQuantity, int newSupplierId) throws ValidationException {
	Supplier supplier = supplierService.findById(newSupplierId);
	removeProduct(id);
	Product productToUpdate = new Product(id, newName, newDescription, newPrice, newQuantity, supplier);

	productValidator.validateProduct(productToUpdate);
	Product updatedProduct = productRepository.save(productToUpdate);
	return updatedProduct;
}

public Iterable<Product> getAllProducts() {
	return productRepository.findAll();
}
}
