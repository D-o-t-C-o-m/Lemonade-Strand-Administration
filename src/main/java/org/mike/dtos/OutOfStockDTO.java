package org.mike.dtos;

import org.mike.domain.Product;
import org.mike.domain.Supplier;


public class OutOfStockDTO {
private final Product product;
private final Supplier supplier;

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

}

