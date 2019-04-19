/**
 * Filename: SimpleProduct.java
 * Description: Implementation of a product that only consists of a single item with a name and price. It extends the abstract Product class.
 * @author Alexander Seiler, 11771276
 * @since 17.04.2019
 */
package rbvs.product;

public class SimpleProduct extends Product {

	/**
	 * Constructor for class SimpleProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public SimpleProduct(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for class SimpleProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param price
	 */
	public SimpleProduct(String name, float price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see rbvs.product.Product#deepCopy()
	 */
	@Override
	public SimpleProduct deepCopy() {
		this.logger.info("[function] deepCopy() of " + this.getName());
		// TODO Auto-generated method stub
		return new SimpleProduct(this.getName(), this.getPrice());
	}

}
