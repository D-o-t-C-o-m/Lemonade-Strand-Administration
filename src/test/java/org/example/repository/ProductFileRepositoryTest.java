package org.example.repository;

import org.example.domain.Product;
import org.example.domain.Supplier;
import org.example.exceptions.IDNotUniqueException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProductFileRepositoryTest {

private String filename;

public ProductFileRepositoryTest(String filename) {
	this.filename = filename;
}

private void clearFile() {
	try (FileOutputStream fos = new FileOutputStream(filename)) {
		// the file is emptied
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void shouldSaveToFileOneElement_whenSaveIsCalled() throws IOException {
	clearFile();
	ProductFileRepository fileRepository = new ProductFileRepository(filename);
	Supplier supplier = new Supplier(1, "Sugar supplier", "supplier@email.com");
	Product firstProductToSave = new Product(1, "Sugar", "Sweet sugar", 10, 10, supplier);

	Product firstSavedSupplier = fileRepository.save(firstProductToSave);

	List<Product> products = fileRepository.readProductsFromFile();
	Product savedProduct = fileRepository.findById(1);

	assert products.size() == 1;
	assert savedProduct != null;
	assert savedProduct.getSupplier() != null;

	clearFile();
}

public void testAllProductFileRepository() throws IOException {
	shouldSaveToFileOneElement_whenSaveIsCalled();
}
}