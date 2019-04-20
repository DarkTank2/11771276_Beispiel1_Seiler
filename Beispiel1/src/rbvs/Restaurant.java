/**
 * Filename: Restaurant.java
 * Description: This class allows to manage a product assortment, tables and orders for a restaurant.
 * @author Alexander Seiler, 11771276
 * @since 19.04.2019
 */
package rbvs;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import rbvs.product.CompositeProduct;
import rbvs.product.ExtendedProduct;
import rbvs.product.IProduct;
import rbvs.product.Product;
import rbvs.product.SimpleProduct;
import utils.Logger;
import utils.Loglevel;

public class Restaurant {

	private String name;
	private List<Order> orderHistory;
	private List<IProduct> productAssortment;
	private List<Table> tables;
	private long uniqueOrderIdentifier = 0;
	
	private Logger logger;
	
	/**
	 * Constructor for class Restaurant.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public Restaurant(String name) {
		this.name = name;
		this.orderHistory = new Vector<Order>();
		this.productAssortment = new Vector<IProduct>();
		this.tables = new Vector<Table>();
		this.logger = new Logger("Restaurant_" + name);
	}

	/**
	 * Returns the name of the restaurant.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	public String getName() {
		this.logger.trace("[get] name of " + this.name);
		return this.name;
	}

	public boolean createTable(String tableIdentifier) {
		this.logger.info("[function] addTable of " + this.name);
		if (this.getTableIdentifiers().contains(tableIdentifier)) {
			this.logger.error("[error] Table wiht name '" + tableIdentifier + "' already exists!");
			return false;
		}
		this.tables.add(new Table(tableIdentifier));
		return true;
	}
	
	public List<String> getTableIdentifiers() {
		this.logger.info("[function] getTableIdentifiers of " + this.name);
		return this.tables.stream().map(el -> el.getTableIdentifier()).collect(Collectors.toList());
	}
	
	public Table getSpecificTable(String identifier) {
		this.logger.info("[function] getSpecificTable with id " + identifier);
		List<Table> l = this.tables.stream().filter(el -> el.getTableIdentifier() == identifier).collect(Collectors.toList());
		return l.size() == 1 ? (Table) l.toArray()[0] : null;
	}
	
	public boolean containsProduct(IProduct compareProduct) {
		this.logger.trace("[contains] contains product '" + compareProduct.getName() + "' in restaurant " + this.name);
		return this.productAssortment.contains(compareProduct);
	}
	
	public IProduct findProduct(String productName) {
		this.logger.info("[function] findProduct by name with name " + productName);
		List<IProduct> l = this.productAssortment.stream().filter(el -> el.getName() == productName).collect(Collectors.toList());
		return l.size() == 1 ? (IProduct) l.toArray()[0] : null;
	}
	
	private IProduct findProduct(IProduct compareProduct) {
		this.logger.info("[function] findProduct by Product with name " + compareProduct.getName());
		List<IProduct> l = this.productAssortment.stream().filter(el -> ((Product) el).equals(compareProduct)).collect(Collectors.toList());
		return l.size() == 1 ? (IProduct) l.toArray()[0] : null;
	}
	
	public boolean addProduct(IProduct product) throws DuplicateProductException {
		this.logger.info("[function] addProduct to Restaurant " + this.name);
		if (product == null) return false;
		if (findProduct(product) != null) throw new DuplicateProductException(product);
		this.productAssortment.add((IProduct) product.deepCopy());
		this.logger.trace("[add-product] succesfully added Product " + product.getName());
		return true;
	}
	
	public boolean addProduct(Collection<IProduct> products) throws DuplicateProductException {
		this.logger.info("[function] addMultipleProducts to Restaurant " + this.name);
		
		List<IProduct> l = products.stream().filter(el -> findProduct(el) != null).collect(Collectors.toList());
		if (l.size() > 0) throw new DuplicateProductException((IProduct) l.toArray()[0]);
		
		products.stream().forEach(el -> {
			if (el == null ) return;
			if (findProduct(el) != null) this.logger.warn("[duplicate] Duplicate Product detected with name '" + el.getName() + "', won't add it to Product List");
			if (findProduct(el) == null) this.productAssortment.add(el);
		});
		if (products.contains(null)) return false;
		return true;
	}
	
	public List<IProduct> getProducts() {
		this.logger.info("[function] getProducts of " + this.name);
		return this.productAssortment
				.stream()
				.map(el -> (IProduct) el.deepCopy())
				.collect(Collectors.toList());
	}
	
	private long generateUniqueIdentifier() {
		this.logger.trace("[trace-function] generateUniqueIdentifier()");
		return ++this.uniqueOrderIdentifier;
	}
	
	public boolean orderProductForTable(Table table, IProduct product) {
		this.logger.info("[function] orderProductForTable()");
		if (table == null) return false;
		if (product == null) return false;
		if (containsProduct(product) == false) return false;
		if (getTableIdentifiers().contains(table.getTableIdentifier()) == false) return false;
		this.logger.trace("[trace] adding Product '" + product.getName() + "' to Table '" + table.getTableIdentifier() + "'");
		List<IProduct> l = new Vector<IProduct>();
		l.add(findProduct(product));
		this.orderHistory.add(new Order(generateUniqueIdentifier(), getSpecificTable(table.getTableIdentifier()), l));
		return true;
	}
	
	public boolean orderProductForTable(Table table, IProduct product, int count) {
		this.logger.info("[function] orderProductForTable()");
		if (table == null) return false;
		if (product == null) return false;
		if (count == 0) return false;
		if (containsProduct(product) == false) return false;
		if (getTableIdentifiers().contains(table.getTableIdentifier()) == false) return false;
		this.logger.trace("[trace] adding Product '" + product.getName() + "' to Table '" + table.getTableIdentifier() + "' x " + count);
		IProduct p = findProduct(product);
		List<IProduct> l = new Vector<IProduct>();
		for (int i = 0; i < count; ++i) {
			l.add(p);
		}
		this.orderHistory.add(new Order(generateUniqueIdentifier(), getSpecificTable(table.getTableIdentifier()), l));
		return true;
	}
	
	public static List<IProduct> generateSimpleProducts() {
		(new Logger("GnerateProducts")).info("[function] generateSimpleProducts()");
		IProduct prod1 = new SimpleProduct("Penne", 4);
		IProduct prod2 = new SimpleProduct("Basil", 2);
		IProduct prod3 = new SimpleProduct("Tomatoe", 3);
		IProduct prod4 = new SimpleProduct("Mozzarella", 1);
		IProduct prod5 = new SimpleProduct("Olive oil", 1);
		IProduct prod6 = new SimpleProduct("Minced Meat", 3);
		IProduct prod7 = new SimpleProduct("Campanelle", 4);
		IProduct prod8 = new SimpleProduct("Bacon", 1);
		List<IProduct> l = new Vector<IProduct>();
		l.add(prod1);
		l.add(prod2);
		l.add(prod3);
		l.add(prod4);
		l.add(prod5);
		l.add(prod6);
		l.add(prod7);
		l.add(prod8);
		return l;
	}
	
	public static List<IProduct> generateCompositeProducts() {
		(new Logger("GnerateProducts")).info("[function] generateCompositeProducts()");
		IProduct prod1 = new SimpleProduct("Penne", 4);
		IProduct prod2 = new SimpleProduct("Basil", 2);
		IProduct prod3 = new SimpleProduct("Tomatoe", 3);
		IProduct prod4 = new SimpleProduct("Mozzarella", 1);
		IProduct prod5 = new SimpleProduct("Olive oil", 1);
		IProduct prod6 = new SimpleProduct("Minced Meat", 3);
		IProduct prod7 = new SimpleProduct("Campanelle", 4);
		IProduct prod8 = new SimpleProduct("Bacon", 1);
		List<IProduct> l = new Vector<IProduct>();
		IProduct comp1 = new CompositeProduct("Penne al Pomodoro", 20);
		IProduct comp2 = new CompositeProduct("Campanelle Bolognese", 15);
		IProduct comp3 = new CompositeProduct("Tomatoe sauce", 10);
		IProduct comp4 = new CompositeProduct("Penne al Arabiata", 10);
		
//		Tomatosauce contains Tomatoes, olive oil and Basil
		((CompositeProduct) comp3).addProduct((Product) prod4);
		((CompositeProduct) comp3).addProduct((Product) prod5);
		((CompositeProduct) comp3).addProduct((Product) prod2);
		
//		Penne al Pomodore contains Penne, Tomatoesauce and Mozzarella 
		((CompositeProduct) comp1).addProduct((Product) comp3);
		((CompositeProduct) comp1).addProduct((Product) prod1);
		((CompositeProduct) comp1).addProduct((Product) prod4);
		((CompositeProduct) comp1).addProduct((Product) prod3);
		
//		Campanelle Bolognese contains Campanelle, Tomatoesauce and Minced Meat
		((CompositeProduct) comp2).addProduct((Product) comp3);
		((CompositeProduct) comp2).addProduct((Product) prod7);
		((CompositeProduct) comp2).addProduct((Product) prod6);
		
//		Penne al Arabiata contains Penne, Tomatosauce and bacon
		((CompositeProduct) comp4).addProduct((Product) comp3);
		((CompositeProduct) comp4).addProduct((Product) prod1);
		((CompositeProduct) comp4).addProduct((Product) prod8);
		
		l.add(comp1);
		l.add(comp2);
		l.add(comp4);
		return l;
	}
	
	/**
	 * @author Alexander Seiler, 11771276
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.setLoglevel(Loglevel.INFO);
		Logger log = new Logger("MAIN");
		Restaurant r = new Restaurant("Vapiano");
		List<IProduct> s = Restaurant.generateSimpleProducts();
		List<IProduct> c = Restaurant.generateCompositeProducts();
		try {
			r.addProduct(s);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			r.addProduct(c);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
		
		IProduct ex1 = new ExtendedProduct("Black Tea: Peach/Vanilla", 4);
		IProduct ex2 = new ExtendedProduct("Red Tea: Pomegranate", 4);
		IProduct ex3 = new ExtendedProduct("Green Tea: Matcha", 4);
		IProduct ex4 = new ExtendedProduct("Coca Cola", 3);
		IProduct ex5 = new ExtendedProduct("Voeslauer 'Prickelnd'", (float) 1.5);
		List<IProduct> drinks = new Vector<IProduct>();
		drinks.add(ex1);
		drinks.add(ex2);
		drinks.add(ex3);
		drinks.add(ex4);
		drinks.add(ex5);
		try {
			r.addProduct(drinks);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
//		This shall fail
		try {
			r.addProduct(ex1);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
		
		IProduct menu1 = new CompositeProduct("Only Drinks", 50);
		IProduct menu2 = new CompositeProduct("Only basics", 50);
		
		((CompositeProduct) menu1).addProduct((Product) ex1);
		((CompositeProduct) menu1).addProduct((Product) ex2);
		((CompositeProduct) menu1).addProduct((Product) ex3);
		((CompositeProduct) menu1).addProduct((Product) ex4);
		((CompositeProduct) menu1).addProduct((Product) ex5);
		
		((CompositeProduct) menu2).addProduct((Product) r.findProduct("Basil"));
		((CompositeProduct) menu2).addProduct((Product) r.findProduct("Olive oil"));
		((CompositeProduct) menu2).addProduct((Product) r.findProduct("Penne"));
		((CompositeProduct) menu2).addProduct((Product) r.findProduct("Campanelle"));
		((CompositeProduct) menu2).addProduct((Product) r.findProduct("Bacon"));
		try {
			r.addProduct(menu1);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			r.addProduct(menu2);
		} catch (DuplicateProductException e) {
			log.error("[error] DuplicateProductException: " + e.getMessage());
			e.printStackTrace();
		}
		
		r.createTable("Buisness Lounge");
		r.createTable("Base 1");
		r.createTable("Base 2");
		
		r.orderProductForTable(r.getSpecificTable("Buisness Lounge"), r.findProduct("Penne al Pomodoro"), 3);
		r.orderProductForTable(r.getSpecificTable("Buisness Lounge"), r.findProduct("Black Tea: Peach/Vanilla"), 4);
		
		r.orderProductForTable(r.getSpecificTable("Base 1"), r.findProduct("Penne al Arabiata"), 1);
		r.orderProductForTable(r.getSpecificTable("Base 1"), r.findProduct("Campanelle Bolognese"), 1);
		r.orderProductForTable(r.getSpecificTable("Base 1"), r.findProduct("Voeslauer 'Prickelnd'"), 2);
		
		r.orderProductForTable(r.getSpecificTable("Base 2"), r.findProduct("Only Drinks"));
		r.orderProductForTable(r.getSpecificTable("Base 2"), r.findProduct("Only basics"));
		
		log.trace(r.toString());
		
		log.finalize();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		this.logger.info("[function] toString()");
		String orderString = this.orderHistory.stream().map(el -> el.toString()).collect(Collectors.joining(", "));
		String productString = this.productAssortment.stream().map(el -> el.toString()).collect(Collectors.joining(", "));
		String tableString = this.tables.stream().map(el -> el.toString()).collect(Collectors.joining(", "));
		return "Restaurant [name=" + this.name + ", orderHistory=[" + orderString + "], productAssortment=["
				+ productString + "], tables=[" + tableString + "], uniqueOrderIdentifier=" + this.uniqueOrderIdentifier + "]";
	}
	
	

}
