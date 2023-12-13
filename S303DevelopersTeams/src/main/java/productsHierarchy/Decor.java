package productsHierarchy;

public class Decor extends Product {

	private String material;

	public Decor(String name, float price, String type, int stock) {
		super(name, price, stock);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Decor [type=" + material + ", name=" + name + ", price=" + price + ", id=" + id + "]";
	}
}
