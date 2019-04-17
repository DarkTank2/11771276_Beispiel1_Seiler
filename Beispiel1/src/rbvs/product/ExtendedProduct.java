/**
 * Filename: ExtendedProduct.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 17.04.2019
 */
package rbvs.product;

import utils.Logger;

public class ExtendedProduct extends SimpleProduct {

	private ExtendedProduct savedState;
	private Logger logger;
	
	/**
	 * Constructor for class ExtendedProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param savedState
	 */
	public ExtendedProduct(ExtendedProduct product) {
		super(product.getName(), product.getPrice());
		this.savedState = null;
	}

	/**
	 * Constructor for class ExtendedProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param price
	 */
	public ExtendedProduct(String name, float price) {
		super(name, price);
		this.savedState = null;
		this.logger = new Logger("ExtendedProduct_" + name);
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		logger.info("[function] setName()");
		this.savedState = new ExtendedProduct(this.getName(), 0);
		super.setName(name);
	}
	
	public void setPrice(float price) throws IllegalArgumentException {
		logger.info("[function] setPrice()");
		this.savedState = new ExtendedProduct(null, this.getPrice());
		try {
			super.setPrice(price);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
	public boolean undo() {
		logger.info("[function] undo()");
		if (this.savedState == null) {
			return false;
		}
		if(this.savedState.getName() == "") {
			super.setPrice(this.savedState.getPrice());
		} else {
			super.setName(this.savedState.getName());
		}
		this.savedState = null;
		return true;
	}
}
