/**
 * Filename: Record.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 19.04.2019
 */
package rbvs.record;

import utils.Logger;

public class Record implements IRecord {

	private long id;
	protected Logger logger;
	
	/**
	 * Constructor for class Record.java
	 * @author Alexander Seiler, 11771276
	 * @param id
	 */
	public Record(long id) {
		this.id = id;
		this.logger = new Logger("Record_" + this.id);
	}

	/* (non-Javadoc)
	 * @see rbvs.record.IRecord#getIdentifier()
	 */
	@Override
	public long getIdentifier() {
		this.logger.trace("[get] identifier");
		// TODO Auto-generated method stub
		return this.id;
	}

}
