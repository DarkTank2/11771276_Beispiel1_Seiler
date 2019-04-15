/**
 * Filename: IDeepCopy.java
 * Description: This Interface is needed since the Cloneable interface provided by Java does not redeclare the clone method of Object. We want to use it as bounded type for generic classes so this additional interface is needed.
 * @author Alexander Seiler, 11771276
 * @since 14.04.2019
 */
package ict.basics;

public interface IDeepCopy {

	/**
	 * Creates and returns a copy of this object by either invoking a private copy constructor, or by creating a new object of the same class and setting its values accordingly.
	 * @author Alexander Seiler, 11771276
	 * @return
	 */
	IDeepCopy deepCopy();
}
