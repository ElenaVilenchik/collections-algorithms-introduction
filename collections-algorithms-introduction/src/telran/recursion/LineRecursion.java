package telran.recursion;

public class LineRecursion {
	public static long factorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	/**
	 * 
	 * @param a (negative or positive)
	 * @param b
	 * @return a^b
	 */
	static public long pow(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException(" power can't be a negative");
		}
		if (b == 0) {
			return 1;
		}
		return multiply(a, pow(a, b - 1));
	}

	private static long multiply(int x, long y) {
		if (y < 0) {
			return multiply(-x, -y);
		}
		if (y == 0) {
			return 0;
		}
		return x + multiply(x, y - 1);
	}

	/**
	 * 
	 * @param x
	 * @return x^2
	 */
	public static int square(int x) {
		if (x < 0) {
			return square(-x);
		}
		if (x == 1) {
			return 1;
		}
		return square(x - 1) + x + (x - 1);
	}

	/**
	 * 
	 * @param ar array of integer numbers
	 * @return sum of all numbers from the given array
	 */
	public static int sum(int ar[]) {
		return sum(0, ar);
	}

	private static int sum(int firstIndex, int[] ar) {
		if (firstIndex == ar.length) {
			return 0;
		}
		return ar[firstIndex] + sum(firstIndex + 1, ar);
	}
}
