package gcdandfibonacci;
/**
 *	计算最大公约数
 *	原理若f(x, y)为x，y的最大公约数，那么f(x, y)也为x,y相除余数的公约数f(x, y)=f(y % x, x)
 * @author beichen
 *
 */
public class Gcd {

	/**
	 * 	循环求解最大公约数
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getGcdByLoop(int x, int y) {
		while(x != 0) {
			int temp = x;
			x = y % x;
			y = temp; 
		}
		return y;
	}
	
	/**
	 *	递归求解最大公约数
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getGcdByRecursion(int x, int y) {
		
		return (x == 0) ? y : getGcdByRecursion(x, (y % x));
		
	}
	
	/**
	 * f(x, y) = f(x-y, y)
	 * 	不适用于相差太大的两数，如(1, 1000)
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getGcdBySubtractionRecursion(int x, int y) {
		if (x < y) {
			return getGcdBySubtractionRecursion(y, x);
		}
		
		return (y == 0) ? x : getGcdBySubtractionRecursion(x-y, x);
	}
}
