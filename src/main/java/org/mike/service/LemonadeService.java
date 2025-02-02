package org.mike.service;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.dtos.OutOfStockDTO;
import org.mike.repository.GenericRepository;

import java.util.ArrayList;
import java.util.Collections;
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
	List<String> oosLemonades = new ArrayList<>();
	boolean isOutOfStock = false;

	for (LemonadeRecipe lemonadeRecipe : recipe) {
		boolean lemonadeOutOfStock = false;
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
			Product recipeProduct = entry.getKey();
			int requiredQuantity = entry.getValue();
			if (recipeProduct.getQuantity() < requiredQuantity) {
				isOutOfStock = true;
				lemonadeOutOfStock = true;
				boolean productAlreadyInReport = false;
				for (OutOfStockDTO dto : report) {
					if (dto.getProduct().getId() == recipeProduct.getId()) {
						productAlreadyInReport = true;
						break;
					}
				}
				if (!productAlreadyInReport) {
					OutOfStockDTO outOfStockDTO = new OutOfStockDTO(recipeProduct, recipeProduct.getSupplier());
					report.add(outOfStockDTO);
				}
			}
		}
		if (lemonadeOutOfStock) {
			oosLemonades.add(lemonadeRecipe.getLemonade().getName());
		}
	}
	if (isOutOfStock) {
		System.out.println("These lemonades are not able to be made due to out of stock items:");
		for (String lemonade : oosLemonades) {
			System.out.println(lemonade);
		}
		return report;
	} else {
		System.out.println("There are no lemonades that are out of stock");
		return Collections.emptyList();
	}
}


}
