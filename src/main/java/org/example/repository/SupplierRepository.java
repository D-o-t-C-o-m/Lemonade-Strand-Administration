package org.example.repository;

import org.example.domain.Supplier;
import java.util.HashMap;
import java.util.Map;

public class SupplierRepository {
private Map<Integer, Supplier> suppliers;

public SupplierRepository() {
	this.suppliers = new HashMap<>();
}

public Supplier save(Supplier supplier) {
	this.suppliers.put(supplier.getId(), supplier);
	return supplier;
}
public Supplier update(Supplier supplier) {
	if (this.suppliers.containsKey(supplier.getId())) {
		this.suppliers.put(supplier.getId(), supplier);
	}
	return supplier;
}
public void delete(int supplierId) {
	this.suppliers.remove(supplierId);
}
public Iterable<Supplier> findAll() {
	return this.suppliers.values();
}
public Supplier findById(int id) {
	return this.suppliers.get(id);
}
}
