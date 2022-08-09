package telran.util;
	import java.util.Iterator;
	import java.util.NoSuchElementException;
	import java.util.function.Predicate;

	/**
	 * Filtered Iterator adds filtering property to wrapped Iterator, 
	 * using the specified Predicate filter
	 *
	 * @param <E> type of element
	 */
	public class FilteredIterator<E> implements Iterator<E> {

		/**
		 * Constructs FilteredIterator wrapping the 'iterator' 
		 * and returning only elements matching the 'filter'.
		 * @param iterator  the iterator to wrap
		 * @param filter elements must match this filter to be returned
		 */
		public FilteredIterator(Iterator<E> iterator, Predicate<E> filter) {
			//TODO
		}

		@Override
		public boolean hasNext() {
			//TODO
			return false;
		}

		@Override
		public E next() {
			//TODO
			return null;
		}
	}

