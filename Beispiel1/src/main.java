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
import rbvs.product.*;

public class main {

	/**
	 * @author Alexander Seiler, 11771276
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Logger("main");
		logger.setLoglevel(Loglevel.ERROR);
		logger.info("[function] main()");
		Table t = new Table("Table1", 5);
		Table tt = new Table("TableAtWindow", 3);
		t.equals(tt);
		tt.setSeatCount(4);
		logger.info("[table-info] " +t.toString());
		
		ExtendedProduct prod = new ExtendedProduct("Paradeiser", 5);
		System.out.println(prod.toString());
		prod.setName("second");
		System.out.println(prod.toString());
		prod.setName("third");
		System.out.println(prod.toString());
		prod.setPrice(8);
		System.out.println(prod.toString());
		if(prod.undo()) System.out.println(prod.toString());
		if(prod.undo()) System.out.println(prod.toString());
		if(prod.undo()) System.out.println(prod.toString());
		if(prod.undo()) System.out.println(prod.toString());
		ExtendedProduct kaese = new ExtendedProduct("Käse", 4);
		ExtendedProduct nudeln = new ExtendedProduct("Nudeln", 3);
		ExtendedProduct fleisch = new ExtendedProduct("Fleisch", 2);
		CompositeProduct comp = new CompositeProduct("Lasagne", 10);
		comp.addProduct(fleisch);
		comp.addProduct(nudeln);
		comp.addProduct(kaese);
		comp.addProduct(prod);
		System.out.println(comp.toString());
		
		logger.trace("[test] test1");
		logger.trace("[test] test2");
		logger.trace("[test] test3");
		logger.finalize();
	}

}
