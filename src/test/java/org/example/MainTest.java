package org.example;

import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.SupplierRepositoryTest;
import org.example.service.SupplierServiceTest;
import org.example.domain.SupplierTest;

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

	SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
	supplierServiceTest.testAllService();

	System.out.println("All tests have run successfully");
} catch (ValidationException | IDNotUniqueException e) {
	System.out.println("The tests have failed, e=" + e.getMessage());
}

}
}
