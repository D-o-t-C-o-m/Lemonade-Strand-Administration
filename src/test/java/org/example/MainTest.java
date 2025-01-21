package org.example;

import org.example.repository.SupplierRepositoryTest;
import org.example.service.SupplierServiceTest;
import org.example.domain.SupplierTest;

public class MainTest {
public static void main(String[] args) {
	runAllTests();
}
public static void runAllTests() {

	SupplierTest supplierTest = new SupplierTest();
	supplierTest.testAllDomain();

	SupplierRepositoryTest supplierRepository = new SupplierRepositoryTest();
	supplierRepository.testAllRepository();

	SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
	supplierServiceTest.testAllService();

	System.out.println("All tests have run successfully");

}
}
