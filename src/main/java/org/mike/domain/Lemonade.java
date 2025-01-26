package org.mike.domain;

public class Lemonade extends Entity{
private String name;
private int totalPrice;

public Lemonade(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public int getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}
public void setName(String name) {
	this.name = name;
}

}
