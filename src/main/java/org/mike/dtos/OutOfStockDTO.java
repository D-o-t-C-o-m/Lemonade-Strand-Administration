package org.mike.dtos;
import org.mike.domain.*;



public class OutOfStockDTO {
private Product product;
private Supplier supplier;

public OutOfStockDTO(Product product, Supplier supplier) {
	this.product = product;
	this.supplier = supplier;
}

public Product getProduct() {
	return product;
}

public Supplier getSupplier() {
	return supplier;
}

public String getSupplierContact(Supplier supplier) {
	return supplier.getName() + ", " + supplier.getEmail();
}
}

