package gcdandfibonacci;

/**
 * 	菲薄那契数列
 * @author beichen
 *
 */
public class Fibonacci {
	
	public static int fibonacciByRecursion(int n) {
		if (n < 1) {
			return -1; 
		} else if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonacciByRecursion(n - 1) + fibonacciByRecursion(n - 2);
		}
	}
	
	public static int fibonacciByLoop(int n) {
		if (n < 1) {
			return -1; 
		}
		
		int fibNMinusTwo = 1;
		int fibNMinusOne = 1;
		int fibN = 1;
		for (int i = 2; i < n; i++) {
			fibN = fibNMinusOne + fibNMinusTwo;
			fibNMinusTwo = fibNMinusOne;
			fibNMinusOne = fibN;
			
		}
		return fibN;
		
		
	}
}
