package ticket;

import java.util.ArrayList;
import java.util.List;

import Florist.Florist;
import input.Input;
import productsHierarchy.Product;

public class Ticket {
	
	private String name;
	private Florist florist;
	private ArrayList<Product> productsList = new ArrayList<Product>();;
	private float price = 0;
	private int id;
	private static int nextId = 0;
	
	public Ticket(String name, Florist florist/*, ArrayList<Product> productsLis*/) {
		this.productsList = productsList;
		this.name = name;
		this.florist = florist;
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
	
	
	public void addProducts() throws Exception {
		
		int opcio;

		do {
			opcio = Input.readInt("Menu afegir productes:"
					+ "\n1- Agegir arbre."
					+ "\n2- Afegir flor."
					+ "\n3- Afegir decoració."
					+ "\n0- Deixar d'afegir productes");

			switch (opcio) {
			case 1:
				florist.withdrawTree();
				break;
			case 2:
				florist.withdrawFlower();
				break;
			case 3:
				florist.withdrawDecor();
				break;
			case 0:
				System.out.println("Estas sortint de l'aplicacio");
				break;

			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (opcio != 0);
	}
		



	public float calculatePrice(ArrayList<Product> productsList) {

		return (float) productsList.stream()
				.mapToDouble(Product::getPrice)
				.sum();
	}
	
	@Override
	public String toString() {
		return "Ticket [name=" + name + ", productsList=" + productsList + ", Price=" + price + ", id=" + id + "]";
	}
}
