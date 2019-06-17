package stackandqueue;

import java.util.Stack;

/**
 * 
 * @author beichen
* 使用栈实现入栈、出栈、获取最小值操作的时间复杂度均为O(1)
* 思路：用空间换时间，额外增加一个栈，用于保存当前栈中的最小值
* 入栈时将当前的栈中的最小值同步压入最小值栈中，出栈时数据栈与最小值栈同时出栈即可 
 */
public class StackGetMin2 {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public StackGetMin2() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}
	
	public void push(int newNum){
		
		if (this.stackMin.isEmpty()) {
			this.stackMin.push(newNum);
		} else if (newNum <= this.getMin()) {
				this.stackMin.push(newNum);
		} else {
			int value = this.stackMin.peek();
			this.stackMin.push(value);
			
		}
		
		this.stackData.push(newNum);
	}
	
	public int pop() {
		if (this.stackData.isEmpty()) {
			throw new RuntimeException("Your stack is Empty.");
		}
		
		this.stackMin.pop();
		
		return this.stackData.pop();
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
