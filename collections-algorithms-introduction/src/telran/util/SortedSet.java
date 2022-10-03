package telran.util;

public interface SortedSet<T> extends Set<T> {
	/**
	 * 
	 * @return reference to the least object
	 */
	T first();

	
	/**
	 * 
	 * @return reference to the most object
	 */
	T last();
	
	
	/**
	 * 
	 * @param pattern
	 * @return 
	 */
	T ceiling(T pattern);
	
	
	/**
	 * 
	 * @param pattern
	 * @return 
	 */
	T floor(T pattern);
}