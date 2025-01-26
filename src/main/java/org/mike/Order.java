package org.mike;

import java.time.LocalDateTime;

public class Order {
private int quantity;
private int finalPrice;
private LocalDateTime date;

public Order(int quantity, int finalPrice, LocalDateTime date) {
	this.quantity = quantity;
	this.finalPrice = finalPrice;
	this.date = date;
}
public int getQuantity() {
	return quantity;
}
public int getFinalPrice() {
	return finalPrice;
}
public LocalDateTime getDate() {
	return date;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public void setFinalPrice(int finalPrice) {
	this.finalPrice = finalPrice;
}
public void setDate(LocalDateTime date) {
	this.date = date;
}
}
