package org.example;

import org.example.repository.SupplierRepository;
import org.example.service.SupplierService;
import org.example.userinterface.UserInterface;
import org.example.validators.SupplierValidator;

//import org.example.MainTest;


public class Main {
public static void main(String[] args) {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierService supplierService = new SupplierService(supplierRepository);
	SupplierValidator supplierValidator = new SupplierValidator();
	UserInterface userInterface = new UserInterface(supplierService);

	//MainTest tests = new MainTest();
	//tests.runAllTests();

	userInterface.runMenu();
}
}