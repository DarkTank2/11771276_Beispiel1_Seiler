/**
 * Filename: ExtendedProduct.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 17.04.2019
 */
package rbvs.product;

public class ExtendedProduct extends SimpleProduct {

	private ExtendedProduct savedState;
	/**
	 * Constructor for class ExtendedProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param savedState
	 */
	public ExtendedProduct(ExtendedProduct product) {
		super(product.getName(), product.getPrice());
		this.savedState = product.savedState;
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
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.logger.info("[function] setName()");
		this.savedState = new ExtendedProduct(this);
		super.setName(name);
	}
	
	public void setPrice(float price) throws IllegalArgumentException {
		this.logger.info("[function] setPrice()");
		this.savedState = new ExtendedProduct(this);
		try {
			super.setPrice(price);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
	public boolean undo() {
		this.logger.info("[function] undo()");
//		if the saved state is null return false since there is no undo possible
		if (this.savedState == null) {
			return false;
		}
//		set the name and the price accordingly to the saved state
		super.setName(this.savedState.getName());
		super.setPrice(this.savedState.getPrice());
//		recursively undo the changes of the saved states
		boolean flag = this.savedState.undo();
//		if the saved state can not undo it means that the end of the chain is reached and set this saved state to null to indicate the end
		if (flag == false) this.savedState = null;
		return true;
	}
	
	public ExtendedProduct deepCopy() {
		this.logger.info("[function] deepCopy() of ExtendedProduct");
		return new ExtendedProduct(this);
	}
	
	@Override
	public String toString() {
		this.logger.info("[function] toString() of ExtendedProduct");
		return "ExtendedProduct [name=" + this.getName() + ",price=" + this.getPrice() + ",undoAvailable="  + (this.savedState == null ? "false" : "true") + "]";
	}
}
