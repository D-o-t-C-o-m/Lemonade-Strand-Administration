package org.example.userinterface;

import org.example.domain.Product;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.service.ProductService;
import org.example.service.SupplierService;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProductMenu {
private SupplierService supplierService;
private ProductService productService;

public ProductMenu(SupplierService supplierService, ProductService productService) {
	this.supplierService = supplierService;
	this.productService = productService;
}
private void showProductsMenu() {
	System.out.println("The Product menu:");

	System.out.println("===================");
	System.out.println("1. Add a product");
	System.out.println("2. Update a product");
	System.out.println("3. Remove a product");
	System.out.println("4. Display all products");
	System.out.println("5. Back to main menu");
	System.out.println("What do you want to do? ");
	System.out.print("> ");
}

public void runProductsMenu(Scanner scanner) throws FileNotFoundException, ValidationException, IDNotUniqueException {
	int option = -1;
	while (option != 5) {
		showProductsMenu();
		option = scanner.nextInt();

		switch (option) {
			case 1:
				handleAddProduct(scanner);
				break;
			case 2:
				handleRemoveProducts(scanner);
				break;
			case 3:
				handleUpdateProduct(scanner);
				break;
			case 4:
				handleShowProducts();
				break;
			case 5:
				break;

		}
	}
}

private void handleAddProduct(Scanner scanner) {
	System.out.println("Product ID: ");
	int id = scanner.nextInt();

	System.out.println("Name: ");
	String name = scanner.next().trim();
	scanner.nextLine();

	System.out.println("Description: ");
	String description = scanner.next().trim();
	scanner.nextLine();

	System.out.println("Price: ");
	int price = scanner.nextInt();

	System.out.println("Quantity: ");
	int quantity = scanner.nextInt();

	System.out.println("Supplier Id: ");
	int supplierId = scanner.nextInt();

	try{
		productService.saveProduct(id, name, description, price, quantity, supplierId);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}
}

private void handleUpdateProduct(Scanner scanner) throws FileNotFoundException {
	System.out.println("ID of product being updated: ");
	int id = scanner.nextInt();

	System.out.println("New Name: ");
	String name = scanner.next().trim();

	System.out.println("New Description: ");
	String description = scanner.next().trim();

	System.out.println("New Price: ");
	int price = scanner.nextInt();

	System.out.println("New Quantity: ");
	int quantity = scanner.nextInt();

	System.out.println("SupplierId: ");
	int supplierId = scanner.nextInt();

	try {
		productService.updateProduct(id, name, description, price, quantity, supplierId);
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the product " + e.getMessage());
	}

}

private void handleRemoveProducts(Scanner scanner) throws FileNotFoundException, ValidationException, IDNotUniqueException {
	System.out.println("ID to Remove Products: ");
	int id = scanner.nextInt();

	productService.removeProduct(id);
	System.out.printf("The product with ID %d was deleted successfully. %n", id);
}
private void handleShowProducts() {
	Iterable<Product> productList = productService.getAllProducts();
	for (Product product : productList) {
		System.out.println(product);
	}
}
}
