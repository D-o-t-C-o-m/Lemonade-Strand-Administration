package org.example.repository;

import org.example.domain.Product;
import org.example.domain.Supplier;
import org.example.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFileRepository extends ProductRepository {

private String filename;

public ProductFileRepository(String filename) throws IOException, IDNotUniqueException {
	super();
	this.filename = filename;
	fileExistenceCheck();
	loadProductsFromFile();
}
private void fileExistenceCheck() throws FileNotFoundException {
	File file = new File(filename);
	if (!new File(filename).exists()) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

@Override
public Product save(Product product) throws IDNotUniqueException {
	Product savedProduct = super.save(product);
	writeToFile();
	return savedProduct;
}

@Override
public Product update(Product product) {
	Product updatedProduct = super.update(product);
	writeToFile();
	return updatedProduct;
}

@Override
public void delete (int Id){
	super.delete(Id);
	writeToFile();
}

public List<Product> readProductsFromFile() throws IOException {
	List<Product> products = new ArrayList<>();
	BufferedReader br = null;
	try{
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String description = parts[2];
			double price = Double.parseDouble(parts[3]);
			int quantity = Integer.parseInt(parts[4]);
			int supplierId = Integer.parseInt(parts[5]);

			Supplier supplier = new Supplier();
			supplier.setId(supplierId);

			Product product = new Product(id, name, description, price, quantity, supplier);
			products.add(product);

			}
		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);

	}
	return products;
}

private void writeToFile() {
	BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("Product ID|Name|Description|Price|Quantity|Supplier ID");
		bw.newLine();
		Iterable<Product> products = findAll();
		for (Product product : products) {
			String line = product.getId()+","+product.getName()+","+product.getDescription()+","+product.getPrice()+","+product.getQuantity()+","+product.getSupplier().getId();
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

private void loadProductsFromFile() throws IDNotUniqueException, IOException {
	List<Product> products = readProductsFromFile();
	for (Product product : products) {
		this.save(product);
	}
}

}
