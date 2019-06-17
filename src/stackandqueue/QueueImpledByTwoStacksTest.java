package stackandqueue;

import org.junit.jupiter.api.Test;

class QueueImpledByTwoStacksTest {

	@Test
	void test() {
		QueueImpledByTwoStacks queue = new QueueImpledByTwoStacks();
		for(int i= 3; i >= 0; i--) {
			queue.add(i);
			queue.add(2);
		}
		
		int size = queue.size();
		for(int i = 0; i < size; i++) {
			System.out.println(queue.peek());
			queue.poll();
		}
	}

}
