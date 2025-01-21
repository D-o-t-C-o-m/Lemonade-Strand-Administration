package org.example;

public class Lemonade {
private String name;
private int totalPrice;
public Lemonade(String name, int totalPrice) {
	this.name = name;
	this.totalPrice = totalPrice;
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
