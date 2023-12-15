package productsHierarchy;

import java.util.Objects;

public class Tree extends Product {

	private float height;

	public Tree(String name, float price, float height,int stock) {
		super(name, price, stock);
		this.height = height;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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
		return "Tree [height=" + height + ", name=" + name + ", price=" + price + ", id=" + id + "]";
	}


}
 