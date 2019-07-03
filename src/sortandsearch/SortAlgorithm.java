package sortandsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * classic sort algorithm
 *	排序算法
 * @author beichen
 *
 */
public class SortAlgorithm {

	/**
	 * ascending sort
	 *  外层循环边界条件：总共需要冒泡的轮数--每一轮都将最大或最小的数冒泡到最后
	 *  内层循环边界条件：冒泡数字移动的边界--最终数字需冒泡到此处
	 *  时间复杂度：O(n^2)
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1 - i; j++) {				 
				//冒泡：相邻两数比较，大的向后冒
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	/**
	 * 每次都将未排序数组中的最大或最小元素找出来和第一个元素交换位置
	 *  时间复杂度：O(n^2)
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		for(int i = 0; i < arr.length - 1; i++) {
			//寻找最小元素的下标，避免频繁交换数组
			int min = i;
			for(int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			//将最小的元素交换到未排序数组的最前面
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
	
	/**
	 * 插入排序：顺次从数组中选择一个数，插入到前面已排序的数组中
	 * 时间复杂度：O(n)~O(n^2)
	 * @param arr
	 */
	public static void insertSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		for(int i = 1; i < arr.length; i++) {
			int value = arr[i];
			//插入的位置
			int j = 0;
			//循环i前面的数，若值比插入的值大，则顺次向后移动
			for (j = i - 1; j >= 0; j--) {
				if(arr[j] > value) {
					arr[j+1] = arr[j];
				} else {
					break;
				}
			}
			arr[j+1]=value;
		}
	}
	
	/**
	 * 	希尔排序：插入排序的改进版，也称缩小增量排序
	 *
	 * @param arr
	 */
	public static void shellSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		int length = arr.length;
		//区间
		int gap = 1;
		while(gap < length) {
			gap = gap * 3 +1;
		}
		
		while(gap > 0) {
			for(int i = gap; i < length; i++) {
				int tmp = arr[i];
				int j = i -gap;
				//跨区间排序
				while(j >= 0 && arr[j] > tmp) {
					arr[j+gap] = arr[j];
					j -= gap;
				}
				arr[j + gap] = tmp;
			}
			gap = gap / 3;
		}
	}
	
	/**
	 * 	归并排序--核心为分治法
	 *	时间复杂度O(nlogn)
	 * @param arr
	 */
	public static void mergeSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		int[] tmpArr = new int[arr.length];
		mSort(arr,tmpArr, 0, arr.length - 1);
	}
	
	private static void mSort(int[] arr, int[] tmpArr, int startIndex, int endIndex) {
		//边界条件：数组已不可再拆
		if (endIndex <= startIndex) {
			return;
		}
		
		//将数组对拆为前后两个数组
		int middleIndex = startIndex + (endIndex - startIndex)/2;
		mSort(arr, tmpArr, startIndex, middleIndex);
		mSort(arr, tmpArr, middleIndex + 1, endIndex);
		
		merge(arr, tmpArr, startIndex, middleIndex, endIndex);
	}
	
	private static void merge(int[] arr, int[] tmpArr, int startIndex, int middleIndex, int endIndex) {
		//将要合并的数组复制到临时数组
		for (int i = startIndex; i <= endIndex; i++) {
			tmpArr[i] = arr[i];
		}
		
		//左边数组起始下标
		int left = startIndex;
		//右边数组起始下标
		int right = middleIndex + 1;
		for(int k = startIndex; k <= endIndex; k++) {	
			if (left > middleIndex) {
				arr[k] = tmpArr[right++];
			} else if (right > endIndex) {
				arr[k] = tmpArr[left++];
			} else if (tmpArr[left] < tmpArr[right]) {
				arr[k] = tmpArr[left++];
			} else {
				arr[k] = tmpArr[right++];
			}			
		}
	}
	
	/**
	 * 	快速排序：随机选取一个参考值，将比参考值小的数移到数组前段，大的值移到后段
	 * 	以参考值为临界点递归拆分数组直至数组不能拆分，此时数组本身已排好序
	 * 	快速排序时间复杂度为O(nlogn)，对于逆序数组复杂度退化为O(n^2)，为了避免极端情况，可随机选取参考值
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		qSort(arr , 0, arr.length - 1);		
	}
	
	private static void qSort(int[] arr, int startIndex, int endIndex) {
		// 设置边界条件
		if (endIndex <= startIndex) {
			return;
		}
		
		// 将数组按参考值整理成比参考值小的前段和比参考值大的后段，返回参考值的位置
		int refIndex = partition(arr, startIndex, endIndex);
		
		// 参考值已确定排序后的位置，不参与数组拆分
		if (refIndex > startIndex) {
			qSort(arr, startIndex, refIndex - 1);
		}
		if (endIndex > refIndex) {
			qSort(arr, refIndex + 1, endIndex);
		}
				
	}

	private static int partition(int[] arr, int startIndex, int endIndex) {
		// 将数组中refValue的值与最后一个数交换，随机选取参考值可避免时间复杂度退化为O(n^2)
		int refIndex = startIndex + new Random().nextInt(endIndex - startIndex + 1);
		// 深坑，当两个数指向同一个时，会影响异或结果
		if (refIndex != endIndex) {
			arr[endIndex] = arr[endIndex] ^ arr[refIndex];
			arr[refIndex] = arr[endIndex] ^ arr[refIndex];
			arr[endIndex] = arr[endIndex] ^ arr[refIndex];
		}
		
		// 分组下标
		int partitionIndex = startIndex - 1;
		// 数组最后一个值为参考值，不参与循环
		for (int dataIndex = startIndex; dataIndex < endIndex; dataIndex++) {
			// 与参考值进行比较，若比参考值小，则移动到数组前面
			if ((arr[dataIndex] < arr[endIndex]) ) {
				// 始终指向最后一个确定比参考值小的值
				++partitionIndex;
				// 如果当前数据的位置与参考下标不一致，将此值与参考下标指向的值交换，保证小的值交换到前面
				if (partitionIndex != dataIndex) {
					arr[dataIndex] = arr[dataIndex] ^ arr[partitionIndex] ;
					arr[partitionIndex] = arr[dataIndex] ^ arr[partitionIndex];
					arr[dataIndex] = arr[dataIndex] ^ arr[partitionIndex];
				}				
			}
		}
		// 将参考值交换到指定位置
		++partitionIndex;
		if (partitionIndex != endIndex) {
			arr[endIndex] = arr[endIndex] ^ arr[partitionIndex] ;
			arr[partitionIndex] = arr[endIndex] ^ arr[partitionIndex];
			arr[endIndex] = arr[endIndex] ^ arr[partitionIndex];
		}
		
		return partitionIndex;
	}
	
	/**
	 *	 堆排序--最大堆实现
	 *	时间复杂度O(nlogn)
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		int length = arr.length;
		//构建堆
		buildHeap(arr, length);
		for (int i = length - 1; i > 0; i--) {
			//将堆元素与末位元素调换
			int temp = arr[0];
			arr[0] =arr[i];
			arr[i] = temp;
			//数组长度-1 隐藏堆尾元素
			length--;
			//将堆顶元素下沉，目的是将最大的元素浮到堆顶来
			sink(arr, 0, length);
		}
	}	
	private static void buildHeap(int[] arr, int length) {
		for (int i = length / 2; i >= 0; i--) {
			sink(arr, i , length);
		}
	}
	
	private static void sink(int[] arr, int index, int length) {
		//左子节点下标
		int leftChild = 2 * index + 1;
		//右子节点下标
		int rigthChild = 2 * index + 2;
		//要调整的节点下标
		int present = index;
		
		//下沉左边
		if (leftChild < length && arr[leftChild] > arr[present]) {
			present = leftChild;
		}
		
		//下沉右边
		if (rigthChild < length && arr[rigthChild] > arr[present]) {
			present = rigthChild;
		}
		
		//如果下标不相等，证明调换过了
		if (present != index) {
			//交换值
			int temp = arr[index];
			arr[index] = arr[present];
			arr[present] = temp;
			
			//继续下沉
			sink(arr, present, length);
		}
	}
	
	/**
	 *	计数排序--时间复杂度为O(n+m)，空间大小取决于数组值，时间复杂度为O(n)
	 *	问题点：数组中不能有负数，否则会抛出越界异常
	 * @param arr
	 */
	public static void countSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		//找出数组中的最大值
		int max = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if (arr[i] < 0) {
				throw new RuntimeException("Cannot use countsort! Array contains negative number.");
			}
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		
		//利用最大值构建一个数组，用空间换时间
		int[] countArr = new int[max + 1];
		
		//计数
		for (int i = 0; i < arr.length; i++) {
			countArr[arr[i]]++;
		}
		
		int index = 0;
		for (int i = 0; i < countArr.length; i++) {
			while (countArr[i] > 0) {
				arr[index++] = i;
				countArr[i]--;
			}
		}
	}
	
	
	/**
	 * 	桶排序--类似于Hash分桶策略
	 * 	良好的分桶策略可实现O(n)时间复杂度
	 * @param arr
	 */
	public static void bucketSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		//最大最小值
		int max = arr[0];
		int min = arr[0];
		int length = arr.length;
		
		for (int i = 1; i < length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			} else if (arr[i] < min) {
				min = arr[i];
			}
		}
		
		//最大值与最小值的差
		int diff = max - min;
		
		//桶列表
		ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			bucketList.add(new ArrayList<>());
		}
		
		//每个桶的存数区间
		float section = (float)diff / (float)(length -1);
		
		//数据入桶
		for (int i = 0; i < length; i++) {
			//当前数除以区间得出存放桶的位置 减1后得出桶的下标
			int num = (int) (arr[i] / section) - 1;
			if (num < 0) {
				num = 0;
			}
			bucketList.get(num).add(arr[i]);
		}
		
		//桶内排序
		for (int i = 0; i < bucketList.size(); i++) {
			Collections.sort(bucketList.get(i));
		}
		
		//写入数据
		int index = 0;
		for (ArrayList<Integer> arrayList: bucketList) {
			for (int value : arrayList) {
				arr[index] = value;
				index++;
			}
		}
	}
	
	/**
	 * 	基数排序
	 * @param arr
	 */
	public static void radixSort(int[] arr) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}

	    int length = arr.length;

	    //最大值
	    int max = arr[0];
	    for(int i = 0;i < length;i++){
	        if(arr[i] > max){
	            max = arr[i];
	        }
	    }
	    //当前排序位置
	    int location = 1;

	    //桶列表
	    ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();

	    //长度为10 装入余数0-9的数据
	    for(int i = 0; i < 10; i++){
	        bucketList.add(new ArrayList<>());
	    }

	    while(true)
	    {
	        //判断是否排完
	        int dd = (int)Math.pow(10, (location - 1));
	        if(max < dd){
	            break;
	        }

	        //数据入桶
	        for(int i = 0; i < length; i++)
	        {
	            //计算余数 放入相应的桶
	            int number = ((arr[i] / dd) % 10);
	            bucketList.get(number).add(arr[i]);
	        }

	        //写回数组
	        int nn = 0;
	        for (int i=0;i<10;i++){
	            int size = bucketList.get(i).size();
	            for(int ii = 0;ii < size;ii ++){
	                arr[nn++] = bucketList.get(i).get(ii);
	            }
	            bucketList.get(i).clear();
	        }
	        location++;
	    }
	}
	
	
	/**
	 * 一个数与另一个数同时异或两次结果仍然为这个数 10^20^20 = 10
	 * @param a
	 * @param b
	 */
	private static void swap(int a, int b) {
		a = a ^ b;
		b = a ^ b;	//a^b^b
		a = a ^ b;
	}
	
}
