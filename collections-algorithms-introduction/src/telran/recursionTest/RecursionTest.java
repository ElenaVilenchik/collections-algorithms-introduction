package telran.recursionTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.recursion.LineRecursion;
import static telran.recursion.LineRecursion.*;

class RecursionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void factorialTest() {
		assertEquals(24, LineRecursion.factorial(4));
	}

	@Test
	void powTest() {
		assertEquals(1000, pow(10, 3));
		assertEquals(100, pow(10, 2));
		assertEquals(-1000, pow(-10, 3));
		assertEquals(10000, pow(-10, 4));
		assertEquals(0, pow(0, 1));
		assertEquals(1, pow(4, 0));
	}

	@Test
	void sumTest() {
		int ar[] = { 1, 2, 3, 4 };
		assertEquals(10, sum(ar));
		int ar1[] = {};
		assertEquals(0, sum(ar1));
	}

	@Test
	void squareTest() {
		assertEquals(0, square(0));
		assertEquals(1, square(1));
		assertEquals(16, square(4));
		assertEquals(25, square(-5));
	}
}
