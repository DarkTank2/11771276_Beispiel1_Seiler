/**
 * Filename: Order.java
 * Description: An Order is a specific type of Record. It has an identifier, is associated with a Table and contains a list of IProducts. In essence, it states what products have been ordered at a specific table. Furthermore, an Order has a status that indicates in which state the order currently is. Possibly values are specified in OrderState.
 * @author Alexander Seiler, 11771276
 * @since 19.04.2019
 */
package rbvs;

import rbvs.record.Record;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import ict.basics.IDeepCopy;
import rbvs.product.*;

public class Order extends Record implements IDeepCopy {

	private OrderState currentState = OrderState.OPEN;
	private List<IProduct> products;
	private Table table;
	
	/**
	 * Constructor for class Order.java
	 * @author Alexander Seiler, 11771276
	 * @param id
	 */
	public Order(long id, Table table, List<IProduct> products) {
		super(id);
		this.table = table;
		this.products = new Vector<IProduct>();
		products
			.stream()
			.forEach(el -> {
				this.products.add(el);
			});
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns a list of copies of the ordered IProducts.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public List<IProduct> getProducts() {
		this.logger.trace("[get] products");
		List<IProduct> l = new Vector<IProduct>();
		this.products
			.stream()
			.forEach(el -> {
				l.add((IProduct) el.deepCopy());
			});
		return l;
	}
	
	/**
	 * Set the state of the order record
	 * @author Alexander Seiler, 11771276
	 * @param newStatus
	 * @return
	 */
	public boolean setState(OrderState newStatus) {
		this.logger.info("[set] setState()");
		if (this.currentState == newStatus) {
			this.logger.debug("[debug] stati are equal");
			return true;
		}
		if (!this.isCancelled() && !this.isPaid()) {
			this.logger.trace("[set] set of status successful, new status is " + newStatus.toString());
			this.currentState = newStatus;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the current state of the order.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public OrderState getState() {
		this.logger.trace("[get] currentState is " + this.currentState.toString());
		return this.currentState;
	}
	
	/**
	 * Checks if the order is OrderState.CANCELLED.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public boolean isCancelled() {
		this.logger.trace("[check] if cancelled");
		return this.currentState == OrderState.CANCELLED;
	}
	
	/**
	 * Checks if the order has already been OrderState.PAID.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public boolean isPaid() {
		this.logger.trace("[check] if paid");
		return this.currentState == OrderState.PAID;
	}
	
	/**
	 * Returns the Table associated with this order.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public Table getTable() {
		this.logger.trace("[get] table");
		return this.table;
	}
	
	@Override
	public Order deepCopy() {
		// TODO Auto-generated method stub
		this.logger.info("[deep-copy] of order with id '" + this.getIdentifier() + "´' for table '" + this.getTable().getTableIdentifier() + "'");
		Order o = new Order(this.getIdentifier(), this.getTable(), this.getProducts());
		o.setState(this.getState());
		return null;
	}
	
	/**
	 * An Order is equal to an object if it has the same class and all of its attributes match.
	 * @author Alexander Seiler, 11771276
	 * @param obj
	 * @return
	 */
	public boolean equals(Object obj) {
		this.logger.trace("[equals] of order with id '" + this.getIdentifier() + "' for table '" + this.getTable().getTableIdentifier() + "'");
		if (!(obj instanceof Order)) return false;
		Order o = (Order) obj;
		if (o.getIdentifier() != this.getIdentifier()) return false;
		if (!o.getTable().equals(this.getTable())) return false;
		if (o.getState() != this.getState()) return false;
//		get the products of the object, filter out the products that are not contained in the list of products of this
		List<IProduct> l = o.getProducts()
				.stream()
				.filter(el -> !this.products.contains(el))
				.collect(Collectors.toList());
//		if the filtered list is bigger than 0 than there are Products contained in the object that are not in this
		if (l.size() > 0) return false;
//		do it vice versa to cross check if this contains Products that are not in the object
		l = this.products
				.stream()
				.filter(el -> !o.getProducts().contains(el))
				.collect(Collectors.toList());
		if (l.size() > 0) return false;
//		Imagine a case where all fields are equal
//		Obj contains of two Products: [name=first,price=3],[name=second,price=4]
//		This contains three Products: [name=first,price=3],[name=second,price=4],[name=third,price=7] (different references but same content so they are 'equal')
//		In the first attempt to filter each product of Obj gets checked and since all two Products are contained within this' list, none are added to the generated list
//		This' list also contains the third element, thus they are not equal, yet only the first check would suggest equality.
//		But due to the second vice-versa-check, the differences are spotted and returned accordingly
		return true;
	}

}
