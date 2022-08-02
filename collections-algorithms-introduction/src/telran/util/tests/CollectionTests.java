package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Collection;

abstract class ColeectionTests {
	private static final int[] EXAMPLE_ARRAY = { 0, 11, 2, -3, 4, 500 };
	protected static final int ADD_100_ITEMS = 100;
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
		assertTrue(collection.remove(500));
		assertEquals(EXAMPLE_ARRAY.length - 1, collection.size());
		assertFalse(collection.remove(7));
		assertEquals(EXAMPLE_ARRAY.length, size);
	}

	@Test
	void removeIfTest() {
		Predicate<Integer> AlwaysFalsePredicate = new AlwaysFalsePredicate();
		assertFalse(collection.removeIf(AlwaysFalsePredicate));
		assertEquals(EXAMPLE_ARRAY.length, collection.size());
	//	assertTrue(collection.removeIf(AlwaysFalsePredicate.negate()));
	//	assertEquals(0, collection.size());
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
		Integer expected1[] = { 3, 11, 2, -3, 4, 500 };
	//	assertArrayEquals(expected1, collection.toArray(new Integer[0]));
		assertTrue(expected1 == collection.toArray(expected1));
		Integer[] expected2 = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected1, Arrays.copyOf(expected2, collection.size()));
	}
}
