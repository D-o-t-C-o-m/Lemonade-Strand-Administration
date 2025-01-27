package org.mike.userinterface;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.repository.LemonadeFileRepository;
import org.mike.repository.LemonadeRecipeFileRepository;
import org.mike.service.LemonadeService;
import org.mike.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class LemonadeMenu {
private LemonadeService lemonadeService;
private LemonadeFileRepository lemonadeRepository;
private LemonadeRecipeFileRepository lemonadeRecipeRepository;

public LemonadeMenu(LemonadeService lemonadeService) {
	this.lemonadeService = lemonadeService;
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
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
		System.out.println(lemonadeRecipe.getQuantity() + " " + lemonadeRecipe.getProduct().getName());
	}
}
}
