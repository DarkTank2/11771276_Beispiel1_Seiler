/**
 * Filename: DuplicateProductException.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 19.04.2019
 */
package rbvs;

import rbvs.product.IProduct;

public class DuplicateProductException extends Exception {

	private IProduct product;
	private static final long	serialVersionUID = 101101;
	
	/**
	 * Constructor for class DuplicateProductException.java
	 * @author Alexander Seiler, 11771276
	 * @param product
	 */
	public DuplicateProductException(IProduct product) {
		this.product = product;
	}
	
	
	public String getMessage() {
		return "Product " + this.product.getName() + " already exists!";
	}
}
