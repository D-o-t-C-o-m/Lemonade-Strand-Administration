package org.mike.domain;

public class LemonadeRecipe extends Entity {
private Product product;
private Lemonade lemonade;
private int quantity;

public LemonadeRecipe(Product product, Lemonade lemonade, int quantity) {
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

}