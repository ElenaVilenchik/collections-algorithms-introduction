package telran.util.tests;

import org.junit.jupiter.api.BeforeEach;
import telran.util.Collection;

abstract class ColeectionTests {
	protected Collection<Integer> collection;

	protected abstract Collection<Integer> createCollection();

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		// TODO fill collection

	}

	// TODO
	// Write 6 tests for collection

}
