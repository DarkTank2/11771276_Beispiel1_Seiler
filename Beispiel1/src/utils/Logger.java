/**
 * Filename: Logger.java
 * Description: Class descriptor for a logging instance. Log-Levels are similar to Javascript's Bunyan-logger.
 * @author Alexander Seiler, 11771276
 * @since 15.04.2019
 */
package utils;

import java.time.*;

public class Logger {

	private String name;
	private static Loglevel level = Loglevel.TRACE;
	
	/**
	 * Constructor for class Logger.java
	 * @author Alexander Seiler, 11771276
	 * @param name
	 */
	public Logger(String name) {
		super();
		this.name = name;
	}
	
	public void setLoglevel(Loglevel newLevel) {
		System.out.println("Loglevel changed!");
		this.level = newLevel;
	}
	
	/**
	 * Printing method, outputs the message
	 * @author Alexander Seiler, 11771276
	 * @param message
	 */
	private void print(String message) {
		LocalDateTime d = LocalDateTime.now();
		System.out.println(d + " | " + message);
	}
	
	/**
	 * Trace (10), debug (20), info (30), warn (40) and error (50)
	 * Log-Levels with severity
	 * @author Alexander Seiler, 11771276
	 * @param message
	 */
	public void trace(String message) {
		if(this.level.ordinal() > Loglevel.TRACE.ordinal()) {
			return;
		}
		this.print("trace | " + this.name + " | " + message);
	}
	public void debug(String message) {
		if(this.level.ordinal() > Loglevel.DEBUG.ordinal()) {
			return;
		}
		this.print("debug | " + this.name + " | " + message);
	}
	public void info(String message) {
		if(this.level.ordinal() > Loglevel.INFO.ordinal()) {
			return;
		}
		this.print("info | " + this.name + " | " + message);
	}
	public void warn(String message) {
		if(this.level.ordinal() > Loglevel.WARN.ordinal()) {
			return;
		}
		this.print("warn | " + this.name + " | " + message);
	}
	public void error(String message) {
		if(this.level.ordinal() > Loglevel.ERROR.ordinal()) {
			return;
		}
		this.print("error | " + this.name + " | " + message);
	}
}