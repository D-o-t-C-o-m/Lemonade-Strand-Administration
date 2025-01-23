package org.example.repository;

import org.example.domain.Supplier;
import org.example.exceptions.IDNotUniqueException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SupplierFileRepositoryTest {

	private String filename;

	public SupplierFileRepositoryTest(String filename) {
		this.filename = filename;
	}

	private void clearFile() {
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			// the file is emptied
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void shouldSaveToFileOneElement_whenSaveIsCalled() throws IDNotUniqueException, FileNotFoundException {
		clearFile();
		SupplierFileRepository fileRepository = new SupplierFileRepository(filename);
		Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

		Supplier firstSavedSupplier = fileRepository.save(firstSupplierToSave);

		List<Supplier> suppliersFromFile = fileRepository.readSupplierFromFile();

		assert suppliersFromFile.size() == 1;
		assert fileRepository.findById(1) != null;

		clearFile();
	}

	public void testAllSupplierFileRepository() throws IDNotUniqueException, FileNotFoundException {
		shouldSaveToFileOneElement_whenSaveIsCalled();
	}
}

