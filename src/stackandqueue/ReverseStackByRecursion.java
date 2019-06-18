package stackandqueue;

import java.util.Stack;

/**
 * 使用递归操作和栈逆序一个栈
 * @author beichen
 *
 */
public class ReverseStackByRecursion {

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}
	
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		
		int element = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(element);
	}
}
