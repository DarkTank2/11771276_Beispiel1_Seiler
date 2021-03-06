/**
 * Filename: Table.java
 * Description: A Table is a simple Plain Old Java Object (POJO) and has an identifier and a number of seats.
 * @author Alexander Seiler, 11771276
 * @since 15.04.2019
 */
package rbvs;

import utils.Logger;

public class Table {

	// Fields:
	private String id;
	private int seats;
	private Logger logger;
	
	/**
	 * Constructor for class Table.java
	 * @author Alexander Seiler, 11771276
	 * @param id
	 */
	public Table(String id) {
		super();
		this.id = id;
		this.seats = 2;
		logger = new Logger("Table_" + id);
	}

	/**
	 * Constructor for class Table.java
	 * @author Alexander Seiler, 11771276
	 * @param id
	 * @param seats
	 */
	public Table(String id, int seats) {
		super();
		this.id = id;
		this.seats = seats;
		logger = new Logger("Table_" + id);
	}
	
	/**
	 * Returns the number of seats.
	 * @author Alexander Seiler, 11771276
	 * @return seats
	 */
	public int getSeatCount() {
		logger.trace("[get] seatCount of " + this.id);
		return this.seats;
	}
	
	/**
	 * Sets the number of seats.
	 * @author Alexander Seiler, 11771276
	 * @param seatCount
	 */
	public void setSeatCount(int seatCount) {
		logger.trace("[set] SeatCount of " + this.id);
		this.seats = seatCount;
	}
	
	/**
	 * Returns the identifier of the Table.
	 * @author Alexander Seiler, 11771276
	 * @return id
	 */
	public String getTableIdentifier() {
		logger.trace("[get] TableIdentifier of " + this.id);
		return this.id;
	}
	
	/**
	 * Returns a string representation of the object.
	 * @author Alexander Seiler, 11771276
	 * @return the stringified Table
	 */
	public String toString() {
		logger.info("[function] toString()");
		return "Table [id=" + this.id + ",seats=" + this.seats + "]";
	}
	
	/**
	 * Indicates whether some other object is equal to this one.
	 * @author Alexander Seiler, 11771276
	 * @return equality
	 */
	public boolean equals(Object obj) {
		logger.info("[function] equals()");
		if (!(obj instanceof Table)) {
			logger.trace("[equals] Object is not a Table");
			return false;
		}
		Table t = (Table) obj;
//		using String.equals(String) since non static user input with same content but different reference
		if (t.getTableIdentifier().equals(this.id) && t.seats == this.seats) {
			logger.trace("[equals] Object matches");
			return true;
		} else {
			logger.trace("[equals] Object does not match");
			return false;
		}
	}
}
