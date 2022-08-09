package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;
import org.junit.jupiter.api.Test;
import telran.util.Collection;
import telran.util.LinkedList;

public class LinkedListTests extends ListTests {

	@Override
	protected Collection<Integer> createCollection() {
		return new LinkedList<>();
	}

	@Test
	void reverseTest() {
		LinkedList<Integer> linkedList = (LinkedList<Integer>) collection;
		Integer expected1[] = { 500, 4, -3, 2, 11, 0 }; // { 0, 11, 2, -3, 4, 500 }; - initial order 1
		linkedList.reverse();
		assertArrayEquals(expected1, linkedList.toArray(new Integer[0]));
		linkedList.add(100);
		Integer expected2[] = { 500, 4, -3, 2, 11, 0, 100 };
		assertArrayEquals(expected2, linkedList.toArray(new Integer[0]));
	}
}
