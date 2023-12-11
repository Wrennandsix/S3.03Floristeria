package productsHierarchy;

public class Flower extends Product {

	private String colour;

	public Flower(String name, float price, int id, String colour,int stock) {
		super(name, price, id,stock);
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Flower [colour=" + colour + ", name=" + name + ", price=" + price + ", id=" + id + "]";
	}

}
