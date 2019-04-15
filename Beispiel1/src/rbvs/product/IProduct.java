/**
 * Filename: IProduct.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 14.04.2019
 */
package rbvs.product;

import ict.basics.IDeepCopy;

public interface IProduct extends IDeepCopy {

	/**
	 * Returns the name of the product.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	String getName();
	
	/**
	 * Returns the price of the product.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	float getPrice();
}
