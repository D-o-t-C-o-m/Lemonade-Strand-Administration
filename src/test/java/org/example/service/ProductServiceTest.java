package org.example.service;

import org.example.domain.Product;
import org.example.domain.Supplier;

import org.example.exceptions.ValidationException;
import org.example.repository.ProductRepository;
import org.example.repository.SupplierRepository;
import org.example.validators.ProductValidator;
import org.example.validators.SupplierValidator;

import java.io.FileNotFoundException;

public class ProductServiceTest {

private SupplierService supplierService;

private ProductService productService;

private void setUp() {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierValidator supplierValidator = new SupplierValidator();
	supplierService = new SupplierService(supplierRepository, supplierValidator);

	ProductRepository productFileRepository = new ProductRepository();
	ProductValidator productValidator = new ProductValidator();
	productService = new ProductService(productFileRepository, productValidator, supplierService);

}

public void shouldSaveProduct_whenSavedMethodCalled() throws ValidationException, ValidationException, FileNotFoundException {
	//System.out.println("shouldSaveProduct_whenSavedMethodCalled now running");
	setUp();

	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());

	assert savedProduct != null;
	assert savedProduct.getId() == 1;
	assert savedProduct.getName().equals("Sugar");

	//System.out.println("shouldSaveProduct_whenSavedMethodCalled Passed");
}

public void shouldUpdateProduct_whenUpdateMethodCalled() throws ValidationException, FileNotFoundException {
	//System.out.println("shouldUpdateProduct_whenUpdateMethodCalled now running");
	setUp();
	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());
	Product updatedProduct = productService.updateProduct(1, "Sugar", "Brown sugar", 10, 10, supplier.getId());
	assert updatedProduct != null;
	assert updatedProduct.getId() == 1;
	assert updatedProduct.getDescription().equals("Brown sugar");
	//System.out.println("shouldUpdateProduct_whenUpdateMethodCalled Passed");
}

public void shouldRemoveProduct_whenRemoveMethodCalled() throws ValidationException, FileNotFoundException {
	setUp();
	//System.out.println("shouldRemoveProduct_whenRemoveMethodCalled now running");
	Supplier supplier = supplierService.saveSupplier(1, "Sugar supplier", "supplier@email.com");
	Product savedProduct = productService.saveProduct(1, "Sugar", "Sweet sugar", 10, 10, supplier.getId());
	productService.removeProduct(1);


	assert !productService.getAllProducts().iterator().hasNext();
	//System.out.println("shouldRemoveProduct_whenRemoveMethodCalled Passed");
}

public void testAllProductService() throws ValidationException, FileNotFoundException {
	shouldSaveProduct_whenSavedMethodCalled();
	shouldUpdateProduct_whenUpdateMethodCalled();
	shouldRemoveProduct_whenRemoveMethodCalled();

}


}