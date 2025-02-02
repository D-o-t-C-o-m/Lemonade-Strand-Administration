package org.mike.service;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.dtos.OutOfStockDTO;
import org.mike.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LemonadeService {
private final GenericRepository<Lemonade> lemonadeRepository;
private final GenericRepository<LemonadeRecipe> lemonadeRecipeRepository;

public LemonadeService(GenericRepository<LemonadeRecipe> lemonadeRecipeRepository, GenericRepository<Lemonade> lemonadeRepository) {
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
}

public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipeForTheRequestedLemonade = new ArrayList<>();

	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
		if (lemonadeRecipe.getLemonade().getId() == lemonadeId) {
			recipeForTheRequestedLemonade.add(lemonadeRecipe);
		}
	}
	return recipeForTheRequestedLemonade;
}

public List<LemonadeRecipe> findAllLemonadeRecipe() {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipesForTheLemonades = new ArrayList<>();
	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
		{
			recipesForTheLemonades.add(lemonadeRecipe);
		}
	}
	return recipesForTheLemonades;
}

public Lemonade findById(int lemonadeId) {
	return lemonadeRepository.findById(lemonadeId);
}

public Iterable<Lemonade> findAll() {
	return lemonadeRepository.findAll();
}

public List<OutOfStockDTO> OOSReport() {
	List<OutOfStockDTO> report = new ArrayList<>();

	List<LemonadeRecipe> recipe = findAllLemonadeRecipe();
	boolean isOutOfStock = false;
	ArrayList<Product> OOSitem = new ArrayList<>();
	ArrayList<LemonadeRecipe> lemonades = new ArrayList<>();
	for (LemonadeRecipe lemonadeRecipe : recipe) {
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
			ArrayList<Product> recipeUsage = new ArrayList<>();
			Product recipeProduct = entry.getKey();
			int recipeQuantity = entry.getValue();
			recipeUsage.add(recipeProduct);

			for (Product products : recipeUsage) {
				int onHands = products.getQuantity();
				if (onHands < recipeQuantity) {
					OOSitem.add(products);
					lemonades.add(lemonadeRecipe);
					isOutOfStock = true;
					OutOfStockDTO OutOfStockDTO = new OutOfStockDTO(products, products.getSupplier());
					report.add(OutOfStockDTO);
				}
			}
		}
	} if (isOutOfStock) {
			for (LemonadeRecipe lemonadeRecipe : lemonades) {
				System.out.println("These lemonades are not able to be made due to the out of stocks:");
				System.out.println(lemonadeRecipe.getLemonade().getName());
				return report;
			}

	} else {
		System.out.println("There are no lemonades that are out of stock");

	}
	return report;
}

}
