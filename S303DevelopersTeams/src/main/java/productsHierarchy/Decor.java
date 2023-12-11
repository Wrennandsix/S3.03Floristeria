package productsHierarchy;

public class Decor extends Product {

	private String type;

	public Decor(String name, float price, int id, String type,int stock) {
		super(name, price, id, stock);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Decor [type=" + type + ", name=" + name + ", price=" + price + ", id=" + id + "]";
	}
}
