package org.example;

import org.example.exceptions.IDNotUniqueException;
import org.example.repository.SupplierFileRepository;
import org.example.service.SupplierService;
import org.example.userinterface.UserInterface;
import org.example.validators.SupplierValidator;

import java.io.FileNotFoundException;

public class Main {
public static void main(String[] args) throws FileNotFoundException, IDNotUniqueException {

	SupplierFileRepository supplierRepository = new SupplierFileRepository("suppliers.csv");
	SupplierService supplierService = new SupplierService(supplierRepository);
	UserInterface userInterface = new UserInterface(supplierService);

	userInterface.runMenu();
}
}