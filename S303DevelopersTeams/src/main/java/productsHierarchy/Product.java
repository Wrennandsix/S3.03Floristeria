package productsHierarchy;

public abstract class Product {

	protected String name;
	protected float price;
	protected int id;
	private static int nextId = 0;

	public Product(String name, float price, int id) {
		this.name = name;
		this.price = price;
		this.id = nextId++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
