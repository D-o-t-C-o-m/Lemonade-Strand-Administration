package org.mike.domain;

import java.util.Objects;

public class LemonadeRecipe extends Entity {
private int id;
private Product product;
private Lemonade lemonade;
private int quantity;

public LemonadeRecipe(int id, Product product, Lemonade lemonade, int quantity) {
	this.id = id;
	this.product = product;
	this.lemonade = lemonade;
	this.quantity = quantity;
}
public Product getProduct() {
	return product;
}
public Lemonade getLemonade() {
	return lemonade;
}
public void setProduct(Product product) {
	this.product = product;
}
public void setLemonade(Lemonade lemonade) {
	this.lemonade = lemonade;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getId() { return id; }
public void setId(int id) { this.id = id; }
@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass()) return false;

	LemonadeRecipe other = (LemonadeRecipe) obj;
	return this.product.equals(other.product) &&
			this.lemonade.equals(other.lemonade) &&
			this.quantity == other.quantity;
}

@Override
public int hashCode() {
	return Objects.hash(product, lemonade, quantity);
}
}
