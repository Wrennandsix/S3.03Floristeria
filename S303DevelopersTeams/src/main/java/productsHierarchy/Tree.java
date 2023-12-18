package productsHierarchy;

import java.util.Objects;

import com.j256.simplecsv.common.CsvColumn;

public class Tree extends Product {
	@CsvColumn(columnName = "height")
	private float height;
	@CsvColumn(columnName = "id")
	private int id;
	private static int nextId = 0;

	public Tree() {
		this.id = ++nextId;
	}

	public Tree(String name, float price, float height, int stock) {
		super(name, price, stock);
		this.height = height;
		this.id = ++nextId;
	}
	
	public Tree(Tree tree) {
		super(tree.getName(), tree.getPrice(), tree.getStock());
		this.height = tree.getHeight();

	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(height);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		return Float.floatToIntBits(height) == Float.floatToIntBits(other.height);
	}

	
	@Override
	public String toString() {
		return "Tree [height=" + height + ", name=" + name + ", price=" + price + ", stock=" + stock + ", id=" + id + "]";
	}


}
 