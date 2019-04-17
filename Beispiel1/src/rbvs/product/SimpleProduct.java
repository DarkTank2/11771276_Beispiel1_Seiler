/**
 * Filename: SimpleProduct.java
 * Description: Implementation of a product that only consists of a single item with a name and price. It extends the abstract Product class.
 * @author Alexander Seiler, 11771276
 * @since 17.04.2019
 */
package rbvs.product;

import utils.Logger;

public class SimpleProduct extends Product {

	private Logger logger;
	
	/**
	 * Constructor for class SimpleProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public SimpleProduct(String name) {
		super(name);
		this.logger = new Logger("SimpleProduct_" + name);
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
		this.logger = new Logger("SimpleProduct_" + name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see rbvs.product.Product#deepCopy()
	 */
	@Override
	public SimpleProduct deepCopy() {
		logger.info("[function] deepCopy()");
		// TODO Auto-generated method stub
		return new SimpleProduct(this.getName(), this.getPrice());
	}

}
