package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Collection;

abstract class ColeectionTests {
	protected static final int[] EXAMPLE_ARRAY = { 0, 11, 2, -3, 4, 500 };
	protected static final int ADD_100_ITEMS = 100;
	private static final int N_RUNS = 10000;
	private static final int N_NUMBERS = 10000;
	private static final int N_RANDOM_RANS = 10000;
	protected Collection<Integer> collection;

	protected abstract Collection<Integer> createCollection();

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		for (int itemCollection : EXAMPLE_ARRAY) {
			collection.add(itemCollection);
		}
	}

	@Test
	void addTest() {
		for (int i = 0; i < ADD_100_ITEMS; i++) {
			collection.add(100);
		}
		assertEquals(EXAMPLE_ARRAY.length + ADD_100_ITEMS, collection.size());
		assertTrue(collection.add(7));
		assertTrue(collection.add(2));

	}

	@Test
	void removeTest() {
		int size = collection.size();
		assertTrue(collection.remove(Integer.valueOf(500)));
		assertEquals(EXAMPLE_ARRAY.length - 1, collection.size());
		assertFalse(collection.remove(Integer.valueOf(7)));
		assertEquals(EXAMPLE_ARRAY.length, size);
	}

	@Test
	void removeIfTest() {
		Predicate<Integer> AlwaysFalsePredicate = new AlwaysFalsePredicate();
		assertFalse(collection.removeIf(AlwaysFalsePredicate));//nothing removed test
		assertEquals(EXAMPLE_ARRAY.length, collection.size());
		assertTrue(collection.removeIf(AlwaysFalsePredicate.negate()));//all removed test
		assertEquals(0, collection.size());
		
		/**********************************************/
		for(int i=0;i<N_RANDOM_RANS;i++) {
		fillRandomCollection();//even numbers removed test
		collection.removeIf(new EvenNumbersPredicate());
		for(int num:collection) {
			assertTrue(num%2==1);
		}
	}
		/***********************************************/
	}

	private void fillRandomCollection() {
		collection=createCollection();
		collection.add((int)(Math.random()*Integer.MAX_VALUE));
	}

	@Test
	void containsTest() {
		assertTrue(collection.contains(500));
		assertFalse(collection.contains(1000));
	}

	@Test
	void sizeTest() {
		assertEquals(EXAMPLE_ARRAY.length, collection.size());
	}

	@Test
	void toArrayTest() {
		Integer expected[] ={ 0, 11, 2, -3, 4, 500 };
		assertArrayEquals(expected, collection.toArray(new Integer[0]));
		assertTrue(expected == collection.toArray(expected));
		Integer[] expected2 = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected, Arrays.copyOf(expected2, collection.size()));
	}

	@Test
	void wrongIteratorRemoveTest() {
		Iterator<Integer> it = collection.iterator();
		wrongRemove(it);// first remove
		it.next();
		it.next();
		it.remove();// two removes with no next
		wrongRemove(it);
	}

	@Test
	void removeIfPerformanceTest() {
		Predicate<Integer> predicate = new AlwaysFalsePredicate().negate();
		for (int i = 0; i < N_RUNS; i++) {
			fillLargeCollection();
			collection.removeIf(predicate);
		}
	}

	private void fillLargeCollection() {
		for (int i = 0; i < N_NUMBERS; i++) {
			collection.add(i);
		}
	}

	protected void wrongRemove(Iterator<Integer> it) {
		boolean flException = false;
		try {
			it.remove();
		} catch (IllegalStateException e) {
			flException = true;
		}
		assertTrue(flException);
	}
}
