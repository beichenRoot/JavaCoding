package sortandsearch;

/**
 *	介绍7中查询算法
 * @author beichen
 *
 */
public class SearchAlgorithm {

	/**
	 *	顺序查找，即为遍历数组，时间复杂度为O(n)
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int sequentialSearch(int[] arr, int value) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 *	二分查找针对以升序排列的数组进行，每次取数组的中间值进行查找
	 *	时间复杂度为O(logn)
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int binarySearch(int[] arr, int value) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high)/2;
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] > value) {
				high = mid -1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 	二分查找--递归实现
	 * @param arr	待查询数组
	 * @param value	查找目标值
	 * @param low	数组起始下标
	 * @param high	数组结束下标
	 * @return	目标值的下标
	 */
	public static int binarySearchByRecursion(int[] arr, int value, int low, int high) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		int mid = low + (high -low)/2;
		if (low == high && arr[mid] != value) {
			return -1;
		}
		if (arr[mid] == value) {
			return mid;
		} else if (arr[mid] > value) {
			return binarySearchByRecursion(arr, value, low, mid - 1);
		} else {
			return binarySearchByRecursion(arr, value, mid + 1, high);
		}
	}
	
	/**
	 * 	插值查找--递归实现，原理与二分查找类似，按目标值的大小计算在数组中的权重，适用于均有有序的数组
	 * @param arr	待查询数组
	 * @param value	查找目标值
	 * @param low	数组起始下标
	 * @param high	数组结束下标
	 * @return	目标值的下标
	 */
	public static int insertionSearch(int[] arr, int value, int low, int high) {
		if (arr == null) {
			throw new RuntimeException("Input arr is null!");
		}
		// 按目标值与最小值的差估算插值下标的位置
		int mid = low + ((value - arr[low]) / (arr[high] - arr[low])) * (high -low);
		if (low == high && arr[mid] != value) {
			return -1;
		}
		if (arr[mid] == value) {
			return mid;
		} else if (arr[mid] > value) {
			return binarySearchByRecursion(arr, value, low, mid - 1);
		} else {
			return binarySearchByRecursion(arr, value, mid + 1, high);
		}
	}
			
}
