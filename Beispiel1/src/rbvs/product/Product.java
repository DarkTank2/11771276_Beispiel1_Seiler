/**
 * Filename: Product.java
 * Description: This class is used to encapsulate the basic properties of a product. In this case a product consists of two attributes: its name and its price. This class is used as a common abstract superclass to concrete implementations given further down in the hierarchy.
 * @author Alexander Seiler, 11771276
 * @since 16.04.2019
 */
package rbvs.product;

public abstract class Product implements IProduct {

	private String name;
	private float price;
	
	/**
	 * Constructor for class Product.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public Product(String name) {
		super();
		initialize(name, 0);
	}
	/**
	 * Constructor for class Product.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param price
	 */
	public Product(String name, float price) {
		super();
		initialize(name, price);
	}


	/* (non-Javadoc)
	 * @see ict.basics.IDeepCopy#deepCopy()
	 */
	@Override
	public abstract Product deepCopy();

	/* (non-Javadoc)
	 * @see rbvs.product.IProduct#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	/**
	 * Setter for field name
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public void setName(String name) {
		this.name = name == null ? "" : name;
	}

	/* (non-Javadoc)
	 * @see rbvs.product.IProduct#getPrice()
	 */
	@Override
	public float getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

	public void setPrice(float price) throws IllegalArgumentException {
		if (price < 0) {
			throw new IllegalArgumentException("[Product.setPrice()] Illegal Argument: Argument is smaller than 0.");
		}
		this.price = price;
	}
	
	/**
	 * Initializes the attributes of the object.
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param price
	 */
	private void initialize(String name, float price) {
		this.name = name == null ? "" : name;
		this.price = price;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [name=" + this.name + ", price=" + this.price + "]";
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @author Alexander Seiler, 11771276
	 * @param obj
	 */
	public final boolean equals(Object obj) {
		if (!(obj instanceof Product)) {
			return false;
		}
		Product p = (Product) obj;
		return p.getName() == this.getName();
	}
}
