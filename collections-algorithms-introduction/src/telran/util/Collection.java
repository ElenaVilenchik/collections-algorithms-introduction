package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	/**
	 * add object of type T in collection
	 * 
	 * @param obj
	 * @return true if add
	 */
	boolean add(T obj);
	
	/***************************************/
	/**
	 * removes object equaled to the given pattern
	 * 
	 * @param pattern any object
	 * @return true if removed
	 */
	boolean remove(Object pattern);

	/***************************************/
	/**
	 * removes all object matching the given predicate
	 * 
	 * @param predicate
	 * @return true if a collection has been updated
	 */
	boolean removeIf(Predicate<T> predicate);

	/***************************************/
	/**
	 * 
	 * @param predicate
	 * @return true if there is an object equaled to the given pattern
	 */
	boolean contains(Object pattern);

	/***************************************/
	/**
	 * 
	 * @return amount of the object
	 */
	int size();

	/***************************************/
	/**
	 * 
	 * 
	 * @param ar
	 * @return regular Java array containing all the collection object
	 */
	default T[] toArray(T[] ar) {
		// TODO 
		//write the default method implementation based on the iterating
		
		Iterator<T> it = iterator();
		// TODO fill array by iterating
		// if array.length<size then you should create new array of type T with proper
		// length (consider method Arrays.
		// if ar.length == size then you jast fill given array and reference to the same
		// array
		// if ar.length>size then you fill the given array and rest part should be
		// filled by null's and reference to the same array will be returned
		return null;
	}
}
