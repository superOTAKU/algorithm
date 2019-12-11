package algorithm.divideAndConquer;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = { 3, 1, 7, 5, 2, 5, 9};
		QuickSort qs = new QuickSort();
		qs.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	/**
	 *	start inclusive, end inclusive
	 */
	private void sort(int[] arr, int start, int end) {
		if (start >= end) {
			return;
		}
		int p = partition(arr, start, end);
		sort(arr, start, p - 1);
		sort(arr, p + 1, end);
	}
	
	private int partition(int[] arr, int start, int end) {
		//pick last element as pivot
		int pivot = arr[end];
		//trace the index smaller than pivot
		int cursor = start - 1;
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
				//move element smaller than pivot to left side by swap
				swap(arr, ++cursor, i);
			}
		}
		//store pivot at correct index, by swap
		swap(arr, ++cursor, end);
		//return pivot index
		return cursor;
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
	}
	
}
