package com.clx.util;

public class Product {
	private int productid;
	private String name;
	private double price;
	private Factory factory;
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int id) {
		this.productid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
}
