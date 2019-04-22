/**
 * Filename: OrderState.java
 * Description: States that can be used by, for example, an Order.
 * @author Alexander Seiler, 11771276
 * @since 19.04.2019
 */
package rbvs;

// methods 'values' and 'valueOf' does not have to be implemented since ENUM implicitly declares those methods

public enum OrderState {
	CANCELLED,
	OPEN,
	PAID;
}
