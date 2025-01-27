package org.mike.service;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LemonadeService {
private GenericRepository<Lemonade> lemonadeRepository;
private GenericRepository<LemonadeRecipe> lemonadeRecipeRepository;
private ProductService productService;

public LemonadeService(GenericRepository<LemonadeRecipe> lemonadeRecipeRepository, GenericRepository<Lemonade> lemonadeRepository, ProductService productService) {
	this.lemonadeRepository = lemonadeRepository;
	this.lemonadeRecipeRepository = lemonadeRecipeRepository;
	this.productService = productService;
}

public List<LemonadeRecipe> findLemonadeRecipe(int lemonadeId) {
	Iterable<LemonadeRecipe> allLemonadeRecipes = lemonadeRecipeRepository.findAll();
	List<LemonadeRecipe> recipeForTheRequestedLemonade = new ArrayList<>();

	for (LemonadeRecipe lemonadeRecipe : allLemonadeRecipes) {
		if (lemonadeRecipe.getLemonade().getId() == lemonadeId) {
			for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
				Product loadedProduct = productService.findById(entry.getKey().getId());
				int productId = loadedProduct.getId();
				entry.setValue(productId);
			}
			recipeForTheRequestedLemonade.add(lemonadeRecipe);
		}
	}
	return recipeForTheRequestedLemonade;
}

public Lemonade findById(int lemonadeId) {
	return lemonadeRepository.findById(lemonadeId);
}

public Iterable<Lemonade> findAll() {
	return lemonadeRepository.findAll();
}
}
