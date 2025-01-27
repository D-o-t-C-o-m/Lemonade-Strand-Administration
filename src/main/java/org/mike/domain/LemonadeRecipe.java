package org.mike.domain;

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

}
