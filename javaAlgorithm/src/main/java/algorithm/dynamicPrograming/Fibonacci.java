package algorithm.dynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、1、2、3、5、8、13、21、34
 * 
 * classic problem to learn dynamic programing
 * 	
 * we will solve it with both bottom up tabulation manner and top down memoizatation manner
 * 
 * @author ljf
 *
 */
public class Fibonacci {
	
	public static void main(String[] args) {
		System.out.println(new Fibonacci().solve(5));
		System.out.println(new Fibonacci().solveBottomUp(5));
	}
	
	private Map<Integer, Integer> cache = new HashMap<>();
	
	/**
	 * 	memoizatation, search from cache before calculate
	 */
	public int solve(int n) {
		if (n <= 2) {
			return 1;
		}
		Integer result = cache.get(n);
		if (result != null) {
			return result;
		}
		result = solve(n - 2) + solve(n - 1);
		cache.put(n, result);
		return result;
	}
	
	/**
	 * 	tabulation, dynamic solve the sub problem and cache the result,
	 *  then solve bigger problem with solved sub problem result
	 */
	public int solveBottomUp(int n) {
		if (n <= 2) {
			return 1;
		}
		int[] cache = new int[n + 1];
		cache[1] = 1; cache[2] = 1;
		for (int i = 3; i <= n; i++) {
			cache[i] = cache[i - 2] + cache[i - 1];
		}
		return cache[n];
	}
	
}
