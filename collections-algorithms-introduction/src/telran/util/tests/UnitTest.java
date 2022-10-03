package telran.util.tests;

public class UnitTest {
	public static boolean assertEquals(String name, boolean src, boolean trg) {
		boolean result = (src == trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, byte src, byte trg) {
		boolean result = (src == trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, short src, short trg) {
		boolean result = (src == trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, int src, int trg) {
		boolean result = (src == trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, long src, long trg) {
		boolean result = (src == trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, float src, float trg, float delta) {
		boolean result = (Math.abs(src - trg) <= delta);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, double src, double trg, float delta) {
		boolean result = (Math.abs(src - trg) <= delta);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

	public static boolean assertEquals(String name, Object src, Object trg) {
		boolean result = src.equals(trg);
		System.out.printf("%-20s\t%s%n", name, (result ? "SUCCESS" : "FAILURE"));
		return result;
	}

}