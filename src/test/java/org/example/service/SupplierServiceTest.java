package org.example.service;

import org.example.domain.Supplier;
import org.example.exceptions.IDNotUniqueException;
import org.example.exceptions.ValidationException;
import org.example.repository.SupplierRepository;

public class SupplierServiceTest {
private SupplierService supplierService;

private void setUp(){
	SupplierRepository supplierRepository = new SupplierRepository();
	supplierService = new SupplierService(supplierRepository);
}

public void shouldSaveSupplier_whenSavedIsCalled() throws ValidationException, IDNotUniqueException {
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

public void shouldDeleteSupplier_whenDeletedIsCalled() throws ValidationException, IDNotUniqueException {
	setUp();
	Supplier supplierToDelete = supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.deleteSupplier(supplierToDelete.getId());

	assert supplierService.findById(1) == null;
}

public void shouldFindAllSupplier_whenFindAllIsCalled() throws ValidationException, IDNotUniqueException {
	setUp();
	supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.saveSupplier(2, "Leo nades", "Coct@leonades.com");

	Supplier firstSupplier = supplierService.findById(1);
	Supplier secondSupplier = supplierService.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;

}
public void shouldNotSaveTheElement_whenWeAddNotUniqueElement() throws IDNotUniqueException {
	SupplierRepository supplierRepository = new SupplierRepository();
	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	Supplier secondSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

	try {
		Supplier firstSavedSupplier = supplierRepository.save(firstSupplierToSave);
		Supplier secondSavedSupplier = supplierRepository.save(secondSupplierToSave);
		assert false;
	} catch (IDNotUniqueException e) {
		assert true;
	}
}
public void testAllService() throws IDNotUniqueException, ValidationException {
	shouldSaveSupplier_whenSavedIsCalled();
	shouldUpdateSupplier_whenUpdateIsCalled();
	shouldDeleteSupplier_whenDeletedIsCalled();
	shouldFindAllSupplier_whenFindAllIsCalled();
	shouldNotSaveTheElement_whenWeAddNotUniqueElement();
}
}
