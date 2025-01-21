package org.example.service;

import org.example.domain.Supplier;
import org.example.repository.SupplierRepository;

public class SupplierServiceTest {
private SupplierService supplierService;

private void setUp(){
	SupplierRepository supplierRepository = new SupplierRepository();
	supplierService = new SupplierService(supplierRepository);
}

public void shouldSaveSupplier_whenSavedIsCalled(){
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");

	assert savedSupplier != null;
	assert savedSupplier.getId() == 1;
	assert savedSupplier.getName().equals("Lemonades");
	assert supplierService.findById(1).getName().equals("Lemonades");
}

public void shouldUpdateSupplier_whenUpdateIsCalled(){
	setUp();
	Supplier supplierToUpdate = new Supplier(1, "Lemonades", "Contact@lemonades.com");
	Supplier updatedSupplier = supplierService.updateSupplier(1, "hom", "Hom@Burgher");

	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Hom");
	assert updatedSupplier.getEmail().equals("hom@Burgher");

}

public void shouldDeleteSupplier_whenDeletedIsCalled(){
	setUp();
	Supplier supplierToDelete = supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.deleteSupplier(supplierToDelete.getId());

	assert supplierService.findById(1) == null;
}

public void shouldFindAllSupplier_whenFindAllIsCalled(){
	setUp();
	supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.saveSupplier(2, "Leo nades", "Coct@leonades.com");

	Supplier firstSupplier = supplierService.findById(1);
	Supplier secondSupplier = supplierService.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;

}
public void testAllService(){
	shouldSaveSupplier_whenSavedIsCalled();
	shouldUpdateSupplier_whenUpdateIsCalled();
	shouldDeleteSupplier_whenDeletedIsCalled();
	shouldFindAllSupplier_whenFindAllIsCalled();
}
}
