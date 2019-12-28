package algorithm.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * from a given set, find a subset whose sum is k
 * 
 * @author linjingfu
 *
 */
public class SubsetSum {
	
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.addAll(Arrays.asList(1, 3, 5, 7));
		System.out.println(new SubsetSum().solve(set, 10));
	}
	
	public Set<Integer> solve(Set<Integer> set, int targetSum) {
		Set<Integer> subset = new HashSet<Integer>();
		int[] setArr = new int[set.size()];
		int i = 0;
		for (int e : set) {
			setArr[i++] = e;
		}
		if (doSolve(setArr, -1, subset, 0, targetSum)) {
			return subset;
		}
		throw new IllegalStateException("no solution found");
	}
	
	private boolean doSolve(int[] set, int idx, Set<Integer> subset, int currentSum, int targetSum) {
		if (currentSum == targetSum) {
			return true;
		}
		//try every element after current element
		for (int i = idx + 1; i < set.length; i++) {
			if (currentSum + set[i] <= targetSum) {
				subset.add(set[i]);
				currentSum += set[i];
				if (doSolve(set, i, subset, currentSum, targetSum)) {
					return true;
				} else {
					//backtracking
					subset.remove(set[i]);
					currentSum -= set[i];
				}
			}
		}
		return false;
	}
	
}
