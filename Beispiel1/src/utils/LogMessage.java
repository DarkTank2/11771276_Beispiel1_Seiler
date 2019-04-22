/**
 * Filename: LogMessage.java
 * Description: This class handles the messages
 * @author Alexander Seiler, 11771276
 * @since 17.04.2019
 */
package utils;
import java.time.*;

public class LogMessage {

	// Fields for the class LogMessage
	private String Message;
	private String Name;
	private Loglevel level;
	private LocalDateTime date;
	
	/**
	 * Constructor for class LogMessage.java
	 * @author Alexander Seiler, 11771276
	 * @param message
	 * @param level
	 */
	public LogMessage(String message, Loglevel level, String Name) {
		super();
		this.Name = Name;
		this.Message = message;
		this.level = level;
//		use the current time
		this.date = LocalDateTime.now();
	}

	@Override
	public String toString() {
//		representation of the message, customized and comparable with JavaScript's Bunyan logger-messages
		return this.date + " | " + this.level.toString()  + " | " + this.Name + " | " + this.Message;
	}
}
