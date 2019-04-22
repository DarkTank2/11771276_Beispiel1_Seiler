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
//		discount must be between 0 and 100, else set it to the closest of those values
//		if smaller than 0 => set to 0
//		else if higher than 100 => set to 100
//		else set to value
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
//		same thoughts as above at line 26
		this.discount = discountPercentage < 0 ? 0 : (discountPercentage > 100 ? 100 : discountPercentage);
		this.containedProducts = new Vector<Product>();
//		adding each item from the passed list to this' list via streams
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
//		basically a copy of this product, is anew product with the same fields but different reference
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
//		List.remove(Object) works with Object.equals(Object) (see java-docs for this)
		return this.containedProducts.remove(product);
	}
	
	/**
	 * Returns the list of products.
	 * @author Alexander Seiler, 11771276
	 * @return the list of products
	 */
	public Collection<Product> getProducts() {
		this.logger.trace("[get] getProducts() of " + this.getName());
		return this.containedProducts;
	}
	
	public float getPrice() {
		this.logger.trace("[get] getPrice() of " + this.getName());
//		summing up the price with streams and mapToDouble and sum
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
//		building up the list of contained products as one string
		String tmp = this.containedProducts
				.stream()
				.map(el -> el.toString())
				.collect(Collectors.joining(","));
		return "CompositeProduct [name=" + this.getName() + ",price=" + this.getPrice() + ",containedProducts=[" + tmp + "],discount=" + this.discount + "]";
	}
	
	
}
