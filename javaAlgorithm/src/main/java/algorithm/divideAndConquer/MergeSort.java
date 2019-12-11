package algorithm.divideAndConquer;

import java.util.Arrays;

public class MergeSort {
	
	public static void main(String[] args) {
		int[] arr = { 3, 1, 7, 5, 2, 5, 9};
		MergeSort ms = new MergeSort();
		ms.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort(int[] arr) {
		sort(arr, 0, arr.length);
	}
	
	/**
	 * start inclusive, end exclusive, so we can deal with mid index easier 
	 */
	private void sort(int[] arr, int start, int end) {
		if (start + 1 >= end) { //end is exclusive
			return;
		}
		int mid = start + (end - start) / 2;
		sort(arr, start, mid);
		sort(arr, mid, end);
		merge(arr, start, mid, end);
	}
	
	private void merge(int[] arr, int start, int mid, int end) {
		//to merge, we need to allocate temp array, and first copy elements into two array
		int[] left = new int[mid - start];
		int[] right = new int[end - mid];
		System.arraycopy(arr, start, left, 0, left.length);
		System.arraycopy(arr, mid, right, 0, right.length);
		
		//perform merge
		int i = start, l = 0, r = 0;
		while (l < left.length && r < right.length) {
			if (left[l] < right[r]) {
				arr[i++] = left[l++];
			} else {
				arr[i++] = right[r++];
			}
		}
		
		while (l < left.length) {
			arr[i++] = left[l++];
		}
		
		while (r < right.length) {
			arr[i++] = right[r++];
		}
	}
}
