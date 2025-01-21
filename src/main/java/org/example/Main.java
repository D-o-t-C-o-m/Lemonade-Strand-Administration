package org.example;

import org.example.repository.SupplierRepository;
import org.example.service.SupplierService;
import org.example.userinterface.UserInterface;



public class Main {
public static void main(String[] args) {
	SupplierRepository supplierRepository = new SupplierRepository();
	SupplierService supplierService = new SupplierService(supplierRepository);
	UserInterface userInterface = new UserInterface(supplierService);

	MainTest tests = new MainTest();
	tests.runAllTest();

	userInterface.runMenu();
}
}