package productsHierarchy;

import java.util.Objects;

public class Flower extends Product {

	private String colour;

	public Flower(String name, float price, String colour,int stock) {
		super(name, price, stock);
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(colour);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flower other = (Flower) obj;
		return Objects.equals(colour, other.colour);
	}

	@Override
	public String toString() {
		return "Flower [colour=" + colour + ", name=" + name + ", price=" + price + ", id=" + id + "]";
	}

}
