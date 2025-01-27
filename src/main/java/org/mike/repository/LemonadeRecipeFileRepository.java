package org.mike.repository;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LemonadeRecipeFileRepository extends GenericRepository<LemonadeRecipe>{
	private String filename;
	private GenericRepository<Product> productRepository;
	private GenericRepository<Lemonade> lemonadeRepository;

public LemonadeRecipeFileRepository(String filename) throws IOException, IDNotUniqueException {
	super();
	this.filename = filename;
	super.fileExistenceCheck(filename);
	loadLemonadesFromFile();
}

@Override
public LemonadeRecipe save(LemonadeRecipe lemonadeRecipe) throws IDNotUniqueException, FileNotFoundException {
	LemonadeRecipe savedLemonadeRecipe = super.save(lemonadeRecipe);
	writeToFile();
	return savedLemonadeRecipe;
}

@Override
public LemonadeRecipe update(LemonadeRecipe lemonadeRecipe) throws FileNotFoundException {
	LemonadeRecipe updatedLemonadeRecipe = super.update(lemonadeRecipe);
	writeToFile();
	return updatedLemonadeRecipe;
}

@Override
public void delete(int Id) throws FileNotFoundException {
	super.delete(Id);
	writeToFile();
}

public List<LemonadeRecipe> readLemonadeRecipesFromFile() {
	List<LemonadeRecipe> lemonadeRecipes = new ArrayList<>();
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			int id = Integer.parseInt(parts[0]);
			int productId = Integer.parseInt(parts[1]);
			int lemonadeId = Integer.parseInt(parts[2]);
			int quantity = Integer.parseInt(parts[3]);

			Product product = new Product(productId);
			Lemonade lemonade = new Lemonade(lemonadeId);

			LemonadeRecipe lemonadeRecipe = new LemonadeRecipe(id,product,lemonade,quantity);
			lemonadeRecipes.add(lemonadeRecipe);

		}
		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);

	}
	return lemonadeRecipes;
}

private void writeToFile() {
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("Recipe ID, Product ID, Lemonade ID, Quantity");
		bw.newLine();
		Iterable<LemonadeRecipe> lemonadeRecipes = findAll();
		for (LemonadeRecipe lemonadeRecipe : lemonadeRecipes) {
			String line = lemonadeRecipe.getId() + "," + lemonadeRecipe.getProduct().getId() + ","  + lemonadeRecipe.getLemonade().getId() + "," + lemonadeRecipe.getQuantity();
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

private void loadLemonadesFromFile() throws IDNotUniqueException, IOException {
	List<LemonadeRecipe> lemonadeRecipes = readLemonadeRecipesFromFile();
	for (LemonadeRecipe lemonadeRecipe : lemonadeRecipes) {
		this.save(lemonadeRecipe);
	}
}

}

