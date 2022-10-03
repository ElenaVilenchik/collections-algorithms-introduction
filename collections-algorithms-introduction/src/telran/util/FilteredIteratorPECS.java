package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Filtered Iterator adds filtering property to wrapped Iterator, using the
 * specified Predicate filter
 *
 * @param <E> type of element
 */
public class FilteredIteratorPECS<E> implements Iterator<E> {
	private final Iterator<? extends E> iterator;
	private final Predicate<? super E> filter;

	private E next;
	private boolean hasNext;

	/**
	 * Constructs FilteredIterator wrapping the 'iterator' and returning only
	 * elements matching the 'filter'.
	 * 
	 * @param iterator the iterator to wrap
	 * @param filter   elements must match this filter to be returned
	 */
	public FilteredIteratorPECS(Iterator<? extends E> iterator, Predicate<? super E> filter) {
		this.iterator = iterator;
		this.filter = filter;
		hasNext = findNext();
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public E next() {
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		E result = next;
		hasNext = findNext();
		return result;
	}

	// returns previously found "matched" value and looks for upcoming "matched"
	// value
	private boolean findNext() {
		while (iterator.hasNext()) {
			next = iterator.next();
			if (filter.test(next)) {
				return true;
			}
		}
		return false;
	}
}
