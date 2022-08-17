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
	public static int pow(int a, int b) {
		if (b == 0) {
			return 1;
		}
		if (a < 0) {
			if ((b & 1) == 1) {
				return -addition(-a, pow(-a, b - 1));
			} else
				return -addition(a, pow(-a, b - 1));
		} else
			return addition(a, pow(a, b - 1));
	}

	private static int addition(int a, int b) {
		if (b == 1) {
			return a;
		}
		return a + addition(a, b - 1);
	}

	/**
	 * 
	 * @param x
	 * @return x^2
	 */
	public static int square(int x) {
		if (x < 0) {
			x = -x;
		}
		return sqr(x);
	}

	private static int sqr(int x) {
		if (x == 0) {
			return 0;
		}
		return sqr(x - 1) + x + (x - 1);
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
