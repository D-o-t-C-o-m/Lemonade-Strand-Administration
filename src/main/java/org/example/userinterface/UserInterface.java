package org.example.userinterface;

import org.example.domain.Product;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.service.ProductService;
import org.example.service.SupplierService;
import org.example.domain.Supplier;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class UserInterface {
private SupplierService supplierService;
private ProductService productService;

private final Scanner scanner = new Scanner(System.in);

public UserInterface(ProductService productService, SupplierService supplierService) {
	this.supplierService = supplierService;
	this.productService = productService;
}

private void showMenu() {
		System.out.println("Welcome to the Lemonade Stand Administration App.");
		System.out.println("The Menu:");
	    System.out.println("==========");
		System.out.println("1. Manage suppliers");
		System.out.println("2. Manage products");
		System.out.println("3. Manage lemonades recipes");
		System.out.println("4. Create an order");
		System.out.println("5. Daily sales report");
		System.out.println("6. Empty products stock report");
		System.out.println("7. Exit");
		System.out.println("What do you want to do? ");
	    System.out.print("> ");
}
public void runMenu(){
	Scanner scanner = new Scanner(System.in);
	int option = -1;
	try {
		while (option != 7) {
			showMenu();
			option = scanner.nextInt();
			switch (option) {
				case 1:
					runSuppliersMenu(scanner);
					break;
				case 2:
					runProductsMenu(scanner);
					break;

				case 3, 4, 5, 6:
					System.out.println("Coming Soon");
					break;
				case 7:
					break;
			}
		}
		scanner.close();
	} catch (InputMismatchException e) {
		System.out.println("Please enter a valid number");
	} catch (ValidationException | FileNotFoundException | IDNotUniqueException e) {
		throw new RuntimeException(e);
	}
}
private void showSuppliersMenu() {
		System.out.println("The Suppliers menu:");

		System.out.println("1. Add a supplier");
		System.out.println("2. Update a supplier");
		System.out.println("3. Remove a supplier");
		System.out.println("4. Display all suppliers");
		System.out.println("5. Back to main menu");
		System.out.println("What do you want to do? ");
		System.out.print("> ");
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
public void runSuppliersMenu(Scanner scanner) {
	int option = -1;
	try {
	while (option != 5) {
		showSuppliersMenu();
		option = scanner.nextInt();
		switch (option) {
			case 1:
				handleAddSupplier(scanner);
				break;
			case 2:
				handleUpdateSupplier(scanner);
				break;
			case 3:
				handleRemoveSupplier(scanner);
			case 4:
				handleShowSuppliers();
				break;
			case 5:
				break;
		}
	}
	} catch (InputMismatchException e) {
		System.out.println("Please enter a valid number");
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}
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
private void handleAddSupplier(Scanner scanner) {
	Random random = new Random();
	int id = random.nextInt(999);
	System.out.println("ID: "+id);

	System.out.println("Name: ");
	String name = scanner.next().trim();
		if(name.isEmpty()) {
		System.out.println("Name cannot be empty");
		return;
	}
		scanner.nextLine();
	System.out.println("Email: ");
	String email = scanner.next().trim();

	try {
		Supplier savedSupplier = supplierService.saveSupplier(id, name, email);
		System.out.printf("The supplier with ID %d was created successfully. %n", savedSupplier.getId());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the supplier " + e.getMessage());
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
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
private void handleUpdateSupplier(Scanner scanner) throws FileNotFoundException {
	System.out.println("ID: ");
	int id = scanner.nextInt();

	System.out.println("Name: ");
	String name = scanner.next();

	System.out.println("Email: ");
	String email = scanner.next();

	Supplier updatedSupplier = supplierService.updateSupplier(id, name, email);
	System.out.printf("The supplier with ID %d was updated successfully. %n", updatedSupplier.getId());
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
private void handleRemoveSupplier(Scanner scanner) throws FileNotFoundException {
	System.out.println("ID to Remove: ");
	int id = scanner.nextInt();

	supplierService.deleteSupplier(id);
	System.out.printf("The supplier with ID %d was deleted successfully. %n", id);
}

private void handleRemoveProducts(Scanner scanner) throws FileNotFoundException, ValidationException, IDNotUniqueException {
	System.out.println("ID to Remove Products: ");
	int id = scanner.nextInt();

	productService.removeProduct(id);
	System.out.printf("The product with ID %d was deleted successfully. %n", id);
}

private void displaySuppliers(Iterable<Supplier> suppliers) {
	for (Supplier supplier : suppliers) {
		System.out.println(supplier);
	}
}
private void handleShowSuppliers() {
	Iterable<Supplier> suppliers = supplierService.findAll();
	displaySuppliers(suppliers);
}
private void handleShowProducts() {
	Iterable<Product> productList = productService.getAllProducts();
	for (Product product : productList) {
		System.out.println(product);
	}
}
}
