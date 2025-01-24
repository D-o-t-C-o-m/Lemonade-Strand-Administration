package org.example.domain;

public class Product {
private int id;
private String name;
private String description;
private int quantity;
private int price;
private Supplier supplier;

public Product(int id, String name, String description, int quantity, int price, Supplier supplier) {
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	this.supplier = supplier;
	this.id = id;
	this.description = description;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Supplier getSupplier() {
	return supplier;
}

public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
@Override
public String toString() {
	return "Product{" +
			"id="+id+
			", name-'"+name+'\''+
			", price-"+price+
			", quantity="+quantity+
			'}';
}
}