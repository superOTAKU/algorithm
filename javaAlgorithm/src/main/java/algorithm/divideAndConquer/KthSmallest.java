package algorithm.divideAndConquer;

/**
 * 
 * find the kth smallest element, search in place(cannot modify the array)
 * 
 * algorithm: iterative search the mid = (min + max) / 2, change min or max base on the search result, until we solve it
 * 
 * 	找出第k小的元素，要求不能更改原本的数组
 * 
 * 	算法：最大最小值，得出中位数，统计小于等于中位数的数量。（经过几次循环得出结果）
 * 
 * @author ljf
 *
 */
public class KthSmallest {
	
	public static void main(String[] args) {
		System.out.println(new KthSmallest().solve(new int[] {7, 10, 4, 3, 20, 15}, 3));
	}
	
	public int solve(int[] data, int k) {
		//find min and mix
		int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
		for (int e : data) {
			if (e < low) low = e;
			if (e > high) high = e;
		}
		
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int lessCount = 0, equalCount = 0;
			for (int e : data) {
				if (e < mid) {
					lessCount++;
				}
				if (e == mid) {
					equalCount++;
				}
			}
			if (lessCount < k && (lessCount + equalCount) >= k) {
				//found it
				return mid;
			} else if (lessCount >= k) {
				//k is smaller than mid
				high = mid - 1;
			} else if ((lessCount + equalCount) < k) {
				//k is bigger than mid
				low = mid + 1;
			}
		}
		
		//no answer, never happen, unless k is illegal
		throw new IllegalStateException("no kth smallest element");
	}
	
}
