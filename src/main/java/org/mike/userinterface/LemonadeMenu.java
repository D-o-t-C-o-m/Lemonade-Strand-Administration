package org.mike.userinterface;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.dtos.OutOfStockDTO;
import org.mike.service.LemonadeService;
import org.mike.service.SupplierService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LemonadeMenu {
private final LemonadeService lemonadeService;
private final SupplierService supplierService;

public LemonadeMenu(LemonadeService lemonadeService, SupplierService supplierService) {
	this.lemonadeService = lemonadeService;
	this.supplierService = supplierService;
}

public void runLemonadeMenu(Scanner scanner) {
	int option = -1;
	while (option != 3) {
		showLemonadesMenu();
		option = scanner.nextInt();

		switch (option) {
			case 1:
				handleShowLemonades();
				break;
			case 2:
				handleShowLemonadeRecipes(scanner);
				break;
			case 3:
				break;

		}
	}
}

private void showLemonadesMenu() {
	System.out.println("\nThe Lemonade menu:");
	System.out.println("====================");
	System.out.println("1. Display all lemonades");
	System.out.println("2. Display the recipe for a lemonade");
	System.out.println("3. Exit");
	System.out.println("What do you want to do? ");
	System.out.print("> ");
}

private void handleShowLemonades() {
	Iterable<Lemonade> lemonades = lemonadeService.findAll();
	for (Lemonade lemonade : lemonades) {
		System.out.println(lemonade.getName());
	}
}

private void handleShowLemonadeRecipes(Scanner scanner) {
	System.out.print("The ID of the lemonade: ");
	int lemonadeId = scanner.nextInt();

	List<LemonadeRecipe> requestedLemonadeRecipe = lemonadeService.findLemonadeRecipe(lemonadeId);
	System.out.println("The requested lemonade contains: ");

	for (LemonadeRecipe lemonadeRecipe : requestedLemonadeRecipe) {
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {

			Product product = entry.getKey();
			int quantity = entry.getValue();

			System.out.println(quantity + " " + product.getName());
		}
	}
}

public void runOOSReport() {

	List<OutOfStockDTO> report = lemonadeService.OOSReport();
	System.out.println("\n\nThese are the Out of Stock products: \n");
	if (report.isEmpty()) {
		System.out.print("");
	} else {
		for (OutOfStockDTO oos : report) {
			System.out.println(oos.getProduct().getName() + " is out of stock, contact supplier(s) for more: " + supplierService.findById(oos.getSupplier().getId()).getName() + ", " + supplierService.findById(oos.getSupplier().getId()).getEmail());
		}
	}
}
}




