package org.mike;

import org.mike.exceptions.IDNotUniqueException;
import org.mike.repository.ProductFileRepository;
import org.mike.repository.SupplierFileRepository;
import org.mike.service.ProductService;
import org.mike.service.SupplierService;
import org.mike.userinterface.UserInterface;
import org.mike.validators.ProductValidator;

import java.io.IOException;

public class Main {
public static void main(String[] args) throws IOException, IDNotUniqueException {

	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
	SupplierService supplierService = new SupplierService(supplierRepository);

	ProductFileRepository productRepository = new ProductFileRepository("products.csv");
	ProductValidator productValidator = new ProductValidator();
	ProductService productService = new ProductService(productRepository, productValidator, supplierService);

	UserInterface userInterface = new UserInterface(productService, supplierService);
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();
}
}