/**
 * Filename: CompositeProduct.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 18.04.2019
 */
package rbvs.product;

import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

public class CompositeProduct extends Product {

	private Collection<Product> containedProducts;
	private float discount;
	
	/**
	 * Constructor for class CompositeProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param discountPercentage
	 */
	public CompositeProduct(String name, float discountPercentage) {
		super(name);
		this.discount = discountPercentage < 0 ? 0 : (discountPercentage > 100 ? 100 : discountPercentage);
		this.containedProducts = new Vector<Product>();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for class CompositeProduct.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 * @param discountPercentage
	 * @param products
	 */
	public CompositeProduct(String name, float discountPercentage, Collection<Product> products) {
		super(name);
		this.discount = discountPercentage < 0 ? 0 : (discountPercentage > 100 ? 100 : discountPercentage);
		this.containedProducts = new Vector<Product>();
		products
			.stream()
			.forEach(prod -> this.containedProducts.add(prod));
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see rbvs.product.Product#deepCopy()
	 */
	@Override
	public CompositeProduct deepCopy() {
		this.logger.info("[function] deepCopy() of " + this.getName());
		// TODO Auto-generated method stub
		return new CompositeProduct(this.getName(), this.discount, this.containedProducts);
	}
	
	/**
	 * Adds a Product to the list of products.
	 * @author Alexander Seiler, 11771276
	 * @param product
	 */
	public void addProduct(Product product) {
		this.logger.info("[function] addProduct() " + product.getName() + " to " + this.getName());
		this.containedProducts.add(product);
	}

	/**
	 * Removes a Product from the list of products.
	 * @author Alexander Seiler, 11771276
	 * @param product
	 * @return removal of product
	 */
	public boolean removeProduct(Product product) {
		this.logger.info("[function] removeProduct() " + product.getName() + " from " + this.getName());
		return this.containedProducts.remove(product);
	}
	
	/**
	 * Returns the list of products.
	 * @author Alexander Seiler, 11771276
	 * @return the list of products
	 */
	public Collection<Product> getProducts() {
		this.logger.info("[function] getProducts() of " + this.getName());
		return this.containedProducts;
	}
	
	public float getPrice() {
		this.logger.info("[function] getPrice() of " + this.getName());
		double sum = this.containedProducts
				.stream()
				.mapToDouble(el -> el.getPrice())
				.sum();
//		100 - discount because the discount is the percentage of the price that is given back
		return (float) (sum * (100 - this.discount) / 100);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		this.logger.info("[function] toString() of " + this.getName());
		String tmp = this.containedProducts
				.stream()
				.map(el -> el.toString())
				.collect(Collectors.joining(","));
		return "CompositeProduct [name=" + this.getName() + ",price=" + this.getPrice() + ",containedProducts=[" + tmp + "],discount=" + this.discount + "]";
	}
	
	
}