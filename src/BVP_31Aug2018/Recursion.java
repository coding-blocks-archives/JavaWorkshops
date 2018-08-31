package BVP_31Aug2018;

/**
 * @author Garima Chhikara
 * @email garima.chhikara@codingblocks.com
 * @date 31-Aug-2018
 */

public class Recursion {

	public static void main(String[] args) {

		// System.out.println(factorial(5));
		// parenthesis(0, 0, 3, "");
		// binaryStrings(3, "", false);

		System.out.println(wildCard("abcdef", "**?********def"));

		// System.out.println(friendPairing(4)) ;
	}

	public static int factorial(int n) {

		if (n == 1) {
			return 1;
		}
		int fnm1 = factorial(n - 1);
		int fn = fnm1 * n;

		return fn;

	}

	public static void parenthesis(int open, int close, int n, String ans) {

		if (open == n && close == n) {
			System.out.println(ans);
			return;
		}

		if (open > n || close > n || close > open) {
			return;
		}

		parenthesis(open + 1, close, n, ans + "(");
		parenthesis(open, close + 1, n, ans + ")");
	}

	public static void binaryStrings(int n, String ans, boolean wasLastOneIncluded) {

		if (n == ans.length()) {

			System.out.println(ans);
			return;
		}

		binaryStrings(n, ans + "0", false);

		if (wasLastOneIncluded == false) {
			binaryStrings(n, ans + "1", true);
		}

	}

	// abcd, ?d*
	public static boolean wildCard(String src, String pattern) {

		if (src.length() == 0 && pattern.length() == 0) {
			return true;
		}

		if (src.length() != 0 && pattern.length() == 0) {
			return false;
		}

		if (src.length() == 0 && pattern.length() != 0) {

			for (int i = 0; i < pattern.length(); i++) {
				if (pattern.charAt(i) != '*') {
					return false;
				}
			}

			return true;
		}
		char chsrc = src.charAt(0); // a
		char chpattern = pattern.charAt(0); // ?

		String rossrc = src.substring(1); // bcd
		String rospattern = pattern.substring(1); // d*

		if (chpattern == '?' || chsrc == chpattern) {
			return wildCard(rossrc, rospattern);
		} else if (chpattern == '*') {

			boolean blank = wildCard(src, rospattern);
			boolean multiple = wildCard(rossrc, pattern);

			return blank || multiple;

		} else {
			return false;
		}

	}

	public static int minStepToOne(int n) {

		if (n == 1) {
			return 0;
		}

		int nby2 = Integer.MAX_VALUE, nby3 = Integer.MAX_VALUE, nm1 = 0;

		if (n % 2 == 0) {
			nby2 = minStepToOne(n / 2);
		}

		if (n % 3 == 0) {
			nby3 = minStepToOne(n / 3);
		}

		nm1 = minStepToOne(n - 1);

		return Math.min(nm1, Math.min(nby2, nby3)) + 1;
	}

	public static int friendPairing(int n) {

		if (n == 0 || n == 1 || n == 2) {
			return n;
		}

		int singlet = friendPairing(n - 1); // friend selects not to get paired with anyone else
		int doublet = (n - 1) * friendPairing(n - 2); // friend gets paired with anyone out of n-1

		return singlet + doublet;

	}
}
