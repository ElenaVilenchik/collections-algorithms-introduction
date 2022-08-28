package telran.util.tests;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import telran.util.FilteredIterator;
/** 
 * Iterates over range: min inclusive, max exclusive
 */
class IntRangeIterator implements Iterator<Integer>
{	
	int max;
	int nextValue;
		
	public IntRangeIterator(int min, int max) {		
		super();
		if (max < min || max + 1 <= min) { // avoid invalid order and overflow
			throw new IllegalArgumentException();
		}
		this.nextValue = min;
		this.max = max;
	}

	@Override
	public boolean hasNext() {		
		return nextValue < max;
	}

	@Override
	public Integer next() {
		if (!hasNext()) throw new NoSuchElementException();
		return nextValue++;
	}	
}


class EvenPredicate implements Predicate<Integer>{
	@Override
	public boolean test(Integer val) {		
		return (val % 2 == 0);
	}	
}

class TruePredicate implements Predicate<Integer>{
	@Override
	public boolean test(Integer val) {		
		return true;
	}	
}

public class FilteredIteratorTest {
	public static void main(String[] args) { 
		Test("Empty", new IntRangeIterator(0, 0), new TruePredicate(), "");
		Test("One unfiltered", new IntRangeIterator(1, 2), new EvenPredicate(), "");
		Test("All", new IntRangeIterator(0, 10), new TruePredicate(), "0 1 2 3 4 5 6 7 8 9");
		Test("First & Last",    new IntRangeIterator(0, 11), new EvenPredicate(), "0 2 4 6 8 10");
		Test("First & ! Last",  new IntRangeIterator(0, 10), new EvenPredicate(), "0 2 4 6 8");
		Test("!First & Last",   new IntRangeIterator(1, 11), new EvenPredicate(), "2 4 6 8 10");
		Test("!First & ! Last", new IntRangeIterator(1, 10), new EvenPredicate(), "2 4 6 8");
		Test("null support", Arrays.asList(1, null).iterator(), new TruePredicate(), "1 null");
	}
	
	private static boolean Test(String name, Iterator<Integer> srcIter, Predicate<Integer> filter, String result) {
		FilteredIterator<Integer> iter = new FilteredIterator<>(srcIter, filter);
		if (!iter.hasNext()) {
			return assertEquals(name, "", result);
		}
		StringBuilder sb = new StringBuilder(iter.next().toString());
		
		while (iter.hasNext()) {
			sb.append(" ").append(iter.next());
		}
		if (! assertEquals(name, sb.toString(), result)) {
			System.out.println("   Expexted:" + result);
			System.out.println("   Actually:" + sb.toString());
			return false;
		}
		return true;
	}
}
