package stackandqueue;

import org.junit.jupiter.api.Test;

class StackGetMin1Test {

	@Test
	void test() {
		StackGetMin1 stack = new StackGetMin1();
		for(int i= 3; i >= 0; i--) {
			stack.push(i);
			stack.push(2);
		}
		
		int size = stack.size();
		for(int i = 0; i < size; i++) {
			System.out.println(stack.getMin());
			stack.pop();
		}
	}

}
