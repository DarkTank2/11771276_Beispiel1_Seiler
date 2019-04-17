/**
 * Filename: main.java
 * Description: 
 * @author Alexander Seiler, 11771276
 * @since 15.04.2019
 */

import utils.Logger;
import utils.Loglevel;
import ict.*;
import rbvs.*;

public class main {

	/**
	 * @author Alexander Seiler, 11771276
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Logger("main");
		logger.setLoglevel(Loglevel.INFO);
		logger.info("[function] main()");
		Table t = new Table("Table1", 5);
		Table tt = new Table("TableAtWindow", 3);
		t.equals(tt);
		tt.setSeatCount(4);
		logger.info("[table-info] " +t.toString());
		logger.trace("[test] test1");
		logger.trace("[test] test2");
		logger.trace("[test] test3");
		logger.finalize();
	}

}
