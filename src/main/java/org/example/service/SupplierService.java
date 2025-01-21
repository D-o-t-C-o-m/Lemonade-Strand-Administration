package org.example.service;
import org.example.repository.SupplierRepository;
import org.example.domain.Supplier;

import java.util.Random;

public class SupplierService {

private SupplierRepository supplierRepository;

public SupplierService(SupplierRepository supplierRepository) {
	this.supplierRepository = supplierRepository;
}
public Supplier saveSupplier(int id, String name, String email) {
	Supplier supplier = new Supplier(id, name, email);
	Supplier savedSupplier = supplierRepository.save(supplier);
	return savedSupplier;
}
public void deleteSupplier(int id) {
	this.supplierRepository.delete(id);
}
public Supplier updateSupplier(int id, String name, String email) {
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
