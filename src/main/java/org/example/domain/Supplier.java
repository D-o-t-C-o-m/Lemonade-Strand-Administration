package org.example.domain;

import java.util.Objects;

public class Supplier {
private int id;
private String name;
private String email;

public Supplier(){
	this.id = -1;
}

public Supplier(Integer id, String name, String email) {
	this.id = id;
	this.name = name;
	this.email = email;
}
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public String getEmail() {
	return email;
}
public void setId(int id) {
	this.id = id;
}
public void setName(String name) {
	this.name = name;
}
public void setEmail(String email) {
	this.email = email;
}


@Override
public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass()) return false;
	Supplier supplier = (Supplier) o;
	return getId() == supplier.getId() && Objects.equals(getName(), supplier.getName()) && Objects.equals(getEmail(), supplier.getEmail());
}

@Override
public String toString() {
	return "Supplier{id=" + id + ", name='" + name + '\'' +", email='" + email + "'" +"}";
}
}
