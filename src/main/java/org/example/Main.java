package org.example;

import org.example.exceptions.IDNotUniqueException;
import org.example.repository.ProductFileRepository;
import org.example.repository.SupplierFileRepository;
import org.example.service.ProductService;
import org.example.service.SupplierService;
import org.example.userinterface.UserInterface;
import org.example.validators.ProductValidator;
import org.example.validators.SupplierValidator;

import java.io.IOException;

public class Main {
public static void main(String[] args) throws IOException, IDNotUniqueException {

	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
	SupplierValidator supplierValidator = new SupplierValidator();
	SupplierService supplierService = new SupplierService(supplierRepository, supplierValidator);

	ProductFileRepository productRepository = new ProductFileRepository("products.csv");
	ProductValidator productValidator = new ProductValidator();
	ProductService productService = new ProductService(productRepository, productValidator, supplierService);

	UserInterface userInterface = new UserInterface(productService, supplierService);
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();
}
}