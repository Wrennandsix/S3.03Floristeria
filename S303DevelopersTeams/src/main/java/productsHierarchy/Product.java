package productsHierarchy;

public abstract class Product {

	protected String name;
	protected float price;
	protected int id;
	protected int stock;
	private static int nextId = 0;
	

	public Product(String name, float price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.id = ++nextId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
