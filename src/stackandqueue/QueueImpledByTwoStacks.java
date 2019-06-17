package stackandqueue;

import java.util.Stack;

/**
 * 使用栈实现队列，支持队列的add,poll,peek
 *  栈为先入后出，队列为先入先出的数据结构；
 *  将栈的数据倒入另一个栈即可实现数据的先入先出，注意数据翻转的时机
 * @author beichen
 *
 */
public class QueueImpledByTwoStacks {
	private Stack<Integer> stackPush;
	private Stack<Integer> stackPop;
	
	public QueueImpledByTwoStacks() {
		this.stackPush = new Stack<Integer>();
		this.stackPop = new Stack<Integer>();
	}
	
	public void pushToPop() {
		if (stackPop.isEmpty()) {
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
	}
	
	public void add(int pushInt) {
		stackPush.push(pushInt);
		pushToPop();
	}
	
	public int poll() {
		if(stackPop.isEmpty() && stackPush.isEmpty()) {
			throw new RuntimeException("Queue is empty!");
		}
		
		pushToPop();
		return stackPop.pop();
	}
	
	public int peek() {
		if(stackPop.isEmpty() && stackPush.isEmpty()) {
			throw new RuntimeException("Queue is empty!");
		}
		
		pushToPop();		
		return stackPop.peek();
	}
	
	public int size() {
		if(stackPop.isEmpty() && stackPush.isEmpty()) {
			throw new RuntimeException("Queue is empty!");
		}
		
		return stackPop.size() + stackPush.size();		
	}
}
