package ticket;

import java.util.ArrayList;
import java.util.List;

import productsHierarchy.Product;

public class Ticket {
	
	private String name;
	private ArrayList<Product> productsList = new ArrayList<Product>();;
	private float price = 0;
	private int id;
	private static int nextId = 0;
	
	public Ticket(String name,ArrayList<Product> productsList) {
		this.productsList = productsList;
		this.name = name;
		this.id = ++nextId;
		this.price = calculatePrice(productsList);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(ArrayList<Product> productsList) {
		this.productsList = productsList;
		
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ticket [name=" + name + ", productsList=" + productsList + ", Price=" + price + ", id=" + id + "]";
	}

	public float calculatePrice(ArrayList<Product> productsList) {

		return (float) productsList.stream()
				.mapToDouble(Product::getPrice)
				.sum();
	}
}
