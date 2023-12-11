package ticket;

import java.util.ArrayList;
import java.util.List;

import productsHierarchy.Product;

public class Ticket {
	
	private String name;
	private List<Product>productsList;
	private float Price;
	int id;
	private static int nextId = 0;
	
	public Ticket(String name, List<Product> productsList, float price, int id) {
		this.name = name;
		this.productsList = new ArrayList<Product>();
		Price = price;
		this.id = nextId++;
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

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ticket [name=" + name + ", productsList=" + productsList + ", Price=" + Price + ", id=" + id + "]";
	}

	public float calculatePrice(ArrayList<Product> productsList) {

		return (float) productsList.stream()
				.mapToDouble(Product::getPrice)
				.sum();
	}
}
