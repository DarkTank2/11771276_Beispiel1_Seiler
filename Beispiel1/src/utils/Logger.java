/**
 * Filename: Logger.java
 * Description: Class descriptor for a logging instance. Log-Levels are similar to Javascript's Bunyan-logger.
 * @author Alexander Seiler, 11771276
 * @since 15.04.2019
 */
package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Logger {

	// Fields of class Logger
	private String name;
	private static Loglevel level = Loglevel.TRACE;
	private static Vector<LogMessage> list = new Vector<LogMessage>();
	
	/**
	 * Constructor for class Logger.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public Logger(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Sets the global log-level accordingly to the parameter
	 * @author Alexander Seiler, 11771276
	 * @param newLevel
	 */
	public static void setLoglevel(Loglevel newLevel) {
		System.out.println("Loglevel changed!");
		list.add(new LogMessage("Changed Log-Level to " + newLevel.toString(), Loglevel.INFO, "LOGGER"));
		Logger.level = newLevel;
	}
	
	/**
	 * Printing method, outputs the message
	 * @author Alexander Seiler, 11771276
	 * @param message
	 */
	private void print(LogMessage message) {
		System.out.println(message.toString());
	}
	
	/**
	 * Trace (10), debug (20), info (30), warn (40) and error (50)
	 * Log-Levels with severity
	 * Each message gets added to the list of all messages
	 * It only gets printed, if the log-level is equal or smaller than the set level
	 * @author Alexander Seiler, 11771276
	 * @param message
	 */
	public void trace(String message) {
//		create new LogMessage with the passed message, the corresponding Log-level and the name of the logger
		LogMessage m = new LogMessage(message, Loglevel.TRACE, this.name);
		list.add(m);
		if(Logger.level.ordinal() > Loglevel.TRACE.ordinal()) {
			return;
		}
//		only printing it to the console, if the set level is below or equal, e.g. if the Log-level is higher do not output it
		this.print(m);
	}
	public void debug(String message) {
		LogMessage m = new LogMessage(message, Loglevel.DEBUG, this.name);
		list.add(m);
		if(Logger.level.ordinal() > Loglevel.DEBUG.ordinal()) {
			return;
		}
		this.print(m);
	}
	public void info(String message) {
		LogMessage m = new LogMessage(message, Loglevel.INFO, this.name);
		list.add(m);
		if(Logger.level.ordinal() > Loglevel.INFO.ordinal()) {
			return;
		}
		this.print(m);
	}
	public void warn(String message) {
		LogMessage m = new LogMessage(message, Loglevel.WARN, this.name);
		list.add(m);
		if(Logger.level.ordinal() > Loglevel.WARN.ordinal()) {
			return;
		}
		this.print(m);
	}
	public void error(String message) {
		LogMessage m = new LogMessage(message, Loglevel.ERROR, this.name);
		list.add(m);
		if(Logger.level.ordinal() > Loglevel.ERROR.ordinal()) {
			return;
		}
		this.print(m);
	}
	
	/**
	 * Method to finalize the logger, it prints every logged message to the log-file
	 * @author Alexander Seiler, 11771276
	 * @param message
	 */
	public void finalize() {
		try {
	    	FileWriter fileWriter = new FileWriter("log.txt");
		    PrintWriter printWriter = new PrintWriter(fileWriter);
//		    writes each log-message to the file, regardless of the log-level, this is done for deeper debugging without using the debugger 
	    	Logger.list
	    		.stream()
	    		.forEach(element -> printWriter.println(element.toString()));
	    	printWriter.close();
	    } catch(IOException e) {
	    	System.out.println("Error during log-fil-writing!");
	    	System.out.print(e.toString());
	    }
		
	}
}