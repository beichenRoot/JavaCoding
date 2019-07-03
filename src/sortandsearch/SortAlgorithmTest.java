package sortandsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

class SortAlgorithmTest {

	@Test
	void test() {
		int[] arr = {2,1,1,7,3};
		//SortAlgorithm.bubbleSort(arr);
		//SortAlgorithm.selectSort(arr);
		//SortAlgorithm.insertSort(arr);
		//SortAlgorithm.shellSort(arr);
		//SortAlgorithm.mergeSort(arr);
		//SortAlgorithm.quickSort(arr);
		//SortAlgorithm.countSort(arr);
		SortAlgorithm.bucketSort(arr);
		printArray(arr);
		//random();
	}

	private void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(i != arr.length -1) {
				System.out.print(arr[i] + " -> ");
			} else {
				System.out.print(arr[i]);
			}
		}
	}
	
	private void random() {
		for(int i=0; i<10; i++)
		System.out.println(new Random().nextInt(3));
	}
	
	@Test
	public void collectionTest() {
		Map listInteger = new HashMap();
//		listInteger.add(1);
		System.out.println(listInteger);
		
//		List list = listInteger;
//		list.add("hello");
//		for(Object obj : listInteger) {
//			System.out.println(obj);
//		}		

	}
	
}
