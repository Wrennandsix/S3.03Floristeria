package productsHierarchy;

import java.util.Objects;

import com.j256.simplecsv.common.CsvColumn;

public abstract class Product {
	@CsvColumn(columnName = "name")
	protected String name;
	@CsvColumn(columnName = "price")
	protected float price;
	@CsvColumn(columnName = "stock")
	protected int stock;

	public Product() {

	}

	public Product(String name, float price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;

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
	
	

	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(name, other.name) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}
	

}
