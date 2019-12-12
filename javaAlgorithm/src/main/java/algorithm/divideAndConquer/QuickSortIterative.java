package algorithm.divideAndConquer;

import java.util.Arrays;
import java.util.Stack;

public class QuickSortIterative {
	
	public static void main(String[] args) {
		int[] arr = {12, 11, 13, 5, 6, 5, 7};
		QuickSortIterative qsi = new QuickSortIterative();
		qsi.quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	/*
	 * implements the quicksort with iterative mode
	 */
	public void quickSort(int[] arr, int start, int end) {
		//we need a stack to save the recursive parameter of start and end
		//it is like a function stack but need less resources(it just store two int element per function call)
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		stack.push(end);
		while (!stack.isEmpty()) {
			int e = stack.pop();
			int s = stack.pop();
			int p = partition(arr, s, e);
			if (s < p - 1) {
				stack.push(s);
				stack.push(p - 1);
			}
			if (e > p + 1) {
				stack.push(p + 1);
				stack.push(e);
			}
		}
	}
	
	
	/**
	 * use last element as pivot 
	 */
	private int partition(int[] arr, int start, int end) {
		int pivot = arr[end];
		//trace the smallest element index
		int i = start - 1;
		for (int j = start; j < end; j++) {
			if (arr[j] < pivot) {
				i++;
				//put the j element which is smaller than pivot in correct index
				swap(arr, i, j);
			}
		}
		//put pivot in correct position
		swap(arr, ++i, end);
		//return pivot index
		return i;
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
