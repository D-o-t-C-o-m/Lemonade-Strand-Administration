package org.example;

import org.example.domain.SupplierTest;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.ProductFileRepositoryTest;
import org.example.repository.SupplierFileRepositoryTest;
import org.example.repository.SupplierRepositoryTest;
import org.example.service.ProductServiceTest;
import org.example.service.SupplierServiceTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {
public static void main(String[] args) {
	runAllTests();
}

public static void runAllTests() {
	try {
		System.out.println("Running Supplier tests");
		SupplierTest supplierTest = new SupplierTest();
		supplierTest.testAllDomain();

		System.out.println("Running Supplier Repository tests");
		SupplierRepositoryTest supplierRepository = new SupplierRepositoryTest();
		supplierRepository.testAllRepository();

		System.out.println("Running Supplier File Repository tests");
		SupplierFileRepositoryTest supplierFileRepository = new SupplierFileRepositoryTest("test-supplier.csv");
		supplierFileRepository.testAllSupplierFileRepository();

		System.out.println("Running Supplier Service tests");
		SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
		supplierServiceTest.testAllService();

		System.out.println("Running Product File Repository tests");
		ProductFileRepositoryTest productFileRepositoryTest = new ProductFileRepositoryTest("test-product.csv");
		productFileRepositoryTest.testAllProductFileRepository();

		System.out.println("Running Product Service tests");
		ProductServiceTest productServiceTest = new ProductServiceTest();
		productServiceTest.testAllProductService();


		System.out.println("All tests have run successfully");
	} catch (ValidationException | IDNotUniqueException | FileNotFoundException e) {
		System.out.println("Test failed because " + e.getMessage());
	} catch (IOException e) {
		throw new RuntimeException(e);
	}

}
}
