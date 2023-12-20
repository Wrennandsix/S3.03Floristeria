package productsHierarchy;

import java.util.Objects;

import com.j256.simplecsv.common.CsvColumn;

public class Flower extends Product {
	@CsvColumn(columnName = "colour")
	private String colour;
	@CsvColumn(columnName = "id")
	private int id;
	private static int nextId = 0;

	public Flower() {
		this.id = ++nextId;
	}
	public Flower(String name, float price, String colour,int stock) {
		super(name, price, stock);
		this.colour = colour;
		this.id = ++nextId;
	}
	
	public Flower(Flower flower) {
		super(flower.getName(), flower.getPrice(), flower.getStock());
		this.colour = flower.getColour();

	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	
	public int getId() {
		return id;
	}
	
	public static void setNextId() {
		nextId --;
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
