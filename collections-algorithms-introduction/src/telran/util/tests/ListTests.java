package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.List;

abstract class ListTests extends ColeectionTests {
	List<Integer> list;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp(); // { 0, 11, 2, -3, 4, 500 }
		list = (List<Integer>) collection;
	}

	@Test // { 0, 11, 2, -3, 4, 500 }
	void addIndexTest() {

		assertTrue(list.add(0, -8));
		assertEquals(-8, list.get(0));
		int size = list.size();
		for (int i = 0; i < ADD_100_ITEMS; i++) {
			list.add(2, 100);
		}
		assertEquals(size + ADD_100_ITEMS, list.size());
		size = list.size();
		assertTrue(list.add(size, 1000));
		assertEquals(1000, list.get(size));
		assertFalse(list.add(-1, 10));
		assertEquals(size + 1, list.size());
	}

	@Test // { 0, 11, 2, -3, 4, 500 }
	void removeIndexTest() {
		int size = list.size();
		assertEquals(0, list.remove(0));
		assertEquals(size - 1, list.size());
		assertEquals(11, list.get(0));
		assertEquals(-3, list.remove(2));
		assertEquals(size - 2, list.size());
		assertEquals(4, list.get(2));
		size = list.size();
		assertEquals(500, list.remove(size - 1));
		assertEquals(size - 1, list.size());
		assertNull(list.remove(-1));
		assertNull(list.remove(size));
	}

	@Test
	void indexOfTest() {
		list.add(11);
		assertEquals(1, list.indexOf(11));
	}

	@Test // { 0, 11, 2, -3, 4, 500 }
	void lastIndexOfTest() {
		list.add(30);
		assertEquals(6, list.lastIndexOf(30));
		assertEquals(-1, list.lastIndexOf(-10));
	}

	@Test
	void getTest() {
		assertEquals(0, list.get(0));
		assertNull(list.get(-1));
	}
}
