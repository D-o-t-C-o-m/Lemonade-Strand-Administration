package org.example.userinterface;

import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.service.SupplierService;
import org.example.domain.Supplier;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class UserInterface {
private SupplierService supplierService;
private Scanner scanner = new Scanner(System.in);

public UserInterface(SupplierService supplierService) {
	this.supplierService = supplierService;
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
				case 2, 3, 4, 5, 6:
					System.out.println("Coming Soon");
					break;
				case 7:
					break;
			}
		}
		scanner.close();
	} catch (InputMismatchException e) {
		System.out.println("Please enter a valid number");
	}
}
private void showSuppliersMenu() {
		System.out.println("The Suppliers menu:");
	    System.out.println("===================");
		System.out.println("1. Add a supplier");
		System.out.println("2. Update a supplier");
		System.out.println("3. Remove a supplier");
		System.out.println("4. Display all suppliers");
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
	}
}
private void handleAddSupplier(Scanner scanner) {
	Random random = new Random();
	int id = random.nextInt(999);
	System.out.println("ID: "+id);

	System.out.println("Name: ");
	String name = scanner.next();

	System.out.println("Email: ");
	String email = scanner.next();

	try {
		Supplier savedSupplier = supplierService.saveSupplier(id, name, email);
		System.out.printf("The supplier with ID %d was created successfully. %n", savedSupplier.getId());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the supplier" + e.getMessage());
	}

}
private void handleUpdateSupplier(Scanner scanner) {
	System.out.println("ID: ");
	int id = scanner.nextInt();

	System.out.println("Name: ");
	String name = scanner.next();

	System.out.println("Email: ");
	String email = scanner.next();

	Supplier updatedSupplier = supplierService.updateSupplier(id, name, email);
	System.out.printf("The supplier with ID %d was updated successfully. %n", updatedSupplier.getId());
}
private void handleRemoveSupplier(Scanner scanner) {
	System.out.println("ID to Remove: ");
	int id = scanner.nextInt();

	supplierService.deleteSupplier(id);
	System.out.printf("The supplier with ID %d was deleted successfully. %n", id);
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
}
