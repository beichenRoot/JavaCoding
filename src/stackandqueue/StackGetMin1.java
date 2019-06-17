package stackandqueue;

import java.util.Stack;

/**
*
* @author beichen
* 使用栈实现入栈、出栈、获取最小值操作的时间复杂度均为O(1)
* 思路：用空间换时间，额外增加一个栈，用于保存当前栈中的最小值
* 入栈时若值大于当前最小值，最小值栈中不增加元素；出栈时需在最小值与当前出栈值大小一致时，同时将最小值栈顶元素移除
*
*/
public class StackGetMin1{
	
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public StackGetMin1() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}
	
	public void push(int newNum){
		if (this.stackMin.isEmpty() || newNum <= this.getMin()) {
			this.stackMin.push(newNum);
		}
		
		this.stackData.push(newNum);
	}
	
	public int pop() {
		if (this.stackData.isEmpty()) {
			throw new RuntimeException("Your stack is Empty.");
		}
		int value = this.stackData.pop();
		if (value <= this.getMin()) {
			this.stackMin.pop();
		}
		return value;
	}
	
	public int getMin() {
		if (this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is Empty.");
		}
		
		return this.stackMin.peek();
	}
	
	public int size() {
		return this.stackData.size();
	}
}