package telran.util;

import java.util.Arrays;
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
		Iterator<T> it = iterator();
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		} else {
			if (ar.length > size) {
				for (int i = size; i < ar.length; i++) {
					ar[i] = null;
				}
			}
			int index = 0;
			while (it.hasNext()) {
				ar[index++] = it.next();
			}
		}
		return ar;
	}
}
