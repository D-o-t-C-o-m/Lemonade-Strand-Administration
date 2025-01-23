package org.example;

import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.SupplierFileRepository;
import org.example.repository.SupplierFileRepositoryTest;
import org.example.repository.SupplierRepositoryTest;
import org.example.service.SupplierServiceTest;
import org.example.domain.SupplierTest;

import java.io.FileNotFoundException;

public class MainTest {
public static void main(String[] args) {
	runAllTests();
}
public static void runAllTests() {
try{
	SupplierTest supplierTest = new SupplierTest();
	supplierTest.testAllDomain();

	SupplierRepositoryTest supplierRepository = new SupplierRepositoryTest();
	supplierRepository.testAllRepository();
	SupplierFileRepositoryTest supplierFileRepository = new SupplierFileRepositoryTest("test-file.csv");
	supplierFileRepository.testAllSupplierFileRepository();

	SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
	supplierServiceTest.testAllService();

	System.out.println("All tests have run successfully");
} catch (ValidationException | IDNotUniqueException | FileNotFoundException e) {
	System.out.println("The tests have failed, e=" + e.getMessage());
}

}
}
