package org.example.service;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.SupplierRepository;
import org.example.domain.Supplier;
import org.example.validators.SupplierValidator;

import java.io.FileNotFoundException;

public class SupplierService {
private SupplierValidator supplierValidator;
private SupplierRepository supplierRepository;

public SupplierService(SupplierRepository supplierRepository, SupplierValidator supplierValidator) {
	this.supplierRepository = supplierRepository;
	this.supplierValidator = new SupplierValidator();
}
public Supplier saveSupplier(int id, String name, String email) throws ValidationException, IDNotUniqueException, FileNotFoundException {
	Supplier supplier = new Supplier(id, name, email);
	//supplierValidator.validateSupplier(supplier);
	Supplier savedSupplier = supplierRepository.save(supplier);

	return savedSupplier;
}
public void deleteSupplier(int id) throws FileNotFoundException {
	this.supplierRepository.delete(id);
}
public Supplier updateSupplier(int id, String name, String email) throws FileNotFoundException {
	Supplier supplierToUpdate = new Supplier(id,name,email);
	Supplier updatedSupplier = this.supplierRepository.update(supplierToUpdate);
	return updatedSupplier;
}
public Iterable<Supplier> findAll() {
	return this.supplierRepository.findAll();
}
public Supplier findById(int id) {
	return this.supplierRepository.findById(id);
}
}
