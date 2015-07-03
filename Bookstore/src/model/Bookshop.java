package model;

public class Bookshop extends Book implements java.io.Serializable{

	private Double Price;
	
	private int Stock;
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}

	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
}
